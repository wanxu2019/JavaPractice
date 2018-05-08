package datastructure_algorithm;

/**
 * Created by Json Wan on 2018/5/7.
 * Description:
 */
public class FloydTest {
    public static void show(int[][] m){
        for(int i=0;i<m.length;i++){
            for(int j=0;j<m[i].length;j++){
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void main(String[] args) {
        final int MAX_VALUE=65535;
        int d[][]={
                {0,1,5,MAX_VALUE,MAX_VALUE,MAX_VALUE,MAX_VALUE,MAX_VALUE,MAX_VALUE},
                {1,0,3,7,5,MAX_VALUE,MAX_VALUE,MAX_VALUE,MAX_VALUE},
                {5,3,0,MAX_VALUE,1,7,MAX_VALUE,MAX_VALUE,MAX_VALUE},
                {MAX_VALUE,7,MAX_VALUE,0,2,MAX_VALUE,3,MAX_VALUE,MAX_VALUE},
                {MAX_VALUE,5,1,2,0,3,6,9,MAX_VALUE},
                {MAX_VALUE,MAX_VALUE,7,MAX_VALUE,3,0,MAX_VALUE,5,MAX_VALUE},
                {MAX_VALUE,MAX_VALUE,MAX_VALUE,3,6,MAX_VALUE,0,2,7},
                {MAX_VALUE,MAX_VALUE,MAX_VALUE,MAX_VALUE,9,5,2,0,4},
                {MAX_VALUE,MAX_VALUE,MAX_VALUE,MAX_VALUE,MAX_VALUE,MAX_VALUE,7,4,0},
        };
        int p[][]={
                {0,1,2,3,4,5,6,7,8},
                {0,1,2,3,4,5,6,7,8},
                {0,1,2,3,4,5,6,7,8},
                {0,1,2,3,4,5,6,7,8},
                {0,1,2,3,4,5,6,7,8},
                {0,1,2,3,4,5,6,7,8},
                {0,1,2,3,4,5,6,7,8},
                {0,1,2,3,4,5,6,7,8},
                {0,1,2,3,4,5,6,7,8}
        };
        for(int k=0;k<9;++k){
            for(int v=0;v<9;++v){
                for(int w=0;w<9;++w){
                    if(d[v][w]>d[v][k]+d[k][w]){
                        d[v][w]=d[v][k]+d[k][w];
//                        p[v][w]=p[v][k];
                        p[v][w]=k;
                    }
                }
            }
        }
        show(d);
        show(p);
    }
}
