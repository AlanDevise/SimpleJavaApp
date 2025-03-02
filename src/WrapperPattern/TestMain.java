package WrapperPattern;

/**
 * @Filename: TestMain.java
 * @Package: WrapperPattern
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2023年05月04日 22:20
 */

public class TestMain {
    public static void main(String[] args) {
        serviceInterfaceImpl serviceInterface = new serviceInterfaceImpl();
        serviceInterface.speak();
        productInterfaceImpl productInterface = new productInterfaceImpl(serviceInterface);
        productInterface.speak();
    }
}
