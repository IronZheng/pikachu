package com.luway.pikachu.core.engine.impl;

import com.luway.pikachu.core.annotations.MatchUrl;
import com.luway.pikachu.core.engine.PiakchuPoolFactory;
import com.luway.pikachu.core.engine.Pikachu;
import com.luway.pikachu.core.exception.SimpleException;
import com.luway.pikachu.core.worker.BathWorker;
import com.luway.pikachu.core.worker.GeneralWorker;
import com.luway.pikachu.core.worker.Worker;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.*;

import static com.luway.pikachu.core.exception.Constant.NO_WORKER;

/**
 * @author zhenggm
 * @create 2018-04-25 13:39
 **/

public class PikachuImpl implements Pikachu {
    private final static Logger logger = LoggerFactory.getLogger(PikachuImpl.class);
    private String name;
    private ExecutorService pikachuPool;
    private ConcurrentHashMap<String, Worker> concurrentHashMap;


    /**
     * 默认最大线程数
     */
    private Integer maxThreadNum = 5;

    /**
     * 默认核心线程数
     */
    private Integer coreNum = 3;

    private Core core;

    /**
     * 开启代理开关
     */
    private volatile Boolean defaultOpenIpProxy = false;
    /**
     * 随机暂停开关
     */
    private volatile Boolean defaultSleepFlag = false;

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
                    new LinkedBlockingDeque<>(1024), new PiakchuPoolFactory(name)
                    , new ThreadPoolExecutor.AbortPolicy());
        }
        if (core == null) {
            core = new Core(pikachuPool, defaultOpenIpProxy, defaultSleepFlag);
        }
        if (concurrentHashMap == null) {
            concurrentHashMap = new ConcurrentHashMap<>();
        }

        logger.debug("pikachu init ...");
        return this;
    }

    @Override
    public PikachuImpl regist(GeneralWorker worker) {
        if (null == worker) {
            throw new SimpleException(NO_WORKER);
        }
        core.putWorker(worker);
        return this;
    }

    @Override
    public PikachuImpl regist(BathWorker worker) {
        if (null == worker) {
            throw new SimpleException(NO_WORKER);
        }
        // 保存至内存中
        putWork(worker);
        core.putWorker(worker);
        return this;
    }

    @Override
    public Boolean putWork(Worker worker) {
        if (concurrentHashMap.containsKey(worker.getId())) {
            return false;
        }
        concurrentHashMap.put(worker.getId(), worker);
        return true;
    }

    @Override
    public void runWorkId(String id) {
        Worker worker = concurrentHashMap.get(id);
        if (worker != null) {
            core.putWorker(worker);
        } else {
            logger.error("worker's id is null in map");
        }
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

    @Override
    public Pikachu setOpenIpProxy(Boolean openIpProxy) {
        this.defaultOpenIpProxy = openIpProxy;
        return this;
    }

    @Override
    public Pikachu setSleep(Boolean sleep) {
        this.defaultSleepFlag = sleep;
        return this;
    }

    @Override
    public Queue<Worker> getQueue() {
        return core.getQueue();
    }

    @Override
    public Document getConnect(String url, MatchUrl.Method method) throws IOException {
        return this.core.getConnect(url, method);
    }

    @Override
    public Document getConnect(String url, MatchUrl.Method method, Map<String, String> cookies) throws IOException {
        return this.core.getConnect(url, method, cookies);
    }

    @Override
    public Document getConnect(String url, MatchUrl.Method method, Map<String, String> cookies, Map<String, String> heads) throws IOException {
        return this.core.getConnect(url, method, cookies, heads);
    }

    @Override
    public Connection getOnlyConnect() {
        // todo S
//        return this.core.getConnect();
        return null;
    }

}