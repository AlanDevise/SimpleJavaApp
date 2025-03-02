package DesignPattern.Singleton;

/**
 * @Filename: Test.java
 * @Package: DesignPattern.Singleton
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2022年09月03日 09:29
 */

public class Test {

    public static void main(String[] args) {
        // 获取已经实例化好的对象
        Main main = Main.getMain();

        // 调用main类中需要使用的方法
        main.tellWhoAmI();
        System.out.println(main.tellWhoAmI());
    }
}
