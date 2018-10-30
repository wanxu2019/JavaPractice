package algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Json Wan on 2018/10/18.
 * 2.求0到3之间可达的所有路径
 这里问题就是关于搜索遍历的问题,但其中需要注意到不能产生回路或环.
 算法描述如下:
 top_node:当前栈顶元素
 adjvex_node;当前top_node已经访问的邻接点
 next_node:即将访问的元素（top_node的第adjvex_node个邻接点所对应的元素）
 找出所有路径采用的是遍历的方法，以“深度优先”算法为基础。从源点出发，先到源点的第一个邻接点N00，再到N00的第一个邻接点N10，再到N10的第一个邻接点N20...当遍历到目标点时表明找到一条路径。
 上述代码的核心数据结构为一个栈，主要步骤：
 ①源点先入栈，并进行标记
 ②获取栈顶元素top_node，如果栈顶为终点时，即找到一条路径,栈顶元素top_node出栈，此时adjvex_node=top_node,新的栈顶元素为top_node，否则执行③
 ③从top_node的所有邻接点中，从adjvex_node为起点，选取下一个邻接点next_node；如果该元素非空,则入栈,使得adjvex_node=-1,(adjvex_node=-1代表top_node的邻接点一个还没有访问)做入栈标记。否则代表没有后续节点了,此时必须出栈栈顶元素,并置adjvex_node为该栈顶元素,并做出栈标记。
 ④为避免回路，已入栈元素要记录，选取新入栈顶点时应跳过已入栈的顶点,当栈为空时，遍历完成
 */

class Vertex {
    //存放点信息
    public int data;
    //与该点邻接的第一个边节点
    public Edge firstEdge;
}

//边节点
class Edge {
    //对应的点下表
    public int vertexId;
    //边的权重
    public int weight;
    //下一个边节点
    public Edge next;

    //getter and setter自行补充
    public int getVertexId() {
        return vertexId;
    }

    public void setVertexId(int vertexId) {
        this.vertexId = vertexId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Edge getNext() {
        return next;
    }

    public void setNext(Edge next) {
        this.next = next;
    }
}

class Graph {
    public Vertex[] vertexList; //存放点的集合

    public Graph(int vertexNum) {
        this.vertexNum = vertexNum;
        vertexList = new Vertex[vertexNum];
    }

    //点个数
    public int vertexNum;
    //边个数
    public int edgeLength;

    public void initVertext(int datas[]) {
        for (int i = 0; i < vertexNum; i++) {
            Vertex vertext = new Vertex();
            vertext.data = datas[i];
            vertext.firstEdge = null;
            vertexList[i] = vertext;
            //System.out.println("i"+vertexList[i]);
        }
        boolean[] isVisited = new boolean[vertexNum];
        for (int i = 0; i < isVisited.length; i++) {
            isVisited[i] = false;
        }
    }

    //针对x节点添加边节点y
    public void addEdge(int x, int y, int weight) {
        Edge edge = new Edge();
        edge.setVertexId(y);
        edge.setWeight(weight);
        //第一个边节点
//        System.out.println(vertexList.length);
        if (null == vertexList[x].firstEdge) {
            vertexList[x].firstEdge = edge;
            edge.setNext(null);
        }
        //不是第一个边节点,则采用头插法
        else {
            edge.next = vertexList[x].firstEdge;
            vertexList[x].firstEdge = edge;
        }
    }

    //得到x的邻接点为y的后一个邻接点位置,为-1说明没有找到
    public int getNextNode(int x, int y) {
        int next_node = -1;
        Edge edge = vertexList[x].firstEdge;
        if (null != edge && y == -1) {
            int n = edge.vertexId;
            //元素还不在stack中
            if (!states.get(n))
                return n;
            return -1;
        }

        while (null != edge) {
            //节点未访问
            if (edge.vertexId == y) {
                if (null != edge.next) {
                    next_node = edge.next.vertexId;
                    if (!states.get(next_node))
                        return next_node;
                } else
                    return -1;
            }
            edge = edge.next;
        }
        return -1;
    }

    //代表某节点是否在stack中,避免产生回路
    public Map<Integer, Boolean> states = new HashMap();

    //存放放入stack中的节点
    public Stack<Integer> stack = new Stack();

    //输出2个节点之间的输出路径
    public void visit(int x, int y) {
        //初始化所有节点在stack中的情况
        for (int i = 0; i < vertexNum; i++) {
            states.put(i, false);
        }
        //stack top元素
        int top_node;
        //存放当前top元素已经访问过的邻接点,若不存在则置-1,此时代表访问该top元素的第一个邻接点
        int adjvex_node = -1;
        int next_node;
        stack.add(x);
        states.put(x, true);
        while (!stack.isEmpty()) {
            top_node = stack.peek();
            //找到需要访问的节点
            if (top_node == y) {
                //打印该路径
                printPath();
                adjvex_node = stack.pop();
                states.put(adjvex_node, false);
            } else {
                //访问top_node的第advex_node个邻接点
                next_node = getNextNode(top_node, adjvex_node);
                if (next_node != -1) {
                    stack.push(next_node);
                    //置当前节点访问状态为已在stack中
                    states.put(next_node, true);
                    //临接点重置
                    adjvex_node = -1;
                }
                //不存在临接点，将stack top元素退出
                else {
                    //当前已经访问过了top_node的第adjvex_node邻接点
                    adjvex_node = stack.pop();
                    //不在stack中
                    states.put(adjvex_node, false);
                }
            }
        }
    }

    //打印stack中信息,即路径信息
    public void printPath() {
        StringBuilder sb = new StringBuilder();
        for (Integer i : stack) {
            sb.append(i + "->");
        }
        sb.delete(sb.length() - 2, sb.length());
        System.out.println(sb.toString());
    }

}

public class GraphPathSearch2 {
    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.initVertext(new int[]{1, 2, 3, 4, 4});
        //System.out.println(g.vertexList[0]);
        g.addEdge(0, 1, 1);
        g.addEdge(0, 2, 3);
        g.addEdge(0, 3, 4);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 3, 1);
//        g.addEdge(2, 0, 1);
//        g.addEdge(1, 3, 2);
        g.visit(0, 3);
    }
}
