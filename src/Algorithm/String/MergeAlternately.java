package Algorithm.String;

/**
 * @Filename: MergeAlternately.java
 * @Package: Algorithm.String
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年02月23日 13:23
 */

public class MergeAlternately {
    private static String mergeAlternately(String word1, String word2) {
        int length = Math.max(word1.length(), word2.length());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            try {
                sb.append(word1.charAt(i));
            } catch (Exception ignored) {
            }
            try {
                sb.append(word2.charAt(i));
            } catch (Exception ignored) {
            }
        }
        return sb.toString();
    }

    private static String mergeAlternately_2(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int i = 0, j = 0;

        StringBuilder ans = new StringBuilder();
        while (i < m || j < n) {
            if (i < m) {
                ans.append(word1.charAt(i));
                ++i;
            }
            if (j < n) {
                ans.append(word2.charAt(j));
                ++j;
            }
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        String s = mergeAlternately("abcd", "pq");
        String s2 = mergeAlternately_2("abcd", "pq");
        System.out.println(s);
        System.out.println(s2);
    }
}
