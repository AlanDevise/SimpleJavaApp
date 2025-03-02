package Algorithm.SlideWindow;

import static java.lang.Math.max;

/**
 * @Filename: LongestSubarray.java
 * @Package: Algorithm.SlideWindow
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年03月01日 17:50
 */

public class LongestSubarray {
    public static int longestSubarray(int[] nums) {
        // 不定长滑动窗口问题
        int left = 0, max_ans = 0, count_1 = 0, count_0 = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) count_0++;
            else count_1++;

            while (count_0 > 1) {
                if (nums[left] == 0) count_0--;
                else count_1--;
                left++;
            }

            max_ans = max(max_ans, count_1);
        }
        if (count_0 == 0) return max_ans - 1;
        return max_ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,1,1,0,1,1,0,1};
        System.out.println(longestSubarray(nums));
    }
}
