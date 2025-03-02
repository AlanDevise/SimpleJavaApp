package Concurrent;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 演示 AtomicReference 的使用
 *
 * @Filename: AtomicReferenceExample.java
 * @Package: Concurrent
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年02月25日 23:07
 */

// 定义一个简单的类，用于演示 AtomicReference 的使用
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}

/**
 * 下面这个实例每次执行结果都不一样，因为两个线程会产生竞争，只有先执行了的才能成功，<br/>
 * 后执行的发现当前值不是expected期待值，就会更新失败
 */
public class AtomicReferenceExample {
    public static void main(String[] args) {
        // 创建一个初始的 Person 对象
        Person initialPerson = new Person("Alice", 25);
        // 创建一个 AtomicReference 实例，并初始化为 initialPerson
        AtomicReference<Person> atomicPerson = new AtomicReference<>(initialPerson);
        Person fakePerson = new Person("Alan", 29);

        // 创建一个线程来更新 Person 对象
        Thread updateThread = new Thread(() -> {
            // 创建一个新的 Person 对象
            Person newPerson = new Person("Bob", 30);
            // 使用 compareAndSet 方法原子性地更新对象引用
            boolean success = atomicPerson.compareAndSet(initialPerson, newPerson);
            if (success) {
                System.out.println("线程1更新成功，新的 Person 对象是: " + atomicPerson.get());
            } else {
                System.out.println("线程1更新失败，当前的 Person 对象是: " + atomicPerson.get());
            }
        });

        // 创建一个线程来读取 Person 对象
        Thread readThread = new Thread(() -> {
            // // 线程休眠 100 毫秒，确保更新线程有机会执行
            // Thread.sleep(100);
            boolean success = atomicPerson.compareAndSet(initialPerson, fakePerson);
            if (success) {
                // System.out.println("读取到的 Person 对象是: " + atomicPerson.get());
                System.out.println("线程2更新成功，新的 Person 对象是: " + atomicPerson.get());
            } else {
                System.out.println("线程2更新失败，新的 Person 对象是: " + atomicPerson.get());
            }

        });

        // 启动线程
        updateThread.start();
        readThread.start();

        try {
            // 等待两个线程执行完毕
            updateThread.join();
            readThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}