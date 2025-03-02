package JavaCoreConception.Chapter03;

import java.util.HashSet;
import java.util.Objects;

/**
 * @Filename: EqualsTest.java
 * @Package: JavaCoreConception.Chapter03
 * @Version: V1.0.0
 * @Description: 1. 用来测试equals 各种实现方法
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2023年02月26日 13:55
 */

public class EqualsTest {
    public static void main(String[] args) {
        Person personOne = new Person("Alan");
        Person personTwo = new Person("Alan");
        Person personThree = personOne;

        /*
        * 默认情况下，当开发者没有重写类中的equals 方法，那么直接调用类的equals 方法，与使用 == 运算法，没有差别。
        * 都是比较引用对象的是不是指向的同一个。而不会去比较值。
        * 除非开发者重写equals 方法。
        * */
        System.out.println("personOne == personTwo ? " + personOne.equals(personTwo));
        System.out.println("personOne == personThree ? " + personOne.equals(personThree));

        HashSet<Person> peopleSet = new HashSet<>();
        peopleSet.add(personOne);
        peopleSet.add(personTwo);
        System.out.println(peopleSet.size());
    }
}

class Person {
    private String name;

    public Person(String name){
        this.name = name;
    }

    // 重写equals方法
    @Override
    public boolean equals(Object obj) {
        // 1. 比较两个引用对象是否为同一个，如果是同一个，毫无疑问是相等的
        if (this == obj) {
            return true;
        }
        // 2. 比较两个引用对象是否类型相同，如果为null或者不同，必定不相等
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        // 3. 将形参强制类型转换为当前类的类型，比较类中的值是否相等
        Person person = (Person) obj;
        return Objects.equals(name, person.name);
    }

    // 重写equals 方法，必须同时重写hashCode方法
    // 不然在于Set类交互时会出现问题
    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
