package java_basics;

/**
 * Created by Json Wan on 2018/3/1.
 * Description:
 */
public class MySecondObject extends MyObject {
    public static void main(String[] args) {
        MySecondObject object2=new MySecondObject();
        object2.a=1;
        object2.b=1;
        //编译不通过的语句
//        object2.c=1;
        object2.d=1;
    }
}
