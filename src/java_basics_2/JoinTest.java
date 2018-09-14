package java_basics_2;

/**
 * Created by Json Wan on 2018/9/11.
 * Description:
 */
public class JoinTest implements Runnable{
    @Override
    public void run() {

        try {
            System.out.println(Thread.currentThread().getName() + " start-----");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " end------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread[] threads=new Thread[5];
        for (int i=0;i<5;i++) {
            Thread test = new Thread(new JoinTest());
            test.start();
            threads[i]=test;
        }
        for (int i=0;i<5;i++) {
            try {
                threads[i].join(); //调用join方法
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Finished~~~");
    }
}