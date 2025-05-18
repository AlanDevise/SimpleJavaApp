package Schedule.TechInsight;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * @Filename: ScheduleService.java
 * @Package: Schedule.TechInsight
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年05月18日 16:18
 */

public class ScheduleService {

    ExecutorService executorService = Executors.newFixedThreadPool(6);

    Trigger trigger = new Trigger();

    /**
     * 功能描述: 1.定时任务的方法
     *
     * @param task  某一个具体任务
     * @param delay 延迟时间，单位为毫秒，每隔delay时间执行一次task
     */
    void schedule(Runnable task,
                  long delay) {
        Job job = new Job();
        job.setTask(task);
        job.setStartTime(System.currentTimeMillis() + delay);
        job.setDelay(delay);
        trigger.queue.offer(job);
        trigger.wakeUp();
    }

    /**
     * 功能描述: 1.等待合适的时间，把对应的任务扔到线程池中
     */
    class Trigger {

        PriorityBlockingQueue<Job> queue = new PriorityBlockingQueue<>();

        Thread thread = new Thread(() -> {
            while (true) {
                // 为什么这里要用while判断而不是if判断？
                // 因为线程被唤醒的时候，可能被虚假唤醒，当被虚假唤醒的时候queue.peek()方法可能回报空指针异常
                while (queue.isEmpty()) {
                    // 如果任务队列是空的，那么就阻塞等待，直到有任务加入队列
                    LockSupport.park();
                }
                // 为了防止被唤醒之后执行之前旧的排名第一的任务，所以这里要peek一下
                // 为什么是peek方法而不是poll方法？
                // 因为peek不会移除元素，而poll会移除元素。先用peek方法看一眼排名第一的任务是否已经到了执行时间
                Job latelyJob = queue.peek();
                if (latelyJob.getStartTime() <= System.currentTimeMillis()) {
                    // 如果排名第一的任务已经到了执行时间，那么就把它从队列中移除
                    // 【注意!!!】这里在多线程的场景下，poll方法拿到的job可能不是peek方法看到的job，但是并不影响结果
                    // 因为无论是不是相同的job，poll方法拿到的job都是需要被立即执行的
                    latelyJob = queue.poll();
                    executorService.execute(latelyJob.getTask());
                    // 计算下一次任务的执行时间，再扔进任务队列中以形成一个无限循环定时执行下去
                    Job nextJob = new Job();
                    nextJob.setTask(latelyJob.getTask());
                    nextJob.setStartTime(System.currentTimeMillis() + latelyJob.getDelay());
                    nextJob.setDelay(latelyJob.getDelay());
                    queue.offer(nextJob);
                } else {
                    // 如果排名第一的任务都还没有到执行时间，那么就阻塞等待最近一个要被执行任务的等待时间
                    LockSupport.parkUntil(latelyJob.getStartTime());
                }
            }
        });

        {
            thread.start();
            System.out.println("触发器启动了");
        }

        void wakeUp() {
            LockSupport.unpark(thread);
        }

    }
}
