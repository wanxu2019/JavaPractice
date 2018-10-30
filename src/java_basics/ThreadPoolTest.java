package java_basics;

import java.util.concurrent.*;

/**
 * Created by Json Wan on 2018/10/17.
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService executor= Executors.newCachedThreadPool();
        ExecutorService executor2= new ThreadPoolExecutor(2,5,1000, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());

    }
}
