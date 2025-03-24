package Algorithm.BitwiseOperation;

import java.util.Random;

public class MedianFinder {

    public static int quickSelect(int[] arr, int left, int right, int k) {
        Random random = new Random();
        int pivotIndex = left + random.nextInt(right - left + 1);
        swap(arr, pivotIndex, right);

        int pivot = arr[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, right);

        if (i + 1 == k) {
            return arr[i + 1];
        } else if (i + 1 > k) {
            return quickSelect(arr, left, i, k);
        } else {
            return quickSelect(arr, i + 2, right, k);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int findMedian(int[] arr) {
        int n = arr.length;
        if (n % 2 == 1) {
            return quickSelect(arr, 0, n - 1, n / 2);
        } else {
            int left = quickSelect(arr, 0, n - 1, n / 2 - 1);
            int right = quickSelect(arr, 0, n - 1, n / 2);
            return (left + right) / 2;
        }
    }

    public static void main(String[] args) {
        // 模拟1000亿个整数中的一部分数据
        int[] arr = new int[1000000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt();
        }

        int median = findMedian(arr);
        System.out.println("中位数是: " + median);
    }
}