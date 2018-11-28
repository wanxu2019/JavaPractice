package java_basics;

/**
 * Created by Json Wan on 2018/11/27.
 */
class A{
    public static void show() {
        System.out.println("hello A");
    }
}
class B extends A{
    public static void show() {
        System.out.println("hello B");
    }
}
public class TestInherit {
    public static void main(String[] args) {
        //结论：通过对象来调用类方法，会以对象的声明类型而不是实例化类型为准。
        A a=new A();
        a.show();
        A b=new B();
        b.show();
        B bb=new B();
        bb.show();
    }
}
