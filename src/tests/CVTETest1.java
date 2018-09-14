package tests;

/**
 * Created by Json Wan on 2018/9/14.
 * Description:研究动态规划解决最长子序列的问题
 * 动规擅长的问题特征：
 * （1）最优子结构；
 * （2）重叠子问题：通过表格记录重叠子问题的解来优化递归解法中的重复求解；
 */
public class CVTETest1 {

    //求解str1 和 str2 的最长公共子序列
    public static int LCS(String str1, String str2){
        int[][] c = new int[str1.length() + 1][str2.length() + 1];
        for(int row = 0; row <= str1.length(); row++)
            c[row][0] = 0;
        for(int column = 0; column <= str2.length(); column++)
            c[0][column] = 0;

        for(int i = 1; i <= str1.length(); i++)
            for(int j = 1; j <= str2.length(); j++)
            {
                if(str1.charAt(i-1) == str2.charAt(j-1))
                    c[i][j] = c[i-1][j-1] + 1;
                else if(c[i][j-1] > c[i-1][j])
                    c[i][j] = c[i][j-1];
                else
                    c[i][j] = c[i-1][j];
            }
        return c[str1.length()][str2.length()];
    }


    //最长公共子串—*—！
    //注意区分最长公共子串与最长公共子序列
    public static int maxSubSequence(String s1,String s2){
        int l1=s1.length();
        int l2=s2.length();
        int maxCount=0;
        int matrix[][]=new int[l1+1][l2+1];
        for(int i=1;i<l1+1;i++){
            for(int j=1;j<l2+1;j++){
                Character ch1=s1.charAt(i-1);
                Character ch2=s2.charAt(j-1);
                if(ch1.equals(ch2)){
                    matrix[i][j]=1+matrix[i-1][j-1];
                }
                //start
                //加上这一段就是最大公共子序列
                //不加就是最大公共子串
                else{
                    if(matrix[i-1][j]>matrix[i][j-1]){
                        matrix[i][j]=matrix[i-1][j];
                    }else{
                        matrix[i][j]=matrix[i][j-1];
                    }
                }
                //end
                if(matrix[i][j]>maxCount)
                    maxCount=matrix[i][j];
            }
        }
        return maxCount;
    }

    public static void main(String[] args) {
        System.out.println(maxSubSequence("BDCABA","ABCBDAB"));
        System.out.println(LCS("BDCABA", "ABCBDAB"));
    }
}
