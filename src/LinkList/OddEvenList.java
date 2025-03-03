package LinkList;

/**
 * @Filename: OddEvenList.java
 * @Package: LinkList
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年03月03日 22:47
 */

public class OddEvenList {


    public static ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    public static void main(String[] args) {
        // 创建节点
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        // 连接节点
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        oddEvenList(node1);


        // 遍历链表
        ListNode current = node1;
        while (current != null) {
            System.out.println(current.val);
            current = current.next;
        }
    }
}
