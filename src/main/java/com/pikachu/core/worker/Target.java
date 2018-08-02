package com.pikachu.core.worker;

/**
 * @author zhenggm
 * @create 2018-04-24 17:19
 **/


public class Target {
    private String name;
    private String type;
    private String selector;
    private String xpath;

    public Target(String name, String type, String selector,String xpath) {
        this.name = name;
        this.type = type;
        this.selector = selector;
        this.xpath=xpath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
                ", type='" + type + '\'' +
                ", selector='" + selector + '\'' +
                ", xpath='" + xpath + '\'' +
                '}';
    }
}
