package com.luway.pikachu.core.pipeline;

import com.luway.pikachu.core.worker.GeneralWorker;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhenggm
 * @create 2018-04-25 13:36
 **/

public abstract class BasePipeline<T> {

    private T bean;

    private List<GeneralWorker> worker = new ArrayList<>();

    public BasePipeline(T t) {
        this.bean = t;
    }

    /**
     * out
     * @param result
     */
    public abstract void output(Map<String, Elements> result, String url);

    /**
     * check
     * @return
     */
    public List<GeneralWorker> checkWorker(){
        return worker;
    }

    public void regiestWorker(GeneralWorker worker){
        this.worker.add(worker);
    }

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }
}
