package Algorithm.String;

/**
 * @Filename: CanPlaceFlowers.java
 * @Package: Algorithm.String
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年02月23日 16:31
 */

public class CanPlaceFlowers {
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed.length == 0) {
            return false;
        }
        if (flowerbed.length == 1) {
            return flowerbed[0] != 1 || n == 0;
        }
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) {
                continue;
            } else if ((i == 0 && flowerbed[i] == 0 && flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                n--;
            } else if ((i == flowerbed.length - 1 && flowerbed[i] == 0 && flowerbed[i - 1] == 0)) {
                flowerbed[i] = 1;
                n--;
            } else if ((i != flowerbed.length - 1) && (flowerbed[i] == 0 && flowerbed[i + 1] == 0 && flowerbed[i - 1] == 0)) {
                flowerbed[i] = 1;
                n--;
            }
        }
        return n <= 0;
    }

    public static void main(String[] args) {
        int[] flowerbed = {1, 0, 0, 0, 1};
        int[] flowerbed_2 = {1, 0, 0, 0, 1, 0, 0};
        int[] flowerbed_3 = {0};
        int[] flowerbed_4 = {1, 0};
        int[] flowerbed_5 = {0,0,1,0,0};
        // System.out.println(canPlaceFlowers(flowerbed, 1));
        System.out.println(canPlaceFlowers(flowerbed_5, 1));
    }
}
