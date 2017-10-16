package reflection;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 根据配置文件去生成对象，Spring框架的核心
 * Created by Json Wan on 2017/10/16.
 */
public class ObjectPoolFactory {
    //定义一个对象池，前面是对象名，后面是实际对象
    private Map<String,Object> objectPool=new HashMap<>();
    //定义一个创建对象的方法
    //该方法只要传入一个字符串类名，程序可以根据该类名生成Java对象
    private Object createObject(String clazzName)throws InstantiationException,IllegalAccessException,ClassNotFoundException{
        //1.根据字符串来获取对应的Class对象
        Class<?> clazz=Class.forName(clazzName);
        //2.使用clazz对应类的默认构造器创建实例
        return clazz.newInstance();
        //3.可使用clazz.getConstructor().newInstance()来使用其余构造器创建对象
    }
    //该方法根据指定文件来初始化对象池
    //它会根据配置文件来创建对象
    public void initPool(String fileName)throws InstantiationException,IllegalAccessException,ClassNotFoundException{
        try(FileInputStream fis=new FileInputStream(fileName)) {
            Properties props=new Properties();
            props.load(fis);
            for(String name:props.stringPropertyNames()) {
                //每取出一对key-value对，就根据value创建一个对象
                //调用createObject（）创建对象，并将对象添加到对象池中
                objectPool.put(name,createObject(props.getProperty(name)));
            }
        }catch(IOException ex){
            System.out.println("读取"+fileName+"异常");
        }
    }
    public Object getObject(String name){
        //从objectPool中取出指定name对应的对象
        return objectPool.get(name);
    }

    public static void main(String[] args)throws Exception {
        ObjectPoolFactory pf=new ObjectPoolFactory();
        pf.initPool("obj.txt");
        System.out.println(pf.getObject("a"));
        System.out.println(pf.getObject("b"));
    }
}
