package com.topscore.order.dispatcher.excel.annotation;


import java.lang.annotation.*;

/**
 * 日期格式化。
 *
 * @author dengb
 * @see org.springframework.format.annotation.DateTimeFormat
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface DateTimeFormat {

    /**
     * 用自定义格式格式化字段。
     */
    String pattern() default "";
}
