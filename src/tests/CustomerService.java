package tests;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 题目：
 阿里巴巴客服管理员管理着n个客服小组，他需要为每一组安排客服24小时值班。为简单起见，假设每组只有2个客服，一天只需要1个客服上班，并且一些客服由于某些原因不能在同一天上班。

 我们已经对客服进行了编号，第i（i>=1&&i<=n）个组的客服编号为2*i-1和2*i。并且知道了m种如下约束关系：客服编号a和客服编号b不能一起上班。

 管理员需要聪明的你帮忙判断今天是否存在可行的方案，既满足m条约束关系，又能让每个组都有1个客服上班。

 输入：n(代表有n个组）

 m(m条约束关系），接下来会有m行
 a,b(代表a，b两位客服标号不能同时上班)

 输出：判断有没有可行方案：如果不可行输出no；如果可行输出yes

 非常典型的2-SAT模版题 如果有对SAT问题了解过应该一眼能看出
 */

public class CustomerService {

    public static ArrayList<ArrayList<Integer>> genChoices(int n) {
        ArrayList<ArrayList<Integer>> choices = new ArrayList<ArrayList<Integer>>();
        if (n == 1) {
            ArrayList<Integer> c1 = new ArrayList<>();
            c1.add(1);
            ArrayList<Integer> c2 = new ArrayList<>();
            c2.add(2);
            choices.add(c1);
            choices.add(c2);
            return choices;
        }
        ArrayList<ArrayList<Integer>> subChoices = genChoices(n - 1);
        for (ArrayList<Integer> subChoice : subChoices) {
            ArrayList<Integer> c1 = (ArrayList<Integer>) subChoice.clone();
            ArrayList<Integer> c2 = (ArrayList<Integer>) subChoice.clone();
            c1.add(2 * n - 1);
            c2.add(2 * n);
            choices.add(c1);
            choices.add(c2);
        }
        return choices;
    }

    public static void mySolution(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        Integer n = Integer.parseInt(line.trim());
        line = in.nextLine();
        Integer m = Integer.parseInt(line.trim());
        int[][] con = new int[m][2];
        for (int i = 0; i < m; i++) {
            line = in.nextLine();
            String nums[] = line.trim().split(",");
            con[i][0] = Integer.parseInt(nums[0]);
            con[i][1] = Integer.parseInt(nums[1]);
            if (con[i][0] == con[i][1] + 1 && con[i][0] % 2 == 0) {
                System.out.println("no");
                return;
            } else if (con[i][1] == con[i][0] + 1 && con[i][1] % 2 == 0) {
                System.out.println("no");
                return;
            }
        }
        ArrayList<ArrayList<Integer>> choices = genChoices(n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < choices.size(); j++) {
                ArrayList<Integer> choice = choices.get(j);
                if (choice.contains(new Integer(con[i][0])) && choice.contains(new Integer(con[i][1]))) {
                    choices.remove(choice);
                    j--;
                }
            }
        }
        if (choices.size() > 0) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    public static void main(String[] args) {
        acSolution();
    }

    /**
     * 作者：blog.lightina.cn
     链接：https://www.nowcoder.com/discuss/80777?type=2&order=0&pos=39&page=0
     来源：牛客网
     */
    public static void acSolution(){
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        //n个组
        int n = sc.nextInt();
        //m个约束
        int m = sc.nextInt();
        TwoSat t = new TwoSat();
        t.init(n);
        for (int i = 0; i < m; i++) {
            String s = sc.next();
            String[] ss = s.split(",");
            int a = Integer.valueOf(ss[0]) - 1;
            int b = Integer.valueOf(ss[1]) - 1;
            t.add_diredge(a, b ^ 1);
            t.add_diredge(b, a ^ 1);
        }
        boolean k = t.solve();
        if (k)
            pw.println("yes");
        else
            pw.println("no");
        pw.flush();
    }
}

class TwoSat {
    int[] s, dfn, low, stack, belong;
    int c;
    int n;
    boolean[] mark, instack;
    //mark[i<<1]数组等于1，表示点i被选择
    //mark[i<<1|1]数组等于1，表示点i没有被选择
    int[] head;
    int maxn = 2005; //注意是对数*2
    int maxm = 4000005;
    int[] to;
    int[] next;
    int cnt, bcount, dindex, top;

    TwoSat() {
        to = new int[maxm];
        next = new int[maxm];
        head = new int[maxn];
        s = new int[maxn];
        mark = new boolean[maxn];
        dfn = new int[maxn];
        low = new int[maxn];
        stack = new int[maxn];
        belong = new int[maxn];
        instack = new boolean[maxn];
    }

    void init(int n_) { //0~n*2
        this.n = n_;
        cnt = 0;
        Arrays.fill(head, -1);
        Arrays.fill(mark, false);
        top = 0;
        bcount = 0;
        dindex = 0;
        Arrays.fill(dfn, 0);
    }

    boolean solve() {
        for (int i = 0; i < 2 * n; i++) {
            if (dfn[i] == 0) tarjan(i);
        }
        for (int i = 0; i < 2 * n; i += 2) {
            if (belong[i] == belong[i ^ 1]) return false;
        }
        return true;
    }

    void add_diredge(int u, int v) {
        to[cnt] = v;
        next[cnt] = head[u];
        head[u] = cnt++;
    }

    void tarjan(int u) {
        dfn[u] = low[u] = ++dindex;
        stack[++top] = u;
        instack[u] = true;
        for (int i = head[u]; i != -1; i = next[i]) {
            int v = to[i];
            if (dfn[v] == 0) {
                tarjan(v);
                low[u] = Math.min(low[u], low[v]);
            } else if (instack[v] && dfn[v] < low[u]) {
                low[u] = dfn[v];
            }
        }
        if (dfn[u] == low[u]) {
            bcount++;
            int v;
            do {
                v = stack[top--];
                instack[v] = false;
                belong[v] = bcount;
            } while (u != v);
        }
    }
}