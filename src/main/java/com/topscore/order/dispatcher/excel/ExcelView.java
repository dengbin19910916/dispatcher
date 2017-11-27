package com.topscore.order.dispatcher.excel;

import com.topscore.order.dispatcher.excel.annotation.Document;
import com.topscore.order.dispatcher.excel.annotation.Header;
import com.topscore.order.dispatcher.excel.annotation.Mapped;
import com.topscore.order.dispatcher.excel.annotation.NumberFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxStreamingView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.topscore.order.dispatcher.excel.ExcelView.DataPolicy.DATA;
import static com.topscore.order.dispatcher.excel.ExcelView.DataPolicy.DATA_PUMP;

/**
 * Excel导出视图。
 * <p>
 * 需要使用model.addAttribute("data", data)为视图注入数据。
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
 *
 * ...
 * </pre>
 * </p>
 *
 * @author dengb
 * @see org.springframework.ui.Model
 * @see org.springframework.web.servlet.ModelAndView
 */
@Component
public class ExcelView extends AbstractXlsxStreamingView {

    public static final String DATA_KEY = "data";

    public static final String TYPE = "type";

    /**
     * The extension to look for existing templates
     */
    private static final String EXTENSION = ".xlsx";

    /**
     * Data type
     */
    private Class<?> type;

    /**
     * Excel headers map
     */
    private final Map<String, String> headers = new LinkedHashMap<>();

    /**
     * Data format instances
     */
    private final Map<String, Format> formats = new HashMap<>();

    /**
     * All column width
     */
    private int[] columnSides;

    /**
     * Tags are ignored attributes
     */
    private List<Integer> customSizeTags = new ArrayList<>();

    /**
     * Data buffer
     */
    private List<Object> data;

    private int rowIndex = 1;

    private DataPolicy dataPolicy;

    public enum DataPolicy {
        DATA,
        DATA_PUMP
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException {
        Object content = model.get(DATA_KEY);
        if (!(content instanceof DataPump)) {
            this.dataPolicy = DataPolicy.DATA;
        } else {
            this.dataPolicy = DataPolicy.DATA_PUMP;
        }

        checkParameter(model);

        Sheet sheet = workbook.createSheet();
        setHeaders(sheet, model);

        setFilename(response);

        // 数据为空时返回空文件
        if (dataPolicy == DATA && (data == null || data.isEmpty())) {
            return;
        }

        if (dataPolicy == DataPolicy.DATA) {
            fillSheet(sheet, data);
        }
        if (dataPolicy == DATA_PUMP){
            DataPump pump = (DataPump) content;
            while (pump.hasNext()) {
                data = pump.getData();
                fillSheet(sheet, data);
            }
        }
    }

    /**
     * 设置导出的文件名。
     *
     * @param response 输出流
     */
    public void setFilename(HttpServletResponse response) {
        if (type == null) {
            type = data.get(0).getClass();
        }

        Document document = type.getDeclaredAnnotation(Document.class);
        String filename = document != null ? document.name() : "导出数据";
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setHeader("Content-Disposition", "attachment;filename=" +
                new String((getFilename(filename)).getBytes(), StandardCharsets.ISO_8859_1));
    }

    /**
     * 检查必须注入的参数。
     *
     * @param model 被注入的Model
     */
    @SuppressWarnings("unchecked")
    private void checkParameter(Map<String, Object> model) {
        // 当没有注入类型时，注入的数据则不能为空
        // 即：当没有数据的时候必须注入TYPE
        if (!model.containsKey(TYPE)) {
            if (dataPolicy == DataPolicy.DATA) {
                data = (List<Object>) model.get(DATA_KEY);
            }
            if (dataPolicy == DataPolicy.DATA_PUMP) {
                DataPump dataPump = (DataPump) model.get(DATA_KEY);
                if (dataPump == null) {
                    throw new IllegalArgumentException("The data is empty and does not contain type.");
                }
                data = dataPump.getData();
            }

            // 数据为空且没有注入类型
            if (data == null || data.isEmpty()) {
                throw new IllegalArgumentException("The data is empty and does not contain type.");
            }
        }
    }

    /**
     * 设置表头映射。
     *
     * @param sheet 表单对象
     * @param model 被注入的Model
     */
    @SuppressWarnings("unchecked")
    private void setHeaders(Sheet sheet, Map<String, Object> model) {
        if (model.containsKey("type")) {
            type = (Class<?>) model.get("type");
        } else {
            // 没有注入类型，则一定有数据
            data = dataPolicy == DATA ? (List<Object>) model.get(DATA_KEY) : ((DataPump) model.get(DATA_KEY)).getData();
            type = data.get(0).getClass();
        }

        // 自定义列宽
        final Map<Integer, Integer> customSizes = new HashMap<>();
        Field[] fields = type.getDeclaredFields();
        for (int i = 0, col = 0; i < fields.length; i++) {
            Field field = fields[i];
            Header header = field.getDeclaredAnnotation(Header.class);
            if (header != null) {
                headerFields.add(field);

                headers.put(field.getName(), header.name());
                if (header.width() > 0) {
                    customSizeTags.add(col);
                    customSizes.put(col, header.width());
                }

                DateTimeFormat dateTimeFormat = field.getDeclaredAnnotation(DateTimeFormat.class);
                Object value = null;
                try {
                    if (data != null) {
                        value = field.get(data.get(0));
                    }
                } catch (IllegalAccessException ignored) {
                }
                if (dateTimeFormat != null && (value instanceof Date || value instanceof Calendar))
                    formats.put(field.getName(), new SimpleDateFormat(dateTimeFormat.pattern()));

                NumberFormat numberFormat = field.getDeclaredAnnotation(NumberFormat.class);
                if (numberFormat != null)
                    formats.put(field.getName(), new DecimalFormat(numberFormat.pattern()));

                col++;
            }
        }

        // 设置表头列宽
        columnSides = new int[headers.size()];
        for (Map.Entry<Integer, Integer> entry : customSizes.entrySet()) {
            columnSides[entry.getKey()] = entry.getValue();
        }
        Row row = sheet.createRow(0);
        int col = 0;
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            Cell cell = row.createCell(col);
            cell.setCellValue(entry.getValue());
            if (!customSizeTags.contains(col))
                addColumnSides(columnSides, col, entry.getValue().getBytes().length);
            col++;
        }
        setColumnSides(sheet);
    }

    /**
     * 返回带有扩展名的文件名称。
     */
    private String getFilename(String filename) {
        return filename.toLowerCase().endsWith(EXTENSION) ? filename : filename + EXTENSION;
    }

    private List<Field> headerFields = new ArrayList<>();

    /**
     * 填充表单数据。
     *
     * @param sheet 表单
     * @param data  数据
     * @throws IllegalAccessException 无法获取字段值
     */
    private void fillSheet(Sheet sheet, List<Object> data) throws IllegalAccessException {
        // 填充数据
        for (Object datum : data) {
            Row row = sheet.createRow(rowIndex++);
            int col = 0;   // 单元格的位置索引
            for (Field field : headerFields) {
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
                if (formats.containsKey(field.getName())) {
                    value = formats.get(field.getName()).format(value);
                }

                Cell cell = row.createCell(col);
                String cellValue = value != null ? value.toString() : "";
                cell.setCellValue(cellValue);
                addColumnSides(columnSides, col, cellValue.getBytes().length);
                col++;
            }
        }

        setColumnSides(sheet);
    }

    /**
     * 设置表单每列的宽度。
     *
     * @param autoWidths 表单每列的宽度
     * @param column     表单的列索引
     * @param width      需要设置的索引
     */
    private void addColumnSides(int[] autoWidths, int column, int width) {
        if (autoWidths[column] == 0 || width > autoWidths[column]) {
            autoWidths[column] = width;
        }
    }

    /**
     * 设置每列的宽度。
     *
     * @param sheet 表单对象
     */
    private void setColumnSides(Sheet sheet) {
        for (int i = 0; i < columnSides.length; i++) {
            sheet.setColumnWidth(i, columnSides[i] * 256 * 2);
        }
    }
}
