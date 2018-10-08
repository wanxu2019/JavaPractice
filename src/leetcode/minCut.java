package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Json Wan on 2018/10/8.
 * 题目描述
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * For example, given s ="aab",
 * Return1since the palindrome partitioning["aa","b"]could be produced using 1 cut.
 * 题意理解：用最少的次数将字符串分割为回文串
 * 思路：拆分为子问题解决；
 */
//他人解法
class Solution {
    private String s;
    private int f[][] = new int[1000][1000];

    public int minCut(String s) {
        this.s = s;
//先求解小段的子序列
        for (int t = 0; t <= s.length(); t++) {
            for (int i = 0, j = t; j < s.length(); i++, j++) {
                f[i][j] = compCut(i, j);
            }
        }
        return f[0][s.length() - 1];
    }

    // 状态转移方程的实现
    public int compCut(int i, int j) {
        if (isPalindrome(i, j)) return 0;
        int min = s.length();
        for (int p = i; p < j; p++) {
            int a = f[i][p];
            int b = f[p + 1][j];
            a = a + b + 1;
            min = min < a ? min : a;
        }
        return min;
    }

    //判断是否回文串
    public boolean isPalindrome(int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}

public class minCut {

    /**
     * 回文的最小分割数
     * 1.dp[i]表示当前i到len-1这段的最小分割数
     * 2.dp[i]=min{dp[j+1]+1}（i=<j<len）其中str[i..j]必须是回文、
     * 3.p[i][j]=true表示str[i..j]是回文
     * 4.p[i][j]=s.charAt(i)==s.charAt(j) && (j-i<2||p[i+1][j-1])
     */
    public static int minCutBest(String s) {
        int[] dp = new int[s.length() + 1];
        boolean[][] p = new boolean[s.length()][s.length()];
        dp[s.length()] = -1;//确保dp[s.length()-1]=0
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || p[i + 1][j - 1])) {
                    p[i][j] = true;
                    dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                }
            }
        }
        return dp[0];
    }

    public static int minCut(String s) {
        int l = s.length();
        if (l <= 1) {
            return 0;
        } else if (l == 2) {
            if (s.charAt(0) == s.charAt(1))
                return 0;
            else
                return 1;
        } else {
            return minCutWithLastIndex(s).get(0);
        }
    }

    public static List<Integer> minCutWithLastIndex(String s) {
        List<Integer> result = new ArrayList<>();
        int l = s.length();
        if (l <= 1) {
            result.add(0);
            result.add(-1);
            result.add(1);
            return result;
        } else if (l == 2) {
            if (s.charAt(0) == s.charAt(1)) {
                result.add(0);
                result.add(-1);
                result.add(1);
                return result;
            } else {
                result.add(1);
                result.add(1);
                result.add(0);
                return result;
            }
        } else {
            String subS = s.substring(0, l - 1);
            List<Integer> subResult = minCutWithLastIndex(subS);
            int subNum = subResult.get(0);
            int subIndex = subResult.get(1);
            int subAllSame = subResult.get(2);
            if (subNum == 0) {
                if (subAllSame == 1) {
                    //如果最后一个字符与整个序列相同
                    if (subS.charAt(0) == s.charAt(l - 1)) {
                        result.add(0);
                        result.add(-1);
                        result.add(1);
                        return result;
                    } else {
                        result.add(1);
                        result.add(l - 1);
                        result.add(0);
                        return result;
                    }
                } else {
                    result.add(1);
                    result.add(l - 1);
                    result.add(0);
                    return result;
                }
            } else {
                String subS2 = subS.substring(subIndex, l - 1);
                char ch = subS2.charAt(0);
                boolean flag = true;
                for (int i = 0; i < subS2.length(); i++) {
                    if (subS2.charAt(i) != ch) {
                        flag = false;
                        break;
                    }
                }
                if (flag && s.charAt(l - 1) == ch) {
                    return subResult;
                } else {
                    //两种方案
                    //1.
                    if (s.charAt(l - 1) == s.charAt(subIndex - 1)) {
                        String subS3 = s.substring(0, subIndex - 1);
                        if (subS3.equals("")) {
                            result.add(0);
                            result.add(-1);
                            result.add(0);
                            return result;
                        }
                        List<Integer> subResult3 = minCutWithLastIndex(subS3);
                        int subNum3 = subResult3.get(0);
                        int subIndex3 = subResult3.get(1);
                        int subAllSame3 = subResult3.get(2);
                        if (subNum3 + 1 < subNum + 1) {
                            result.add(subNum3 + 1);
                            result.add(subIndex - 1);
                            result.add(0);
                            return result;
                        } else {
                            result.add(subNum + 1);
                            result.add(l - 1);
                            result.add(0);
                            return result;
                        }
                    } else {
                        result.add(subNum + 1);
                        result.add(l - 1);
                        result.add(0);
                        return result;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String s="aabcdfasdjflasdkjf;lskadjflkjsadlkfjlksdjlkjfasdflkasjdlkfjasl;dkjf;lksajdlgkjdafl;kjg;lkdfjalfkjasdlkjfl;ksdajfl;kjasld;kjf;laksjdfl;kjasd;lkjf;laksjdf;lkjasdl;kfj;laskdjfl;kjsadl;fkjasdl;kjf;lkjasd;lkfjsl;dakjfl;kjsdalfkjsdlkfjl;ksadjfl;kajsdl;kfj;sdlkjflkasdjflkjsdal;kfjls;dakjf;lkjsdalkfjsdlakjflkjsda;lfkj;sdalf;lksdjflkjsadlkfjsdlkjfl;sadjflsdkfjlsdjksdfjklfjsldkjfdklsfjlksdjflsdkjflksfkladlf;asdjflsdkfjkljkaabcdfasdjflasdkjf;lskadjflkjsadlkfjlksdjlkjfasdflkasjdlkfjasl;dkjf;lksajdlgkjdafl;kjg;lkdfjalfkjasdlkjfl;ksdajfl;kjasld;kjf;laksjdfl;kjasd;lkjf;laksjdf;lkjasdl;kfj;laskdjfl;kjsadl;fkjasdl;kjf;lkjasd;lkfjsl;dakjfl;kjsdalfkjsdlkfjl;ksadjfl;kajsdl;kfj;sdlkjflkasdjflkjsdal;kfjls;dakjf;lkjsdalkfjsdlakjflkjsda;lfkj;sdalf;lksdjflkjsadlkfjsdlkjfl;sadjflsdkfjlsdjksdfjklfjsldkjfdklsfjlksdjflsdkjflksfkladlf;asdjflsdkfjkljkaabcdfasdjflasdkjf;lskadjflkjsadlkfjlksdjlkjfasdflkasjdlkfjasl;dkjf;lksajdlgkjdafl;kjg;lkdfjalfkjasdlkjfl;ksdajfl;kjasld;kjf;laksjdfl;kjasd;lkjf;laksjdf;lkjasdl;kfj;laskdjfl;kjsadl;fkjasdl;kjf;lkjasd;lkfjsl;dakjfl;kjsdalfkjsdlkfjl;ksadjfl;kajsdl;kfj;sdlkjflkasdjflkjsdal;kfjls;dakjf;lkjsdalkfjsdlakjflkjsda;lfkj;sdalf;lksdjflkjsadlkfjsdlkjfl;sadjflsdkfjlsdjksdfjklfjsldkjfdklsfjlksdjflsdkjflksfkladlf;asdjflsdkfjkljkaabcdfasdjflasdkjf;lskadjflkjsadlkfjlksdjlkjfasdflkasjdlkfjasl;dkjf;lksajdlgkjdafl;kjg;lkdfjalfkjasdlkjfl;ksdajfl;kjasld;kjf;laksjdfl;kjasd;lkjf;laksjdf;lkjasdl;kfj;laskdjfl;kjsadl;fkjasdl;kjf;lkjasd;lkfjsl;dakjfl;kjsdalfkjsdlkfjl;ksadjfl;kajsdl;kfj;sdlkjflkasdjflkjsdal;kfjls;dakjf;lkjsdalkfjsdlakjflkjsda;lfkj;sdalf;lksdjflkjsadlkfjsdlkjfl;sadjflsdkfjlsdjksdfjklfjsldkjfdklsfjlksdjflsdkjflksfkladlf;asdjflsdkfjkljkaabcdfasdjflasdkjf;lskadjflkjsadlkfjlksdjlkjfasdflkasjdlkfjasl;dkjf;lksajdlgkjdafl;kjg;lkdfjalfkjasdlkjfl;ksdajfl;kjasld;kjf;laksjdfl;kjasd;lkjf;laksjdf;lkjasdl;kfj;laskdjfl;kjsadl;fkjasdl;kjf;lkjasd;lkfjsl;dakjfl;kjsdalfkjsdlkfjl;ksadjfl;kajsdl;kfj;sdlkjflkasdjflkjsdal;kfjls;dakjf;lkjsdalkfjsdlakjflkjsda;lfkj;sdalf;lksdjflkjsadlkfjsdlkjfl;sadjflsdkfjlsdjksdfjklfjsldkjfdklsfjlksdjflsdkjflksfkladlf;asdjflsdkfjkljkaabcdfasdjflasdkjf;lskadjflkjsadlkfjlksdjlkjfasdflkasjdlkfjasl;dkjf;lksajdlgkjdafl;kjg;lkdfjalfkjasdlkjfl;ksdajfl;kjasld;kjf;laksjdfl;kjasd;lkjf;laksjdf;lkjasdl;kfj;laskdjfl;kjsadl;fkjasdl;kjf;lkjasd;lkfjsl;dakjfl;kjsdalfkjsdlkfjl;ksadjfl;kajsdl;kfj;sdlkjflkasdjflkjsdal;kfjls;dakjf;lkjsdalkfjsdlakjflkjsda;lfkj;sdalf;lksdjflkjsadlkfjsdlkjfl;sadjflsdkfjlsdjksdfjklfjsldkjfdklsfjlksdjflsdkjflksfkladlf;asdjflsdkfjkljkaabcdfasdjflasdkjf;lskadjflkjsadlkfjlksdjlkjfasdflkasjdlkfjasl;dkjf;lksajdlgkjdafl;kjg;lkdfjalfkjasdlkjfl;ksdajfl;kjasld;kjf;laksjdfl;kjasd;lkjf;laksjdf;lkjasdl;kfj;laskdjfl;kjsadl;fkjasdl;kjf;lkjasd;lkfjsl;dakjfl;kjsdalfkjsdlkfjl;ksadjfl;kajsdl;kfj;sdlkjflkasdjflkjsdal;kfjls;dakjf;lkjsdalkfjsdlakjflkjsda;lfkj;sdalf;lksdjflkjsadlkfjsdlkjfl;sadjflsdkfjlsdjksdfjklfjsldkjfdklsfjlksdjflsdkjflksfkladlf;asdjflsdkfjkljkaabcdfasdjflasdkjf;lskadjflkjsadlkfjlksdjlkjfasdflkasjdlkfjasl;dkjf;lksajdlgkjdafl;kjg;lkdfjalfkjasdlkjfl;ksdajfl;kjasld;kjf;laksjdfl;kjasd;lkjf;laksjdf;lkjasdl;kfj;laskdjfl;kjsadl;fkjasdl;kjf;lkjasd;lkfjsl;dakjfl;kjsdalfkjsdlkfjl;ksadjfl;kajsdl;kfj;sdlkjflkasdjflkjsdal;kfjls";
        System.out.println(minCut(""));
        System.out.println(minCut("aabcd"));
        System.out.println(minCut("efe"));
        System.out.println(minCut("efee"));
        System.out.println(minCutBest("aabcd"));
        System.out.println(minCut(s));
        System.out.println(minCutBest(s));
    }
}
