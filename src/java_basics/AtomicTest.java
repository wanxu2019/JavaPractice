package java_basics;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Json Wan on 2018/10/17.
 */
public class AtomicTest {

    public static void main(String[] args) {
        AtomicInteger a=new AtomicInteger(0);
        for (int i = 0; i < 100; i++) {
            new Adder(a).start();
        }
        AtomicReference<String> atomicReference=new AtomicReference<>("aaaa");
        String b=atomicReference.get();
        atomicReference.getAndAccumulate("bbb", (s, s2) -> {
            //apply函数会在单线程中调用，不必保证是线程安全的
            return s+s2;
        });
        System.out.println(atomicReference.get());
        System.out.println(b);
    }

    static class Adder extends Thread{
        AtomicInteger value;

        public Adder(AtomicInteger value) {
            this.value = value;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                value.getAndAdd(1);
//                try {
//                    sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
            System.out.println(this.value);
        }
    }
}
