package java_basics_2;

import java.util.HashMap;

/**
 * Created by Json Wan on 2018/9/26.
 * Description:
 */
public class TestMemory {
    static HashMap map = new HashMap();

    public static void main(String[] args) {
        int i = 0;
        while (true) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(i, "aa" + i);
            i++;
        }
    }
}
