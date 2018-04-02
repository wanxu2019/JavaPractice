package leetcode;

/**
 * Created by Json Wan on 2018/1/22.
 * Given an array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

 Note:
 Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public class P260 {
    class Solution {
        public int[] singleNumber(int[] nums) {
            int diff = 0;
            for(int x:nums) {
                diff ^= x;
            }
            diff &= -diff;
            int num1=0,num2=0;
            for (int x: nums) {
                if ((x & diff)!=0) num1^=x;
                else num2^=x;
            }
            return new int[]{num1,num2};
        }
    }
    public static void main(String[] args) {
        int[] nums=new P260().new Solution().singleNumber(new int[]{1,2,1,2,3,4,5,4});
        System.out.println(nums[0]+","+nums[1]);
    }
}
