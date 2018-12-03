package com.luway.pikachu.core.engine;

import com.luway.pikachu.core.annotations.MathUrl;
import com.luway.pikachu.core.worker.BathWorker;
import com.luway.pikachu.core.worker.GeneralWorker;
import com.luway.pikachu.core.worker.Worker;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;
import java.util.Queue;

/**
 * @author : iron
 * @version : 1.0.0
 * @date : 下午7:25 2018/8/20
 */

public interface Pikachu {

    /**
     * 初始化爬虫核心程序
     *
     * @return
     */
    Pikachu init();

    /**
     * 注册worker
     *
     * @param worker
     * @return
     */
    Pikachu regist(GeneralWorker worker);


    /**
     * 注册批量worker
     *
     * @param worker
     * @return
     */
    Pikachu regist(BathWorker worker);

    /**
     * 启动爬虫服务
     */
    void start();

    /**
     * 停止服务
     */
    void stop();

    /**
     * 设置最大线程数
     *
     * @param maxThreadNum
     * @return
     */
    Pikachu setMaxThreadNum(Integer maxThreadNum);

    /**
     * 设置核心线程数
     *
     * @param coreNum
     * @return
     */
    Pikachu setCoreNum(Integer coreNum);

    /**
     * 获取队列
     *
     * @return
     */
    Queue<Worker> getQueue();


    /**
     * 获取html
     *
     * @param url
     * @param method
     * @return
     */
    Document getConnect(String url, MathUrl.Method method) throws IOException;

    /**
     * 获取html,携带cookies
     *
     * @param url
     * @param method
     * @return
     */
    Document getConnect(String url, MathUrl.Method method, Map<String, String> cookies) throws IOException;
}
