package Algorithm.HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Filename: FindDifference.java
 * @Package: Algorithm.HashMap
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年03月01日 18:36
 */

public class FindDifference {
    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        int length = Math.max(nums1.length, nums2.length);
        HashMap<Integer, Integer> nums1Map = new HashMap<>(nums1.length);
        HashMap<Integer, Integer> nums2Map = new HashMap<>(nums2.length);
        for (int i = 0; i < length; i++) {
            if (i < nums1.length) {
                nums1Map.put(nums1[i], i);
            }
            if (i < nums2.length) {
                nums2Map.put(nums2[i], i);
            }
        }
        List<Integer> differenceWithStream = findDifferenceWithStream(nums1Map, nums2Map);
        List<Integer> differenceWithStream2 = findDifferenceWithStream(nums2Map, nums1Map);
        List<List<Integer>> result = new ArrayList<>();
        result.add(differenceWithStream);
        result.add(differenceWithStream2);
        return result;
    }

    public static List<Integer> findDifferenceWithStream(Map<Integer, ?> mapA, Map<Integer, ?> mapB) {
        return mapA.keySet().stream()
                .filter(key -> !mapB.containsKey(key))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 3};
        int[] nums2 = {1, 1, 2, 2};
        System.out.println(findDifference(nums1, nums2));
    }
}
