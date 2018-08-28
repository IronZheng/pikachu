package com.luway.pikachu.core.engine;

import com.luway.pikachu.core.worker.GeneralWorker;
import com.luway.pikachu.core.worker.Worker;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;
import java.util.Queue;

/**
 * @author : iron
 * @version : 1.0.0
 * @date : 上午11:26 2018/8/21
 */

public abstract class AbstractTempMethod {

    /**
     * 抓取url页面内容
     *
     * @param url
     * @param method
     * @return
     * @throws IOException
     */
    protected abstract Document getConnect(String url, String method) throws IOException;

    /**
     * 添加cookies，抓取页面内容
     *
     * @param url
     * @param method
     * @param cookies
     * @return
     * @throws IOException
     */
    protected abstract Document getConnect(String url, String method, Map<String, String> cookies) throws IOException;

    /**
     * 获取队列
     *
     * @return
     */
    public abstract Queue<Worker> getQueue();
}
