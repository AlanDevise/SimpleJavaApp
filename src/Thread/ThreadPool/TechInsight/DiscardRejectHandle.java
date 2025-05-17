package Thread.ThreadPool.TechInsight;

import java.util.concurrent.BlockingQueue;

/**
 * @Filename: DiscardRejectHandle.java
 * @Package: Thread.ThreadPool.TechInsight
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年05月17日 18:35
 */

public class DiscardRejectHandle implements RejectHandler{
    @Override
    public void reject(Runnable rejectedCommand, MyThreadPool myThreadPool) {
        BlockingQueue<Runnable> blockingQueue = myThreadPool.getBlockingQueue();
        // 直接丢弃一个任务，然后再执行当前的任务
        blockingQueue.poll();
        myThreadPool.execute(rejectedCommand);
    }
}
