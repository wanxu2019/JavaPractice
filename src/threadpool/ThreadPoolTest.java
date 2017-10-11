package threadpool;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by Json Wan on 2017/10/6.
 * Description:
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        //1.创建线程池
//        ExecutorService cachedThreadPool= Executors.newCachedThreadPool();
        //固定线程数的线程池
//        cachedThreadPool= Executors.newFixedThreadPool(500);
//        //单线程线程池
//        cachedThreadPool=Executors.newSingleThreadExecutor();

//        ScheduledExecutorService cachedThreadPool=Executors.newScheduledThreadPool(500);
//        for (int i = 0; i < 1; i++) {
//            final int index=i;
//            try{
//                Thread.sleep(1);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
        //2.用线程池执行任务
//            cachedThreadPool.scheduleAtFixedRate(() -> System.out.println(index), 1, 5, TimeUnit.SECONDS);
//        }

//        ExecutorService singleThreadExecutor = Executors.newCachedThreadPool();
//        for (int i = 0; i < 100; i++) {
//            final int index = i;
//            singleThreadExecutor.execute(new Runnable() {
//                public void run() {
//                    try {
//                        while(true) {
//                            System.out.println(index);
//                            Thread.sleep(10 * 1000);
//                        }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        //测试Vector
//        Vector<String> vector=new Vector<>();
//        vector.add("aaa");
//        vector.add("bbb");
//        vector.add("ccc");
//        vector.add("ddd");
//        Iterator<String> iterator=vector.iterator();
//        while(iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
//        vector.removeAllElements();
//        while(true){
//            int i=0;
//            i++;
//            i--;
//        }
        /**
         * 测试Arrays
         */
//        int[] a1 = new int[5];
//        int[] a2 = new int[5];
//        Arrays.fill(a1, 47);
//        Arrays.fill(a2, 47);
//        for (int i = 0; i < a2.length; i++)
//            System.out.print(a2[i]);
//        //判断两个数组是否包含同样多的元素，相同位置元素是否相同
//        System.out.println(Arrays.equals(a1, a2));
//        a2[3] = 11;
//        a2[2] = 9;
//        System.out.println(Arrays.equals(a1, a2));
//        Arrays.sort(a2);
//        for (int i = 0; i < a2.length; i++)
//            System.out.print(a2[i]);
//        //二分查找
//        System.out.println(Arrays.binarySearch(a2, 11));
//        ArrayList<Integer> list=new ArrayList<>();
////        list.add("fsdaf");
//        list.add(4);
//        list.add(4);
//        list.add(5);
//        System.out.println(list.get(1)+list.get(2));
//        ArrayList<String> list=new ArrayList<>(5);
//        Collections.fill(list, "fuck");
//        System.out.println(list);
//        list.add("a");
//        list.add("c");
//        list.add("b");
//        list.add("d");
////        list.add(null);
//        list.sort((x, y) -> x.compareTo(y));
//        Collections.reverse(list);
//        System.out.println(Collections.binarySearch(list, "c"));
//        list.addAll(list);
//        System.out.println(list);
        // 进行10次测试
        for (int i = 0; i < 10; i++) {
            test();
        }

    }

    public static void test() {
        // 用来测试的List
        List<Object> list = new Vector<Object>();
        // 线程数量(1000)
        int threadCount = 1000;
        // 用来让主线程等待threadCount个子线程执行完毕
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        // 启动threadCount个子线程
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new MyThread(list, countDownLatch));
            thread.start();
        }

        try {
            // 主线程等待所有子线程执行完成，再向下执行
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // List的size
        System.out.println(list.size());
    }

    static class MyThread implements Runnable {
        private List<Object> list;
        private CountDownLatch countDownLatch;
        public MyThread(List<Object> list, CountDownLatch countDownLatch) {
            this.list = list;
            this.countDownLatch = countDownLatch;
        }
        public void run() {
            // 每个线程向List中添加100个元素
            for (int i = 0; i < 100; i++) {
                list.add(new Object());
            }
            // 完成一个子线程
            countDownLatch.countDown();
        }
    }
}
