package com.example.demo.common;

public class MyTask implements Runnable{

    private int taskNum;

    public MyTask(int taskNum){

        this.taskNum = taskNum;
    }

    @Override
    public void run() {

        System.out.println("正在执行task: " + this.taskNum);

        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("task: " + this.taskNum + "执行完成");
    }
}
