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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
