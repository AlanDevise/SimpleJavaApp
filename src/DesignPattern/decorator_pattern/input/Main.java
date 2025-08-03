package DesignPattern.decorator_pattern.input;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;

/**
 * @Filename: Main.java
 * @Package: DesignPattern.decorator_pattern.input
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年08月03日 15:36
 */

public class Main {
    public static void main(String[] args) {
        File file = new File("ElasticStack.pdf");
        long l = Instant.now().toEpochMilli();
        try (InputStream fileInputStream = new BufferedFileInputStream(new FileInputStream(file))) {
            while (true) {
                int read = fileInputStream.read();
                if (read == -1) {
                    break;
                }
            }
            System.out.println("耗时：" + (Instant.now().toEpochMilli() - l) + "毫秒");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
