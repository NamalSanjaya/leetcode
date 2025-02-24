class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        ListNode temp = head;
        int length = 0;
        while (temp != null) {
            length++;
            temp = temp.next;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy, curr = dummy, next = dummy;

        while (length >= k) {
            curr = prev.next;
            next = curr.next;

            for (int i = 1; i < k; i++) {
                curr.next = next.next;
                next.next = prev.next;
                prev.next = next;
                next = curr.next;
            }

            prev = curr;
            length -= k;
        }

        return dummy.next;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " --> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));

        ReverseNodesInKGroup solution = new ReverseNodesInKGroup();
        int k = 2;
        ListNode result = solution.reverseKGroup(head, k);
        printList(result);
    }
}
