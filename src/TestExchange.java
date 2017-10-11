/**
 * Created by Json Wan on 2017/10/7.
 * Description:异或操作可以得到a,b间的差异信息，a，b各自结合差异信息即可得到对方的值
 */
public class TestExchange {
    public static void main(String[] args) {
        int a=2017,b=9999,diff;
        System.out.println("原始：a="+a+";b="+b);
        //实现交换
        a=a^b;
        b=b^a;
        a=a^b;
        System.out.println("a="+a+";b="+b);
        //分析原理
        diff=a^b;
        b=b^diff;
        a=diff^a;
        System.out.println("a="+a+";b="+b);
        //验证交换律
        a=a^b;
        b=a^b;
        a=a^b;
        System.out.println("a="+a+";b="+b);
        diff=a^b;
        b=b^diff;
        a=a^diff;
        System.out.println("a="+a+";b="+b);
        //验证对称性
        b=a^b;
        a=a^b;
        b=a^b;
        System.out.println("a="+a+";b="+b);
        //验证负数
        a=-9999;b=2017;
        System.out.println("原始：a="+a+";b="+b);
        b=a^b;
        a=a^b;
        b=a^b;
        System.out.println("a="+a+";b="+b);
        a=-9999;b=-2017;
        System.out.println("原始：a="+a+";b="+b);
        b=a^b;
        a=a^b;
        b=a^b;
        System.out.println("a="+a+";b="+b);
    }
}
