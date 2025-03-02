package WrapperPattern;

/**
 * @Filename: serviceInterfaceImpl.java
 * @Package: WrapperPattern
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2023年05月04日 22:23
 */

public class serviceInterfaceImpl implements serviceInterface {
    @Override
    public void speak() {
        System.out.println("I can speak");
    }
}
