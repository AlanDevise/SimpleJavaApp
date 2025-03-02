package JavaCoreConception.Chapter02;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Filename: Reflection.java
 * @Package: JavaCoreConception.Chapter02
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2023年02月24日 21:30
 */

public class Reflection {
    // 运行时获取已知名称的类或已有对象相关信息，包括类的方法，属性，父类等信息
    // JDK中，主要由以下类来实现Java反射机制，这些类都位于java.lang.reflect包中
    /**
     * Class类：代表一个类
     * Constructor类：代表类的构造方法
     * Field类：代表类的成员变量
     * Method：代表类的方法
     */
    int m;

    public Reflection() {
        System.out.println("无参构造方法");
    }

    public Reflection(String k) {
        System.out.println("一个参数构造方法---" + k);
    }

    public Reflection(String k, Integer v) {
        System.out.println("两个参数构造方法---" + k + ":" + v);
    }

    public void fun1() {
        System.out.println("无参数成员方法");
    }

    public void fun2(String k) {
        System.out.println("一个参数成员方法---" + k);
    }

    public void fun3(String k, Integer v) {
        System.out.println("两个参数成员方法---" + k + ":" + v);
    }

    public static void main(String[] args) throws Exception {
        // 加载并初始化指定的类reflect
        Class classInfo = Class.forName("JavaCoreConception.Chapter02.Reflection");// 代表类名是A
        System.out.println("类Reflection构造函数如下所示：");
        Constructor[] constructors = classInfo.getConstructors();
        for (int i = 0; i < constructors.length; i++) {
            System.out.println(constructors[i].toString());
        }

        // 获得类的所有变量
        System.out.println("\n类reflect变量如下：");
        Field[] declaredFields = classInfo.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            System.out.println(declaredFields[i].toString());
        }

        // 获得类的所有方法
        System.out.println("\n类reflect方法如下：");
        Method[] declaredMethods = classInfo.getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; i++) {
            System.out.println(declaredMethods[i].toString());
        }


        //=========================================构造方法=============================================


        // 第一种方式
        constructors = classInfo.getConstructors();

        // 调用两个参数构造方法
        constructors[1].newInstance("Hello", 2019);
        // 调用一个参数构造方法
        constructors[2].newInstance("Hello");
        // 调用无参构造方法
        constructors[0].newInstance();

        // 第二种方式
        // 调用无参构造方法
        Constructor constructor = classInfo.getConstructor();
        constructor.newInstance();
        // 调用一个参数构造方法
        constructor = classInfo.getConstructor(String.class);
        constructor.newInstance("Hello");
        // 调用两个参数构造方法
        constructor = classInfo.getConstructor(String.class, Integer.class);
        constructor.newInstance("Hello", 2019);


        //=========================================成员方法=============================================

        // 调用无参构造函数，生成新的实例对象
        Object obj = classInfo.getConstructor().newInstance();

        // 调用无参成员函数
        Method method1 = classInfo.getMethod("fun1");
        // 通过实例对象进行方法的实例化
        method1.invoke(obj);


        // 调用一个参数成员函数
        Method method2 = classInfo.getMethod("fun2", String.class);
        method2.invoke(obj, "Hello");

        // 调用两个参数成员函数
        Method method3 = classInfo.getMethod("fun3", String.class, Integer.class);
        method3.invoke(obj, "Hello", 2019);
    }

}
