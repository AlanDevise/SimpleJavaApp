package Algorithm.Matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 根据 百度百科 ， 生命游戏 ，简称为 生命 ，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
 * <p>
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态： 1 即为 活细胞 （live），或 0 即为 死细胞 （dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * <p>
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是 同时 发生的。给你 m x n 网格面板 board 的当前状态，返回下一个状态。
 * <p>
 * 给定当前 board 的状态，更新 board 到下一个状态。
 * <p>
 * 注意 你不需要返回任何东西。
 * </p>
 *
 * @Filename: LifeGame.java
 * @Package: Algorithm.Matrix
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2024年12月18日 21:13
 */

public class LifeGame {

    static class Solution {
        public void LifeGame(int[][] board) {
            // 一个大小和原始矩阵一模一样的结果矩阵
            int[][] result = new int[board.length][board[0].length];
            int rows = board.length;
            int cols = board[0].length;
            // 从原数组复制一份到 copyBoard 中
            for (int row = 0; row < rows; row++) {
                System.arraycopy(board[row], 0, result[row], 0, cols);
            }

            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    // System.out.println(matrix[i][j] + "\t");
                    try {
                        int leftTop = board[i - 1][j - 1];
                        temp.add(leftTop);
                    } catch (Exception ignored) {
                    }
                    try {
                        int top = board[i - 1][j];
                        temp.add(top);
                    } catch (Exception ignored) {
                    }
                    try {
                        int rightTop = board[i - 1][j + 1];
                        temp.add(rightTop);
                    } catch (Exception ignored) {
                    }
                    try {
                        int left = board[i][j - 1];
                        temp.add(left);
                    } catch (Exception ignored) {
                    }
                    try {
                        int right = board[i][j + 1];
                        temp.add(right);
                    } catch (Exception ignored) {
                    }
                    try {
                        int leftBottom = board[i + 1][j - 1];
                        temp.add(leftBottom);
                    } catch (Exception ignored) {
                    }
                    try {
                        int bottom = board[i + 1][j];
                        temp.add(bottom);
                    } catch (Exception ignored) {
                    }
                    try {
                        int rightBottom = board[i + 1][j + 1];
                        temp.add(rightBottom);
                    } catch (Exception ignored) {
                    }
                    if (board[i][j] == 0) {
                        if (Collections.frequency(temp, 1) == 3) {
                            result[i][j] = 1;
                        }
                    } else {
                        if ((Collections.frequency(temp, 1) != 3)
                                && (Collections.frequency(temp, 1) != 2)) {
                            result[i][j] = 0;
                        }
                    }
                    temp.clear();
                }
            }
            // 将结果复制到原board中
            for (int row = 0; row < rows; row++) {
                System.arraycopy(result[row], 0, board[row], 0, cols);
            }
        }
    }

    public static void main(String[] args) {
        LifeGame.Solution solution = new Solution();
        int[][] staticArray = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0},
        };
        solution.LifeGame(staticArray);
    }
}
