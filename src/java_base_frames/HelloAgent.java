package java_base_frames;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.*;

/**
 * Created by Json Wan on 2018/11/1.
 */
public class HelloAgent implements NotificationListener {
    private MBeanServer mbs;

    public HelloAgent() {
        this.mbs = MBeanServerFactory.createMBeanServer("HelloAgent");

        HelloWorld hw = new HelloWorld();
        ObjectName helloWorldName = null;
        try{
            helloWorldName = new ObjectName("HelloAgent222:a=helloWorld");
            mbs.registerMBean(hw, helloWorldName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        startHtmlAdaptorServer();
    }

    public void startHtmlAdaptorServer(){
        HtmlAdaptorServer htmlAdaptorServer = new HtmlAdaptorServer();
        ObjectName adapterName = null;
        try {
            // 多个属性使用,分隔
            adapterName = new ObjectName("HelloAgent:name=htmladapter,port=9092");
            htmlAdaptorServer.setPort(9092);
            mbs.registerMBean(htmlAdaptorServer, adapterName);
            htmlAdaptorServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        System.out.println(" hello agent is running");
        HelloAgent agent = new HelloAgent();
        System.out.println("main thread end");
    }

    @Override
    public void handleNotification(Notification notification, Object handback) {
        System.out.println(notification.getMessage());
    }
}
