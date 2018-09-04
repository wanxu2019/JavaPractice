package tests;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Json Wan on 2018/9/4.
 * Description: 阿里2018秋招模拟笔试编程题
 */
public class Test {

    private static final int MAX_PATH_LENGTH = 65535;


    public static void main(String[] args) {
        //输入
        Scanner scanner = new Scanner(System.in);

        int startX = scanner.nextInt();
        int startY = scanner.nextInt();
        Direction startDirection = Direction.valueOf(scanner.next());

        int endX = scanner.nextInt();
        int endY = scanner.nextInt();
        Direction endDirection = Direction.valueOf(scanner.next());

        int row = scanner.nextInt();
        int column = scanner.nextInt();
        int[][] map = new int[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                map[i][j] = scanner.nextInt();
            }
        }

        //实现此函数，或 完全按照自己想法进行函数编写
        int pathLength = cal(map, startDirection, startX, startY, endDirection, endX, endY,row, column);
        //输出
        System.out.print(pathLength);
    }

    private static int[] getDir(Direction direction){
        if(direction==Direction.NORTH){
            return new int[]{0,-1};
        }else if(direction==Direction.EAST){
            return new int[]{1,0};
        }else if(direction==Direction.SOUTH){
            return new int[]{0,1};
        }else if(direction==Direction.WEST) {
            return new int[]{-1,0};
        }
        return new int[]{0,0};
    }

    //左转后的新方向
    private static Direction getDirAfterTurningLeft(Direction direction){
        Direction[] newDirections=new Direction[]{Direction.WEST,Direction.NORTH,Direction.EAST,Direction.SOUTH};
        return newDirections[direction.getIndex()-1];
    }

    //右转90后的新方向
    private static Direction getDirAfterTurningRight(Direction direction){
        Direction[] newDirections=new Direction[]{Direction.EAST,Direction.SOUTH,Direction.WEST,Direction.NORTH};
        return newDirections[direction.getIndex()-1];
    }

    //右转180后的新方向
    private static Direction getDirAfterTurningRightRight(Direction direction){
        Direction[] newDirections=new Direction[]{Direction.SOUTH,Direction.WEST,Direction.NORTH,Direction.EAST};
        return newDirections[direction.getIndex()-1];
    }

    //拷贝二维数组
    private static int[][] copyMap(int[][] map){
        int rows=map.length;
        int cols=map[0].length;
        int[][] newMap=new int[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                newMap[i][j]=map[i][j];
            }
        }
        return newMap;
    }

    private static int cal(int[][] map, Direction startDirection, int startX, int startY, Direction endDirection, int endX, int endY,int row, int column) {
        //应保证每一次都要前进，否则不收敛，前进是必须的，转向是可选的
        //递归解决问题
        if (startX == endX && startY == endY) {
            //已到位，直接转向即可
            int distance=Math.abs(startDirection.getIndex()-endDirection.getIndex());
            return 2-Math.abs(distance - 2);
        }
        //还没有到位
        //根据机器人当前朝向，可以有四种选择：
        //（1）沿当前方向前进
        int d1=MAX_PATH_LENGTH;
        int[] dir=getDir(startDirection);
        int newX=startX+dir[0];
        int newY=startY+dir[1];
        if(newX>=0 && newX<column && newY>=0 && newY<row && map[newY][newX]!=1){
            int[][] newMap= copyMap(map);
            newMap[startY][startX]=1;
            d1=1+cal(newMap,startDirection,newX,newY,endDirection,endX,endY,row,column);
        }
        //（2）左转90度，前进
        int d2=MAX_PATH_LENGTH;
        Direction newDirection=getDirAfterTurningLeft(startDirection);
        dir=getDir(newDirection);
        newX=startX+dir[0];
        newY=startY+dir[1];
        if(newX>=0 && newX<column && newY>=0 && newY<row && map[newY][newX]!=1){
            int[][] newMap= copyMap(map);
            newMap[startY][startX]=1;
            d2=2+cal(newMap,newDirection,newX,newY,endDirection,endX,endY,row,column);
        }
        //（3）右转90度，前进
        int d3=MAX_PATH_LENGTH;
        newDirection=getDirAfterTurningRight(startDirection);
        dir=getDir(newDirection);
        newX=startX+dir[0];
        newY=startY+dir[1];
        if(newX>=0 && newX<column && newY>=0 && newY<row && map[newY][newX]!=1){
            int[][] newMap= copyMap(map);
            newMap[startY][startX]=1;
            d3=2+cal(newMap,newDirection,newX,newY,endDirection,endX,endY,row,column);
        }
        //（4）右转180度，前进
        int d4=MAX_PATH_LENGTH;
        newDirection=getDirAfterTurningRightRight(startDirection);
        dir=getDir(newDirection);
        newX=startX+dir[0];
        newY=startY+dir[1];
        if(newX>=0 && newX<column && newY>=0 && newY<row && map[newY][newX]!=1){
            int[][] newMap= copyMap(map);
            newMap[startY][startX]=1;
            d4=3+cal(newMap,newDirection,newX,newY,endDirection,endX,endY,row,column);
        }
        int minD=d1>d2?d2:d1;
        minD=minD>d3?d3:minD;
        minD=minD>d4?d4:minD;
        return minD;
    }

    public enum Direction {

        NORTH(1),
        EAST(2),
        SOUTH(3),
        WEST(4),
        UNKNOWN(5);

        int index;

        public int getIndex() {
            return index;
        }

        Direction(int index) {
            this.index = index;
        }

        static Direction getDirectionByIndex(int index) {
            for (Direction direction : Direction.values()) {
                if (direction.index == index) {
                    return direction;
                }
            }
            return UNKNOWN;
        }

    }
}
