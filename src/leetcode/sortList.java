package leetcode;

/**
 * Created by Json Wan on 2018/9/23.
 * Description:
 * 题目描述
 Sort a linked list in O(n log n) time using constant space complexity.
 */

/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class sortList {
    /**
     * 用O(nlogn)的时间复杂度+常量空间排序链表
     * 思路：归并
     * @param head
     * @return
     */
    public static ListNode sortList(ListNode head) {
        if(head==null||head.next==null)
            return head;
        ListNode head1=head;
        ListNode head2=head.next;
        if(head2.next==null){
            head.next=null;
            return merge(head,head2);
        }
        //快慢指针找到中点
        while(head2!=null){
            head1=head1.next;
            head2=head2.next;
            if(head2!=null)
                head2=head2.next;
        }
        head2=head1.next;
        head1.next=null;
        head=sortList(head);
        head2=sortList(head2);
        return merge(head,head2);
    }

    public static ListNode merge(ListNode head1,ListNode head2){
        if(head1==null)
            return head2;
        else if(head2==null)
            return head1;
        ListNode head=new ListNode(0);
        ListNode pHead=head;
        while(head1!=null&&head2!=null) {
            if (head1.val < head2.val){
                head.next=head1;
                head1=head1.next;
            }else{
                head.next=head2;
                head2=head2.next;
            }
            head=head.next;
        }
        if(head1==null){
            head.next=head2;
        }else{
            head.next=head1;
        }
        return pHead.next;
    }

    public static void testMerge(){
        ListNode node1=new ListNode(1);
        ListNode node3=new ListNode(3);
        ListNode node5=new ListNode(5);
        ListNode node7=new ListNode(7);
        node1.next=node3;
        node3.next=node5;
        node5.next=node7;
        ListNode node2=new ListNode(2);
        ListNode node4=new ListNode(4);
        ListNode node6=new ListNode(6);
        ListNode node8=new ListNode(8);
        node2.next=node4;
        node4.next=node6;
        node6.next=node8;
        ListNode pHead=merge(node1,node2);
        printList(pHead);
    }

    public static void printList(ListNode head){
        while(head!=null){
            System.out.println(head.val+" ");
            head=head.next;
        }
    }

    public static void main(String[] args) {
        ListNode node1=new ListNode(1);
        ListNode node3=new ListNode(3);
        ListNode node5=new ListNode(5);
        ListNode node7=new ListNode(7);
        ListNode node2=new ListNode(2);
        ListNode node4=new ListNode(4);
        ListNode node6=new ListNode(6);
        ListNode node8=new ListNode(8);
        node1.next=node3;
        node3.next=node2;
        node2.next=node5;
        node5.next=node4;
        node4.next=node8;
        node8.next=node6;
        node6.next=node7;
        ListNode pHead=sortList(node1);
        printList(pHead);
    }
}
