package com.charjay.ctrl.lock;

public class Thread1 extends Thread{
    private DeadLock d1;
    public Thread1(DeadLock d1){
        this.d1=d1;
    }
    public void run(){
        try {
            d1.leftRight();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}