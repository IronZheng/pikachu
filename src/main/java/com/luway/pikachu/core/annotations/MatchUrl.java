package com.luway.pikachu.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : iron
 * @version : 1.0.0
 * @date : 10:11 AM 2018/12/14
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MatchUrl {
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

    boolean loadJs() default false;
}
