package leetcode;

/**
 * Created by Json Wan on 2018/9/22.
 * Description:
 Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 */

import java.util.*;

/**
 * Definition for a point.
 * class Point {
 * int x;
 * int y;
 * Point() { x = 0; y = 0; }
 * Point(int a, int b) { x = a; y = b; }
 * }
 */

/**
 40 -23
 9 138
 429 115
 50 -17
 -3 80
 -10 33
 5 -21
 -3 80
 -6 -65
 -18 26
 -6 -65
 5 72
 0 77
 -9 86
 10 -2
 -8 85
 21 130
 18 -6
 -18 26
 -1 -15
 10 -2
 8 69
 -4 63
 0 3
 -4 40
 -7 84
 -8 7
 30 154
 16 -5
 6 90
 18 -6
 5 77
 -4 77
 7 -13
 -1 -45
 16 -5
 -9 86
 -16 11
 -7 84
 1 76
 3 77
 10 67
 1 -37
 -10 -81
 4 -11
 -20 13
 -10 77
 6 -17
 -27 2
 -10 -81
 10 -1
 -9 1
 -8 43
 2 2
 2 -21
 3 82
 8 -1
 10 -1
 -9 1
 -12 42
 16 -5
 -5 -61
 20 -7
 9 -35
 10 6
 12 106
 5 -21
 -5 82
 6 71
 -15 34
 -10 87
 -14 -12
 12 106
 -5 82
 -46 -45
 -4 63
 16 -5
 4 1
 -3 -53
 0 -17
 9 98
 -18 26
 -9 86
 2 77
 -2 -49
 1 76
 -3 -38
 -8 7
 -17 -37
 5 72
 10 -37
 -4 -57
 -3 -53
 3 74
 -3 -11
 -8 7
 1 88
 -12 42
 1 -37
 2 77
 -6 77
 5 72
 -4 -57
 -18 -33
 -12 42
 -9 86
 2 77
 -8 77
 -3 77
 9 -42
 16 41
 -29 -37
 0 -41
 -21 18
 -27 -34
 0 77
 3 74
 -7 -69
 -21 18
 27 146
 -20 13
 21 130
 -6 -65
 14 -4
 0 3
 9 -5
 6 -29
 -2 73
 -1 -15
 1 76
 -4 77
 6 -29
 答案：
 25
 */

public class maxPointsOnALineBak {
    static class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    static class Line {
        Point p1;
        Point p2;

        public Line(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }

    public static boolean isOnLine(Point p, Line line) {
        return (line.p2.y - line.p1.y) * (line.p1.x - p.x) == (line.p2.x - line.p1.x) * (line.p1.y - p.y)||(line.p1.y - line.p2.y) * (line.p2.x - p.x) == (line.p1.x - line.p2.x) * (line.p2.y - p.y);
    }

    public static int maxPoints(Point[] points) {
        int l = points.length;
        int start=0;
        while(start<l-1 && points[start].x==points[start+1].x &&points[start].y==points[start+1].y)
            start++;
        if (l <= start+1) {
            return l;
        } else {
            List<Line> lines = new ArrayList<>();
            Point p1 = points[start];
            Point p2 = points[start+1];
            Line line = new Line(p1, p2);
            lines.add(line);
            HashMap<Line, Set<Point>> map = new HashMap<>();
            Set<Point> pointSet = new HashSet<>();
            for(int ii=0;ii<start;ii++)
                pointSet.add(points[ii]);
            pointSet.add(p1);
            pointSet.add(p2);
            map.put(line, pointSet);
            for (int i = start+1; i < l; i++) {
                Point p = points[i];
                if(p.x==-3 && p.y==80){
                    System.out.println("GOT");
                }
                //记录所有与该点共线的点
                Set<Point> linePointSet = new HashSet<>();
                for (Line currentLine : lines) {
                    if(currentLine.p1.x==currentLine.p2.x && currentLine.p1.y==currentLine.p2.y){
                        System.out.println("FUCK....");
                    }
                    //如果点在线上，把线上原先有的点全部记录下来，把当前点添加进线的点集
                    if (isOnLine(p, currentLine)) {
                        linePointSet.addAll(map.get(currentLine));
                        map.get(currentLine).add(p);
                    }
                }
                //不和所有已有点共线，则生成若干新的线
                //已经和某些点共线的情况下，还是应该和不共线的那些点生成新的线
                HashSet<Point> repeatPointSet=new HashSet<>();
                for (int j = 0; j < i; j++) {
                    Point lastPoint = points[j];
                    if(lastPoint.x==p.x && lastPoint.y==p.y){
                        repeatPointSet.add(lastPoint);
                    }
                }
                for (int j = 0; j < i; j++) {
                    Point lastPoint = points[j];
                    if (!linePointSet.contains(lastPoint)) {
                        Line newLine = new Line(lastPoint, p);
                        if(lastPoint.x==p.x && lastPoint.y==p.y){
                            System.out.println("FUCK=============");
                        }
                        lines.add(newLine);
                        pointSet = new HashSet<>();
                        //注意这里不应该只有两个点，还有与当前点重复的点！！！！！！！！
                        for(Point repeatPoint:linePointSet){
                            if(repeatPoint.x==p.x && repeatPoint.y==p.y) {
                                System.out.println("FUCK=============");
                                pointSet.add(repeatPoint);
                            }
                        }
                        for (int k = 0; k < i; k++) {
                            Point repeatPoint = points[k];
                            if(repeatPoint!=lastPoint && repeatPoint.x==lastPoint.x && repeatPoint.y==lastPoint.y) {
                                System.out.println("FUCK=============");
                                pointSet.add(repeatPoint);
                            }
                        }
                        pointSet.addAll(repeatPointSet);
                        pointSet.add(lastPoint);
                        pointSet.add(p);
                        map.put(newLine, pointSet);
                    }
                }
            }
            int maxValue = 0;
            Set<Point> maxSet=null;
            List<Point> maxList=new ArrayList<>();
            for (Line keyLine : map.keySet()) {
                Set<Point> set=map.get(keyLine);
                int value=set.size();
                if(value>maxValue){
                    maxValue=value;
                    maxSet=set;
                }
            }
            maxList.addAll(maxSet);
            maxList.sort(new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return o1.x-o2.x;
                }
            });

            for(Point p:maxList){
                System.out.println(""+p.x+" "+p.y);
            }
            System.out.println("=====================================");
            HashSet<Double> kNum=new HashSet<>();
            for(Line line1:lines){
                Double k=null;
                if(line1.p1.x==line1.p2.x&&line1.p1.y==line1.p2.y){

                }else if(line1.p1.x==line1.p2.x){
                    k=Double.MAX_VALUE;
                }else if(line1.p1.y==line1.p2.y){
                    k=0.0;
                }else{
                    k=1.0*(line1.p1.y-line1.p2.y)/(line1.p1.x-line1.p2.x);
                }
                if(k!=null && !kNum.contains(k))
                    kNum.add(k);
            }
            System.out.println(kNum.size());
            return maxValue;
        }
    }

    public static int bestMaxPoints(Point[] points) {
        int num=0;
        int max=0;
        List<Point> maxPointLine=null;
        if(points.length==0)return 0;
        if(points.length==1)return 1;
        HashSet<Double> kNum=new HashSet<>();
        double k=0.0;
        for(int i=0;i<points.length;i++){
            HashMap<Double, Integer> map = new HashMap<Double, Integer>();
            HashMap<Double, List<Point>> pointMap = new HashMap<>();
            List<Point> tempMaxPointLine=null;
            int temp=0;
            int tempmax=1;
            HashSet<Point> repeatPoints=new HashSet<>();
            for(int j=i+1;j<points.length;j++){

                if(points[i].y==points[j].y&&points[i].x==points[j].x){
                    temp++;
                    repeatPoints.add(points[j]);
                }
                else{
                    if(points[i].x==points[j].x){
                        k=Double.MAX_VALUE;
                    }
                    else if(points[i].y==points[j].y){
                        k=0.0;
                    }
                    else{
                        k=1.0*(points[i].y-points[j].y)/(points[i].x-points[j].x);
                    }
                    if(map.get(k)!=null){
                        num=map.get(k)+1;
                        pointMap.get(k).add(points[j]);
                    }
                    else{
                        num=2;
                        List<Point> pointListOnLine=new ArrayList<>();
                        pointListOnLine.add(points[i]);
                        pointListOnLine.add(points[j]);
                        pointMap.put(k,pointListOnLine);
                    }
                    if(num>tempmax){
                        tempmax=num;
                        tempMaxPointLine=pointMap.get(k);
                    }
                    map.put(k,num);
                    if(!kNum.contains(k))
                        kNum.add(k);
                }

            }
            if(max<(tempmax+temp)){
                max=tempmax+temp;
                maxPointLine=tempMaxPointLine;
                for(Point p:repeatPoints){
                    maxPointLine.add(p);
                }
            }
        }
        System.out.println(maxPointLine.size());
        maxPointLine.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.x-o2.x;
            }
        });
        for(Point p:maxPointLine){
            System.out.println(""+p.x+" "+p.y);
        }
        return max;
    }

    public static void main(String[] args) {
        List<Point> pointList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int N=scanner.nextInt();
        for (int i = 0; i < N; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            pointList.add(new Point(x, y));
        }
        Point[] pointsArr = new Point[pointList.size()];
        pointList.toArray(pointsArr);
        int maxNum = maxPoints(pointsArr);
        System.out.println(maxNum);
        System.out.println(bestMaxPoints(pointsArr));

        System.out.println("=======================");
        Set<Point> pointSet = new HashSet<>();
        pointSet.add(new Point(1,1));
        pointSet.add(new Point(1,1));
        pointSet.add(new Point(1,1));
        System.out.println(pointSet.size());
        System.out.println(pointSet.contains(new Point(1,1)));
    }
}
