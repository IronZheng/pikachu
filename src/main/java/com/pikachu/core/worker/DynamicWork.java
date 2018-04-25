package com.pikachu.core.worker;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author zhenggm
 * @create 2018-03-01 上午 11:10
 **/


public class DynamicWork {
    private String id;

    public DynamicWork(String id) {
        this.id = id;
    }

    public Worker html(String id,Class<?> bean) {
        return new Worker(id,bean);
    }

    public Worker html(Class<?> bean) {
        return new Worker(RandomStringUtils.randomAlphabetic(6) + System.nanoTime(),bean);
    }
}
