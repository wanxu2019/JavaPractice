package java_basics;

import javax.annotation.processing.AbstractProcessor;

public class TestDepend2 {
    public static String msg="hello depend2";
    static {
        System.out.println(TestDepend1.msg);
    }

    public static void main(String[] args) {
        System.out.println("Hello world");
    }
}
