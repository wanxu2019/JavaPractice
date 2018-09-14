package java_basics_2;

/**
 * Created by Json Wan on 2018/9/10.
 * Description:
 */
public class TestFinally {

    public static void main(String[] args) {
        System.out.println(test());
    }

    public static String test2(){
        System.out.println("A");
        return "D";
    }
    public static String test(){
        try{
            System.out.println("B");
            return test2();
        }catch (Exception e){
            System.out.println("C");
        }finally {
            System.out.println("E");
        }
        return "F";
    }
}
