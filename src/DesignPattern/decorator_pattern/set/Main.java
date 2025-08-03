package DesignPattern.decorator_pattern.set;

import java.util.Set;
import java.util.TreeSet;

/**
 * @Filename: Main.java
 * @Package: DesignPattern.decorator_pattern
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年08月03日 15:18
 */

public class Main {
    public static void main(String[] args) {
        Set<String> set = new HistorySet<>(new TreeSet<>());
        set.add("1");
        set.add("2");
        set.add("3");
        set.add("4");
        set.remove("4");
        set.remove("4");
        set.remove("5");
        set.remove("1");
        System.out.println(set);
    }
}
