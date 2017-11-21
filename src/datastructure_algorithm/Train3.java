package datastructure_algorithm;

import java.util.Iterator;

/**
 * Created by Json Wan on 2017/11/16.
 * Description:
 */
public class Train3 {
    public static void main(String[] args) {
        MyLinkedList<String> list=new MyLinkedList();
        list.add("aaaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddda");
        System.out.println(list.size());
        Iterator iterator=list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
