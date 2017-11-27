package com.topscore.order.dispatcher.excel.annotation;


import java.lang.annotation.*;

/**
 * 数字格式化。
 *
 * @author dengb
 * @see org.springframework.format.annotation.NumberFormat
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface NumberFormat {

    /**
     * 用自定义格式格式化字段。
     */
    String pattern() default "";
}
