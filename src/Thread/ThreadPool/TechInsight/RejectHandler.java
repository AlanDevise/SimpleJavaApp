package Thread.ThreadPool.TechInsight;

/**
 * @Filename: RejectHandler.java
 * @Package: Thread.ThreadPool.TechInsight
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年05月17日 18:23
 */

public interface RejectHandler {
    void reject(Runnable rejectedCommand, MyThreadPool myThreadPool);
}
