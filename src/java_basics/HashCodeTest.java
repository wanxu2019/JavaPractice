package java_basics;

/**
 * Created by Json Wan on 2018/11/21.
 */
public class HashCodeTest {

    public static void main(String[] args) {
        Object o=new Object();
        System.out.println(o.hashCode());
        Object o2=new Object();
        System.out.println(o2.hashCode());
    }
}
