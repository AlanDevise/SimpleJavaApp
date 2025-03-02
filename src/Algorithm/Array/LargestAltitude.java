package Algorithm.Array;

/**
 * @Filename: LargestAltitude.java
 * @Package: Algorithm.Array
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年03月01日 17:58
 */

public class LargestAltitude {
    public static int largestAltitude(int[] gain) {
        int largest = 0;
        int currentAltitude = 0;
        for (int i = 0; i < gain.length; i++) {
            currentAltitude += gain[i];
            if (currentAltitude > largest) {
                largest = currentAltitude;
            }
        }
        return largest;
    }

    public static void main(String[] args) {
        int[] gain = {-4,-3,-2,-1,4,3,2};
        System.out.println(largestAltitude(gain));
    }
}
