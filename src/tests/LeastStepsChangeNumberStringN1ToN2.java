package tests;

/**
 * Created by Json Wan on 2018/5/11.
 * Description:
 */
//        * 题目描述：
//        * 给定两个长度为 n ( 0 < n <= 8 ) 的 数字串 (由1到9构成)  ，我们希望对第一个数字串做一系列如下操作：
//        * 1、将数字串的某一位加1
//        * 2、将数字串的某一位减1
//        * 3、交换数字串中任意两个数字的位置
//        * 最终使得第一个数字串变成第二个数字串， 请问最少需要多少操作。
/**

 * 分析：
 * 首先这个问题不能被分解成相互独立的子问题，所以不能使用分治法；然后考虑这个问题可以看成是一个多阶段决策过程，
 * 具有“最优子结构”性质，并且具有“重叠子问题”特征，选择使用DP思想解决。思路如下：
 * 首先将原问题分解为第1个元素和剩下的元素两个阶段，对于S1的第一个元素和S2的第一个元素，如果相等则不需要处理，
 * 如果不同，则只有两种方式达到我们最终的目的：（1）只是用加减操作将S1【0】变为S2【0】;（2）先使用一次交换策略
 * 将S1【0】和S1【j】交换，然后使用加减操作达到最终的形式（其中j一定要是S1【0】后面的元素）。然后对于除了第一个
 * 元素之外的剩下部分其实就是一个规模-1的原问题。两个阶段之间是相互影响的，所以考虑使用DP。
 * 推广：
 * 对于S1和S2中的第i个元素，假设通过前面一步步的调整已经保证S1和S2的前i-1个元素对应相同了，那么只需要继续考虑
 * 第i个元素的转换为题。同样，对于第i个元素有两种方式达到我们的最终目的：（1）只用加减操作将S1【i】转换为S2【i】；
 * （2）先进行一次交换，然后使用加减操作。比如首先交换S1【i】和S1【j】(其中j大于i)，然后使用加减策略转换成S2【i】。
 * <p>
 * DP方程：
 * 假设Di代表将S1中从第i个元素到末尾转换为S2中的第i个元素到末尾所需要的最少操作步数；那么很显然有如下DP方程：
 * Di = Min{ Math.abs(S1[i]-S2[i])+Di+1, 1+ Math.abs(S1[j]-S2[i])+Di+1'}    0=<i<j<n
 * Di = Math.abs(S1[i]-S2[i])                                                i == n-1 （剩下最后一个元素）
 * 最优解为D1
 */
public class LeastStepsChangeNumberStringN1ToN2 {

    public static int LeastStepsForChange(int[] D, int[] source, int[] destination, int index) {
        /**@Description： 将source中[index, source.length-1]的元素转化为destination中[index,source.length-1]的元素
         * @Param D int[] ： 用于存储DP结果的数组
         * @Param source int[] ： 第一个数字串
         * @Param destination int[] ：第二个数字串
         * @Param index int ：指示当前已经处理到第index个数字
         * */
        //设置两个变量分别存储两种转换方式所需要的最少转换步骤
        int steps1 = 0;
        int steps2 = Integer.MAX_VALUE;
        if (index == (source.length - 1)) {//如果当前已经处理到最后一个元素了，那么只需要计算对应位置元素之间差的绝对值
            D[index] = Math.abs(source[index] - destination[index]);
        } else {//否则就需要进行两种方式的尝试
            steps1 = Math.abs(source[index] - destination[index]) + LeastStepsForChange(D, source, destination, index + 1);
            //因为第二种方式会尝试交换index后面的任何一个元素，并且交换之后source也会变化，所以一方面要循环，
            //另一方面还要修改source数组.
            int[] src;
            for (int j = index + 1; j < source.length; j++) {
                //首先交换source[index]和source[j]
                int temp = 0;
                temp = source[j];
                source[j] = source[index];
                source[index] = temp;
                //记录变化之后的source数组
                src = source;
                if ((1 + Math.abs(source[index] - destination[index]) + LeastStepsForChange(D, src, destination, index + 1)) < steps2) {
                    steps2 = 1 + Math.abs(source[index] - destination[index]) + LeastStepsForChange(D, src, destination, index + 1);
                }
            }
            D[index] = Math.min(steps1, steps2);
        }
        return D[index];
    }
    public static void main(String[] args) {
        int[] source = new int[]{1, 2, 3, 4};
        int[] destination = new int[]{2, 3, 4, 1};
        //存放DP结果的数组
        int[] D = new int[8];
        for (int i = 0; i < D.length; i++)
            D[i] = 0;
        LeastStepsForChange(D, source, destination, 0);
        System.out.println(D[0]);
        for(int x:D) {
            System.out.print(x+" ");
        }
    }
}
