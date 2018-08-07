package com.luway.pikachu.core.engine;

import java.util.*;
import java.util.concurrent.ThreadFactory;

/**
 * @author zhenggm
 * @create 2018-04-25 13:38
 **/
public class PiakchuFactory implements ThreadFactory {
    private Map<String, Pikachu> pcmMap = new HashMap<>();
    private Pikachu pikachu;
    private Boolean flag;

    private int counter;
    private String name;
    private List<String> stats;

    public PiakchuFactory(String name) {
        counter = 0;
        this.name = name;
        stats = new ArrayList<String>();
    }


    public String getStas() {
        StringBuffer buffer = new StringBuffer();
        Iterator<String> it = stats.iterator();
        while (it.hasNext()) {
            buffer.append(it.next());
            buffer.append("\n");
        }
        return buffer.toString();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, name + "-Thread-" + counter);
        counter++;
        stats.add(String.format("Created thread %d with name %s on%s\n", t.getId(), t.getName(), new Date()));
        return t;
    }
}
