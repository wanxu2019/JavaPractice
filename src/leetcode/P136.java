package leetcode;

/**
 * Created by Json Wan on 2018/1/22.
 * Description:
 * Given an array of integers, every element appears twice except for one. Find that single one.
 Note:
 Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public class P136 {
    class Solution {
        public int singleNumber(int[] nums) {
            int diff = 0;
            for(int x:nums) {
                diff ^= x;
            }
            return diff;
        }
    }

    public static void main(String[] args) {
        System.out.println(new P136().new Solution().singleNumber(new int[]{1,2,1,2,3,4,5,4,3}));
    }
}
