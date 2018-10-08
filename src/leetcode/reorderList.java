package leetcode;

/**
 * Created by Json Wan on 2018/9/24.
 * Description:
 */
public class reorderList {

    public static void reorderList(ListNode head) {
        if(head==null||head.next==null){
            return;
        }
        ListNode p1=head;
        ListNode p2=head.next;
        ListNode p3=p1;
        ListNode p4=p2;
        while(p2!=null) {
            p3=p1;
            p4=p2;
            while (p4.next != null) {
                p3 = p3.next;
                p4 = p4.next;
            }
            //注意：这句必须有，要避免创造出next指向自身的节点造成无限循环
            if(p4==p2)
                break;
            //将p4插入到p1,p2之间
            p4.next=p2;
            p1.next=p4;
            p3.next=null;
            p1=p2;
            p2=p2.next;
        }
    }

    public static void printList(ListNode head){
        while(head!=null){
            System.out.println(head.val+" ");
            head=head.next;
        }
    }

    public static void main(String[] args) {
        ListNode node1=new ListNode(1);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(3);
        ListNode node4=new ListNode(4);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        reorderList(node1);
        printList(node1);
    }
}
