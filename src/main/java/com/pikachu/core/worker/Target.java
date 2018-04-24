package com.pikachu.core.worker;

/**
 * @author zhenggm
 * @create 2018-04-24 17:19
 **/


public class Target {
    private String name;
    private String type;
    private String selector;

    public Target(String name, String type, String selector) {
        this.name = name;
        this.type = type;
        this.selector = selector;
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

    @Override
    public String toString() {
        return "Target{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", selector='" + selector + '\'' +
                '}';
    }
}
