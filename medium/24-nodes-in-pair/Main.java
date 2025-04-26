class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode current = dummy;

        while (current.next != null && current.next.next != null) {
            ListNode first = current.next;
            ListNode second = current.next.next;

            first.next = second.next;
            second.next = first;
            current.next = second;

            current = first;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
            Solution solution = new Solution();

            int[] values1 = {1, 2, 3, 4};
            ListNode head1 = createList(values1);
            printList(solution.swapPairs(head1));

            int[] values2 = {};
            ListNode head2 = createList(values2);
            printList(solution.swapPairs(head2));
    }
}
