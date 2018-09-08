package tests;

import java.util.*;

public class AliTest2 {

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

    public static boolean isDC(ArrayList<Integer> list) {
        int d = list.get(1) - list.get(0);
        for (int i = 1; i < list.size() - 1; i++) {
            if (list.get(i + 1) - list.get(i) != d)
                return false;
        }
        return true;
    }

    public static boolean isDB(ArrayList<Integer> list) {
        for (int i = 1; i < list.size() - 1; i++) {
            if (list.get(i) * list.get(i) != list.get(i + 1) * list.get(i - 1))
                return false;
        }
        return true;
    }

    public static boolean isLegal(ArrayList<Integer> numList) {
        return isDC(numList) || isDB(numList);
    }

    public static ArrayList<ArrayList<Integer>> getSolutionList(int n) {
        ArrayList<ArrayList<Integer>> solutionList = new ArrayList<>();
        if (n < 3) {
            return null;
        } else if (n == 3) {
            ArrayList<Integer> subList = new ArrayList<>();
            subList.add(3);
            solutionList.add(subList);
            return solutionList;
        } else {
            ArrayList<ArrayList<Integer>> subSolutionList = getSolutionList(n - 1);
            for (ArrayList<Integer> solution : subSolutionList) {
                for (int i = 0; i < solution.size(); i++) {
                    ArrayList<Integer> newSolution = copyArrayList(solution);
                    int value = newSolution.get(i);
                    if (value < 5) {
                        newSolution.set(i, value + 1);
                        solutionList.add(newSolution);
                    } else {
                        ArrayList<Integer> newSolution2 = copyArrayList(solution);
                        newSolution.set(i, value + 1);
                        solutionList.add(newSolution);
                        newSolution2.set(i, value - 2);
                        newSolution2.add(3);
                        solutionList.add(newSolution2);
                    }
                }
            }
        }
        return solutionList;
    }

    public static ArrayList<ArrayList<ArrayList<Integer>>> genSubArrayList(ArrayList<Integer> numList) {
        ArrayList<ArrayList<ArrayList<Integer>>> combinationList = new ArrayList<>();
        //根据原始数组长度生成子数组大于3的所有拆分方案
        ArrayList<ArrayList<Integer>> solutionList = getSolutionList(numList.size());
        for (ArrayList<Integer> solution : solutionList) {
            for (Integer x : solution) {
                show(x + "");
            }
            show("=======");
        }

        return combinationList;
    }

    public static long solve(ArrayList<Integer> numList) {
        if (numList.size() < 3) {
            return 0;
        } else if (numList.size() == 3) {
            if (isLegal(numList))
                return 1;
            else
                return 0;
        } else {
            long count = 0;
            //取得所有的拆分数组列表
            ArrayList<ArrayList<ArrayList<Integer>>> subArrayList = genSubArrayList(numList);
            for (ArrayList<ArrayList<Integer>> subArray : subArrayList) {
                boolean flag = true;
                for (ArrayList<Integer> arr : subArray) {
                    if (!isLegal(arr)) {
                        flag = false;
                        break;
                    }
                }
                if (flag)
                    count++;
            }
            return count;
        }
    }

    public static void main(String[] args) {
        //输入读取
//        Scanner scanner = new Scanner(System.in);
//        long n=scanner.nextLong();
//        ArrayList<Integer> numList=new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            numList.add(Integer.parseInt(scanner.next()));
//        }
        //数组拆分
//        show(solve(numList)+"");
        for (int i = 7; i < 10; i++) {
            ArrayList<ArrayList<Integer>> solutionList = getSolutionList(i);
            for (ArrayList<Integer> solution : solutionList) {
                for (Integer x : solution) {
                    show(x + "");
                }
                show("=======");
            }
            show("===============================");
        }
    }
}
