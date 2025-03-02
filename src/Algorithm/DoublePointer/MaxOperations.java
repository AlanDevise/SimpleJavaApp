package Algorithm.DoublePointer;

import java.util.HashMap;

/**
 * @Filename: MaxOperations.java
 * @Package: Algorithm.DoublePointer
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年02月26日 22:37
 */

public class MaxOperations {

    public static int maxOperations(int[] nums, int k) {
        int ans = 0;
        HashMap<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            int c = cnt.getOrDefault(k - x, 0);
            if (c > 0) {
                cnt.put(k - x, c - 1);
                ans++;
            } else {
                cnt.merge(x, 1, Integer::sum);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        int k = 3;
        System.out.println(maxOperations(nums, k));
    }
}
