package com.pikachu.core.engine;

import com.pikachu.core.worker.Worker;

/**
 * @author zhenggm
 * @create 2018-04-25 13:39
 **/

public class Pikachu {
    private String name;
    private Worker worker;
    private Integer maxThreadNum;
    private Integer coreNum;


    public Pikachu(String name) {
        this.name = name;
    }

    public Pikachu regiest(Worker worker){
        this.worker = worker;
        return this;
    }

    public void putTask(){

    }

    public void start(){
        this.worker.start();
    }

    public void stop(){

    }
}