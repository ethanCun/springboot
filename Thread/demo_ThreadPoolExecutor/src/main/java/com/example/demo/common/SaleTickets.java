package com.example.demo.common;

public class SaleTickets implements Runnable {

    private int tickets = 10;

    //synchronized 加锁
    @Override
    public synchronized void run() {

        for(int i=0; i<this.tickets; i++){

            if (this.tickets > 0){

                System.out.println("当前线程:" + Thread.currentThread().getName() + " i = " + i);
            }
        }

        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
