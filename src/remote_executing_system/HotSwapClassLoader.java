package remote_executing_system;

/**
 * Created by Json Wan on 2018/11/28.
 */
public class HotSwapClassLoader extends ClassLoader {
    public HotSwapClassLoader() {
        //获取当前类的类加载器
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadByte(byte[] classByte){
        //目的：公开父类中的protected方法defineClass
        return defineClass(null,classByte,0,classByte.length);
    }
}
