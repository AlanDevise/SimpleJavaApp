package JavaCoreConception.Chapter02;

/**
 * @Filename: RobotTiger.java
 * @Package: JavaCoreConception.Chapter02
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2023年02月12日 21:18
 */

public interface RobotTiger extends Tiger,Cat{
    public void Mew();

    @Override
    default void call() {
        // 这里发生了菱形继承冲突，需要开发者明确指出是用哪一个接口中的call方法，或者自己重写一个
        Cat.super.call();
    }
}
