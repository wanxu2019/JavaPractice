package java_basics;

import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by Json Wan on 2018/2/10.
 * Description:
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
//        URL[] urls=sun.misc.Launcher.getBootstrapClassPath().getURLs();
//        for (int i = 0; i < urls.length; i++) {
//            System.out.println(urls[i].toExternalForm());
//        }

        char han='永';
        System.out.format("%x", (short) han);

        char han2=0x6c38;
        System.out.println(han2);

        System.out.println((char)(1+'0'));

        Short myshort=-32768;
        float z=1.0f;
        int t="abc".length();
        byte b=99;
        byte bb='a';
        byte bbb=0x22;
        char c=99;
        char cc='a';
        char ccc=0x22;
        short s=99;
        short ss='a';
        short sss=0x22;
//        s=c;
//        c=s;
//        b=c;
//        c=b;
//        b=s;
//        s=b;
//        boolean bo=true;
//        boolean boo=2;
//        boolean boo0='a';
        int i=012;
        int j=034;
        int k=(int)056L;
//        int l=078;
        System.out.println(i);
        System.out.println(j);
        System.out.println(k);
        short s0=32767;
        s0+=1;
        s0+=1;
        System.out.println(s0);
        int num=-32;
        System.out.println(num<<1);
        System.out.println(num<<2);
        System.out.println(num<<4);
        System.out.println(num<<8);
        System.out.println(num<<16);
        System.out.println(num<<28);
        System.out.println(num<<30);
        System.out.println(num<<31);
        System.out.println(num<<32);
        System.out.println(num<<64);
        System.out.println(num>>1);
        System.out.println(num>>2);
        System.out.println(num>>4);
        System.out.println(num>>8);
        System.out.println(num>>9);
        System.out.println(num>>10);
        System.out.println(num>>11);
        System.out.println(num>>12);
        System.out.println(num>>13);
        System.out.println(num>>14);
        System.out.println(num>>15);
        System.out.println(num>>16);
        System.out.println(num>>20);
        System.out.println(num>>24);
        System.out.println(num>>28);
        System.out.println(num>>29);
        System.out.println(num>>30);
        System.out.println(num>>31);
        System.out.println(num>>32);
//        s0=s0+(short)1;

    }
}
