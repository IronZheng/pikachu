package com.pikachu.core.engine;

import com.alibaba.fastjson.JSON;
import com.pikachu.core.annotations.MathUrl;
import com.pikachu.core.exception.SimpleException;
import com.pikachu.core.worker.Target;
import com.pikachu.core.worker.Worker;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.omg.IOP.TAG_RMI_CUSTOM_MAX_STREAM_FORMAT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.ObjectName;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author : iron
 * @version : 1.0.0
 * @date : 下午3:31 2018/8/1
 */

public class PikachuCore {
    private final static Logger log = LoggerFactory.getLogger(Pikachu.class);
    private Integer maxThreadNum;
    private Integer coreNum;
    private Document doc;
    private List<Worker> workers;

    public PikachuCore(List<Worker> workers) {
        this.workers = workers;
    }

    public void start() {
//        this.worker.start();
        for (Worker worker : workers) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        select(worker);
                    } catch (Exception e) {
                        log.error("connect error", e);
                        throw new SimpleException("connect error", e);
                    }
                }
            }).start();
        }
    }

    public synchronized void select(Worker worker) throws IOException {
        if (MathUrl.Method.GET.equals(worker.getMethod())) {
            doc = Jsoup.connect(worker.getUrl()).get();
        } else if (MathUrl.Method.POST.equals(worker.getMethod())) {
            doc = Jsoup.connect(worker.getUrl()).post();
        }
        if (doc == null) {
            worker.getPipeline().output(null);
        }

        Map<String,Object> target = new HashMap<>();
        for (Map.Entry<String, Target> attr : worker.getAttr().entrySet()) {
            Elements elements = doc.select(attr.getValue().getSelector());
            target.put(attr.getValue().getName(),elements.toString());
        }
        String json = JSON.toJSONString(target);
        worker.getPipeline().output(json);
    }
}
