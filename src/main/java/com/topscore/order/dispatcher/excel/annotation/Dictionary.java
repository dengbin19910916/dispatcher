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
public @interface Dictionary {

    Map[] values();

    @interface Map {

        String value();
        String text();
    }
}
