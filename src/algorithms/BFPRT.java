package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Json Wan on 2018/5/11.
 */
public class BFPRT {
    public int find(int[] a, int j) {
        System.out.println("================================");
        System.out.println("待查找的数组 ： ");
        print(a);
        System.out.println("找出第 " + j + "小的数");
        if (a.length / 5 <= 1) {
            Arrays.sort(a);
            return a[j];
        }

        int x = getCenterForArray(a);

        // 4. 用x来分割数组，设小于等于x的个数为k，大于x的个数即为n-k。
        List<Integer> left = new ArrayList<Integer>();
        List<Integer> right = new ArrayList<Integer>();

        for (int i = 0; i < a.length; i++) {
            if (a[i] <= x) {
                left.add(a[i]);
            } else {
                right.add(a[i]);
            }
        }
        // 5. 若i==k，返回x；若i<k，在小于x的元素中递归查找第i小的元素；若i>k，在大于x的元素中递归查找第i-k 小的元素。
        int K = left.size();
        if (j == K) {
            return x;
        } else if (j < K) {
            int[] leftArray = new int[left.size()];
            for (int i = 0; i < left.size(); i++) {
                leftArray[i] = left.get(i);
            }
            return find(leftArray, j);
        } else {
            int[] rightArray = new int[right.size()];
            for (int i = 0; i < right.size(); i++) {
                rightArray[i] = right.get(i);
            }
            return find(rightArray, j - K);
        }
    }

    // 得到整个数组的分割数
    private int getCenterForArray(int[] a) {
        // 1.将n个元素每5个一组，分成n/5(上界)组，最后的一个组的元素个数为n%5，有效的组数为n/5。
        int length = a.length;
        int num = length / 5;
        int[] centers = new int[num];
        for (int i = 0; i < num; i++) {
            // 取每一组中的中位数
            int center = getCenterPerArray(a, 5 * i, 5 * (i + 1) - 1);
            centers[i] = center;
            System.out.println("该数组的中位数 : " + center);
        }
        System.out.println("中位数的集合 ： ");
        print(centers);
        // 3. 递归的调用 selection 算法查找上一步中所有中位数的中位数，设为x，偶数个中位数的情况下设定为选取中间小的一个。
//        new Selectionsort().sort(centers);
        Arrays.sort(centers);
        System.out.println("经过选择排序后的中位数数组 ： ");
        print(centers);
        int x = 0;

        if (centers.length % 2 == 0) {
            // 偶数个中位数
            x = min(centers[centers.length / 2 - 1],
                    centers[centers.length / 2]);
        } else {
            x = centers[centers.length / 2];
        }
        System.out.println("定义的x 为 ： " + x);

        return x;
    }

    private int min(int a, int b) {
        if (a >= b) {
            return b;
        } else {
            return a;
        }
    }

    // 得到每个子数组中的中位数
    private int getCenterPerArray(int[] a, int start, int end) {
        // 2.取出每一组的中位数，最后一个组的不用计算中位数，任意排序方法，这里的数据比较少只有5个，可以用简单的冒泡排序或是插入排序。
        int[] b = new int[end - start + 1];
        int index = 0;
        for (int i = start; i <= end; i++) {
            b[index++] = a[i];
        }
        System.out.println("被分割的数组 ： ");
        print(b);
//        new Insertsort().sort(b);
        Arrays.sort(b);
        return b[b.length / 2];
    }

    public static void main(String[] args) {
        // 求数组中第7小的数

        int[] datas = { 4, 1, 2, 56, 24, 5, 6, 97, 8, 0, 4, 8, 6, 2, 3, 6, 1, 9, 3, 4, 6, 2 };
//        int[] datas = { 4, 1, 2, 56, 24};
        int index = 3;
        int findX = new BFPRT().find(datas, index);
//        datas = new QuickSort().sort(datas);
        Arrays.sort(datas);
        print(datas);
        System.out.println("第" + index + "小的数为 ： " + findX);
    }

    public static void print(int[] datas) {
        for (int i = 0; i < datas.length; i++) {
            System.out.print(datas[i] + " ");
        }
        System.out.println("");
    }

}
