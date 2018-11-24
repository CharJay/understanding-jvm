package com.charjay.ctrl.lock;

import java.util.concurrent.TimeUnit;

public class DeadLock {
    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight() throws InterruptedException {
        synchronized (left){
            TimeUnit.SECONDS.sleep(2);
            System.out.println("leftRight start");
            synchronized (right){
                System.out.println("leftRight end");
            }
        }
    }

    public void rightLeft() throws InterruptedException {
        synchronized (right){
            TimeUnit.SECONDS.sleep(2);
            System.out.println("rightLeft start");
            synchronized (left){
                System.out.println("rightLeft end");
            }
        }
    }
}
