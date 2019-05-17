package com.example.demo.common;

public class MyRunnable implements Runnable {

    @Override
    public void run() {

        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("3秒后输出这条信息runnable");
    }
}
