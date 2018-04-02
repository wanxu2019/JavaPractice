package leetcode;

/**
 * Created by Json Wan on 2018/1/22.
 * Given an array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

 Note:
 Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public class P137 {
    class Solution {
        public int bestSingleNumber(int[] nums) {
            // 1. 存入ones
            // 2. 清空ones， 存入twos
            // 3. 清空twos
            int ones = 0, twos = 0;
            for(int i = 0; i < nums.length; i++){
                ones = (ones ^ nums[i]) & ~twos;
                twos = (twos ^ nums[i]) & ~ones;
            }
            return ones;
        }

        /**
         * int 数据共有32位，可以用32变量存储 这 N 个元素中各个二进制位上  1  出现的次数，最后 在进行 模三 操作，如果为1，那说明这一位是要找元素二进制表示中为 1 的那一位
         * @param nums
         * @return
         */
        public int singleNumber2(int[] nums) {
            int bitNum[]=new int[32];
            int res=0;
            for(int i=0; i<32; i++){
                for(int num:nums) {
                    bitNum[i] += (num >> i) & 1;
                }
                res|=(bitNum[i]%3)<<i;
            }
            return res;
        }
        /**
         * 利用三个变量分别保存各个二进制位上 1 出现一次、两次、三次的分布情况，最后只需返回变量一就行了。
         * @param nums
         * @return
         */
        public int singleNumber(int[] nums) {
            int one=0, two=0, three=0;
            for(int i=0; i<nums.length; i++){
                two |= one&nums[i];
                one^=nums[i];
                three=one&two;
                one&= ~three;
                two&= ~three;
            }
            return one;
        }
    }
    public static void main(String[] args) {
        System.out.println(~5);
        System.out.println(~-5);
        System.out.println(~0);
        System.out.println(~-0);
        System.out.println(new P137().new Solution().singleNumber(new int[]{1,2,3,4,4,3,2,1,1,2,3,4,5}));
    }
}
