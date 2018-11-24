package com.charjay.ctrl.lock;

public class Thread0 extends Thread{
    private DeadLock d1;
    public Thread0(DeadLock d1){
        this.d1=d1;
    }
    public void run(){
        try {
            d1.rightLeft();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}