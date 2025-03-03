package Concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Filename: CyclicBarrierExample.java
 * @Package: Concurrent
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年03月02日 22:43
 */

// 运动员类
class Athlete implements Runnable {
    private final int athleteId;
    private final CyclicBarrier barrier;

    public Athlete(int athleteId, CyclicBarrier barrier) {
        this.athleteId = athleteId;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            // 模拟运动员准备时间
            System.out.println("运动员 " + athleteId + " 正在准备...");
            Thread.sleep((long) (Math.random() * 5000));
            System.out.println("运动员 " + athleteId + " 准备完毕，等待其他运动员...");

            // 等待所有运动员准备好
            barrier.await();

            // 所有运动员都准备好后，一起起跑
            System.out.println("运动员 " + athleteId + " 起跑！");
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}

public class CyclicBarrierExample {
    public static void main(String[] args) {
        // 运动员数量
        int numAthletes = 5;
        // 创建 CyclicBarrier 实例，当所有运动员都到达屏障点时，执行以下操作
        CyclicBarrier barrier = new CyclicBarrier(numAthletes, () -> {
            System.out.println("所有运动员都准备好，比赛开始！");
        });

        // 创建并启动运动员线程
        for (int i = 1; i <= numAthletes; i++) {
            Thread athleteThread = new Thread(new Athlete(i, barrier));
            athleteThread.start();
        }
    }
}