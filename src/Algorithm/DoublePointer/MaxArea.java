package Algorithm.DoublePointer;

/**
 * LeetCode 75<br/>
 * 盛水最多的容器
 *
 * @Filename: MaxArea.java
 * @Package: Algorithm.DoublePointer
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年02月26日 22:14
 */

public class MaxArea {

    public static int maxArea(int[] height) {
        // 记录最大值
        int max = 0;
        // 设置左右的指针
        int left = 0;
        int right = height.length - 1;

        while (left != right) {
            int length = right - left;
            int heigth = Math.min(height[left], height[right]);
            int area = length * heigth;
            if (area > max) {
                max = area;
            }
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        // 返回记录中最大的值
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(nums));

        int[] nums2 = {1, 1};
        System.out.println(maxArea(nums2));
    }
}
