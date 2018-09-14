package java_basics_2;

/**
 * Created by Json Wan on 2018/9/11.
 * Description:
 */
interface TestInterface{
    public static final int a=1;
    public static void test1(){
        System.out.println("hello world");
    }
//    protected void test2();

}
enum Number{
    ONE,TWO,THREE;
    static{
        System.out.println("hello enum static");
    }
    Number(){
        System.out.println("hello enum");
    }
    public static void test(){
        System.out.println(THREE);
    }
}
public class Interrupt {
    static {
        System.out.println("hello Interrupt");
    }
    Interrupt(){
        System.out.println("hello Interrupt constructor");
    }
    public static void main(String[] args) throws Exception {
        Number a=Number.ONE;
        Interrupt b=new Interrupt();
//        a.test();
        Thread t = new Thread(new Worker());
        t.start();

        Thread.sleep(200);
        t.interrupt();

        System.out.println("Main thread stopped.");
    }

    public static class Worker implements Runnable {
        public void run() {
            System.out.println("Worker started.");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread curr = Thread.currentThread();
                //再次调用interrupt方法中断自己，将中断状态设置为“中断”
                System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
                System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
                curr.interrupt();
                System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
                System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
                System.out.println("Static Call: " + Thread.interrupted());//clear status
                System.out.println("---------After Interrupt Status Cleared----------");
                System.out.println("Static Call: " + Thread.interrupted());
                System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
                System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
            }

            System.out.println("Worker stopped.");
        }
    }
}
