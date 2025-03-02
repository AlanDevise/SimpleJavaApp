package Algorithm.String;

import java.util.Collections;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Filename: DecodeString.java
 * @Package: Algorithm.String
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年03月02日 16:11
 */

public class DecodeString {
    public static void main(String[] args) {
        String s = "2[abc]3[cd]ef";
        System.out.println(decodeString_2(s));
    }

    public static String decodeString_2(String s) {
        boolean flag = true;
        // 定义正则表达式模式
        Pattern pattern = Pattern.compile("(\\d+)\\[(\\w+)\\]");

        while (flag) {
            flag = false;
            Matcher matcher = pattern.matcher(s);
            StringBuffer sb = new StringBuffer();

            // 查找匹配项并替换
            while (matcher.find()) {
                flag = true;
                int num = Integer.parseInt(matcher.group(1));
                String str = matcher.group(2);
                StringBuilder repeatedStr = new StringBuilder();
                for (int i = 0; i < num; i++) {
                    repeatedStr.append(str);
                }
                // 将匹配项替换为重复后的字符串
                matcher.appendReplacement(sb, repeatedStr.toString());
            }
            // 将剩余部分追加到结果中
            matcher.appendTail(sb);
            s = sb.toString();
        }
        return s;
    }

    static int ptr;

    public static String decodeString(String s) {
        LinkedList<String> stk = new LinkedList<String>();
        ptr = 0;

        while (ptr < s.length()) {
            char cur = s.charAt(ptr);
            if (Character.isDigit(cur)) {
                // 获取一个数字并进栈
                String digits = getDigits(s);
                stk.addLast(digits);
            } else if (Character.isLetter(cur) || cur == '[') {
                // 获取一个字母并进栈
                stk.addLast(String.valueOf(s.charAt(ptr++)));
            } else {
                ++ptr;
                LinkedList<String> sub = new LinkedList<String>();
                while (!"[".equals(stk.peekLast())) {
                    sub.addLast(stk.removeLast());
                }
                Collections.reverse(sub);
                // 左括号出栈
                stk.removeLast();
                // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                int repTime = Integer.parseInt(stk.removeLast());
                StringBuffer t = new StringBuffer();
                String o = getString(sub);
                // 构造字符串
                while (repTime-- > 0) {
                    t.append(o);
                }
                // 将构造好的字符串入栈
                stk.addLast(t.toString());
            }
        }

        return getString(stk);
    }

    public static String getDigits(String s) {
        StringBuffer ret = new StringBuffer();
        while (Character.isDigit(s.charAt(ptr))) {
            ret.append(s.charAt(ptr++));
        }
        return ret.toString();
    }

    public static String getString(LinkedList<String> v) {
        StringBuffer ret = new StringBuffer();
        for (String s : v) {
            ret.append(s);
        }
        return ret.toString();
    }
}
