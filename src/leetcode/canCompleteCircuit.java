package leetcode;

/**
 * Created by Json Wan on 2018/10/7.
 * Description:
 * 题目描述
 There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

 You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

 Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 Note:
 The solution is guaranteed to be unique.
 思路：显然应该从一开始能省下油最多的那几站出发，如果这样都不能走完一圈，那其他的方案肯定不行；
 问题也就转变成了：寻找一个环中的最大连续段，贪心计算即可。
 */
public class canCompleteCircuit {

    public static int canCompleteCircuitBest(int[] gas, int[] cost) {
        //total记录全局是否有解，sum记录i到i+1是否可行
        int total = 0, sum = 0;
        int index = -1;
        for (int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i];        //本次消耗
            total += gas[i] - cost[i];  //总消耗
            if (sum < 0) {
                sum = 0;
                index = i;    //记录的是解的前一个位置
            }
        }
        return total >= 0 ? index + 1 : -1;//只要total>=0，肯定有解
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int i=0;
        int len=gas.length;
        int l=0;
        //找一个出发点
        while(gas[i]>=cost[i]){
            i--;
            l++;
            if(i<0)
                i=len+i;
            if(l==len) {
                //所有站的油都很充足，从任意一点出发都可以绕完一圈
                return 0;
            }
        }
        i=(i+1)%len;
        //
        int max_accumulate=0;
        int max_i=i;
        l=0;
        while(l<=len) {
            int start = i;
            int accumulate = 0;
            while (l<=len && gas[i] >= cost[i]) {
                l++;
                accumulate += gas[i] - cost[i];
                i = (i + 1) % len;
            }
            if (accumulate > max_accumulate) {
                max_accumulate = accumulate;
                max_i = start;
            }
            //往前寻找下一段连续的起始点
            while (l<=len && gas[i] < cost[i]) {
                l++;
                i = (i + 1) % len;
            }
        }
        //从max_i开始考察是否能够满足
        max_accumulate=0;
        for(i=0;i<len;i++){
            max_accumulate+=gas[max_i]-cost[max_i];
            max_i = (max_i + 1) % len;
            if(max_accumulate<0)
                return -1;
        }
        return max_i;
    }



    public static void main(String[] args) {
        int[] gas=new int[]{5,6,1,2,3,4};
        int[] cost=new int[]{5,6,2,3,4,1};
        System.out.println(canCompleteCircuit(gas,cost));
    }
}
