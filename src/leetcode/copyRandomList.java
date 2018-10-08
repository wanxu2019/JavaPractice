package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Json Wan on 2018/10/7.
 * Description:
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 Return a deep copy of the list.
 思路：先一次遍历复制主链，通过Map记录旧链每一个节点对应的位置，通过List记录新链每一个节点，再一次遍历根据位置在新链上建立Random连接。
 */
public class copyRandomList {
    class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }

    public RandomListNode copyRandomList(RandomListNode head) {
        //先复制主链，记录每一个节点的位置
        Map<RandomListNode,Integer> map=new HashMap<>();
        //记录新表每一个节点
        List<RandomListNode> list=new ArrayList<>();
        int i=0;
        RandomListNode pHead=head;
        RandomListNode newHead=new RandomListNode(0);
        RandomListNode nHead=newHead;
        while(head!=null){
            map.put(head,i);
            newHead.next=new RandomListNode(head.label);
            newHead=newHead.next;
            list.add(newHead);
            i++;
            head=head.next;
        }
        i=0;
        nHead=nHead.next;
        newHead=nHead;
        while(pHead!=null){
            if(pHead.random!=null)
                nHead.random=list.get(map.get(pHead.random));
            pHead=pHead.next;
            nHead=nHead.next;
        }
        return newHead;
    }

    public static void main(String[] args) {

    }
}
