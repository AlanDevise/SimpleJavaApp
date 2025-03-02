package Algorithm.String;

import java.util.Arrays;

/**
 * @Filename: moveZeroes.java
 * @Package: Algorithm.String
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年02月24日 22:51
 */

public class moveZeroes {

    public static void moveZeroes(int[] nums) {
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[cur] = nums[i];
                cur++;
            }
        }
        for (int i = cur; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        int[] nums_2 = {0, 0, 0, 0, 0, 3, 12};
        moveZeroes(nums_2);
        System.out.println(Arrays.toString(nums_2));
    }
}
