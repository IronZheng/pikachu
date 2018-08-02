package com.pikachu.core.pipeline;

import java.util.Map;

/**
 * @author zhenggm
 * @create 2018-04-25 13:36
 **/

public abstract class BasePipeline<T> {

    private T bean;

    public BasePipeline(T t) {
        this.bean = t;
    }

    /**
     * out
     * @param result
     */
    public abstract void output(Map<String, Object> result);

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }
}
