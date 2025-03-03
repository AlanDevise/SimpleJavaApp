package Concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * <h1>CountDownLatch的实现Demo示例</h1>
 *
 * @Filename: CountDownLatchExample.java
 * @Package: Concurrent
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年03月02日 22:34
 */

public class CountDownLatchExample {
    public static void main(String[] args) {
        // 创建一个 CountDownLatch 对象，计数器初始值为 3
        CountDownLatch latch = new CountDownLatch(3);

        // 创建并启动 3 个工作线程
        for (int i = 0; i < 3; i++) {
            final int index = i;
            new Thread(() -> {
                try {
                    System.out.println("Thread " + index + " is working.");
                    // 模拟线程执行任务
                    Thread.sleep((long) (Math.random() * 100000));
                    System.out.println("Thread " + index + " has completed its task.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 线程完成任务后，计数器减 1
                    latch.countDown();
                }
            }).start();
        }

        try {
            System.out.println("Main thread is waiting for all tasks to complete.");
            // 主线程等待，直到计数器的值变为 0
            latch.await();
            System.out.println("All tasks have been completed. Main thread continues.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}