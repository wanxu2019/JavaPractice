package tests;

import java.util.ArrayList;

/**
 * Created by Json Wan on 2018/9/13.
 * Description:
 */
public class VivoTest1 {

    /**
     * int数组的二分查找
     *
     * @param arr
     * @param value
     * @return
     */
    public static int binarySearch(int[] arr, int value) {
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            //不用担心数组越界
            int midValue = arr[mid];
            if (midValue == value) return mid;
            else if (midValue < value) l = mid + 1;
            else r = mid - 1;
        }
        if (arr[l] > value) l--;
        //返回值值域：[-1,arr.length-1]
        return l;
    }

    /**
     * ArrayList的二分查找
     *
     * @param list
     * @param obj
     * @param <T>
     * @return
     */
    public static <T extends Comparable> int binarySearch(ArrayList<T> list, T obj) {
        int l = 0;
        int r = list.size() - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            T midValue = list.get(mid);
            if (midValue.compareTo(obj) == 0) {
                return mid;
            } else if (midValue.compareTo(obj) < 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        if (list.get(l).compareTo(obj) > 0) {
            l--;
        }
        return l;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(6);
        list.add(9);
        int []arr=new int[]{1,3,4,6,9};
        //正常值测试
        System.out.println(binarySearch(list, 1));
        System.out.println(binarySearch(list, 9));
        //边界测试，不存在测试
        System.out.println(binarySearch(list, 0));
        System.out.println(binarySearch(list, 5));
        System.out.println(binarySearch(list, 10));
        //数组二分查找测试
        System.out.println(binarySearch(arr, 1));
        System.out.println(binarySearch(arr, 9));
        //边界测试，不存在测试
        System.out.println(binarySearch(arr, 0));
        System.out.println(binarySearch(arr, 5));
        System.out.println(binarySearch(arr, 10));
    }
}
