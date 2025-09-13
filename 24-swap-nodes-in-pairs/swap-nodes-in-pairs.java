/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        return swap(head);
    }
    public ListNode swap(ListNode n) {
        if(n == null) {
            return null;
        }
        if(n.next ==  null) {
            return n;
        }

        ListNode n1 = n, n2 = n.next, n3 = n.next.next;
        n2.next = n1;
        n1.next = swap(n3);
        return n2;
    }
}