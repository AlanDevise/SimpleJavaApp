package Algorithm.SlideWindow;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Filename: LongestOnes.java
 * @Package: Algorithm.SlideWindow
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年03月01日 15:47
 */

public class LongestOnes {
    public static int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int left = 0, lsum = 0, rsum = 0;
        int ans = 0;
        for (int right = 0; right < n; ++right) {
            rsum += 1 - nums[right];
            while (lsum < rsum - k) {
                lsum += 1 - nums[left];
                ++left;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;

    }

    public static int longestOnes_2(int[] nums, int k) {
        int l = 0, r = 0;
        while (r < nums.length) {
            if (nums[r++] == 0) k--;
            if (k < 0 && nums[l++] == 0) k++;
        }
        return r - l;
    }

    public static int longestOnes3(int[] nums, int k) {
        int l = 0, r = 0, ans = 0, max = 0;
        Queue<Integer> q = new LinkedList<>();
        if (k > 0) {
            while (r < nums.length) {
                if (r < nums.length && nums[r] == 0 && q.size() < k) {
                    q.add(r);
                    r++;
                } else if (r < nums.length && nums[r] == 1) {
                    r++;
                }
                if (r < nums.length && nums[r] == 0 && q.size() >= k && k != 0) {
                    l = q.remove();
                }
                if (r < nums.length) {
                    ans = Math.max(r - l, ans);
                }
            }
            if (l == 0 && q.size() > 0) {
                ans = r;
            }
        } else {
            while (r < nums.length) {
                if (nums[r] == 1) {
                    ans++;
                } else {
                    ans = 0;
                }
                max = Math.max(max, ans);
                r++;
            }
            ans = max;
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        // int k = 2;
        // System.out.println(longestOnes3(nums, k));
        //
        // int[] nums2 = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        // int k2 = 3;
        // System.out.println(longestOnes3(nums2, k2));
        //
        // int[] nums3 = {0, 0, 1, 1, 1, 0, 0};
        // int k3 = 0;
        // System.out.println(longestOnes3(nums3, k3));
        //
        // int[] nums4 = {0, 0, 0, 1};
        // int k4 = 4;
        // System.out.println(longestOnes3(nums4, k4));

        int[] nums5 = {0, 0, 1, 1};
        int k5 = 1;
        System.out.println(longestOnes3(nums5, k5));
    }
}