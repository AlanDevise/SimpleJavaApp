package JavaCoreConception.Chapter01;

/**
 * @Filename: IntelligenceSocket.java
 * @Package: JavaCoreConception.Chapter01
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2023年02月12日 14:40
 */

public interface IntelligenceSocket extends Socket{
    String wifiName = null;
    String wifiPassword = null;
    void ConnectToNetwork(String wifiName, String wifiPassword);
    void getCommand(String command);
}
