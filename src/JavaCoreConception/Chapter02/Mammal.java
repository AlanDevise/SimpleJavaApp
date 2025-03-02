package JavaCoreConception.Chapter02;

/**
 * @Filename: Mammal.java
 * @Package: JavaCoreConception.Chapter02
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2023年02月12日 21:17
 */

public interface Mammal {
    default void call() {
        System.out.println("I am mammal.");
    }
}
