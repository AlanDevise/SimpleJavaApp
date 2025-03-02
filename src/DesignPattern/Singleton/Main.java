package DesignPattern.Singleton;

/**
 * @Filename: Main.java
 * @Package: DesignPattern.Singleton
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2022年09月03日 09:25
 */

public class Main {

    // 将构造方法设为私有方法，只有自己能够将自己实例化出来
    private Main(){}

    // Main类自己生成实例
    private static final Main INSTANCE  = new Main();

    // 给外部调用自己的机会
    public static Main getMain(){
        return INSTANCE;
    }

    // 设为public方法，让其他类能够使用此方法
    public String tellWhoAmI(){
        return "My name is Alan.";
    }
}
