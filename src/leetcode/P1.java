package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Json Wan on 2017/10/7.
 * Description:
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 You may assume that each input would have exactly one solution, and you may not use the same element twice.
 Example:
 Given nums = [2, 7, 11, 15], target = 9,
 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].
 */
public class P1 {

    public int[] twoSum(int[] nums, int target) {

        return new int[]{};
    }
    //3ms,4ms的解法，看不懂的解法。。。
    public int[] ss3ms(int[] nums, int target) {
        int[] map = new int[16030];
        for (int i = 0; i < nums.length; i++) {
            int diff=target-nums[i]+5;
            if (diff < 0) continue;
            if(map[diff]>0){
                return new int[] {map[diff]-1,i};
            }
            map[nums[i]+5]=i+1;
        }
        throw null;
    }
    //5ms的解法，与自己稍有不同
    public int[] ss5ms(int[] nums, int target) {
        int[] copyNums = nums.clone();
        Arrays.sort(nums);
        int startIdx = 0;
        int endIdx = nums.length-1;
        while (startIdx < endIdx){
            if (nums[startIdx] + nums[endIdx] < target){
                startIdx++;
            } else if (nums[startIdx] + nums[endIdx] > target){
                endIdx--;
            } else {
                break;
            }
        }
        for (int i = 0; i < copyNums.length; i++){
            if (copyNums[i] == nums[startIdx]){
                startIdx = i;
                break;
            }
        }
        for (int i = copyNums.length-1; i >= 0; i--){
            if (copyNums[i] == nums[endIdx]){
                endIdx = i;
                break;
            }
        }
        return new int[]{startIdx,endIdx};
    }
    //6ms的解法，与自己的相似
    public int[] ss6ms(int[] nums, int target) {
        if (null != nums) {
            int[] copy = nums.clone();
            Arrays.sort(nums);
            int first = 0;
            int second = nums.length - 1;
            while (first < second) {
                if (nums[first] + nums[second] > target) {
                    //值过大，将后面的序号往前推
                    --second;
                } else if (nums[first] + nums[second] < target) {
                    ++first;
                } else {
                    //找到了
                    break;
                }
            }
            //看是找到了还是遍历完了
            if (target == nums[first] + nums[second]) {
                //找到原数组中的序号，注意数组中存在相同数值的情况
                int retFir = -1;
                int retSec = -1;
                for (int i = 0; i < copy.length; ++i) {
                    if (-1 == retFir && copy[i] == nums[first]) {
                        retFir = i;
                    }
                    if (-1 == retSec && copy[i] == nums[second]) {
                        if (retFir != i) {
                            retSec = i;
                        }
                    }
                    if (-1!=retFir && -1!=retSec) {
                        return new int[]{retFir, retSec};
                    }
                }
            }
        }
        return null;
    }
    //标准解法1：n2
    public int[] ss1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[] { i, j };
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    //标准解法2：n
    public int[] ss2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    //标准解法3：n
    public int[] ss3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    //最简单的方法，Cn2遍历即可
    public int[] solution1(int[] nums, int target) {
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target)
                    return new int[]{i,j};
            }
        }
        return null;
    }

    public int[] solution2(int[] nums, int target) {
        //1.先用最快的排序方法将数组排序
//        int[] numsCopy=Arrays.copyOf(nums,nums.length);
//        quickSort(nums, 0, nums.length - 1);
        //换成以下方式后效率高很多
        int[] numsCopy=nums.clone();
        Arrays.sort(nums);
        //2.双下标游走，将不适合组合的数字逐步外逼
        int lowIndex=0,highIndex=nums.length-1;
        int sum=nums[lowIndex]+nums[highIndex];
        while(sum!=target){
            if(sum>target)
                highIndex--;
            else
                lowIndex++;
            sum=nums[lowIndex]+nums[highIndex];
        }
        //查找原始低标
        for(int i=0;i<numsCopy.length;i++){
            if(nums[lowIndex]==numsCopy[i]){
                lowIndex=i;
                break;
            }
        }
        //查找原始高标
        for(int i=0;i<numsCopy.length;i++){
            if(nums[highIndex]==numsCopy[i]&&i!=lowIndex){
                highIndex=i;
                break;
            }
        }
        return new int[]{lowIndex,highIndex};
    }
    public static void quickSort(int[] nums,int start,int end){
        if(start<end){
            int center=partition(nums,start,end);
            quickSort(nums,start,center-1);
            quickSort(nums,center+1,end);
        }
    }
    public static int partition(int[] nums,int start,int end){
        //双下标游走，i控制小数部分的前沿下标，j遍历，并控制大数部分的前沿下标
        int x=nums[end];
        int i=start-1;
        for(int j=start;j<end;j++){
            if(nums[j]<=x){
                i++;
                if(i!=j){
                    nums[i]=nums[i]^nums[j];
                    nums[j]=nums[i]^nums[j];
                    nums[i]=nums[i]^nums[j];
                }
            }
        }
        if(nums[i+1]!=nums[end]) {
            nums[i + 1] = nums[i + 1] ^ nums[end];
            nums[end] = nums[i + 1] ^ nums[end];
            nums[i + 1] = nums[i + 1] ^ nums[end];
        }
        return i+1;
    }

    public static void main(String[] args) {
        int nums[]=new int[]{3,3};
        int target=6;
        long timeStart=System.currentTimeMillis();
        int[] result=new P1().ss6ms(nums,target);
        System.out.println("time consuming:"+(System.currentTimeMillis()-timeStart)+"ms");
        System.out.println(result[0]+","+result[1]);
    }
}
