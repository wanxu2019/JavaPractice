package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Json Wan on 2018/9/24.
 * Description:
 */
public class wordBreakI {

    public static boolean wordBreak(String s, Set<String> dict) {
        if(s.length()==0)
            return true;
        for(int i=1;i<=s.length();i++){
            String subStr=s.substring(0,i);
            if(dict.contains(subStr)){
                if(wordBreak(s.substring(i),dict)){
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 方法二：
     * 状态转移方程：
     * f(i) 表示s[0,i]是否可以分词
     * f(i) = f(j) && f(j+1,i); 0 <= j < i;
     *
     */
    public static boolean bestWordBreak(String s,Set<String> dict){
        //动态规划解法
        int len = s.length();
        boolean[] arrays = new boolean[len+1];
        arrays[0] = true;
        for (int i = 1; i <= len; ++i){
            for (int j = 0; j < i; ++j){
                if (arrays[j] && dict.contains(s.substring(j, i))){
                    arrays[i] = true;
                    break;
                }
            }
        }
        return arrays[len];
    }

    public static void main(String[] args) {
        Set<String> set=new HashSet<>();
//        set.add("cata");
//        set.add("catsa");
//        set.add("and");
//        set.add("sand");
//        set.add("dog");
        set.add("leet");
        set.add("code");
        System.out.println(wordBreak("leetcodee", set));
    }
}
