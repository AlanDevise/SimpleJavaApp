package AQS_Lock.TechInsight;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/**
 * @Filename: MyLock.java
 * @Package: AQS_Lock.TechInsight
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年05月19日 23:24
 */

public class MyLock {
    /**
     * 锁的状态
     */
    AtomicBoolean flag = new AtomicBoolean(false);

    /**
     * 当前持有锁的线程
     */
    Thread owner = null;

    /**
     * 为什么要使用AtomicReference<Node>呢？<br/>
     * 因为我们需要在做引用操作的时候，保证原子性<br/>
     */
    AtomicReference<Node> head = new AtomicReference<>(new Node());
    AtomicReference<Node> tail = new AtomicReference<>(head.get());

    /**
     * 加锁<br/>
     * 1. 加锁的逻辑<br/>
     * 获取到锁应该是直接返回，没有获取到锁应该是阻塞等待<br/>
     * 2. 加锁的方式<br/>
     * 3. 加锁的时机<br/>
     */
    public void lock() {
        // 如果能将flag的值从false改成true，那么就说明获取到了锁
        // 如果注释掉下面这个if代码块，那么这个锁就变成了公平锁，因为所有的线程都需要先链表尾部加入，然后再去尝试获取锁
        if (flag.compareAndSet(false, true)) {
            System.out.println(Thread.currentThread().getName() + "直接获取到了锁");
            owner = Thread.currentThread();
            return;
        }
        // 到这里意味着没有获取到锁
        // 创建一个当前节点
        Node current = new Node();
        // 当前节点的线程就是当前线程
        current.thread = Thread.currentThread();

        while(true){
            Node currentTail = tail.get();
            if (tail.compareAndSet(currentTail, current)){
                System.out.println(Thread.currentThread().getName() + "加入到了链表尾部");
                // 当前节点的前驱节点应该是之前的尾节点
                current.pre = currentTail;
                // 之前尾节点的后继节点应该是当前节点
                currentTail.next = current;
                System.out.println(Thread.currentThread().getName() + "被唤醒之后拿到锁");
                break;
            }
        }

        while(true){
            // Condition
            // 如果当前节点的前驱节点是头节点，并且能将flag的值从false改成true，那么就说明获取到了锁
            if (current.pre == head.get()
                    && flag.compareAndSet(false, true)) {
                owner = Thread.currentThread();
                // 下面这个操作一定是线程安全的，因为只有一个线程能执行到这里
                head.set(current);
                // 断掉当前节点的前驱节点的联系
                current.pre.next = null;
                current.pre = null;
                return;
            }
            // 真正进入阻塞之前，判断一次当前线程是不是真的拿不到锁了，如果拿不到就需要等其他线程来唤醒自己
            // 未避免虚假唤醒，我们将park()放在while循环中
            LockSupport.park();
        }
    }

    /**
     * 解锁
     */
    public void unlock() {
        if (Thread.currentThread() != owner) {
            throw new IllegalStateException("The thread is not owned by this lock");
        }
        // 拿到当前的头节点
        Node headNode = head.get();
        // 下一个应该被唤醒的节点
        Node next = headNode.next;
        // 只有一个线程会到这里，所以这里是线程安全的
        flag.set(false);
        if (next != null){
            System.out.println(Thread.currentThread().getName() + "唤醒了"+next.thread.getName());
            // 唤醒下一个应该被唤醒的节点
            LockSupport.unpark(next.thread);
        }
    }

    class Node{
        Node pre;
        Node next;
        Thread thread;
    }
}
