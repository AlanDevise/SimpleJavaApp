package WrapperPattern;

/**
 * @Filename: productInterfaceImpl.java
 * @Package: WrapperPattern
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2023年05月04日 22:24
 */

public class productInterfaceImpl implements serviceInterface{
    private final serviceInterface serviceInterface;

    public productInterfaceImpl(WrapperPattern.serviceInterface serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    @Override
    public void speak() {
        serviceInterface.speak();
    }

    private void sing(){
        System.out.println("I can sing");
    }
}
