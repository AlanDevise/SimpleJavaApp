package Concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Filename: ReadWriteMap.java
 * @Package: Concurrent
 * @Version: V1.0.0
 * @Description: 1. 通过读写锁来封装一个线程安全的读写Map
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2024年03月05日 22:23
 */

public class ReadWriteMap<K, V> {
    private final Map<K, V> map;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    /**
     * 读锁
     */
    private final Lock r = lock.readLock();
    /**
     * 写锁
     */
    private final Lock w = lock.writeLock();

    public ReadWriteMap(Map<K, V> map) {
        this.map = map;
    }

    public V put(K key, V value) {
        w.lock();
        try {
            return map.put(key, value);
        } finally {
            w.unlock();
        }
    }

    public V get(Object key) {
        r.lock();
        try {
            TimeUnit.MICROSECONDS.sleep(10000000);
            return map.get(key);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            r.unlock();
        }
    }

    public static class MyThread extends Thread {
        public MyThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
//        代码就是线程在开启之后执行的代码
            for (int i = 0; i < 100; i++) {
                String name = getName();
                System.out.println(name+"线程开启了" + i);
                try {
                    TimeUnit.MICROSECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        HashMap hashMap = new HashMap<>();
        hashMap.put("key1", "value1");
        // 提前向读写map中写入测试数据
        ReadWriteMap<String, String> readWriteLockMap = new ReadWriteMap<String, String>(hashMap);
        readWriteLockMap.put("key1","value1");
        // 开启一个现成来读取其中的值，并且持续5秒钟
        new Thread(()->{
            while (true) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // 读取Map中的值并且打印到屏幕
                System.out.println("线程1获取到读锁，读到此时key1的值为："+readWriteLockMap.get("key1"));

            }
        }).start();

        // 开启一个现成来读取其中的值，并且持续5秒钟
        new Thread(()->{
            while (true) {
                long timeMillis = System.currentTimeMillis();
                try {
                    TimeUnit.MICROSECONDS.sleep(1000);
                } catch (InterruptedException ignored) {

                }
                readWriteLockMap.put("key1",String.valueOf(timeMillis));
                // 读取Map中的值并且打印到屏幕
                System.out.println("线程2获取到写锁，写到此时key1的值为："+timeMillis);
            }
        }).start();
    }
}

