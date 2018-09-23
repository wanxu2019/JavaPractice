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
    }
}
