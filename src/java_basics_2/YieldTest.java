package java_basics_2;

/**
 * Created by Json Wan on 2018/9/11.
 * Description:
 */

public class YieldTest implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0;i<5;i++){
            System.out.println(Thread.currentThread().getName() + ": " + i);
            //调度器可能会忽略该方法。
            //使用的时候要仔细分析和测试，确保能达到预期的效果。
            //很少有场景要用到该方法，主要使用的地方是调试和测试。
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        YieldTest runn = new YieldTest();
        Thread t1 = new Thread(runn,"FirstThread");
        Thread t2 = new Thread(runn,"SecondThread");

        t1.start();
        t2.start();

    }
}
