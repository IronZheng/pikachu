package com.luway.pikachu.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MathUrl {
    enum Method {
        /**
         * GET请求方法
         */
        GET,
        /**
         * post请求方法
         */
        POST}

    String url() default "";

    Method method() default Method.GET;
}