package JavaCoreConception.Chapter02;

/**
 * @Filename: Cat.java
 * @Package: JavaCoreConception.Chapter02
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2023年02月12日 21:17
 */

public interface Cat extends Mammal{
    @Override
    default void call() {
        System.out.println("I am cat.");
    }
}
