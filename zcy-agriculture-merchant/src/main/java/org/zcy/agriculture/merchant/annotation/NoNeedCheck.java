package org.zcy.agriculture.merchant.annotation;

import java.lang.annotation.*;

/**
 * 无需进行切面的方法
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoNeedCheck {
    String value() default "";
}