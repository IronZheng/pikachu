package com.luway.pikachu.core.exception;

/**
 * @author zhenggm
 * @create 2018-03-01 下午 5:25
 **/


public class SimpleException extends RuntimeException {

    public SimpleException() {
        super();
    }

    public SimpleException(Exception e) {
        super(e);
    }

    public SimpleException(String message) {
        super(message);
    }

    public SimpleException(String message, Exception e) {
        super(message, e);
    }
}
