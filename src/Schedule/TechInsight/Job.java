package Schedule.TechInsight;

/**
 * 一个具体任务的封装
 *
 * @Filename: Job.java
 * @Package: Schedule.TechInsight
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年05月18日 16:31
 */

public class Job implements Comparable<Job> {
    // 为什么这里要实现Comparable接口？
    // 因为我们要把Job放到一个有序的集合中，且这个有序结合需要通过判断startTime的大小来排序，所以要实现Comparable接口

    /**
     * @Description: 真正要执行的任务
     */
    private Runnable task;

    /**
     * @Description: 任务开始执行时间
     */
    private long startTime;


    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    private long delay;

    public Runnable getTask() {
        return task;
    }

    public void setTask(Runnable task) {
        this.task = task;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    @Override
    public int compareTo(Job o) {
        return Long.compare(this.startTime, o.getStartTime());
    }
}
