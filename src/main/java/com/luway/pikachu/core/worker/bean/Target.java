package com.luway.pikachu.core.worker.bean;

/**
 * @author zhenggm
 * @create 2018-04-24 17:19
 **/


public class Target {
    private String name;
    private String selector;
    private String xpath;

    public Target(String name, String selector,String xpath) {
        this.name = name;
        this.selector = selector;
        this.xpath=xpath;
    }


    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    @Override
    public String toString() {
        return "Target{" +
                "name='" + name + '\'' +
                ", selector='" + selector + '\'' +
                ", xpath='" + xpath + '\'' +
                '}';
    }
}
