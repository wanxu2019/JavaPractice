package tests;

import java.util.*;

/**
 * Created by Json Wan on 2018/10/10.
 */
public class MoMo2 {
    public static void main(String[] args) {
        MoMo2 instance=new MoMo2();
        Scanner scanner=new Scanner(System.in);
        String srcString=scanner.next();
        char[] arr=srcString.toCharArray();
        Arrays.sort(arr);
        srcString=new String(arr);
        instance.listPermutations(srcString);
    }
    public void listPermutations(String str){
        ArrayList<Character> list=new ArrayList<>();
        for(char x:str.toCharArray()){
            list.add(x);
        }
        listArrayList("",list);
    }
    public void listArrayList(String prefix,ArrayList<Character> list){
        Set<Character> set=new TreeSet<>();
        if(list.size()==0){
            if(prefix.length()>0)
                System.out.println(prefix);
        }
        for(char x:list){
            if(!set.contains(x)){
                set.add(x);
                ArrayList<Character> subList=(ArrayList<Character>)list.clone();
                subList.remove(new Character(x));
                listArrayList(prefix+x,subList);
            }
        }
    }
}
