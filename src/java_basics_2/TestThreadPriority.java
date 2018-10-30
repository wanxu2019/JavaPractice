package java_basics_2;

/**
 * Created by Json Wan on 2018/9/10.
 * Description:
 */
public class TestThreadPriority implements Runnable{
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            MyThread myThread1=new MyThread("myThread1");
            MyThread myThread2=new MyThread("myThread2");
            myThread1.setPriority(1);
            myThread2.setPriority(10);
            myThread1.start();
            myThread2.start();
            System.out.println("=================================");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {

    }
}
class MyThread extends Thread{

    public MyThread(String name) {
        super(name);
    }

    public void run(){
        for (int i=0; i<5; i++) {
            System.out.println(Thread.currentThread().getName()
                    +"("+Thread.currentThread().getPriority()+ ")"
                    +", loop "+i);
        }
    }

}