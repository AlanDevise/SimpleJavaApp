package ParallelStream;

import java.util.ArrayList;
import java.util.List;

/**
 * @Filename: Main.java
 * @Package: ParallelStream
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2023年04月01日 13:45
 */


public class Main {
    // 定义一个实体类
    static class Person {
        int id;
        String name;
        String sex;
        float height;

        public Person(int id, String name, String sex, float height) {
            this.id = id;
            this.name = name;
            this.sex = sex;
            this.height = height;
        }
    }

    /**
     * 构造数据
     */
    public static List<Person> constructPersons() {

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Person p = new Person(i, "name" + i, "sex" + i, i);
            persons.add(p);
        }
        return persons;
    }

    /**
     * for
     */
    public static void doFor(List<Person> persons) {
        long start = System.currentTimeMillis();

        for (Person p : persons) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException ignored) {
            }
            System.out.println(p.name);
        }

        long end = System.currentTimeMillis();
        System.out.println("doFor cost:" + (end - start) + " ms");
    }

    /**
     * 顺序流
     */
    public static void doStream(List<Person> persons) {
        long start = System.currentTimeMillis();

        persons.forEach(person -> {
            try {
                Thread.sleep(2);
            } catch (InterruptedException ignored) {
            }
            System.out.println(person.name);
        });

        long end = System.currentTimeMillis();
        System.out.println("doStream cost:" + (end - start) + "ms");
    }

    /**
     * 并行流
     */
    public static void doParallelStream(List<Person> persons) {
        long start = System.currentTimeMillis();
        persons.parallelStream().forEach(person -> {
            try {
                Thread.sleep(2);
            } catch (InterruptedException ignored) {
            }
            System.out.println(person.name);
        });
        long end = System.currentTimeMillis();
        System.out.println("doParallelStream cost:" + (end - start) + "ms");
    }


    public static void main(String[] args) {
        List<Person> people = constructPersons();
        doFor(people);
        doStream(people);
        doParallelStream(people);
    }
}
