package com.luway.pikachu.core.engine.impl;

import com.luway.pikachu.core.engine.PiakchuPoolFactory;
import com.luway.pikachu.core.engine.Pikachu;
import com.luway.pikachu.core.exception.SimpleException;
import com.luway.pikachu.core.worker.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhenggm
 * @create 2018-04-25 13:39
 **/

public class PikachuImpl implements Pikachu {
    private final static Logger logger = LoggerFactory.getLogger(PikachuImpl.class);
    private String name;
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

    public PikachuImpl(String name) {
        this.name = name;
    }

    /**
     * 初始化
     *
     * @return
     */
    @Override
    public PikachuImpl init() {
        if (pikachuPool == null) {
            pikachuPool = new ThreadPoolExecutor(
                    coreNum, maxThreadNum, 0L, TimeUnit.SECONDS,
                    new LinkedBlockingDeque<>(1024), new PiakchuPoolFactory("pikachu")
                    , new ThreadPoolExecutor.AbortPolicy());
        }
        if (core == null) {
            core = new PikachuCore(pikachuPool);
        }

        logger.debug("pikachu init ...");
        return this;
    }

    @Override
    public PikachuImpl regist(Worker worker) {
        if (null == worker) {
            throw new SimpleException("worker is null");
        }
        core.putWorker(worker);
        return this;
    }


    /**
     * start pikachu
     */
    @Override
    public void start() {
        if (null == core) {
            throw new SimpleException("pikachu核心未初始化,请先初始化引擎");
        }
        core.start();
    }

    /**
     * 关闭线程池，等当前任务全部执行完毕。
     */
    @Override
    public void stop() {
        logger.debug("pikachu stop ...");
        core.stop();
    }

    /**
     * 在所有任务都结束且设置的时间内没有新任务则结束爬虫线程。
     * 推荐使用
     *
     * @param time
     */
    @Override
    public void stopAfterTime(Long time) {
        core.stopAfterTime(time);
    }

    @Override
    public PikachuImpl setMaxThreadNum(Integer maxThreadNum) {
        this.maxThreadNum = maxThreadNum;
        return this;
    }

    @Override
    public PikachuImpl setCoreNum(Integer coreNum) {
        this.coreNum = coreNum;
        return this;
    }
}