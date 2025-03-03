package Concurrent;

/**
 * @Filename: VolatileExample.java
 * @Package: Concurrent
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年03月02日 21:43
 */

public class VolatileExample {
    // 使用 volatile 修饰的状态标志
    private static volatile boolean flag = false;

    public static void main(String[] args) {
        // 启动一个线程来修改标志
        Thread writer = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 修改标志
            flag = true;
            System.out.println("Flag has been set to true.");
        });

        // 启动一个线程来读取标志
        Thread reader = new Thread(() -> {
            while (!flag) {
                // 等待标志被设置为 true
                System.out.println("Flag is not true.");
            }
            System.out.println("Flag is now true.");
        });

        writer.start();
        reader.start();
    }
}