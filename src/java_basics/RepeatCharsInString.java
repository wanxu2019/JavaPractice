package java_basics;

import java.util.*;

/**
 * Created by Json Wan on 2018/2/13.
 * Description:
 */
public class RepeatCharsInString {
    private int a=1;
    public static void main(String[] args) {
        String input="aavzcadfdsfsdhshgWasdfasdfdddaaa";
        RepeatCharsInString instance=new RepeatCharsInString();
        instance.a=23;
        System.out.println(instance.a);
        B b=new B();
        long beginTime=System.currentTimeMillis();
        b.doString(input);
        System.out.println("time consuming:"+(System.currentTimeMillis()-beginTime));
        beginTime=System.currentTimeMillis();
        b.doString2(input);
        System.out.println("time consuming:"+(System.currentTimeMillis()-beginTime));
    }
    //自己的解法，速度更快，但是需要耗费o(n)的空间
    public void doString2(String input){
        //1.将每个字符出现的次数统计到一张hashmap中
        HashMap<Character,Integer> map=new HashMap<Character,Integer>();
        ArrayList<Character> maxList=new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            Character ch=input.charAt(i);
            if(map.keySet().contains(ch)){
                map.put(ch,map.get(ch)+1);
            }else{
                map.put(ch,1);
            }
        }
        //2.找出最多的次数是多少次
        int maxRepeatTimes=0;
        for(int x : map.values()){
            if(x>maxRepeatTimes)
                maxRepeatTimes=x;
        }
        //3.根据出现最多的次数找出对应的各个字符
        for(Character x:map.keySet()){
            if(map.get(x)==maxRepeatTimes)
                System.out.print(x + " ");
        }
        System.out.println();
        System.out.println("max"+maxRepeatTimes);
    }
    //参考解法，也需要耗费o(n)的空间
    public void doString(String input){
        char[] chars=input.toCharArray();
        //1.遍历字符串所有字符，将字符的种类存入一个TreeSet，将所有字符存入一个list并排序
        ArrayList lists=new ArrayList();
        TreeSet set=new TreeSet();
        for (int i = 0; i < chars.length; i++) {
            lists.add(String.valueOf(chars[i]));
            set.add(String.valueOf(chars[i]));
        }
        System.out.println(set);
        Collections.sort(lists);
        System.out.println(lists);
        //2.重新生成字符排好序后的input字符串
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < lists.size(); i++) {
            sb.append(lists.get(i));
        }
        input=sb.toString();
        System.out.println(input);
        //3.根据Set中的字符在新的字符串中找长度最大的相同字符串
        int max=0;
        String maxString="";
        ArrayList maxList=new ArrayList();
        Iterator its=set.iterator();
        while(its.hasNext()){
            String os=(String)its.next();
            int begin=input.indexOf(os);
            int end=input.lastIndexOf(os);
            int value=end-begin+1;
            //4.找到一个更长的就记录长度，字符，并把对应字符放入maxList，若等长则只是放入字符到list
            if(value>max){
                max=value;
                maxString =os;
                maxList.add(os);
            }else if(value==max){
                maxList.add(os);
            }
        }
        //5.根据记录下的字符截取maxList右半部分作为结果
        int index=0;
        for (int i = 0; i < maxList.size(); i++) {
            if(maxList.get(i).equals(maxString)){
                index=i;
                break;
            }
        }
        System.out.println("max data");
        for (int i = index; i < maxList.size(); i++) {
            System.out.print(maxList.get(i) + " ");
        }
        System.out.println();
        System.out.println("max"+max);
    }
    static class B extends RepeatCharsInString{
        public B(){

        }
    }
}
