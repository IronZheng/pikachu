package com.luway.pikachu.core.engine;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.luway.pikachu.core.annotations.MathUrl;
import com.luway.pikachu.core.worker.Target;
import com.luway.pikachu.core.worker.Worker;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
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
    private Document doc;
    private volatile Boolean flag = true;
    private volatile Long currentTime;
    private Long stopTime;

    private Queue<Worker> workerQueue;

    private ExecutorService pikachuPool;

    public PikachuCore(List<Worker> workers, ExecutorService pikachuPool) {
        this.workerQueue = new ArrayBlockingQueue<>(1024);
        this.pikachuPool = pikachuPool;
        currentTime = System.currentTimeMillis();
    }

    protected boolean putWorker(Worker worker) {
        return workerQueue.offer(worker);
    }


    public void start() {
        pikachuPool.execute(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    try {
                        Worker worker = workerQueue.poll();
                        if (null != worker) {
                            pikachuPool.execute(() -> {
                                try {
                                    load(worker);
                                } catch (Exception e) {
                                    log.error("core error", e);
                                }
                            });
                            currentTime = System.currentTimeMillis();
                        } else {
                            judgeTime();
                        }
                        Thread.sleep(500);
                    } catch (Exception e) {
                        log.error("core error", e);
                        stop();
                    }
                }
            }
        });
    }

    public synchronized void load(Worker worker) throws Exception {

        if (worker.isLoadJs()) {
            loadJs(worker);
        } else {
            loadHtml(worker);
        }
    }

    private void loadJs(Worker worker) throws Exception {
        // HtmlUnit 模拟浏览器
        WebClient wc = new WebClient(BrowserVersion.FIREFOX_52);
        wc.setJavaScriptTimeout(5000);
        wc.getOptions().setUseInsecureSSL(true);//接受任何主机连接 无论是否有有效证书
        wc.getOptions().setJavaScriptEnabled(true);//设置支持javascript脚本
        wc.getOptions().setCssEnabled(false);//禁用css支持
        wc.getOptions().setThrowExceptionOnScriptError(false);//js运行错误时不抛出异常
        wc.getOptions().setTimeout(100000);//设置连接超时时间
        wc.getOptions().setDoNotTrackEnabled(false);
        HtmlPage page = wc.getPage(worker.getUrl());

        String pageAsXml = page.asXml();
        // Jsoup解析处理
        Document doc = Jsoup.parse(pageAsXml, worker.getUrl());
        select(doc, worker);
    }

    private void loadHtml(Worker worker) throws Exception {
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
        select(doc, worker);
    }

    private void select(Document doc, Worker worker) throws Exception {

        Map<String, Object> target = new HashMap<>(16);
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

    public void stop() {
        this.flag = false;
        pikachuPool.shutdown();
    }

    public void stopAfterTime(Long time) {
        this.stopTime = time;
    }

    private void judgeTime() {
        Long nowDate = System.currentTimeMillis();
        if ((nowDate - currentTime) > (stopTime * 1000)) {
            this.stop();
        }
    }
}
