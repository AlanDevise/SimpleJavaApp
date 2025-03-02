package Algorithm.String;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Filename: KidsWithCandies.java
 * @Package: Algorithm.String
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年02月23日 16:19
 */

public class KidsWithCandies {
    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = IntStream.of(candies).max().getAsInt();
        Stream<Boolean> booleanStream = IntStream.of(candies).mapToObj(x -> x + extraCandies >= max);
        return booleanStream.collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] candy = new int[5];
        candy[0] = 2;
        candy[1] = 3;
        candy[2] = 5;
        candy[3] = 1;
        candy[4] = 3;
        List<Boolean> booleans = kidsWithCandies(candy, 3);
        System.out.println(booleans);
    }
}
