package Algorithm.Matrix;

import java.util.Arrays;

/**
 * @Filename: MatrixToZero.java
 * @Package: Algorithm.Matrix
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2024年12月23日 20:31
 */

public class MatrixToZero {
    static class Solution {
        public void MatrixToZero(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            boolean flagCol0 = false;
            for (int i = 0; i < m; i++) {
                if (matrix[i][0] == 0) {
                    flagCol0 = true;
                }
                for (int j = 1; j < n; j++) {
                    if (matrix[i][j] == 0) {
                        matrix[i][0] = matrix[0][j] = 0;
                    }
                }
            }
            for (int i = m - 1; i >= 0; i--) {
                for (int j = 1; j < n; j++) {
                    if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                        matrix[i][j] = 0;
                    }
                }
                if (flagCol0) {
                    matrix[i][0] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        MatrixToZero.Solution solution = new MatrixToZero.Solution();
        int[][] staticArray = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1},
        };
        solution.MatrixToZero(staticArray);
        System.out.println(Arrays.deepToString(staticArray));
    }
}
