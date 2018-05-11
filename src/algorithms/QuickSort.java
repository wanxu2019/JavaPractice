package algorithms;

/**
 * Created by Json Wan on 2017/10/7.
 * Description:
 */
public class QuickSort {
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
    public static int quicksort2(int[] nums,int start,int end){
        //
        if(start>=end)
            return start;
        int x=nums[start];
        int l=start,r=end;
        while(l<r){
            while(l<r && nums[r]>=x)
                r--;
            if(l<r){
                nums[l]=nums[r];
                l++;
            }
            while(l<r && nums[l]<x)
                l++;
            if(l<r){
                nums[r]=nums[l];
                r--;
            }
        }
        nums[l]=x;
        quicksort2(nums,start,l-1);
        quicksort2(nums,l+1,end);
        return l;
    }
    public static void main(String[] args) {
        int nums[]=new int[]{1,3,4,5,2,6,23,3,5,6,343,343,55,6,6,676};
//        quickSort(nums,0,nums.length-1);
        quicksort2(nums,0,nums.length-1);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
