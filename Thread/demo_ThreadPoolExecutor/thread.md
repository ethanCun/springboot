#### Thread
```
    //Thread:写一个类继承自Thread类，重写run方法。用start方法启动线程
    public static void testThread(){

        MyThread myThread = new MyThread();
        MyThread2 myThread2 = new MyThread2();

        myThread.start();
        myThread2.start();
    }

public class MyThread extends Thread {

    @Override
    public void run() {

        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("3秒后输出这条信息 MyThread 1");
    }
}

public class MyThread2 extends Thread {

    @Override
    public void run() {

        System.out.println("my thread 2");
    }
}
```
#### Runnable
```
   //Runnable:写一个类实现Runnable接口，实现run方法。用new Thread(Runnable target).start()方法来启动
    /**
     * 实现Runnable接口比继承Thread类所具有的优势：
     *
     * 1）：适合多个相同的程序代码的线程去处理同一个资源
     *
     * 2）：可以避免java中的单继承的限制
     *
     * 3）：增加程序的健壮性，代码可以被多个线程共享，代码和数据独立
     * */
    public static void testRunnable(){

        MyRunnable myRunnable = new MyRunnable();
        MYRunnable2 myRunnable2 = new MYRunnable2();

        Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(myRunnable2);

        thread1.start();
        thread2.start();
    }

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

public class MYRunnable2 implements Runnable {

    @Override
    public void run() {

        System.out.println("runnable 2");
    }
}

```

#### Thread Runnable
```
    public static void saleTickets(){

        SaleTickets saleTickets = new SaleTickets();
        SaleTickets saleTickets2 = new SaleTickets();
        SaleTickets saleTickets3 = new SaleTickets();

        //开启3个线程 同一个runnable对象
        new Thread(saleTickets).start();
        new Thread(saleTickets).start();
        new Thread(saleTickets).start();

        //开启3个线程 3个runnable对象 此时synchronized不起作用
//        new Thread(saleTickets).start();
//        new Thread(saleTickets2).start();
//        new Thread(saleTickets3).start();
    }

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
```

#### 线程池
```
        //创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE
        //ExecutorService executorService = Executors.newCachedThreadPool();

        //创建容量为1的缓冲池
        //ExecutorService executorService = Executors.newSingleThreadExecutor();

        //创建固定容量大小的缓冲池
        //ExecutorService executorService = Executors.newFixedThreadPool(10);
```
```
    //线程池
    /**
     * 如果并发的线程数量很多，并且每个线程都是执行一个时间很短的任务就结束了，
     * 这样频繁创建线程就会大大降低系统的效率，因为频繁创建线程和销毁线程需要时间。
     *
     * Java中可以通过线程池 使得线程可以复用
     * */
    public static void testExecutor(){

        //参数：线程池预存线程数量 线程池最大线程数量 超出线程池预存线程数量的线程空闲时保留的时间 时间单位 缓存队列
        /**
         * 　　workQueue的类型为BlockingQueue<Runnable>，通常可以取下面三种类型：
         *
         * 　　1）ArrayBlockingQueue：基于数组的先进先出队列，此队列创建时必须指定大小；
         *
         * 　　2）LinkedBlockingQueue：基于链表的先进先出队列，如果创建时没有指定此队列大小，则默认为Integer.MAX_VALUE；
         *
         * 　　3）synchronousQueue：这个队列比较特殊，它不会保存提交的任务，而是将直接新建一个线程来执行新来的任务。
         * */
        /**
         * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
         * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
         * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
         * ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
         * */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5));

        //i最大为15：线程池最大线程数量+线程池预存线程数量
        //超出这个数量时抛出异常：java.util.concurrent.RejectedExecutionException:
        for(int i=0; i<15; i++){

            MyTask myTask = new MyTask(i);

            executor.execute(myTask);

            System.out.println("线程池中线程数量:" + executor.getPoolSize()
                    + "队列中等待执行的任务数量：" + executor.getQueue().size()
                    + "已执行完成的任务数量:" + executor.getCompletedTaskCount());
        }

        /**
         * 线程池的关闭:
         * shutdown()：不会立即终止线程池，而是要等所有任务缓存队列中的任务都执行完后才终止，但再也不会接受新的任务
         * shutdownNow()：立即终止线程池，并尝试打断正在执行的任务，并且清空任务缓存队列，返回尚未执行的任务
         * */
        executor.shutdown();
    }


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

```
