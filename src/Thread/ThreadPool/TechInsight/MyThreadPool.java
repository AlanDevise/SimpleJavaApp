package Thread.ThreadPool.TechInsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 一个自定义的线程池对象
 *
 * @Filename: MyThreadPool.java
 * @Package: Thread.ThreadPool.TechInsight
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年05月17日 16:45
 */

public class MyThreadPool {

    /**
     * 核心线程数，即线程池的初始大小<br/>
     */
    private final int corePoolSize;

    /**
     * 最大线程数，即线程池的最大大小<br/>
     */
    private final int maxSize;

    /**
     * 辅助线程超时时间<br/>
     */
    private final int timeout;

    /**
     * 辅助线程超时时间的单位<br/>
     */
    private final TimeUnit timeUnit;

    /**
     * 拒绝策略<br/>
     */
    private final RejectHandler rejectHandler;

    /**
     * 核心线程集合<br/>
     * 单个线程变成一个集合，每次从这个集合中取出一个线程来执行任务<br/>
     * <p>
     * 思考：我们的线程池应该有多少个线程？<br/>
     */
    List<Thread> coreList = new ArrayList<>();

    /**
     * 支持线程集合（核心线程忙不过来了，用来救场的线程）
     */
    List<Thread> supportList = new ArrayList<>();

    /**
     * 任务队列，存放任务的队列<br/>
     * 1. 为什么是BlockingQueue？因为我们要保证任务队列是线程安全的，所以我们使用BlockingQueue,
     * 更加关键的一点是，我们需要阻塞队列的“阻塞”特点来避免当队列为空的时候还会无限循环浪费cpu资源<br/>
     * 2. 为什么是ArrayBlockingQueue？因为我们要保证任务队列是线程安全的，所以我们使用ArrayBlockingQueue<br/>
     * 3. 为什么是1024？因为我们要保证任务队列是线程安全的，所以我们使用1024<br/>
     */
    private final BlockingQueue<Runnable> blockingQueue;

    public MyThreadPool(int corePoolSize,
                        int maxSize,
                        int timeout,
                        TimeUnit timeUnit,
                        RejectHandler rejectHandler,
                        BlockingQueue<Runnable> blockingQueue) {
        this.corePoolSize = corePoolSize;
        this.maxSize = maxSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.rejectHandler = rejectHandler;
        this.blockingQueue = blockingQueue;
    }

    // 1. 线程什么时候创建？
    // 2. 线程的runnable是什么？是我们提交的command吗？

    /**
     * 获取任务队列<br/>
     *
     * @return 当前线程池的任务队列<br />
     */
    public BlockingQueue<Runnable> getBlockingQueue() {
        return this.blockingQueue;
    }

    // 判断threadList是否为空，如果为空，就创建一个线程，否则就从threadList中取出一个线程来执行任务
    void execute(Runnable command) {
        if (coreList.size() < corePoolSize) {
            // 核心线程集合还没满，就创建一个新的核心线程来执行任务
            Thread thread = new CoreThread();
            coreList.add(thread);
            thread.start();
        }
        if (blockingQueue.offer(command)) {
            return;
        }
        // 为什么要用offer方法而不是add方法？因为add方法会抛出异常，而offer方法会返回false
        // 返回的false表示队列已满，此时我们可以选择阻塞等待，或者直接返回false
        // 任务队列已满（大概率因为核心线程忙不过来了），创建一个新的线程来执行任务
        if (coreList.size() + supportList.size() < maxSize) {
            // 如果核心线程和支持线程的数量小于最大线程数，就创建一个新的辅助线程来执行任务
            Thread thread = new SupportThread();
            supportList.add(thread);
            thread.start();
        }
        if (!blockingQueue.offer(command)) {
            // 第二次尝试将任务放入任务队列，如果还是失败，就抛出异常
            // throw new RuntimeException("阻塞队列真的满了，装不下了");
            // 抛异常的处理还是太糙了，可以抽象成一个阻塞策略，并且使用拒绝策略中的方法即可
            rejectHandler.reject(command, this);
        }
    }


    /**
     * 核心线程类<br/>
     */
    class CoreThread extends Thread {
        @Override
        public void run() {
            // 所有的核心线程都是执行这个相同的逻辑的
            while (true) {
                try {
                    Runnable command = blockingQueue.take();
                    command.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 辅助线程类<br/>
     */
    class SupportThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    // 为什么这里用poll而不是take方法？因为poll方法带有超时时间，
                    // 当队列为空的时候，会等待1秒，如果1秒后还是为空，就会返回null，
                    // 此时我们就可以跳出循环了，因为我们的线程池已经没有任务了，可以结束了
                    Runnable command = blockingQueue.poll(timeout, timeUnit);
                    if (Objects.isNull(command)) {
                        break;
                    }
                    command.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("因为没有任务拿了，所以" + Thread.currentThread().getName() + "线程结束了！");
        }
    }

}
