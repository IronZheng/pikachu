package com.pikachu.core.pipeline;

import java.util.Map;

/**
 * @author zhenggm
 * @create 2018-04-25 13:36
 **/

public abstract class Pipeline<T> {

    private T bean;

    public Pipeline(T t) {
        this.bean = t;
    }

    public abstract void output(Map<String, Object> result);

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }
}
