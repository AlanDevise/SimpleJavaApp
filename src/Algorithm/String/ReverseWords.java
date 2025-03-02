package Algorithm.String;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Filename: ReverseWords.java
 * @Package: Algorithm.String
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年02月24日 21:17
 */

public class ReverseWords {

    public static String reverseWords(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("the sky is blue"));
        System.out.println(reverseWords("  hello   world  "));
    }
}
