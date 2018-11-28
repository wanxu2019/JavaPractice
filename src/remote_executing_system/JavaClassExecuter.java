package remote_executing_system;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * Created by Json Wan on 2018/11/28.
 */
public class JavaClassExecuter {
    public static String execute(byte[] classByte){
        HackSystem.clearBuffer();
        ClassModifier cm=new ClassModifier(classByte);
        byte[] modiBytes=cm.modifyUTF8Constant("java/lang/System","remote_executing_system/HackSystem");
        HotSwapClassLoader loader=new HotSwapClassLoader();
        Class clazz=loader.loadByte(modiBytes);
        try{
            Method method=clazz.getMethod("main",new Class[]{String[].class});
            method.invoke(null,new String[]{null});
        }catch (Throwable e){
            e.printStackTrace(HackSystem.out);
        }
        return HackSystem.getBufferString();
    }

    public static void main(String[] args) {
        try {
            InputStream is=new FileInputStream("src/remote_executing_system/Test.class");
            byte[] b=new byte[is.available()];
            is.read(b);
            is.close();
            System.out.println(JavaClassExecuter.execute(b));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
