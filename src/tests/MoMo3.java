package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Json Wan on 2018/10/10.
 * description：题目描述：
 有长度为n的整数数组，从这 n 个整数中按照顺序选取m个，要求相邻两个整数在原数组中的位置相差不超过i，使得这 m 个整数的乘积最大，请返回最大乘积。
 输入
 第一行 整数n （1<n<20）
 第二行 按顺序的n个整数   整数的范围在-50 到 50之间
 第三行 整数i和m  （1<i<20, 1<m<50)
 输出
 返回乘积
 */
public class MoMo3 {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int arr[]=new int[n];
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=0;i<n;i++){
            arr[i]=scanner.nextInt();
            list.add(arr[i]);
        }
        int i=scanner.nextInt();
        int m=scanner.nextInt();
        ArrayList<Integer> copyList= (ArrayList<Integer>) list.clone();
        Arrays.sort(arr);
        for(int k=0;k<n-m;k++){
            list.remove(new Integer(arr[k]));
        }
        int result=1;
        for(int x:list){
            result*=x;
        }
        System.out.println(result);
    }
}
