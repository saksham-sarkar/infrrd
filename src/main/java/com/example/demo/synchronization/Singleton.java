package com.example.demo.synchronization;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Saksham
 */
public enum Singleton {
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(Singleton.class);

    void printNumber(int n) {
        StringBuilder res = new StringBuilder();
        res.append("Table of ").append(n).append("==>   ");
        synchronized (this){
            for(int i=1;i<=10;i++) {
                res.append(n).append(" * ").append(i).append(" = ").append(n * i);
                try {
                    Thread.sleep(1000);
                }
                catch(InterruptedException e) {
                    LOGGER.info("Interrupted::::: for:::::::  " + n);
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
        LOGGER.info(res.toString());
    }
}
