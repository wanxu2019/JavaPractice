package leetcode;

/**
 * Created by Json Wan on 2018/10/7.
 * Description:
 * 题目描述
 There are N children standing in a line. Each child is assigned a rating value.
 You are giving candies to these children subjected to the following requirements:
 Each child must have at least one candy.
 Children with a higher rating get more candies than their neighbors.
 What is the minimum candies you must give?
 思路：从左往右扫描，通过+1保证分数高的孩子分得的糖果比相邻孩子多，再从右往左扫描，将不够的再加一加即可。
 */
public class candy {

    public static int candy(int[] ratings) {
        int candies[]=new int[ratings.length];
        candies[0]=1;
        for(int i=0;i<ratings.length-1;i++){
            if(ratings[i+1]>ratings[i]){
                candies[i+1]=candies[i]+1;
            }else{
                candies[i+1]=1;
            }
        }
        for(int i=ratings.length-1;i>0;i--){
            if(ratings[i-1]>ratings[i]){
                if(candies[i-1]<=candies[i]){
                    candies[i-1]=candies[i]+1;
                }
            }
        }
        int result=0;
        for(int i=0;i<candies.length;i++){
            result+=candies[i];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(candy(new int[]{2,3,5,2,1}));
    }
}
