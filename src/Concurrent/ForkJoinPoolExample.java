package Concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @Filename: ForkJoinPoolExample.java
 * @Package: Concurrent
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年03月02日 21:58
 */

// 继承 RecursiveTask 类，用于有返回结果的任务
class SumTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 10;
    private int[] array;
    private int start;
    private int end;

    public SumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= THRESHOLD) {
            // 任务足够小，直接计算结果
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            // 任务过大，进行拆分
            int mid = (start + end) / 2;
            SumTask leftTask = new SumTask(array, start, mid);
            SumTask rightTask = new SumTask(array, mid, end);

            // 执行左任务
            leftTask.fork();
            // 执行右任务
            int rightResult = rightTask.compute();
            // 获取左任务的结果
            int leftResult = leftTask.join();

            // 合并结果
            return leftResult + rightResult;
        }
    }
}

public class ForkJoinPoolExample {
    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i + 1;
        }

        // 创建 ForkJoinPool 实例
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 创建任务
        SumTask task = new SumTask(array, 0, array.length);
        // 执行任务并获取结果
        int result = forkJoinPool.invoke(task);

        System.out.println("数组元素的总和是: " + result);
    }
}