package com.luway.pikachu.core.engine;

import com.luway.pikachu.core.annotations.MatchUrl;
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
     * 置入一个worker，但不运行
     * @param worker
     * @return
     */
    Boolean putWork(Worker worker);

    /**
     * 运行某个id对应的worker
     * @param id
     */
    void runWorkId(String id);

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
     * 是否开启动态ip代理
     * @param openIpProxy
     * @return
     */
    Pikachu setOpenIpProxy(Boolean openIpProxy);

    /**
     * 是否开启随机暂停，如果开启每次访问站点会随机休眠减少压力。
     * @param sleep
     * @return
     */
    Pikachu setSleep(Boolean sleep);

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
    Document getConnect(String url, MatchUrl.Method method) throws IOException;

    /**
     * 获取html,携带cookies
     *
     * @param url
     * @param method
     * @return
     */
    Document getConnect(String url, MatchUrl.Method method, Map<String, String> cookies) throws IOException;


    /**
     * 获取html,携带cookies
     *
     * @param url
     * @param method
     * @return
     */
    Document getConnect(String url, MatchUrl.Method method, Map<String, String> cookies,Map<String,String> heads) throws IOException;

}
