package com.luway.pikachu.core.engine;

import com.luway.pikachu.core.engine.impl.PikachuImpl;
import com.luway.pikachu.core.worker.Worker;

/**
 * @author : iron
 * @version : 1.0.0
 * @date : 下午7:25 2018/8/20
 */

public interface Pikachu {

    /**
     * 初始化爬虫核心程序
     * @return
     */
    public Pikachu init();

    /**
     * 注册worker
     * @param worker
     * @return
     */
    public PikachuImpl regist(Worker worker);

    /**
     * 启动爬虫服务
     */
    public void start();

    /**
     * 停止服务
     */
    public void stop();

    /**
     * 指定空闲多少时间之后，停止爬虫服务
     * @param time
     */
    public void stopAfterTime(Long time);

    /**
     *
     * @param maxThreadNum
     * @return
     */
    public PikachuImpl setMaxThreadNum(Integer maxThreadNum);

    /**
     *
     * @param coreNum
     * @return
     */
    public PikachuImpl setCoreNum(Integer coreNum);
}
