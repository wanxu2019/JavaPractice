package leetcode;

/**
 * Created by Json Wan on 2018/9/23.
 * Description:
 */
public class insertionSortList {

    public static int getLength(ListNode head){
        int l=0;
        while(head!=null){
            l++;
            head=head.next;
        }
        return l;
    }

    public static ListNode insertToList(ListNode head,ListNode node,int index){
        if(index==0){
            node.next=head;
            return node;
        }else{
            ListNode pHead=head;
            ListNode lastNode=head;
            while(index>0){
                lastNode=head;
                head=head.next;
                index--;
            }
            node.next=head;
            lastNode.next=node;
            return pHead;
        }
    }

    public static ListNode insertionSortList(ListNode head) {
        if(head==null||head.next==null)
            return head;
        ListNode newListNode=head;
        ListNode p=head.next;
        newListNode.next=null;
        while(p!=null){
            //遍历新链表，找到插入位置
            int i=0;
            ListNode tmp=newListNode;
            while(tmp!=null&&tmp.val<p.val) {
                i++;
                tmp=tmp.next;
            }
            ListNode temp=p.next;
            //注意这一句会更改p.next
            newListNode=insertToList(newListNode,p,i);
            p=temp;
        }
        return newListNode;
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
        ListNode pHead=insertionSortList(node1);
        printList(pHead);
    }

}
