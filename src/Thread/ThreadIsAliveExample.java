package Thread;

/**
 * @Filename: ThreadIsAliveExample.java
 * @Package: Thread
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年03月02日 23:24
 */

class MyThread extends Thread {
    // 重写 run 方法，定义线程要执行的任务
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 线程开始执行");
        try {
            // 线程休眠 2 秒，模拟执行耗时任务
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 线程执行结束");
    }
}

public class ThreadIsAliveExample {
    public static void main(String[] args) {
        // 创建一个自定义线程对象
        MyThread myThread = new MyThread();

        // 在启动线程之前调用 isAlive() 方法
        System.out.println("线程启动前，isAlive() 返回: " + myThread.isAlive());

        // 启动线程
        myThread.start();

        // 在启动线程之后调用 isAlive() 方法
        System.out.println("线程启动后，isAlive() 返回: " + myThread.isAlive());

        try {
            // 主线程休眠 3 秒，确保自定义线程有足够的时间执行完毕
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 在线程执行结束后调用 isAlive() 方法
        System.out.println("线程执行结束后，isAlive() 返回: " + myThread.isAlive());
    }
}