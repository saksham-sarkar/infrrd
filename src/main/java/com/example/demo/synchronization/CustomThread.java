package com.example.demo.synchronization;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Saksham
 */
@Component
@Scope("prototype")
public class CustomThread implements Runnable
{
    private int n;

    public void run() {
        Singleton.INSTANCE.printNumber(n);
    }

    public void setN(int n){
        this.n = n;
    }
}
