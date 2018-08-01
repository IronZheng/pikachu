package com.pikachu.core.pipeline;

/**
 * @author zhenggm
 * @create 2018-04-25 13:36
 **/

public abstract class Pipeline<T> {

    private T bean;
    public Pipeline(T t) {
        this.bean = t;
    }

    public abstract void output(String json);

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }
}
