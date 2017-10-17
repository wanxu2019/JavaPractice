package reflection;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * 题目：开发一个工具类，该工具类提供一个eval()方法，实现JavaScript中eval()函数的功能——可以动态运行一行或多行程序代码。例如eval("System.out.println(\"aa\")"),将输出aa。
 * Created by Json Wan on 2017/10/17.
 */
class MyClassLoader extends ClassLoader {
    public Class<?> loadMyClass(String className, byte[] raw, int off, int len) {
        return defineClass(className, raw, off, len);
    }
}

public class Quiz1 {
    //读取一个文件的内容
    private static byte[] getBytes(String fileName) throws IOException {
        File file = new File(fileName);
        long len = file.length();
        byte[] raw = new byte[(int) len];
        try (
                FileInputStream fin = new FileInputStream(file);
        ) {
            //一次读取Class文件的全部二进制数据
            int r = fin.read(raw);
            if (r != len) throw new IOException("无法读取全部文件：" + r + " != " + len);
            return raw;
        }
    }

    public static void eval(String code) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        String tempClassName = "TempClazz";
        File tempFile = new File(tempClassName + ".java");
        File classFile = new File(tempClassName + ".class");
        //0.清理工作
        if (classFile.exists()) {
            classFile.delete();
        }
        //1.生成一个运行代码的类
        //1.1写文件前半部分
        if (!tempFile.exists()) {
            tempFile.createNewFile();
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
        bw.write("public class TempClazz{" + "\n");
        bw.write("    public static void main(String[] args){" + "\n");
        bw.write("        System.out.println(args[0]);" + "\n");
        //2.将代码放入类的main方法当中
        bw.write("        "+code+"\n");
        //1.2写文件后半部分
        bw.write("    }" + "\n");
        bw.write("}" + "\n");
        //3.生成代码文件
        bw.flush();
        bw.close();
        //4.在Runtime里编译文件，获取字节码class文件
        Process process = Runtime.getRuntime().exec("javac TempClazz.java");
        try {
            //其他线程都等待这个线程完成
            process.waitFor();
        } catch (InterruptedException ie) {
            System.out.println(ie);
        }
        System.out.println("编译进程返回值为：" + process.exitValue());
        //5.加载class文件得到Class对象
        if (!classFile.exists()) {
            System.out.println("编译临时文件失败！");
            return;
        }
        Class tempClass = null;
        try {
            //将Class文件的二进制数据读入数组
            byte[] raw = getBytes(classFile.getName());
            //调用ClassLoader的defineClass方法将二进制数据转换成Class对象
            MyClassLoader classLoader = new MyClassLoader();
            tempClass = classLoader.loadMyClass(tempClassName, raw, 0, raw.length);
        } catch (IOException ie) {
            ie.printStackTrace();
            return;
        }
        //6.删除临时文件与class文件
        //tempFile.delete();
        //classFile.delete();
        //6.反射调用main方法
        Method method = tempClass.getMethod("main",(new String[0]).getClass());
        method.invoke(tempClass.newInstance(),new Object[]{new String[]{"hello"}});
    }

    public static void main(String[] args) throws Exception {
        //System.out.println((new String[0]).getClass().getName());
        Scanner scanner=new Scanner(System.in);
        while(true) {
            System.out.println("请输入要执行的代码：");
            String code=scanner.nextLine();
//            eval("System.out.println(\"Hello World!\");");
            eval(code);
        }
    }
}
