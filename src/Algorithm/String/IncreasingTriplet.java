package Algorithm.String;

import java.util.Stack;

/**
 * @Filename: IncreasingTriplet.java
 * @Package: Algorithm.String
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年02月24日 22:16
 */

public class IncreasingTriplet {
    public static boolean increasingTriplet(int[] nums) {
        int length = nums.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(nums[0]);
        for (int i = 1; i < length; i++) {
            if (stack.isEmpty() || nums[i] > stack.peek()) {
                stack.push(nums[i]);
            } else {
                stack.clear();
                stack.push(nums[i]);
            }
            if (stack.size() == 3) {
                return true;
            }
        }
        if (stack.size() == 3) {
            return true;
        }
        return false;
    }

    public static boolean increasingTriplet_2(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        int first = nums[0], second = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (num > second) {
                return true;
            } else if (num > first) {
                second = num;
            } else {
                first = num;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] num1 = {1, 2, 3, 4, 5};
        int[] num2 = {5, 4, 3, 2, 1};
        int[] num3 = {2, 1, 5, 0, 4, 6};
        int[] num4 = {20, 100, 10, 12, 5, 13};
        // boolean b = increasingTriplet(num1);
        // boolean b2 = increasingTriplet(num2);
        // boolean b3 = increasingTriplet(num3);
        boolean b4 = increasingTriplet_2(num4);
        // System.out.println(b);
        // System.out.println(b2);
        // System.out.println(b3);
        System.out.println(b4);

    }
}
