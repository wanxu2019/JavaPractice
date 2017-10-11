package threadpool;

/**
 * Created by Json Wan on 2017/10/7.
 * Description:
 */
public class ThreadState implements Runnable {
    public synchronized void waitForASecond() throws InterruptedException {
        wait(500);
    }

    public synchronized void waiting() throws InterruptedException {
        wait();
    }

    public synchronized void notifyNow() throws InterruptedException {
        notify();
    }

    @Override
    public void run() {
        try {
            waitForASecond();
            waiting();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadState state = new ThreadState();
        Thread thread = new Thread(state);
        System.out.println("新建线程：" + thread.getState());
        thread.start();
        System.out.println("线程运行：" + thread.getState());
        Thread.sleep(100);
        System.out.println("计时等待线程：" + thread.getState());
        Thread.sleep(1000);
        System.out.println("等待线程：" + thread.getState());
        state.notifyNow();
        System.out.println("唤醒线程：" + thread.getState());
        Thread.sleep(1000);
        System.out.println("终止线程：" + thread.getState());
    }

}