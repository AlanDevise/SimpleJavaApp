package Algorithm.Stack;

import java.util.Stack;

/**
 * @Filename: EffectiveBracket.java
 * @Package: Algorithm.Stack
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2024年12月24日 23:01
 */

public class EffectiveBracket {
    static class Solution {
        public boolean EffectiveBracket(String s) {
            if (s == null || s.length() % 2 != 0) {
                return false;
            }
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                if (!stack.isEmpty()) {
                    if (s.charAt(i) == ')' && stack.peek() == '(') {
                        stack.pop();
                    } else if (s.charAt(i) == ']' && stack.peek() == '[') {
                        stack.pop();
                    } else if (s.charAt(i) == '}' && stack.peek() == '{') {
                        stack.pop();
                    } else {
                        stack.push(s.charAt(i));
                    }
                } else {
                    stack.push(s.charAt(i));
                }
            }
            return stack.isEmpty();
        }
    }

    public static void main(String[] args) {
        EffectiveBracket.Solution solution = new EffectiveBracket.Solution();
        String test1 = "([])";
        System.out.println(solution.EffectiveBracket(test1));
    }
}
