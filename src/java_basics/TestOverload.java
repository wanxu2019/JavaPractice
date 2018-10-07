package java_basics;

/**
 * Created by Json Wan on 2018/10/7.
 * Description:
 */
public class TestOverload {

    static class Base{
        public static void test() throws Exception {
            System.out.println("Hello");
            throw new Exception("RE");
        }
    }
    static class Son1 extends Base{
        public static void test(){
            System.out.println("Hello from Son");
        }
        public static void test(String msg){
            System.out.println("Hello from Son,"+msg);
        }

    }
    public static void main(String[] args) {
        Son1 son1=new Son1();
        son1.test();
        son1.test("World");
    }
}
