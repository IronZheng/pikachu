package com.luway.pikachu.core.pipeline;

import com.luway.pikachu.core.worker.Worker;

import java.util.Map;

/**
 * @author zhenggm
 * @create 2018-04-25 13:36
 **/

public abstract class BasePipeline<T> {

    private T bean;

    private Worker worker;

    public BasePipeline(T t) {
        this.bean = t;
    }

    /**
     * out
     * @param result
     */
    public abstract void output(Map<String, Object> result);

    /**
     * check
     * @return
     */
    public Worker checkWorker(){
        return worker;
    }

    public void regiestWorker(Worker worker){
        this.worker = worker;
    }

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }
}
