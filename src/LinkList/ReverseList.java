package LinkList;

import java.util.Stack;

/**
 * @Filename: ReverseList.java
 * @Package: LinkList
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年03月03日 23:04
 */

public class ReverseList {
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Stack<ListNode> stack = new Stack<>();
        // 遍历链表
        ListNode current = head;
        while (current != null) {
            stack.push(current);
            current = current.next;
        }
        ListNode newHead1 = null;
        ListNode newHead2 = null;
        while (!stack.isEmpty()) {
            if (newHead1 == null) {
                newHead1 = stack.pop();
            }
            if (newHead2 == null) {
                newHead2 = newHead1;
            }
            newHead1.next = stack.pop();
            newHead1 = newHead1.next;
            newHead1.next = null;
        }
        return newHead2;
    }

    public ListNode reverseList_2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList_2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        // 创建节点
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        // ListNode node3 = new ListNode(3);
        // ListNode node4 = new ListNode(4);
        // ListNode node5 = new ListNode(5);

        // 连接节点
        node1.next = node2;
        // node2.next = node3;
        // node3.next = node4;
        // node4.next = node5;


        // 遍历链表
        ListNode current = reverseList(node1);
        while (current != null) {
            System.out.println(current.val);
            current = current.next;
        }
    }
}
