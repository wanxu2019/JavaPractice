package leetcode;

/**
 * Created by Json Wan on 2017/10/7.
 * Description:
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 */
public class P2 {
    public static class ListNode{
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
        ListNode(int x,ListNode next) {
            val = x;
            this.next=next;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }
    //mySolution 51ms
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultNode=new ListNode(0);
        ListNode resultNodeCopy=resultNode;
        //进位
        int carryBit=0;
        int sum;
        while(l1!=null||l2!=null||carryBit!=0){
            resultNode.next=new ListNode(0);
            resultNode=resultNode.next;
            if(l1==null&&l2==null){
                resultNode.val=carryBit;
                carryBit=0;
            }
            else if(l1==null) {
                sum=resultNode.val+l2.val+carryBit;
                resultNode.val=sum%10;
                if(sum>=10)
                    carryBit=1;
                else
                    carryBit=0;
                l2=l2.next;
            }else if(l2==null){
                sum=resultNode.val+l1.val+carryBit;
                resultNode.val=sum%10;
                if(sum>=10)
                    carryBit=1;
                else
                    carryBit=0;
                l1=l1.next;
            }else{
                sum=resultNode.val+l1.val+l2.val+carryBit;
                resultNode.val=sum%10;
                if(sum>=10)
                    carryBit=1;
                else
                    carryBit=0;
                l1=l1.next;
                l2=l2.next;
            }
        }
        return resultNodeCopy.next;
    }
    //45ms的解法
    public ListNode ss45ms(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(l1.val + l2.val);
        ListNode head = result;
        if(result.val >= 10){
            if(result.next == null){
                result.next = new ListNode(1);
            }
            result.val -= 10;
        }

        while(l1.next != null && l2.next != null){
            l1 = l1.next;
            l2 = l2.next;
            if(result.next == null){
                result.next = new ListNode(0);
            }
            result = result.next;
            result.val += (l1.val + l2.val);

            if(result.val >= 10){
                result.val -= 10;
                if(result.next == null){
                    result.next = new ListNode(1);
                }
            }
        }

        while(l1.next != null){
            l1 = l1.next;
            if(result.next == null){
                result.next = new ListNode(0);
            }
            result = result.next;
            result.val += l1.val;

            if(result.val >= 10){
                result.val -= 10;
                if(result.next == null){
                    result.next = new ListNode(1);
                }
            }
        }

        while(l2.next != null){
            l2 = l2.next;
            if(result.next == null){
                result.next = new ListNode(0);
            }
            result = result.next;
            result.val += l2.val;

            if(result.val >= 10){
                result.val -= 10;
                if(result.next == null){
                    result.next = new ListNode(1);
                }
            }

        }

        return head;
    }
    //46ms的解法
    private ListNode ss46ms(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return null;
        ListNode result = new ListNode(-1);
        ListNode temp = result;
        int carry = 0;
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                carry += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                carry += l2.val;
                l2 = l2.next;
            }
            temp.next = new ListNode(carry % 10);
            temp = temp.next;
            carry = carry / 10;
        }
        if (carry == 1) temp.next = new ListNode(1);
        return result.next;
    }
    //47ms的解法//carry第一次取值为0
    public ListNode ss47ms(ListNode l1, ListNode l2, int carry)
    {
        if(l1==null&&l2==null&carry==0) return null;

        int val=0;
        val+=carry;

        if(l1!=null)
            val+=l1.val;

        if(l2!=null)
            val+=l2.val;

        ListNode node = new ListNode(val%10);
        node.next = ss47ms(l1 == null ? null : l1.next, l2 == null ? null : l2.next, val / 10);
        return node;
    }
    //48ms的解法
    public ListNode ss48ms(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0), p = head;
        int sum = 0;
        while (l1 != null || l2 != null) {
            sum /= 10;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            p.next = new ListNode(sum % 10);
            p = p.next;
        }

        if (sum >= 10) p.next = new ListNode(1);
        return head.next;
    }
    //49ms的解法
    public ListNode ss49ms(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode res = new ListNode((l1.val+l2.val)%10);
        ListNode result = res;
        if(l1.val+l2.val>9) carry = 1;
        while(l1.next !=null && l2.next != null){
            result.next = new ListNode((l1.next.val+l2.next.val+carry)%10);
            result = result.next;
            if(l1.next.val+l2.next.val+carry>9) carry = 1;
            else carry = 0;
            l1 = l1.next;
            l2 = l2.next;
        }
        while(!(l1.next == null && l2.next == null)){
            if(l1.next == null && l2.next != null) {
                result.next = new ListNode((l2.next.val+carry)%10);
                result = result.next;
                if(l2.next.val+carry>9) carry = 1;
                else carry = 0;
                l2 = l2.next;
            }
            else if(l2.next == null && l1.next != null) {
                result.next = new ListNode((l1.next.val+carry)%10);
                result = result.next;
                if(l1.next.val+carry>9) carry = 1;
                else carry = 0;
                l1 = l1.next;
            }
        }
        if (carry == 1) {
            result.next = new ListNode(1);
        }
        return res;
    }
    //50ms的解法
    public ListNode ss50ms(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
    //51ms的解法
    public ListNode ss51ms(ListNode l1, ListNode l2) {
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        int carry = 0;
        boolean first = true;
        boolean second = false;
        ListNode l3 = new ListNode(0);
        ListNode cur = new ListNode(0);
        while (cur1 != null || cur2 != null || carry != 0) {
            int val1 = 0;
            int val2 = 0;
            if (cur1 != null) {
                val1 = cur1.val;
                cur1 = cur1.next;
            }
            if (cur2 != null) {
                val2 = cur2.val;
                cur2 = cur2.next;
            }
            int value = val1 + val2 + carry;
            if (value < 10) {
                if (first) {
                    l3.val = (value);
                    first = false;
                    second = true;
                } else if (second) {
                    cur.val = (value);
                    l3.next = cur;
                    second = false;
                } else {
                    cur.next = new ListNode(value);
                    cur = cur.next;
                }
                carry = 0;
            } else {
                if (first) {
                    l3.val = value - 10;
                    first = false;
                    second = true;
                } else if (second) {
                    cur.val = (value - 10);
                    l3.next = cur;
                    second = false;
                } else {
                    cur.next = new ListNode(value - 10);
                    cur = cur.next;
                }
                carry = 1;
            }
        }
        return l3;
    }
    //打印链表
    public static void printListNode(ListNode node){
        while(node!=null){
            System.out.print(node.getVal()+" ");
            node=node.getNext();
        }
        System.out.println();
    }
    public static void main(String[] args) {
        ListNode node1=new ListNode(3);
        ListNode node2=new ListNode(4);
        ListNode node3=new ListNode(3);
        node1.setNext(node2);
        node2.setNext(node3);
        ListNode node4=new ListNode(7);
        ListNode node5=new ListNode(6);
        ListNode node6=new ListNode(4);
        node4.setNext(node5);
        node5.setNext(node6);
        printListNode(node1);
        printListNode(node4);
        long timeStart=System.currentTimeMillis();
        ListNode resultNode=new P2().addTwoNumbers(node1,node4);
        System.out.println("time consuming:"+(System.currentTimeMillis()-timeStart)+"ms");
        printListNode(resultNode);
        resultNode=new P2().addTwoNumbers(new ListNode(5),new ListNode(5));
        printListNode(resultNode);
    }
}
