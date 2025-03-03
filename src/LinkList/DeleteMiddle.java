package LinkList;

/**
 * @Filename: DeleteMiddle.java
 * @Package: LinkList
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年03月03日 22:21
 */

public class DeleteMiddle {

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode deleteMiddle(ListNode head) {
        // 引入哑巴节点
        ListNode dummy = new ListNode(0, head);

        ListNode fast = dummy;
        ListNode slow = dummy;

        while (true) {
            if (fast != null)
                fast = fast.next;
            if (fast != null)
                fast = fast.next;

            if(fast == null)
                break;

            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        int a = 7;
        if (a % 2 == 0) {
            System.out.println(a / 2 );
        } else {
            System.out.println(a / 2 );
        }

    }

}
