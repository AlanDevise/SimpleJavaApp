package JavaCoreConception.Chapter01;

/**
 * @Filename: XiaomiIntelligenceSocket.java
 * @Package: JavaCoreConception.Chapter01
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2023年02月12日 14:44
 */

public class XiaomiIntelligenceSocket implements IntelligenceSocket{

    // 第一个属性
    private final String brand = "Xiaomi";

    @Override
    public void ConnectToNetwork(String wifiName, String wifiPassword) {
        System.out.println("执行连接网络的方法");
    }

    @Override
    public void getCommand(String command) {
        System.out.println("执行获取命令后执行的方法");
    }

    @Override
    public void ProvidePower() {
        System.out.println("执行提供电源的方法");
    }
}
