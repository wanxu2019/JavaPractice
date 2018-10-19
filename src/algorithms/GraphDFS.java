package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Json Wan on 2018/10/19.
 * 无向无权图的深度优先遍历
 * 存储结构：邻接表
 */
public class GraphDFS {

    static class Node {
        int name;
        List<Node> neighbors = new ArrayList<>();

        public Node(int name) {
            this.name = name;
        }

        public void addNeighbor(Node node) {
            neighbors.add(node);
        }
    }

    public static List<Node> dfs(Node rootNode) {
        return dfs(rootNode, new HashSet<Node>());
    }

    public static List<Node> dfs(Node rootNode, Set<Node> visitedNodes) {
        List<Node> result = new ArrayList<>();
        result.add(rootNode);
        visitedNodes.add(rootNode);
        rootNode.neighbors.stream().filter(node -> !visitedNodes.contains(node)).forEach(node -> {
            result.addAll(dfs(node, visitedNodes));
        });
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
        //DFS算法
        List<Node> dfsPath = dfs(node1);
        for (Node node : dfsPath)
            System.out.print(node.name + " ");
    }
}
