package com.pikachu.core.engine;

import com.pikachu.core.exception.SimpleException;
import com.pikachu.core.pipeline.Pipeline;
import com.pikachu.core.worker.Worker;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenggm
 * @create 2018-04-25 13:39
 **/

public class Pikachu {
    private final static Logger log = LoggerFactory.getLogger(Pikachu.class);
    private String name;
    private List<Worker> workerList;
    private Integer maxThreadNum;
    private Integer coreNum;
    private Document doc;
    private PikachuCore core;

    public Pikachu(String name) {
        this.name = name;

    }

    /**
     * 初始化
     *
     * @return
     */
    public Pikachu init() {
        workerList = new ArrayList<>();
        core = new PikachuCore(workerList);
        return this;
    }

    public Pikachu regist(Worker worker) {
        if (null == worker) {
            throw new SimpleException("null");
        }
        workerList.add(worker);
        return this;
    }

    public void start() {
        if (null == core) {
            throw new SimpleException("pikachu核心未初始化,请先初始化引擎");
        }
        for (Worker worker : workerList) {
            if (null == worker.getPipeline()) {
                throw new SimpleException("存在没有添加pipeline的worker");
            }
        }
        core.start();
    }

    public void stop() {

    }
}