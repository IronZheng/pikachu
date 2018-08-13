package com.luway.pikachu.core.engine;

import com.luway.pikachu.core.exception.SimpleException;
import com.luway.pikachu.core.worker.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhenggm
 * @create 2018-04-25 13:39
 **/

public class Pikachu {
    private final static Logger logger = LoggerFactory.getLogger(Pikachu.class);
    private String name;
    private List<Worker> workerList;

    private ExecutorService pikachuPool;

    /**
     * 默认最大线程数
     */
    private Integer maxThreadNum = 10;

    /**
     * 默认核心线程数
     */
    private Integer coreNum = 3;

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
        workerList = new ArrayList<Worker>();
        pikachuPool = new ThreadPoolExecutor(
                coreNum, maxThreadNum, 0L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1024), new PiakchuFactory("pikachu")
                , new ThreadPoolExecutor.AbortPolicy());
        core = new PikachuCore(workerList, pikachuPool);
        logger.debug("pikachu init ...");
        return this;
    }

    public Pikachu regist(Worker worker) {
        if (null == worker) {
            throw new SimpleException("worker is null");
        }
        workerList.add(worker);

        core.putWorker(worker);
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
        logger.debug("pikachu stop ...");
        core.stop();
    }

    public Pikachu setMaxThreadNum(Integer maxThreadNum) {
        this.maxThreadNum = maxThreadNum;
        return this;
    }

    public Pikachu setCoreNum(Integer coreNum) {
        this.coreNum = coreNum;
        return this;
    }
}