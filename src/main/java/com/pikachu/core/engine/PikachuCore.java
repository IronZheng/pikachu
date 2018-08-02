package com.pikachu.core.engine;

import com.alibaba.fastjson.JSON;
import com.pikachu.core.annotations.MathUrl;
import com.pikachu.core.exception.SimpleException;
import com.pikachu.core.worker.Target;
import com.pikachu.core.worker.Worker;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;

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

    private Queue<Worker> workerQueue;

    private ExecutorService pikachuPool;

    public PikachuCore(List<Worker> workers, ExecutorService pikachuPool) {
        this.workers = workers;
        this.workerQueue = new ArrayBlockingQueue<>(1024);
        this.pikachuPool = pikachuPool;
    }

    protected boolean putWorker(Worker worker) {
        return workerQueue.offer(worker);
    }


    public void start() {
        new Thread(() -> {
            while (true) {
                try {
                    Worker worker = workerQueue.poll();
                    if (null != worker) {
                        pikachuPool.execute(() -> {
                            try {
                                select(worker);
                            } catch (Exception e) {
                                log.error("core error", e);
                            }
                        });
                    }
                    Thread.sleep(500);
                } catch (Exception e) {
                    log.error("core error", e);
                }
            }
        }).start();
    }

    public synchronized void select(Worker worker) throws Exception {
        if (MathUrl.Method.GET.equals(worker.getMethod())) {
            doc = Jsoup.connect(worker.getUrl())
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0")
                    .get();
        } else if (MathUrl.Method.POST.equals(worker.getMethod())) {
            doc = Jsoup.connect(worker.getUrl()).post();
        }
        if (doc == null) {
            worker.getPipeline().output(null);
        }

        Map<String, Object> target = new HashMap<>();
        HtmlCleaner hc = new HtmlCleaner();
        TagNode tn = hc.clean(doc.body().html());
        org.w3c.dom.Document dom = new DomSerializer(new CleanerProperties()).createDOM(tn);
        XPath xPath = XPathFactory.newInstance().newXPath();

        for (Map.Entry<String, Target> attr : worker.getAttr().entrySet()) {
            if (null != attr.getValue().getSelector()) {
                Elements elements = doc.select(attr.getValue().getSelector());
                target.put(attr.getValue().getName(), elements.toString());
            }
            if (null != attr.getValue().getXpath()) {
                NodeList result = (NodeList) xPath.evaluate(attr.getValue().getXpath(),
                        dom, XPathConstants.NODESET);
                target.put(attr.getValue().getName(), result);
            }
        }
        worker.getPipeline().output(target);
    }
}
