package datastructure_algorithm;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Json Wan on 2017/11/21.
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {

    private int theSize;
    private int modCount=0;
    //头节点
    private Node<AnyType> beginMarker;
    //尾节点
    private Node<AnyType> endMarker;

    private static class Node<AnyType>{
        public AnyType data;
        public Node<AnyType> prev;
        public Node<AnyType> next;

        public Node(AnyType d,Node<AnyType> p,Node<AnyType> n){
            data=d;
            prev=p;
            next=n;
        }
    }
    public MyLinkedList(){
        clear();
    }
    public void clear(){
        beginMarker=new Node<AnyType>(null,null,null);
        endMarker=new Node<AnyType>(null,beginMarker,null);
        beginMarker.next=endMarker;

        theSize=0;
        modCount++;
    }
    public int size(){
        return theSize;
    }
    public boolean isEmpty(){
        return size()==0;
    }
    public boolean add(AnyType x){
        add(size(),x);
        return true;
    }

    /**
     * 在节点p前增加一个数据x
     * @param p
     * @param x
     */
    private void addBefore(Node<AnyType> p,AnyType x){
        p.prev=p.prev.next=new Node<AnyType>(x,p.prev,p);
        theSize++;
        modCount++;
    }

    /**
     * 删除节点p
     * @param p
     * @return
     */
    private AnyType remove(Node<AnyType> p){
        p.prev.next=p.next;
        p.next.prev=p.prev;
        theSize--;
        modCount++;
        return p.data;
    }

    /**
     * 根据列表中的下标找到节点
     * @param idx
     * @return
     */
    private Node<AnyType> getNode(int idx){
        Node<AnyType> p;
        if(idx<0||idx>size())
            throw new IndexOutOfBoundsException();
        if(idx<size()/2){
            p=beginMarker.next;
            for(int i=0;i<idx;i++)
                p=p.next;
        }else{
            p=endMarker;
            for(int i=size();i>idx;i--)
                p=p.prev;
        }
        return p;
    }

    public void add(int idx,AnyType x){
        addBefore(getNode(idx),x);
    }
    public AnyType get(int idx){
        return getNode(idx).data;
    }
    public AnyType set(int idx,AnyType newVal){
        Node<AnyType> p=getNode(idx);
        AnyType oldVal=p.data;
        p.data=newVal;
        return oldVal;
    }
    public AnyType remove(int idx){
        return remove(getNode(idx));
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }
    private class LinkedListIterator implements Iterator<AnyType>{

        private Node<AnyType> current=beginMarker.next;
        private int expectedModCount=modCount;
        private boolean okToRemove=false;

        @Override
        public boolean hasNext() {
            return current!=endMarker;
        }

        @Override
        public AnyType next() {
            if(modCount!=expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if(!hasNext())
                throw new NoSuchElementException();
            AnyType nextItem=current.data;
            //向后推进
            current=current.next;
            okToRemove=true;
            return nextItem;
        }
        public void remove(){
            if(modCount!=expectedModCount)
                throw new ConcurrentModificationException();
            if(!okToRemove)
                throw new IllegalStateException();
            MyLinkedList.this.remove(current.prev);
            okToRemove=false;
            expectedModCount++;
        }
    }
}
