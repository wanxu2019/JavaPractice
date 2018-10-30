package tests;

/**
 * Created by Json Wan on 2018/10/30.
 */
public class Apple1 {

    public static int remove(int[] nums,int val){
        int l=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=val){
                nums[l]=nums[i];
                l++;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        int[] nums=new int[]{1,2,0,3,4,0,5,7};
        System.out.println(remove(nums, 0));
        for (int x :
                nums) {
            System.out.print(x+" ");
        }
    }
}
