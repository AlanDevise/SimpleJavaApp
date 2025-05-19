package AQS_Lock.TechInsight;

import java.util.ArrayList;
import java.util.List;

/**
 * @Filename: Main.java
 * @Package: AQS_Lock.TechInsight
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年05月19日 23:15
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[] count = new int[]{1000};
        List<Thread> threads = new ArrayList<>();
        MyLock lock = new MyLock();

        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> {
                // 开始遍历前加一把锁
                lock.lock();
                for (int i1 = 0; i1 < 10; i1++) {
                    // try {
                    //     Thread.sleep(2);
                    // } catch (InterruptedException e) {
                    //     throw new RuntimeException(e);
                    // }
                    count[0]--;
                }
                // 遍历完了释放锁
                lock.unlock();
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(count[0]);
    }
}
