package com.luway.pikachu.core.worker.bean;

import com.luway.pikachu.core.annotations.MathUrl;
import com.luway.pikachu.core.annotations.WorkerType;
import com.luway.pikachu.core.pipeline.BasePipeline;

import java.util.List;
import java.util.Map;

/**
 * @author zhenggm
 * @create 2018-04-24 17:18
 **/


public class BaseWorker {
    public String id;
    public String url;
    public Map<String, Target> attr;

    public BasePipeline pipeline;
    public List<String> urlList;
    public boolean loadJs;

    private MathUrl.Method method;
    private Map<String, String> cookies;

    private WorkerType type;


    public WorkerType getType() {
        return type;
    }

    public void setType(WorkerType type) {
        this.type = type;
    }

    public MathUrl.Method getMethod() {
        return method;
    }

    protected void setMethod(MathUrl.Method method) {
        this.method = method;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Target> getAttr() {
        return attr;
    }

    protected void setAttr(Map<String, Target> attr) {
        this.attr = attr;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    protected void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public BasePipeline getPipeline() {
        return pipeline;
    }

    public void setPipeline(BasePipeline pipeline) {
        this.pipeline = pipeline;
    }

    public boolean isLoadJs() {
        return loadJs;
    }

    public void setLoadJs(boolean loadJs) {
        this.loadJs = loadJs;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    protected void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }
}
