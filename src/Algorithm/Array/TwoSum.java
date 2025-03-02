package Algorithm.Array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Filename: TwoSum.java
 * @Package: Algorithm.Array
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年02月26日 23:01
 */

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        // int[] nums = new int[]{2, 7, 11, 15};
        // int target = 9;
        // int[] ans = twoSum(nums, target);
        // System.out.println(Arrays.toString(ans));

        int[] nums_2 = new int[]{3, 2, 4};
        int target_2 = 6;
        int[] ans_2 = twoSum(nums_2, target_2);
        System.out.println(Arrays.toString(ans_2));
    }
}
