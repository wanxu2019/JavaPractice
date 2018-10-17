package java_basics;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 * 非阻塞NIO
 *
 *一、使用NIO完成网络通信
 *1.通道（Channel）:负责连接
 *2.缓冲区（Buffer）:负责数据的存取
 *3.选择器（Selector）:用于监控IO情况
 *
 * @author dxx
 * @version 2017-11-05 上午10:42:18
 */
public class NioNotBlock {
    //客户端
    public void client() throws IOException{
        //1.获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8888));
        //2.切换为非阻塞模式
        sChannel.configureBlocking(false);
        //3.分配指定大小的缓冲区
        ByteBuffer bb = ByteBuffer.allocate(1024);
        //4.发送数据到客户端(实时时间+键盘录入)
        Scanner sc=new Scanner(System.in);
        String[] nameArr={"Kobe","James","Iverson","Duncan","Curry","Harden"};
        Random rand = new Random();
        int i = rand.nextInt(5);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        while(sc.hasNext()){
            String str = sc.next();
            bb.put((nameArr[i]+"  "+date+"\n"+str).getBytes());
            bb.flip();
            sChannel.write(bb);
            bb.clear();
        }
        //5.关闭通道
        sChannel.close();
    }

    //服务端
    public void server() throws IOException{
        //1.获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        //2.切换为非阻塞模式
        ssChannel.configureBlocking(false);
        //3.绑定连接
        ssChannel.bind(new InetSocketAddress(8888));
        //4.获取选择器
        Selector selector=Selector.open();
        //5.将通道注册到选择器上,指定“监听接收事件”
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        //6.轮询获取选择器上已经“准备就绪”的事件
        while(selector.select()>0){
            //7.获取当前选择器中所有注册的“选择键（已就绪的监听事件）”
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while(it.hasNext()){
                //8.获取准备就绪的事件
                SelectionKey sk = it.next();
                //9.判断是什么事件准备就绪
                if(sk.isAcceptable()){
                    //10.若是接收就绪，则获取客户端连接
                    SocketChannel clientCh = ssChannel.accept();
                    //11.切换非阻塞模式
                    clientCh.configureBlocking(false);
                    //12.将该通道注册到选择器
                    clientCh.register(selector,SelectionKey.OP_READ);
                }else if(sk.isReadable()){
                    //13.获取当前选择器上“读就绪”的通道
                    SocketChannel rChannel=(SocketChannel) sk.channel();
                    //14.读取数据
                    ByteBuffer bb = ByteBuffer.allocate(1024);
                    int len=0;
                    while((len=rChannel.read(bb))>0){
                        bb.flip();
                        System.out.println(new String(bb.array(),0,len));
                        bb.clear();
                    }
                }
                //15.取消选择键 SelectionKey
                it.remove();
            }
        }
    }

    public static void main(String[] args) {
        NioNotBlock test=new NioNotBlock();
        try {
            test.server();
            test.client();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
