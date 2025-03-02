package Algorithm.Stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Filename: InorderTraversal.java
 * @Package: Algorithm.Stack
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2024年12月25日 21:02
 */

public class InorderTraversal {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 迭代算法
     */
    static class Solution {
        public List<Integer> InorderTraversal(TreeNode root) {
            List<Integer> res = new LinkedList<>();
            LinkedList<TreeNode> stack = new LinkedList<>();
            TreeNode pNode = root;
            while (pNode != null || !stack.isEmpty()) {
                if (pNode != null) {
                    stack.push(pNode);
                    pNode = pNode.left;
                } else { //pNode == null && !stack.isEmpty()
                    TreeNode node = stack.pop();
                    // System.out.print(node.val+"  ");
                    res.add(node.val);
                    pNode = node.right;
                }
            }
            return res;
        }
    }

    /**
     * Morris算法
     */
    static class Solution2 {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
            TreeNode predecessor = null;

            while (root != null) {
                if (root.left != null) {
                    // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                    predecessor = root.left;
                    while (predecessor.right != null && predecessor.right != root) {
                        predecessor = predecessor.right;
                    }

                    // 让 predecessor 的右指针指向 root，继续遍历左子树
                    if (predecessor.right == null) {
                        predecessor.right = root;
                        root = root.left;
                    }
                    // 说明左子树已经访问完了，我们需要断开链接
                    else {
                        res.add(root.val);
                        predecessor.right = null;
                        root = root.right;
                    }
                }
                // 如果没有左孩子，则直接访问右孩子
                else {
                    res.add(root.val);
                    root = root.right;
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        EffectiveBracket.Solution solution = new EffectiveBracket.Solution();
        String test1 = "([])";
        System.out.println(solution.EffectiveBracket(test1));
    }
}
