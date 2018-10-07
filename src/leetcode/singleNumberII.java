package leetcode;

/**
 * Created by Json Wan on 2018/10/7.
 * Description:
 * 题目描述
 Given an array of integers, every element appears three times except for one. Find that single one.
 Note:
 Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 思路：如果没有单个数字，所有数字加起来一定是3的倍数，而对于每一位也是这样，因此可将每一位加起来求单数字的那一位；
 扩展：如果不同的数字有两个，则先将两个数字分到不同的两组当中（根据某一位的和对3取模后为单数的那一位来区分），再分别求出，若两个数字相同则再循环一次计算位时直接除以2即可。
 */
public class singleNumberII {

    public static int singleNumber(int[] A) {
        int count[]=new int[32];
        int result=0;
        for(int i=0;i<32;i++){
            for(int j=0;j<A.length;j++){
                count[i]+=(A[j]>>i)&1;
            }
            count[i]=count[i]%3;
            result|=(count[i]<<i);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{1,1,1,3,2,2,2,0,0,0}));
    }
}
