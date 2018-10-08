package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by Json Wan on 2018/9/24.
 * Description:
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class binaryTreePostorderTraversal {

    //递归解法
    public ArrayList<Integer> postorderTraversal1(TreeNode root) {
        ArrayList<Integer> result=new ArrayList<>();
        if(root==null){
            return result;
        }else {
            result.addAll(postorderTraversal(root.left));
            result.addAll(postorderTraversal(root.right));
            result.add(root.val);
            return result;
        }
    }

    //非递归解法
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> result=new ArrayList<>();
        if(root==null){
            return result;
        }else {
            Stack<TreeNode> stack=new Stack<>();
            stack.push(root);
            while(!stack.empty()){
                TreeNode node=stack.pop();
                //注意：后续遍历应当先压左边，最后再倒序
                if(node.left!=null){
                    stack.push(node.left);
                }
                if(node.right!=null){
                    stack.push(node.right);
                }
                result.add(node.val);
            }
            Collections.reverse(result);
            return result;
        }
    }

    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result=new ArrayList<>();
        if(root==null){
            return result;
        }else {
            Stack<TreeNode> stack=new Stack<>();
            stack.push(root);
            while(!stack.empty()){
                TreeNode node=stack.pop();
                //注意：前序遍历应当先压右边，因为左边要先出栈
                if(node.right!=null){
                    stack.push(node.right);
                }
                if(node.left!=null){
                    stack.push(node.left);
                }
                result.add(node.val);
            }
            return result;
        }
    }

    public static void main(String[] args) {

    }
}
