package java_basics;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by Json Wan on 2018/10/17.
 */
public class AtomicIntegerArrayDemo {
    //使用原子数组（线程安全）的做法
    static AtomicIntegerArray arr = new AtomicIntegerArray(10);

    //线程不安全的做法
//    static int[] arr = new int[10];

    public static class AddThread implements Runnable {
        public void run() {
            for (int k = 0; k < 10000; k++)
                arr.getAndIncrement(k % arr.length());
                //线程不安全的做法
//                arr[k % arr.length]+=1;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[10];
        for (int k = 0; k < 10; k++) {
            ts[k] = new Thread(new AddThread());
        }
        for (int k = 0; k < 10; k++) {
            ts[k].start();
        }
        for (int k = 0; k < 10; k++) {
            ts[k].join();
        }
        System.out.println(arr);
        //线程不安全的做法
//        for (int x:arr) {
//            System.out.print(x + " ");
//        }
    }
}
