package leetcode;

import java.util.HashSet;
import java.util.Stack;

/**
 * Created by Json Wan on 2018/9/22.
 * Description:
 */
public class evaluateReversePolishNotation {

    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack=new Stack<>();
        HashSet<String> set=new HashSet<>();
        set.add("+");
        set.add("-");
        set.add("/");
        set.add("*");
        for(String token:tokens){
            if(set.contains(token)){
                int numB=stack.pop();
                int numA=stack.pop();
                if(token.equals("+")){
                    stack.push(numA+numB);
                }
                else if( token.equals("-")){
                    stack.push(numA-numB);
                }
                else if( token.equals("*")){
                    stack.push(numA*numB);
                }
                else if( token.equals("/")){
                    stack.push(numA/numB);
                }
            }else{
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        System.out.println(evalRPN(new String[]{"2","1","+","3","*"}));
        System.out.println(evalRPN(new String[]{"4","13","5","/","+"}));
    }
}
