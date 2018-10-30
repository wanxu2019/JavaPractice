package tests;

import java.util.Set;

/**
 * Created by Json Wan on 2018/9/27.
 * Description:
 */
public class BeautyUnion {
    /**
     * 方法二：
     * 状态转移方程：
     * f(i) 表示s[0,i]是否可以分词
     * f(i) = f(j) && f(j+1,i); 0 <= j < i;
     *
     */
    public static boolean bestWordBreak(String string,Set<String> dict){
        //动态规划解法
        int len = string.length();
        boolean[] arrays = new boolean[len+1];
        arrays[0] = true;
        for (int i = 1; i <= len; ++i){
            for (int j = 0; j < i; ++j){
                if (arrays[j] && dict.contains(string.substring(j, i))){
                    arrays[i] = true;
                    break;
                }
            }
        }
        return arrays[len];
    }

    public static void main(String[] args) {

    }
}
