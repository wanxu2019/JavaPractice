package java_basics_2;

import java_basics.MyObject;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Json Wan on 2018/3/1.
 * Description:
 */
public class MySecondObject extends MyObject {
    public static void main(String[] args) {
        MySecondObject object2=new MySecondObject();
        object2.a=1;
        object2.b=1;
        // 编译不通过的语句
//        object2.c=1;
//        object2.d=1;
        //1.容器持有String对象：对象内容改变，容器内对象内容不变
        Queue<String> queue=new PriorityQueue<>();
        ArrayList<String> list=new ArrayList<>();
        String s1=new String("Hello");
        queue.add(s1);
        list.add(s1);
        s1=s1.concat(" World");
        s1="fuck~";
        System.out.println(queue.peek());
        System.out.println(list.get(0));
        //2.容器持有Integer对象：对象内容改变，容器内对象内容不变
        Queue<Integer> queue3=new PriorityQueue<>();
        ArrayList<Integer> list3=new ArrayList<>();
        Integer s3=new Integer(222);
        queue3.add(s3);
        list3.add(s3);
        s3=333;
        System.out.println(queue3.peek());
        System.out.println(list3.get(0));
        //3.自定义对象：对象内容改变，容器内对象内容也改变
        Queue<One> queue2=new PriorityQueue<>();
        ArrayList<One> list2=new ArrayList<>();
        MySecondObject.One.Two s2=new MySecondObject.One.Two();
//        One copyS2=new MySecondObject.One.Two(s2);
        s2.value=100;
        queue2.add(s2);
        list2.add(s2);
        s2.value=200;
        System.out.println(queue2.peek().value);
        System.out.println(list2.get(0).value);
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());
        System.out.println(System.nanoTime());
        System.out.println(System.nanoTime());
        //可正常执行
        ((MySecondObject)null).test();
        //空指针异常，因为转换后还是为null
//        ((MySecondObject)null).test2()

    }
    interface IS{
        final int ss=1;
        int f();
    }
    public static void test(){
        System.out.println("test");
    }
    public void test2(){
        System.out.println("test2");
    }
    public static class One{
        public int value=0;
        public One(){
            System.out.println("One initialed");
        }
        public One(int i){
            System.out.println("One "+i);
        }
        public int fuck(String a){
            return 0;
        }
        private static class Two extends One{
            Two(){
                super(1);
                System.out.println("Two initialed");
            }
            public static void main1(String[] args) {
                System.out.println("two");
            }
            //override
            public int fuck(String s){
                return 100;
            }
            //overload
            public int fuck(int i){
                return 10*i;
            }
        }
    }
}
