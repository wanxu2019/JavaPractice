package leetcode;

/**
 * Created by Json Wan on 2018/9/24.
 * Description:
 */
public class linkedListCycleII {

    public static ListNode detectCycle(ListNode head) {
        if(head==null)
            return null;
        //1.制造环内相遇
        ListNode p1=head;
        ListNode p2=head.next;
        while(p2!=null&&p2!=p1){
            p1=p1.next;
            p2=p2.next;
            if(p2==null)
                return null;
            else
                p2=p2.next;
        }
        if(p2==null)
            return null;
        //2.求环的大小
        int l=0;
        p1=p1.next;
        p2=p2.next.next;
        l++;
        while(p1!=p2){
            p1=p1.next;
            p2=p2.next.next;
            l++;
        }
        //3.制造环入口相遇
        p1=head;
        p2=head;
        while(l>0){
            p2=p2.next;
            l--;
        }
        while(p1!=p2){
            p1=p1.next;
            p2=p2.next;
        }
        //巧妙解法：在第一次相遇后，将快指针放回head往后跑，慢指针继续往前跑，二者一定在环入口处相遇，可通过代数符号运算证明。
        //2(x+y)=x+y+m(y+z)
        //==>x=m(y+z)-y=z+(m-1)(y+z)
        return p1;
    }

    public static boolean hasCycle(ListNode head) {
        //快慢指针直接做
        if(head==null)
            return false;
        //1.制造环内相遇
        ListNode p1=head;
        ListNode p2=head.next;
        while(p2!=null&&p2!=p1){
            p1=p1.next;
            p2=p2.next;
            if(p2==null)
                return false;
            else
                p2=p2.next;
        }
        if(p2==null)
            return false;
        return true;
    }

    public static void main(String[] args) {
        ListNode node1=new ListNode(1);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(3);
        ListNode node4=new ListNode(4);
        ListNode node5=new ListNode(5);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=node3;
        System.out.println(detectCycle(node1).val);
    }
}
