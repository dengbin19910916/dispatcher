package com.topscore.order.dispatcher.excel.annotation;

import java.lang.annotation.*;

/**
 * 枚举类型的名称。
 *
 * @author dengb
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Mapped {

    Entry[] values();

    @interface Entry {
        String value();
        String text();
    }
}
