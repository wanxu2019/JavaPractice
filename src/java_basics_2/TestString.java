package java_basics_2;

/**
 * Created by Json Wan on 2018/9/15.
 * Description:
 */
public class TestString {

    public static void main(String[] args) {
        String s1=new String("abc");
        String s2="abc";
        String s3="abc";
        System.out.println(s1==s2);
        System.out.println(s2==s3);
        String str = "abc";
        char data[] = {'a', 'b', 'c'};
        String str2 = new String(data);
        System.out.println(str==str2);
        String s4=new String("abc");
        String s5=new String("abc");
        System.out.println(s5==s4);
        short c=1;
//        c=c+c;
        int a=2;
        a=a+a;
        long b=2;
        b=b+b;
    }
}
