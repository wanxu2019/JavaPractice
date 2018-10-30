package tests;

/**
 * Created by Json Wan on 2018/10/30.
 */
public class Apple2 {

    public static void main(String[] args) {
        String[][] strings=new String[][]{
                {"The"},
                {"dog","cat"},
                {"runs","walks"},
                {"quickly","slowly"},
                {"."}
        };
        expand(new StringBuilder(),strings,0);
    }

    public static void expand(StringBuilder builder,String[][] strings,int i){
        for(int j=0;j<strings[i].length;j++){
            StringBuilder next=new StringBuilder(builder).append(strings[i][j]);
            //一定要注意i++与i+1的区别，在循环里面写i++一定要谨慎！！！一次循环对i的改变会影响后面的循环！！
//            i++;
            if(i+1<strings.length){
                //next只会被使用一次，所以这两个没有区别
                expand(new StringBuilder(next.append(" ")),strings,i+1);
//                expand(new StringBuilder(next).append(" "),strings,i+1);
            }else{
                System.out.println(next);
            }
        }
    }
}
