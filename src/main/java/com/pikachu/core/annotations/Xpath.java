package com.pikachu.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : iron
 * @version : 1.0.0
 * @date : 上午11:30 2018/8/2
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Xpath {

    String xpath() default "";
}
