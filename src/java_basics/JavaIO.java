package java_basics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Json Wan on 2018/9/6.
 */
public class JavaIO {
    public void sayHello(){
        show("hello");
    }
    public static void show(String msg){
        System.out.println(msg);
    }
    public static void main(String[] args) {
//        FilterReader reader=new PushbackReader(new InputStreamReader(System.in));
//        try {
//            System.out.println(reader.read());
//            System.out.println(reader.read());
//            System.out.println(reader.read());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        PushbackInputStream pbins=new PushbackInputStream(System.in);
//        show(pbins.toString());

//        Date date=new Date(System.currentTimeMillis());
//
//        try {
//            byte[]  bytes=new byte[2000];
//            OutputStream baos=new ByteArrayOutputStream(2000);
//            ObjectOutputStream objos=new ObjectOutputStream(baos);
//            objos.writeObject(date);
//            objos.flush();
//            baos.write(bytes);
//            show("helloworld");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Date date=new Date(System.currentTimeMillis());
        JavaIO io=new JavaIO();
        try {
            io.finalize();
            io.sayHello();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        show(""+io);
        System.gc();
        List<String> list=new ArrayList<>();
        while(true){
            list.add(new String("hello"));
        }
//        show("main end");
    }

    @Override
    protected void finalize() throws Throwable {
        show("hello finalize");
        super.finalize();
    }
}
