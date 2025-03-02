package Algorithm.SlideWindow;

/**
 * @Filename: MaxVowels.java
 * @Package: Algorithm.SlideWindow
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年02月26日 23:26
 */

public class MaxVowels {

    public static int maxVowels(String s, int k) {
        int n = s.length();
        int vowel_count = 0;
        for (int i = 0; i < k; ++i) {
            vowel_count += isVowel(s.charAt(i));
        }
        int ans = vowel_count;
        for (int i = k; i < n; ++i) {
            // 右侧新进入窗口的字母为元音字母，左侧移出窗口的字母也是元音字母，这样一进一出抵消掉了
            // 右侧新进入窗口的字母为元音字母，左侧移出窗口的字母非元音字母，此时元音字母个数+1
            // 右侧新进入窗口的字母非元音字母，左侧移出窗口的字母为元音字母，此时元音字母个数-1
            vowel_count += isVowel(s.charAt(i)) - isVowel(s.charAt(i - k));
            ans = Math.max(ans, vowel_count);
        }
        return ans;
    }

    public static int isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ? 1 : 0;
    }

    public static void main(String[] args) {
        String s = "aeiou";
        int k = 2;
        System.out.println(maxVowels(s, k));
    }
}
