package java_basics_2;

/**
 * Created by Json Wan on 2018/9/10.
 * Description:
 */
class Base{
    static {
        System.out.println("Base");
    }
}
class Other{
    static {
        System.out.println("Other");
    }
    public static int b=5;
    public static final void sayHello(){
        System.out.println("hello other");
    }
    static {
        System.out.println("after Other");
    }
}
public class TestClassLoading extends Base {
    public static final int a=5;
    {
        System.out.println("before TestClassLoading");
    }

    public static void main(String[] args) {
        System.out.println("Hello");
        System.out.println(Other.b);
//        Other.sayHello();
//        System.out.println(TestClassLoading.a);
    }
    static {
        System.out.println("TestClassLoading");
    }
}