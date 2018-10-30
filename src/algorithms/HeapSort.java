package algorithms;

/**
 * Created by Json Wan on 2018/9/14.
 * Description:
 */
public class HeapSort {

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    //从上往下逐步调整堆
    public static void heapAdjust(int[] arr, int i, int len) {
        int j = i * 2 + 1;
        while (j < len) {
            if (j + 1 < len && arr[j + 1] > arr[j])
                j += 1;
            if (arr[j] > arr[i]) {
                swap(arr, i, j);
                //递归成本较高，改用while循环较好
                //heapAdjust(arr,j,len);
            }
            i = j;
            j = i * 2 + 1;
        }
    }

    //堆排序
    public static void heapSort(int[] arr) {
        //第一步：从下往上构建堆
        //树状结构是隐式的，本来就有的，只是要不断调整
        for (int i = (arr.length + 1) / 2; i >= 0; i--) {
            heapAdjust(arr, i, arr.length);
        }
        heapPrint(arr);
        //第二步：调整堆
        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, 0, j);
            heapAdjust(arr, 0, j);
        }
    }

    public static void heapPrint(int[] arr) {
        int step = 1;
        int stage = 2;
        for (int x : arr) {
            System.out.print(x + " ");
            step++;
            if (step == stage) {
                System.out.println();
                stage *= 2;
            }
        }
        System.out.println();
    }

    public static void show(int[] arr) {
        for (int x : arr) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 5, 6, 7, 2};
        heapSort(arr);
        show(arr);
    }
}
