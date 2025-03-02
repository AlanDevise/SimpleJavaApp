package ParallelStream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * @Filename: Test.java
 * @Package: ParallelStream
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2023年04月01日 14:17
 */

public class Test {
    public static void main(String[] args) throws InterruptedException {
        System.out.printf("  >>> 电脑 CPU 并行处理线程数 :: %s, 并行流公共线程池内线程数 :: %s%n", Runtime.getRuntime().availableProcessors(), ForkJoinPool.commonPool().getParallelism());
        List<String> stringList = new ArrayList<>();
        List<String> stringList2 = new ArrayList<>();
        for (int i = 0; i < 13; i++) stringList.add(String.valueOf(i));
        for (int i = 0; i < 3; i++) stringList2.add(String.valueOf(i));

        new Thread(() -> {
            stringList.parallelStream().forEach(each -> {
                System.out.println(Thread.currentThread().getName() + " 开始执行 :: " + each);
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }, "子线程-1").start();

        Thread.sleep(1500);

        new Thread(() -> {
            stringList2.parallelStream().forEach(each -> {
                System.out.println(Thread.currentThread().getName() + " :: " + each);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }, "子线程-2").start();
    }
}
