package Algorithm.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Filename: ReverseVowels.java
 * @Package: Algorithm.String
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年02月23日 17:10
 */

public class ReverseVowels {
    public static String reverseVowels(String s) {
        List<String> vowels = Arrays.asList("a", "e", "i", "o", "u", "A", "E", "I", "O", "U");
        List<String> record = new ArrayList<>();
        List<Integer> index = new ArrayList<>();
        String[] split = s.split("");
        for (int i = 0; i < split.length; i++) {
            if (vowels.contains(split[i])) {
                record.add(split[i]);
                index.add(i);
            }
        }
        Collections.reverse(record);
        for (int i = 0; i < record.size(); i++) {
            split[index.get(i)] = record.get(i);
        }
        return String.join("", split);
    }

    public static void main(String[] args) {
        System.out.println(reverseVowels("IceCreAm"));
    }
}
