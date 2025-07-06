package Algorithm.HashMap;

import java.util.Arrays;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

/**
 * @Filename: CloseStrings.java
 * @Package: Algorithm.Collection
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年03月02日 14:37
 */

public class CloseStrings {
    public static void main(String[] args) {
        String str1 = "cabbba";
        String str2 = "abbccc";
        System.out.println(closeStrings(str1, str2));
    }

    public static boolean closeStrings(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] sCnt = new int[26];
        for (byte c : s.getBytes(ISO_8859_1)) {
            sCnt[c - 'a']++;
        }

        int[] tCnt = new int[26];
        for (byte c : t.getBytes(ISO_8859_1)) {
            tCnt[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if ((sCnt[i] == 0) != (tCnt[i] == 0)) {
                return false;
            }
        }

        Arrays.sort(sCnt);
        Arrays.sort(tCnt);
        return Arrays.equals(sCnt, tCnt);

    }
}
