package tests;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Json Wan on 2018/9/18.
 * Description:
 */
public class SFTest2 {

    public static void main(String[] args) {
        //输入读取
        Scanner scanner = new Scanner(System.in);
        int n=Integer.parseInt(scanner.nextLine());
        String arr1Str=scanner.nextLine();
        String sArr1[]=arr1Str.split(" ");
        int[] arr1=new int[sArr1.length];
        for(int i=0;i<sArr1.length;i++) {
            arr1[i] = Integer.parseInt(sArr1[i]);
        }
        String arr2Str=scanner.nextLine();
        String sArr2[]=arr2Str.split(" ");
        HashMap<Integer,Integer> map2=new HashMap<>();
        for(int i=0;i<sArr2.length;i++) {
            map2.put(Integer.parseInt(sArr2[i]), i);
        }
        int count=0;
        for(int i=0;i<arr1.length-1;i++)
            for(int j=i+1;j<arr1.length;j++){
                int i2=map2.get(arr1[i]);
                int j2=map2.get(arr1[j]);
                if(j2<i2){
                    count++;
                }
            }
        System.out.println(count);
    }
}
