package java_base_frames;

/**
 * Created by Json Wan on 2018/11/1.
 */
public interface HelloWorldMBean {
    String getGreeting();
    void setGreeting(String greeting);
    void printGreeting();
}
