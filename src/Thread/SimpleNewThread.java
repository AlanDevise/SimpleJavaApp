package Thread;

import DesignPattern.Singleton.Main;

import java.util.ArrayList;
import java.util.List;

/**
 * @Filename: SimpleNewThread.java
 * @Package: Thread
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2022年09月03日 09:38
 */

public class SimpleNewThread {
    public static void main(String[] args) {
        Main main = Main.getMain();
        for (int i = 0; i < 10; i++) {
            new Thread(()->
                    System.out.println(main.hashCode())
                    ).start();
        }
    }

    private void functionOne(){
        List<String> strings = new ArrayList<>();

    }
}
