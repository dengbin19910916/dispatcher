package com.topscore.order.dispatcher.excel;

import com.topscore.order.dispatcher.excel.annotation.Document;
import com.topscore.order.dispatcher.excel.annotation.Header;
import com.topscore.order.dispatcher.excel.annotation.NumberFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

/**
 * 工具类。
 *
 * @author dengb
 */
public class Excels {

    public static DataStrategy getDataStrategy(Map<String, Object> model) {
        return !(model.get("data") instanceof DataPump) ? DataStrategy.ALL : DataStrategy.PART;
    }

    /**
     * Get data type.
     *
     * @param model combined output Map (never {@code null}),
     *              with dynamic values taking precedence over static attributes
     * @return Data type
     */
    public static Class<?> getType(Map<String, Object> model) {
        if (model.containsKey("type")) {
            return (Class<?>) model.get("type");
        } else {
            Object data = model.get("data");
            return data instanceof DataPump ? ((DataPump<?>) data).getData().get(0).getClass() : ((List<?>) data).get(0).getClass();
        }
    }

    /**
     * Get file name.
     *
     * @param type data type
     * @return file name
     */
    public static String getFilename(Class<?> type) {
        Assert.notNull(type, "Data type is null.");
        Document document = type.getDeclaredAnnotation(Document.class);
        String filename = document == null ? "导出数据.csv" : document.name();
        return new String(filename.getBytes(), ISO_8859_1);
    }

    public static Map<String, String> getHeaders(Class<?> type, Map<String, Format> formats) {
        Map<String, String> headers = new LinkedHashMap<>();
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            Header header = field.getDeclaredAnnotation(Header.class);
            if (header != null) {
                headers.put(field.getName(), header.name());

                DateTimeFormat dateTimeFormat = field.getDeclaredAnnotation(DateTimeFormat.class);
                if (dateTimeFormat != null) {
                    formats.put(field.getName(), new SimpleDateFormat(dateTimeFormat.pattern()));
                }

                NumberFormat numberFormat = field.getDeclaredAnnotation(NumberFormat.class);
                if (numberFormat != null) {
                    formats.put(field.getName(), new DecimalFormat(numberFormat.pattern()));
                }
            }

        }

        return headers;
    }
}
