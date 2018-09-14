package tests;

import java.util.*;

/**
 * Created by Json Wan on 2018/9/9.
 * Description:
 */
public class JDTest3 {

    /**
     * 三维点
     */
    class ThreeDPoint {
        public int x;
        public int y;
        public int z;

        public ThreeDPoint(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        /**
         * 计算两点间距离
         *
         * @param p
         * @return
         */
        public double distanceTo(ThreeDPoint p) {
            return Math.sqrt((p.x - this.x) * (p.x - this.x) + (p.y - this.y) * (p.y - this.y) + (p.z - this.z) * (p.z - this.z));
        }
    }

    /**
     * 二维点
     */
    static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * 计算两点间距离
         *
         * @param p
         * @return
         */
        public double distanceTo(Point p) {
            return Math.sqrt((p.x - this.x) * (p.x - this.x) + (p.y - this.y) * (p.y - this.y));
        }
    }

    //快速打印
    public static void show(String msg) {
        System.out.println(msg);
    }

    //拷贝ArrayList//OK
    public static <T> ArrayList<T> copyArrayList(ArrayList<T> list) {
        return (ArrayList<T>) list.clone();
    }

    //ArrayList切片
    public static <T> ArrayList<T> slice(ArrayList<T> list, int start, int end) {
        ArrayList<T> newList = new ArrayList<>();
        for (int i = start >= 0 ? start : 0; i < end && i < list.size(); i++) {
            newList.add(list.get(i));
        }
        return newList;
    }

    //二维数组切片
    public static int[][] slice(int[][] arr, int start, int end) {
        if (start < 0) start = 0;
        if (end > arr.length) end = arr.length;
        int[][] newArr = new int[end - start][];
        for (int i = start; i < end; i++) {
            newArr[i - start] = arr[i];
        }
        return newArr;
    }


    //一维数组切片
    public static int[] slice(int[] arr, int start, int end) {
        if (start < 0) start = 0;
        if (end > arr.length) end = arr.length;
        int[] newArr = new int[end - start];
        for (int i = start; i < end; i++) {
            newArr[i - start] = arr[i];
        }
        return newArr;
    }

    //拷贝一维数组
    public static int[] copyArray(int[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }

    //拷贝二维数组
    public static int[][] copyMap(int[][] map) {
        int rows = map.length;
        int cols = map[0].length;
        int[][] newMap = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }

    //快排
    public static int quicksort(int[] nums, int start, int end) {
        if (start >= end)
            return start;
        int x = nums[start];
        int l = start;
        int r = end;
        while (l < r) {
            while (l < r && nums[r] >= x)
                r--;
            if (l < r) {
                nums[l] = nums[r];
                l++;
            }
            while (l < r && nums[l] < x)
                l++;
            if (l < r) {
                nums[r] = nums[l];
                r--;
            }
        }
        nums[l] = x;
        quicksort(nums, start, l - 1);
        quicksort(nums, l + 1, end);
        return l;
    }

    //A-Star算法元素
    public static class Knight extends Point {
        public int step = 0;
        public int g = 0;
        public int h = 0;

        public Knight() {
            super(0, 0);
        }

        public Knight(int x, int y) {
            super(x, y);
        }

        public int getF() {
            return this.g + this.h;
        }

        public static class KnightComparator<T extends Knight> implements Comparator<T> {
            @Override
            public int compare(T o1, T o2) {
                return o1.getF() - o2.getF();
            }
        }
    }

    //A-Star算法判断点是否在棋盘内
    public static boolean isInTable(Point p, int width, int height) {
        boolean result = !(p.x < 0 || p.y < 0 || p.x >= width || p.y >= height);
        return result;
    }

    //A-Star算法估价函数
    public static double heuristic(Point currentPoint, Point targetPoint) {
        return (Math.abs(currentPoint.x - targetPoint.x) + Math.abs(currentPoint.y - targetPoint.y)) * 10;
    }

    //测试A-Star算法
    public static void testAStar() {
        int width = 8;
        int height = 8;
        int ans = 0;
        //起始点
        Point startPoint = new Point(0, 0);
        //终止点
        Point endPoint = new Point(7, 7);
        //8个移动方向
        int[][] dirs = new int[][]{
                {-2, -1},
                {-2, 1},
                {2, -1},
                {2, 1},
                {-1, -2},
                {-1, 2},
                {1, -2},
                {1, 2},
        };
        //访问记录table
        boolean visited[][] = new boolean[height][width];
        PriorityQueue<Knight> queue = new PriorityQueue<Knight>(new Knight.KnightComparator<>());
        //初始化
        Knight startKnight = new Knight(startPoint.x, startPoint.y);
        startKnight.g = startKnight.step = 0;
        startKnight.h = (int) heuristic(startPoint, endPoint);
        queue.add(startKnight);
        while (!queue.isEmpty()) {
            //t为当前格
            Knight t = queue.poll();
            visited[t.x][t.y] = true;
            if (t.x == endPoint.x && t.y == endPoint.y) {
                ans = t.step;
                break;
            }
            for (int[] dir : dirs) {
                Knight s = new Knight();
                s.x = t.x + dir[0];
                s.y = t.y + dir[1];
                if (isInTable(s, width, height) && !visited[s.x][s.y]) {
                    //当前成本
                    s.g = t.g + 1;
                    //预计还要花的成本
                    s.h = (int) heuristic(s, endPoint);
                    //已走步数
                    s.step = t.step + 1;
                    queue.add(s);
                }
            }
        }
        show("ans=" + ans);
    }

    //动规-物品
    public static class Widget {
        public String name;
        public int price;
        public int value;

        public Widget(String name, int price, int value) {
            this.name = name;
            this.price = price;
            this.value = value;
        }
    }

    //测试动态规划回溯求解
    public static List<List<Widget>> solve(ArrayList<Widget> widgets, int[][] valueMatrix) {
        List<List<Widget>> answers = new ArrayList<>();
        int widgetsNum = widgets.size();
        int capacity = valueMatrix[0].length - 1;
        if (capacity <= 0) {
//            answers.add(new ArrayList<>());
            return answers;
        }
        if (widgets.size() == 0) {
            return answers;
        }
        ArrayList<Widget> subWidgets = copyArrayList(widgets);
        subWidgets.remove(subWidgets.size() - 1);
        //相同容量下价值比前一项价值大，则说明选择了该物品
        if (valueMatrix[widgetsNum][capacity] > valueMatrix[widgetsNum - 1][capacity]) {
            int[][] subMatrix = slice(valueMatrix, 0, widgetsNum);
            for (int i = 0; i < subMatrix.length; i++) {
                subMatrix[i] = slice(subMatrix[i], 0, capacity + 1 - widgets.get(widgetsNum - 1).price);
            }
            List<List<Widget>> subAnswers = solve(subWidgets, subMatrix);
            for (List<Widget> ans : subAnswers) {
                ans.add(widgets.get(widgetsNum - 1));
                answers.add(ans);
            }
            return answers;
        }
        //相同容量下价值与前一项价值相同，则有可能选择了该物品，也有可能没选
        else {
            //如果选择了该项物品之后价值达不到当前最大价值，则说明没选，如果能力不够，肯定也没选
            if (capacity < widgets.get(widgetsNum - 1).price || widgets.get(widgetsNum - 1).value + valueMatrix[widgetsNum - 1][
                    capacity - widgets.get(widgetsNum - 1).price] < valueMatrix[widgetsNum][capacity]) {
                int[][] subMatrix = slice(valueMatrix, 0, widgetsNum);
                List<List<Widget>> subAnswers = solve(subWidgets, subMatrix);
                for (List<Widget> ans : subAnswers) {
                    //没有选这一个
//                    ans.add(widgets.get(widgetsNum-1));
                    answers.add(ans);
                }
                return answers;
            } else {
                // 两种可能
                // 没选择这一个
                int[][] sub_matrix_1 = slice(valueMatrix,0,widgetsNum);
                List<List<Widget>> sub_answers_1 = solve(subWidgets, sub_matrix_1);
                // print(sub_answers_1)
                for(List<Widget> ans : sub_answers_1) {
                    answers.add(ans);
                }
                // 选择了这一个
                int[][] sub_matrix_2 = slice(valueMatrix, 0, widgetsNum);
                for (int i = 0; i < sub_matrix_2.length; i++) {
                    sub_matrix_2[i] = slice(sub_matrix_2[i], 0, capacity + 1 - widgets.get(widgetsNum - 1).price);
                }

                List<List<Widget>> sub_answers_2 = solve(subWidgets, sub_matrix_2);
                // print(sub_answers_2)
                for (List<Widget> ans : sub_answers_2) {
                    ans.add(widgets.get(widgetsNum - 1));
                    answers.add(ans);
                }
                return answers;
            }
        }
    }

    //测试动态规划算法
    public static void testDP() {
        //最大能力
        int capacity = 10;
        //物品集合
        ArrayList<Widget> widgets = new ArrayList<>();
        widgets.add(new Widget("物品1", 2, 3));
        widgets.add(new Widget("物品2", 1, 2));
        widgets.add(new Widget("物品3", 3, 6));
        widgets.add(new Widget("物品4", 4, 3));
        widgets.add(new Widget("物品5", 5, 8));
        widgets.add(new Widget("物品6", 6, 12));
        widgets.add(new Widget("物品7", 7, 13));
        //1.生成最大价值矩阵，物品为行，能力为列，size要比能力与物品数量多1
        int[][] valueMatrix = new int[widgets.size() + 1][capacity + 1];
        //2.遍历行填充价值矩阵
        for (int i = 1; i < valueMatrix.length; i++) {
            for (int j = 1; j < capacity + 1; j++) {
                //第i个物品，对应于能力j时的最大价值
                Widget widget = widgets.get(i - 1);
                //有能力拿这个物品
                if (j >= widget.price) {
                    //选择不拿这个物品的价值
                    int v1 = valueMatrix[i - 1][j];
                    //选择拿这个物品的价值,剩给之前的所有的物品的空间就只有j-widget['price']
                    int v2 = widget.value + valueMatrix[i - 1][j - widget.price];
                    valueMatrix[i][j] = v1 > v2 ? v1 : v2;
                }
                //没有能力拿这个物品
                else {
                    valueMatrix[i][j] = valueMatrix[i - 1][j];
                }
            }
        }
        //3.取出最大价值
        show("maxValue=" + valueMatrix[widgets.size()][capacity]);
        //4.回溯求解
        List<List<Widget>> answers = solve(widgets, valueMatrix);
        for (List<Widget> answer : answers) {
            for (Widget widget : answer) {
                show(widget.name);
            }
            show("=========================");
        }
    }

    public static void main(String[] args) {
        
    }

}
