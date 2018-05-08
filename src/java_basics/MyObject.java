package java_basics;

/**
 * Created by Json Wan on 2018/3/1.
 * Description:
 */
public class MyObject extends Object {
    public int a;
    protected int b;
    private int c;
    int d;

    public static void main(String[] args) {
        MyObject myObject=new MyObject();
        myObject.a=1;
        myObject.b=1;
        myObject.c=1;
        myObject.d=1;
    }
    public static class InnerClass{
        public static void main(String[] args) {
            MyObject myObject=new MyObject();
            myObject.a=1;
            myObject.b=1;
            myObject.c=1;
            myObject.d=1;
        }
    }
}
class Class2{
    public static void main(String[] args) {
        MyObject myObject=new MyObject();
        myObject.a=1;
        myObject.b=1;
//        myObject.c=1;
        myObject.d=1;
    }
}