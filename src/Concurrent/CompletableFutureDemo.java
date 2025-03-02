package Concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Filename: CompletableFutureDemo.java
 * @Package: Concurrent
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年03月02日 16:44
 */

public class CompletableFutureDemo {
    public static void main(String[] args) {
        // 创建一个包含 1 到 10 的整数列表
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 对列表中的每个元素创建一个 CompletableFuture 对象，将每个元素的处理作为一个异步任务
        List<CompletableFuture<Integer>> futures = nums.stream()
                // 使用 map 方法将列表中的每个元素转换为一个 CompletableFuture 对象
                .map(value -> CompletableFuture.supplyAsync(() -> {
                    // 这里是每个异步任务要执行的操作，直接返回当前元素
                    return value;
                }))
                // 将流转换为列表
                .toList();

        // 创建一个 CompletableFuture 对象，用于等待所有异步任务完成，并对结果进行合并
        CompletableFuture<Integer> sumFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                // 当所有异步任务完成后，使用 thenApplyAsync 方法进行异步处理
                .thenApplyAsync(v -> {
                    // 所有异步计算任务完成后，将它们的结果进行合并
                    int sum = futures.stream()
                            // 将每个 CompletableFuture 对象的结果提取出来，并转换为 IntStream
                            .mapToInt(CompletableFuture::join)
                            // 对 IntStream 中的元素求和
                            .sum();
                    return sum;
                });

        // 等待 sumFuture 完成，并获取最终的计算结果
        int sum = sumFuture.join();

        // 输出所有整数的总和
        System.out.println(sum);
    }
}
