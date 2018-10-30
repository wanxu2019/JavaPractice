package java_basics;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *通道(Channel)：用于源节点与目标节点的连接，在java NIO 中负责缓冲区中的数据传输，
 *Channel本身不存储数据，因此需要缓冲区配合进行传输，通道就相当于铁轨，缓冲区就相当于火车
 *
 *JDK 1.7中的NIO 针对各个通道提供了静态方法open()
 *
 *通道之间的数据传输
 *transferTo()
 *transferFrom()
 *
 *
 *分散与聚集
 *分散读取：将通道中的数据分散到多个缓冲区中去
 *聚集写入:将多个缓冲区的数据聚集到通道中去
 *
 * @author dxx
 * @version 2017-11-02 下午8:33:05
 */
public class NioChannel {

    //4.分散与聚集
    public void run4() throws IOException{
        //1.分散读取
        RandomAccessFile raf1=new RandomAccessFile("1.txt", "rw");
        //获取通道
        FileChannel channel1 = raf1.getChannel();
        //分配两个指定大小的缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);
        //构建缓冲区数组
        ByteBuffer[] bufArr={buf1,buf2};
        //通道读取
        channel1.read(bufArr);
        //切换缓冲区为写模式
        for (ByteBuffer byteBuffer : bufArr) {
            byteBuffer.flip();
        }
        System.out.println(new String(bufArr[0].array(), 0, bufArr[0].limit()));
        System.out.println("--------------------------");
        System.out.println(new String(bufArr[1].array(), 0, bufArr[1].limit()));

        //2.聚集写入
        //聚集写入到2.txt中
        RandomAccessFile raf2=new RandomAccessFile("2.txt", "rw");
        FileChannel channel2 = raf2.getChannel();
        //将缓冲区数组写入通道中
        channel2.write(bufArr);
    }


    //3.通道之间的数据传输，直接缓冲区
    public void run3() throws IOException{
        FileChannel inChannel = FileChannel.open(Paths.get("e:/01.avi"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("e:/02.avi"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
//      inChannel.transferTo(0, inChannel.size(), outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());
    }

    //2.利用直接缓冲区完成文件的复制
    public void run2() throws IOException{
        long start=System.currentTimeMillis();
        //获取通道
        //读模式
        FileChannel inChannel = FileChannel.open(Paths.get("e:/01.avi"), StandardOpenOption.READ);
        //读写模式
        FileChannel outChannel = FileChannel.open(Paths.get("e:/02.avi"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);

        //内存映射文件
        MappedByteBuffer inBuf = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outBuf = outChannel.map(MapMode.READ_WRITE,0,inChannel.size());

        //对缓冲区的数据进行读写操作
        byte[] by=new byte[inBuf.limit()];
        inBuf.get(by);
        outBuf.put(by);
        inChannel.close();
        outChannel.close();
        long end=System.currentTimeMillis();
        System.out.println("耗费时间："+(end-start));
    }

    //1.利用通道完成文件的复制（非直接缓冲区）
    public void run() throws IOException{
        long start=System.currentTimeMillis();
        FileInputStream fis=null;
        FileOutputStream fos=null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis=new FileInputStream("e:/01.avi");
            fos=new FileOutputStream("e:/02.avi");
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();
            //分配指定大小的缓冲区
            ByteBuffer bb = ByteBuffer.allocate(1024);
            //将通道中的数据存入缓冲区,这个时候的缓冲区是写模式
            while(inChannel.read(bb)!=-1){
                //将缓冲区切换为读模式
                bb.flip();
                outChannel.write(bb);
                bb.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            fis.close();
            fos.close();
            inChannel.close();
            outChannel.close();
        }
        long end=System.currentTimeMillis();
        System.out.println("耗费时间："+(end-start));
    }

    public static void main(String[] args) {
        NioChannel test=new NioChannel();
        try {
            test.run();
            test.run2();
            test.run3();
            test.run4();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
