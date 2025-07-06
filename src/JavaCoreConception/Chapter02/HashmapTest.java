package JavaCoreConception.Chapter02;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Filename: HashmapTest.java
 * @Package: JavaCoreConception.Chapter02
 * @Version: V1.0.0
 * @Description: 1. Some hashmap test.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2023年02月22日 23:10
 */

public class HashmapTest {

    public static void main(String[] args) {
        /* 全局HashMap*/
        // Collection<Integer, Integer> hashMap = new Collection<>();
        ConcurrentHashMap<Integer, Integer> hashMap = new ConcurrentHashMap<>();
        hashMap.put(0, 0);

        /* 多线程编辑100次*/
        for (int i = 0; i < 1000; i++) {
            new Thread(new EditThread(hashMap)).start();
        }

        /** 读取线程*/
        new Thread(new ReadThread(hashMap)).start();
        /** 输出最终结果*/
        System.out.println("Demo1 main value " + hashMap.get(0));
    }
}

class EditThread implements Runnable {

    Map<Integer, Integer> hashMap;

    public EditThread(Map<Integer, Integer> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public void run() {
        hashMap.put(0, hashMap.get(0) + 1);
    }
}

class ReadThread implements Runnable {

    Map<Integer, Integer> hashMap;

    public ReadThread(Map<Integer, Integer> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public void run() {
        System.out.println("value " + hashMap.get(0));
    }
}
