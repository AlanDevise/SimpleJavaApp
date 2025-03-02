package Thread;

/**
 * @Filename: CommunicateInThread.java
 * @Package: Thread
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年03月02日 16:30
 */

public class CommunicateInThread {
    public static InheritableThreadLocal<Integer> shareData = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        shareData.set(0);
        MyThread myThread = new MyThread();
        myThread.start();
        shareData.set(shareData.get() + 1);
        System.out.println("Share Data in main thread: " + shareData.get());
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("shareData in child thread: " + shareData.get());
            shareData.set(shareData.get() + 1);
            System.out.println("shareData in child thread after increment: " + shareData.get());
        }
    }
}
