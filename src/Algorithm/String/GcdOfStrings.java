package Algorithm.String;

import java.util.*;

/**
 * 示例 1：
 * <p>
 * 输入：str1 = "ABCABC", str2 = "ABC"
 * 输出："ABC"
 * </p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：str1 = "ABABAB", str2 = "ABAB"
 * 输出："AB"
 * </p>
 * <p>
 * 示例 3：
 * <p>
 * 输入：str1 = "LEET", str2 = "CODE"
 * 输出：""
 * </p>
 *
 * @Filename: GcdOfStrings.java
 * @Package: Algorithm.String
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年02月23日 14:47
 */

public class GcdOfStrings {
    public static String gcdOfStrings(String str1, String str2) {
        String shorter = str1.length() < str2.length() ? str1 : str2;
        String longer = str1.length() > str2.length() ? str1 : str2;
        // 遍历最小的字符串
        List<String> probability = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < shorter.length(); i++) {
            if (couldBeDived(shorter, sb.toString())) {
                probability.add(sb.toString());
            }
            sb.append(str1.charAt(i));
        }
        // 最后将最小字符串的整体算进可能性
        probability.add(sb.toString());
        List<String> result = new ArrayList<>();
        for (String prob : probability) {
            if (couldBeDived(longer, prob)) {
                result.add(prob);
            }
        }
        // 使用 Collections.max 方法结合自定义比较器找出最长的元素
        if (!result.isEmpty()) {
            return Collections.max(result, Comparator.comparingInt(String::length));
        } else {
            return "";
        }
    }

    public static boolean couldBeDived(String str1, String str2) {
        String[] split = str1.split(str2);
        return split.length == 0;
    }

    public static void main(String[] args) {
        System.out.println(gcdOfStrings("LEET", "CODE"));
        System.out.println(gcdOfStrings("ABABABAB", "ABAB"));
    }
}
