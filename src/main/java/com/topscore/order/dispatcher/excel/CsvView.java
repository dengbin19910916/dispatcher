package com.topscore.order.dispatcher.excel;

import com.topscore.order.dispatcher.excel.annotation.Mapped;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.text.Format;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.topscore.order.dispatcher.excel.Excels.*;

/**
 * Excel导出视图。
 * <p>
 * 需要使用model.addAttribute("data", data)为视图注入数据。
 * data 为 {@link List} 或 {@link DataPump}。
 * example:
 * <pre>
 * public String export(Model model) {
 *     model.addAttribute("data", data);
 *     return "excelView";
 * }
 *
 * or
 *
 * public ModelAndView export(Model model) {
 *     model.addAttribute("data", data);
 *     return new ModelAndView(new ExcelView());
 * }
 * ...
 * </pre>
 * </p>
 *
 * @author dengb
 * @see DataPump
 * @see org.springframework.ui.Model
 * @see org.springframework.web.servlet.ModelAndView
 */
@Component
public class CsvView extends AbstractView {

    /**
     * The extension to look for existing templates
     */
    private static final String EXTENSION = ".csv";

    public CsvView() {
        setContentType("text/csv");
    }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,
                                           HttpServletRequest request, HttpServletResponse response) throws IOException, IllegalAccessException {

        DataStrategy dataStrategy = getDataStrategy(model);

        Class<?> type = getType(model);

        response.setCharacterEncoding("GBK");
        response.setHeader("Content-Disposition", "attachment;filename=" + getFilename(type) + EXTENSION);

        // data format map
        final Map<String, Format> formats = new HashMap<>();
        // csv headers map
        final Map<String, String> headers = getHeaders(type, formats);

        PrintWriter writer = response.getWriter();

        fillHeader(headers, writer);

        // body
        if (dataStrategy == DataStrategy.ALL) {
            List<?> data = (List<Object>) model.get("data");
            fillBody(headers, data, formats, writer);
        } else {
            DataPump<?> dataPump = (DataPump) model.get("data");
            while (dataPump.hasNext()) {
                List<?> data = dataPump.getData();
                fillBody(headers, data, formats, writer);
            }
        }
    }

    private void fillHeader(Map<String, String> headers, PrintWriter writer) {
        List<String> row = new ArrayList<>();
        // write header
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            row.add(entry.getValue());
        }
        writeRow(row, writer);
    }

    private void fillBody(Map<String, String> headers, List<?> data, Map<String, Format> formats, PrintWriter writer) throws IllegalAccessException {
        List<String> row = new ArrayList<>();
        // write body
        for (Object datum : data) {
            row.clear();

            Field[] fields = datum.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (headers.containsKey(field.getName())) {
                    field.setAccessible(true);
                    Object value = field.get(datum);

                    //字段值映射
                    Mapped enums = field.getDeclaredAnnotation(Mapped.class);
                    if (enums != null) {
                        Mapped.Entry[] ems = enums.values();
                        for (Mapped.Entry em : ems) {
                            if (em.value().toLowerCase().equals(value.toString().toLowerCase())) {
                                value = em.text();
                            }
                        }
                    }

                    // 字段需要格式化
                    if (formats.containsKey(field.getName()) && value != null) {
                        value = formats.get(field.getName()).format(value);
                    }

                    row.add(value == null ? "" : value.toString());
                }
            }

            writeRow(row, writer);
        }
    }

    /**
     * Write a row into csv stream.
     *
     * @param row    a row data
     * @param writer csv stream
     */
    private static void writeRow(List<?> row, PrintWriter writer) {
        for (Object data : row) {
            String cell = "\"" + data + "\"\t,";
            writer.print(cell);
        }
        writer.println();
        writer.flush();
    }
}
