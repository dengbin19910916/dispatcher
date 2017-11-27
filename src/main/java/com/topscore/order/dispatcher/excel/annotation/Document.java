package com.topscore.order.dispatcher.excel.annotation;

import java.lang.annotation.*;

/**
 * 文档的信息。
 *
 * @author dengb
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Document {
    /**
     * 文档名称。
     */
    String name();
}
