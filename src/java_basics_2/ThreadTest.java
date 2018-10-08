package java_basics_2;

import java.util.concurrent.*;

/**
 * Created by Json Wan on 2018/9/18.
 * Description:
 */
public class ThreadTest {

    static Byte currentByte=1;
    static Integer currentNum=0;
    static Long currentLong=0L;
    static Float currentFloat=0f;
    static Double currentDouble=0.0;
    static Character currentChar='a';
    final static String Lock="";

    static StringBuffer sbuff;
    static StringBuilder sbuilder;


    public static void workWithBlockingQueue(){
        BlockingQueue<Runnable> queue=new ArrayBlockingQueue<Runnable>(5);

        new Thread(){
            @Override
            public void run() {
                while(true){
                    Runnable task= null;
                    try {
                        task = queue.take();
                        task.run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        for(int i=0;i<100;i++){
            final int index=i;
            try {
                queue.put(new Thread() {
                    @Override
                    public void run() {
                        System.out.println("hello " + index);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void work1(){
        synchronized (ThreadTest.class){
            while(currentNum>=5){
                try {
                    ThreadTest.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            currentNum++;
            new Thread(){
                @Override
                public void run() {
                    System.out.println("hello");
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (ThreadTest.class) {
                        currentNum--;
                        ThreadTest.class.notifyAll();
                    }
                }
            }.start();
        }
    }

    public static void work(){
        synchronized (Lock){
            while(currentNum>=5){
                try {
                    Lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            currentNum++;
            new Thread(){
                @Override
                public void run() {
                    System.out.println("hello");
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (Lock) {
                        currentNum--;
                        Lock.notifyAll();
                    }
                }
            }.start();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        for(int i=0;i<100;i++){
//            work();
//        }
//            int index=i;
//            executorService.execute(() -> {
//                        try {
//                            Thread.sleep(2000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println("i:" + index + " executorService");
//                    }
//            );
//            executorService.submit(()->{
//                        try {
//                            Thread.sleep(2000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println("i:"+index+" executorService");
//                }
//            );
//        }
        workWithBlockingQueue();
    }
}
