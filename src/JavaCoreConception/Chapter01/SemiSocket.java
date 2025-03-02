package JavaCoreConception.Chapter01;

/**
 * @Filename: SemiSocket.java
 * @Package: JavaCoreConception.Chapter01
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2023年02月12日 14:51
 */

public abstract class SemiSocket implements IntelligenceSocket{
    @Override
    public void ConnectToNetwork(String wifiName, String wifiPassword) {
        System.out.println("执行连接网络的方法");
    }

}
