package tests;

/**
 * Created by Json Wan on 2018/3/15.
 * Description:2018阿里Java研发岗实习笔试题目
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Optimizition {
    /** 请完成下面这个函数，实现题目要求的功能 **/
    /** 当然，你也可以不按照这个模板来作答，完全按照自己的想法来 ^-^  **/
    public static void main(String[] args) {

        List<Integer> order = new ArrayList<Integer>();
        Map<String, List<Integer>> boms = new HashMap<String, List<Integer>>();

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        Integer n = Integer.parseInt(line.split(",")[0]);
        Integer m = Integer.parseInt(line.split(",")[1]);

        line = in.nextLine();
        String[] itemCnt = line.split(",");
        for(int i = 0; i < n ; i++){
            order.add(Integer.parseInt(itemCnt[i]));
        }

        for(int i = 0; i < m; i++){
            line = in.nextLine();
            String[] bomInput = line.split(",");
            List<Integer> bomDetail = new ArrayList<Integer>();

            for(int j = 1; j <= n; j++ ){
                bomDetail.add(Integer.parseInt(bomInput[j]));
            }
            boms.put(bomInput[0], bomDetail);
        }
        in.close();

        Map<String, Integer> res = resolve(order, boms);

        System.out.println("match result:");
        for(String key : res.keySet()){
            System.out.println(key+"*"+res.get(key));
        }
    }

    // write your code here
    public static Map<String, Integer> resolve(List<Integer> order, Map<String, List<Integer>> boms) {
        HashMap<String,Integer> resultMap=new HashMap<String,Integer>();
        List<Integer> solutions=new ArrayList<Integer>();
        //1.求出每个bom数量的可行域
        List<Integer> solutionsRange=new ArrayList<Integer>();
        for(int i=0;i<boms.keySet().toArray().length;i++){
            List<Integer> bom=boms.get("bom"+(i+1));
            solutionsRange.add(Integer.MAX_VALUE);
            for(Integer x:bom){
                if(x!=0){
                    if(order.get(i)/x<solutionsRange.get(i)){
                        solutionsRange.set(i,order.get(i)/x);
                    }
                }
            }
        }
        //2.遍历可行域找最优解

        return resultMap;
    }
}