package Algorithm.Array;

import java.util.Arrays;

/**
 * @Filename: PivotIndex.java
 * @Package: Algorithm.Array
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年03月01日 18:07
 */

public class PivotIndex {
    public static int pivotIndex(int[] nums) {
        int sumLeft = 0, sumRight = Arrays.stream(nums).sum();
        for (int i = 0; i < nums.length; i++) {
            sumRight -= nums[i];
            // 若左侧元素和等于右侧元素和，返回中心下标 i
            if (sumLeft == sumRight)
                return i;
            sumLeft += nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] gain = {2, -1, -1};
        System.out.println(pivotIndex(gain));
    }
}
