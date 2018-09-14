package tests;

/**
 * Created by Json Wan on 2018/9/13.
 * Description:
 */
public class XiaomiTest2 {

    private static String solution(String line) {
        // 在此处理单行数据

        // 返回处理后的结果
        // return ans;
        // 在此处理单行数据
        String[] numstrs=line.split(" ");
        int a=Integer.parseInt(numstrs[0]);
        int b=Integer.parseInt(numstrs[1]);
        int c=Integer.parseInt(numstrs[2]);
        int d=Integer.parseInt(numstrs[3]);
        return (int)(
                    Math.pow(2,(a+b))
                    +d*Math.pow(2,(a+c))
                    +b*c*Math.pow(2,a)
                    +b*(b-1)/2*Math.pow(2,a))+"";
    }

    public static void main(String[] args) {
        System.out.println(solution("1 1 1 1"));
    }
}
