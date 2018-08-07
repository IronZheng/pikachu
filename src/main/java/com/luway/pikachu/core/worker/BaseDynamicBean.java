package com.luway.pikachu.core.worker;

import java.util.Map;

/**
 * @author zhenggm
 * @create 2018-04-24 17:18
 **/


public abstract class BaseDynamicBean {
    public String id;
    public String url;
    public Map<String, Target> attr;

    public abstract void start();

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

    public void setAttr(Map<String, Target> attr) {
        this.attr = attr;
    }
}
