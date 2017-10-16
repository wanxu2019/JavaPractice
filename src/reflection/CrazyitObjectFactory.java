package reflection;

import javax.swing.*;
import java.util.Date;

/**
 * Created by Json Wan on 2017/10/16.
 */
public class CrazyitObjectFactory {
    public static Object getInstance(String clsName){
        try{
            //创建指定类对应的Class对象
            Class cls=Class.forName(clsName);
            //返回使用该Class对象创建的实例
            return cls.newInstance();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Date d=(Date)CrazyitObjectFactory.getInstance("java.util.Date");
        JFrame f=(JFrame)CrazyitObjectFactory.getInstance("java.util.Date");
    }
}
