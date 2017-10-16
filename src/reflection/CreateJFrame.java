package reflection;

import java.lang.reflect.Constructor;

/**
 * Created by Json Wan on 2017/10/16.
 */
public class CreateJFrame {
    public static void main(String[] args)throws Exception {
        //获取JFrame对应的Class对象
        Class<?> jframeClazz=Class.forName("javax.swing.JFrame");
        //获取JFrame中带一个字符串参数的构造器
        Constructor ctor=jframeClazz.getConstructor(String.class);
        //调用Constructor的newInstance()方法创建对象
        Object obj=ctor.newInstance("测试窗口");
        //输出JFrame对象
        System.out.println(obj);
    }
}
