package com.topscore.order.dispatcher.excel.annotation;

import java.lang.annotation.*;

/**
 * 数据表的表头。
 *
 * @author dengb
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Header {
    /**
     * 表头名称。
     */
    String name();

    /**
     * 单元格宽度。
     */
    int width() default 0;
}
