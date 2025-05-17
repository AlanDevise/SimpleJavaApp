package Thread.ThreadPool.TechInsight;

/**
 * @Filename: ThrowRejectHandle.java
 * @Package: Thread.ThreadPool.TechInsight
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年05月17日 18:29
 */

public class ThrowRejectHandle implements RejectHandler{
    @Override
    public void reject(Runnable rejectedCommand, MyThreadPool myThreadPool) {
        throw new RuntimeException("阻塞队列真的满了，装不下了");
    }
}
