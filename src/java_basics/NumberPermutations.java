package java_basics;

import java.util.*;
import java_basics_2.MySecondObject;

/**
 * Created by Json Wan on 2018/2/19.
 * Description:
 */
public class NumberPermutations {
    public static void main(String[] args) {
        NumberPermutations instance=new NumberPermutations();
        instance.listPermutations("1223");
        try {
            instance.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        ArrayList obj=new ArrayList<Integer>();
        obj.clone();
        MyObject myObject=new MyObject();
        //编译不通过的语句
//        myObject.c=1;
        myObject.d=1;
//        new MySecondObject.One.Two();
        new MySecondObject.One();
    }
    public void listPermutations(String str){
        ArrayList<Character> list=new ArrayList<>();
        for(char x:str.toCharArray()){
            list.add(x);
        }
        listArrayList("",list,list.size());
    }
    public void listArrayList(String prefix,ArrayList<Character> list,int length){
        Set<Character> set=new TreeSet<>();
//        System.out.print("" + prefix);
        if(list.size()==0){
            System.out.println(prefix);
        }
        for(char x:list){
            if(!set.contains(x)){
                set.add(x);
                ArrayList<Character> subList=(ArrayList<Character>)list.clone();
                subList.remove(new Character(x));
                listArrayList(prefix+x,subList,length);
            }
        }
    }
}
