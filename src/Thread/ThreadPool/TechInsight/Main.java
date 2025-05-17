package Thread.ThreadPool.TechInsight;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Filename: Main.java
 * @Package: Thread.ThreadPool.TechInsight
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年05月17日 16:44
 */

public class Main {
    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(
                2,
                4,
                1,
                TimeUnit.SECONDS,
                new DiscardRejectHandle(),
                new ArrayBlockingQueue<>(2));
        for (int i = 0; i < 8; i++) {
            final int fi = i;
            myThreadPool.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " " + fi);
            });
        }
        System.out.println("主线程没有被阻塞");
    }
}
