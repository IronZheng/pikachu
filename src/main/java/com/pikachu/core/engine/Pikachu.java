package com.pikachu.core.engine;

import com.pikachu.core.worker.Worker;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author zhenggm
 * @create 2018-04-25 13:39
 **/

public class Pikachu {
    private final static Logger log = LoggerFactory.getLogger(Pikachu.class);
    private String name;
    private Worker worker;
    private Integer maxThreadNum;
    private Integer coreNum;
    private Document doc;

    public Pikachu(String name) {
        this.name = name;
    }

    public Pikachu regiest(Worker worker) {
        this.worker = worker;
        return this;
    }

    public void putTask() {

    }

    public void start() {
        this.worker.start();
        try {
            if ("GET".equals(this.worker.getMethod())) {
                doc = Jsoup.connect(this.worker.getUrl()).get();
            } else if ("POST".equals(this.worker.getMethod())) {
                doc = Jsoup.connect(this.worker.getUrl()).post();
            }
        } catch (IOException e) {
            log.error("connect error", e);
        }
    }

    public void stop() {

    }
}