package Algorithm.Matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * <p>
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * </p>
 *
 * <p>提示：
 * </p>
 * <p>m == matrix.length<br/>
 * n == matrix[i].length<br/>
 * 1 <= m, n <= 10<br/>
 * -100 <= matrix[i][j] <= 100</p>
 *
 * @Filename: SpiralMatrix.java
 * @Package: Algorithm.Matrix
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2024年12月17日 23:11
 */

public class SpiralMatrix {

    static class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> order = new ArrayList<>();
            if (Objects.isNull(matrix)
                    || matrix.length == 0
                    || matrix[0].length == 0) {
                return order;
            }
            int rows = matrix.length, columns = matrix[0].length;
            // 下面是坐标的思想
            // 先找出四角的坐标
            int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
            // 从最外围开始的向内的顺时针输出，left=right说明是同一列了，top=bottom说明已经是同一行了
            while (left <= right && top <= bottom) {
                // 首先从左至右横向开始，下面这个循环仅改变列号，不改变行号
                for (int column = left; column <= right; column++) {
                    order.add(matrix[top][column]);
                }
                // 再从上至下继续，下面这个循环仅改变行号，不改变列号
                for (int row = top + 1; row <= bottom; row++) {
                    order.add(matrix[row][right]);
                }
                // if为true表示内向还有没有遍历的数字
                if (left < right && top < bottom) {
                    // 从右往左开始便利，仅改变列号，不改变行号
                    for (int column = right - 1; column > left; column--) {
                        order.add(matrix[bottom][column]);
                    }
                    // 从下至上，仅改变行号，不改变列号
                    for (int row = bottom; row > top; row--) {
                        order.add(matrix[row][left]);
                    }
                }
                // 最外层遍历完，往里面缩一层，所以：左+1，右-1，顶+1，底-1
                left++;
                right--;
                top++;
                bottom--;
            }
            return order;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] staticArray = {
                {1, 2, 3, 4},
                {5, 5, 6, 7},
                {8, 9, 10, 11},
        };
        System.out.println(solution.spiralOrder(staticArray));
    }

}
