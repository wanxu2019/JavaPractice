package algorithms;

import java.util.*;

/**
 * Created by Json Wan on 2018/10/19.
 * 无向无权图的广度优先遍历
 * 存储结构：邻接表
 */
public class GraphBFS {

    static class Node {
        int name;
        List<Node> neighbors = new ArrayList<>();

        public Node(int name) {
            this.name = name;
        }

        public void addNeighbor(Node node) {
            neighbors.add(node);
        }

        @Override
        public String toString() {
            return "node"+name;
        }
    }

    public static List<Node> bfs(Node rootNode) {
        List<Node> result = new ArrayList<>();
        Set<Node> visitedNodes=new HashSet<>();
        Queue<Node> queue=new LinkedList<>();
        queue.add(rootNode);
        while(!queue.isEmpty()){
            Node node=queue.poll();
            if(!visitedNodes.contains(node)){
                result.add(node);
                visitedNodes.add(node);
                node.neighbors.stream().forEach(queue::add);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        //初始化节点
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        //建立连接//node1
        node1.addNeighbor(node2);
        node1.addNeighbor(node3);
        node1.addNeighbor(node4);
        node1.addNeighbor(node5);
        //node2
        node2.addNeighbor(node1);
        node2.addNeighbor(node4);
        node2.addNeighbor(node5);
        //node3
        node3.addNeighbor(node1);
        node3.addNeighbor(node4);
        node3.addNeighbor(node5);
        //node4
        node4.addNeighbor(node1);
        node4.addNeighbor(node2);
        node4.addNeighbor(node3);
        node4.addNeighbor(node6);
        //node5
        node5.addNeighbor(node1);
        node5.addNeighbor(node2);
        node5.addNeighbor(node3);
        node5.addNeighbor(node6);
        //node6
        node6.addNeighbor(node4);
        node6.addNeighbor(node5);
        //BFS算法
        List<Node> bfsPath = bfs(node1);
        for (Node node : bfsPath)
            System.out.print(node.name + " ");
    }
}
