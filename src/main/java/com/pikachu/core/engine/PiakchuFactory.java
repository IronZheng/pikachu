package com.pikachu.core.engine;

import com.pikachu.core.exception.SimpleException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhenggm
 * @create 2018-04-25 13:38
 **/
public class PiakchuFactory {
    private Map<String, Pikachu> pcmMap = new HashMap<>();
    private Pikachu pikachu;
    private Boolean flag;

    private PiakchuFactory() {
    }

    public Pikachu create(String name) {
        pikachu = new Pikachu(name);
        pcmMap.put(name, pikachu);
        return pikachu;
    }

    public Boolean destoryAll() {
        try {
            pcmMap.clear();
            flag = true;
        } catch (SimpleException e) {
            flag = false;
            throw new RuntimeException(e);
        }
        return flag;
    }
}
