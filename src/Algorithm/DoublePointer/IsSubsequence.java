package Algorithm.DoublePointer;

/**
 * @Filename: IsSubsequence.java
 * @Package: Algorithm.DoublePointer
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年02月26日 21:55
 */

public class IsSubsequence {

    public static boolean isSubsequence(String s,
                                        String t) {
        if (s.isEmpty()) {
            return true;
        }
        String[] sArray = s.split("");
        String[] tArray = t.split("");
        for (int i = 0; i < sArray.length; i++) {
            // System.out.println(sArray[i]);
            for (int j = 0; j < tArray.length; j++) {
                if (sArray[i].equals(tArray[j])) {
                    i++;
                    if (i >= sArray.length) {
                        return true;
                    }
                }
            }
            if (i >= sArray.length) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static boolean isSubsequence_2(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }

    public static void main(String[] args) {
        String s = "abc", t = "ahbgdc";
        System.out.println(isSubsequence_2(s, t));

        String axc = "axc", ahbgdc = "ahbgdc";
        System.out.println(isSubsequence_2(axc, ahbgdc));

        String empty = "";
        System.out.println(isSubsequence_2(empty, ahbgdc));
    }
}
