package java_basics;

public class TestDepend1 {
    public static String msg="hello depend1";
    static {
        System.out.println(TestDepend2.msg);
    }
}
