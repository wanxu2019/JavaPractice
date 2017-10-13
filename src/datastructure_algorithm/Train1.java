package datastructure_algorithm;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Json Wan on 2017/10/11.
 * Description:
 */
public class Train1 {

    /**
     * 返回n的二进制表示中1的个数
     * @param n
     * @return
     */
    public static int subject5(int n){
        if(n==0){
            return 0;
        }
        else if(n==1){
            return 1;
        }else{
            return subject5(n/2)+n%2;
        }
    }

    /**
     * 显示字符的所有排列
     * @param str
     */
    public static void subject6(String str){
        permute(str.toCharArray(),0,str.length()-1);
    }
    public static void permute(char[] str,int low,int high){
        ArrayList<String> list=calcAllArrangeMents(str,0,str.length-1);
        for(String e:list){
            System.out.print(e + " ");
        }
    }
    public static ArrayList<String> calcAllArrangeMents(char[] str,int low,int high){
        ArrayList<String> list=new ArrayList<>();
        if(low==high){
            list.add(""+str[low]);
        }else{
            for(int i=low;i<=high;i++){
                char[] strCopy= Arrays.copyOf(str,str.length);
                char temp=strCopy[i];
                strCopy[i]=strCopy[low];
                strCopy[low]=temp;
                ArrayList<String> subList=calcAllArrangeMents(strCopy, low + 1, high);
                for(String e:subList){
                    if(e.length()==str.length-1){
                        System.out.println(str[i]+e);
                    }
                    list.add(str[i]+e);
                }
            }
        }
        return list;
    }

    public static int subject10(int n){
        if(n==0)return 1;
        else if(n==1)return 2;
        else{
            return (subject10(n-1)*2)%5;
        }
    }

    public static void show(Object msg){
        System.out.println(msg);
    }
    public static void main(String[] args) {

        show(subject5(7));
        show(subject5(9));
        show(subject5(1000));

        subject6("abc");

        show(subject10(1000));
    }
}
