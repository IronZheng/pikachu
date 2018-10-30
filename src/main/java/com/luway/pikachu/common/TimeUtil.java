package com.luway.pikachu.common;

/**
 * @author : iron
 * @version : 1.0.0
 * @date : 上午11:00 2018/8/31
 */

public class TimeUtil {

    public static void sleep() {
        try {
            int sleep = (int) (Math.random() * 500 + 1);
            Thread.sleep(new Long(sleep));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        while (true) {
            sleep();
        }
    }
}
