package datastructure_algorithm;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Json Wan on 2017/11/16.
 * Description:
 */
public class Train3 {

    /**
     * 打印列表
     * @param list
     * @param <T>
     */
    public static <T> void  printList(MyLinkedList<T> list){
        Iterator<T> iterator=list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    /**
     * 判断表达式是不是运算符
     * @param exp
     * @return
     */
    public static boolean isOperator(String exp){
        if(exp.equals("+")||exp.equals("-")||exp.equals("*")||exp.equals("/")||exp.equals("("))
            return true;
        return false;
    }
    /**
     * 将表达式分割为一个个的数字与符号
     * @param expression
     * @return
     */
    public static MyLinkedList<String> splitExpression(String expression){
        System.out.println("splitExpression begin:");
        MyLinkedList<String> elementList=new MyLinkedList<>();
        boolean minusSign=false;
        boolean pointSign=false;
        boolean operatorSign=false;
        boolean rightBracketSign=false;
        String element="";
        Stack<Character> bracketStack=new Stack<>();
        for(int i=0;i<expression.length();i++){
            char ch=expression.charAt(i);
            switch(ch){
                case '.':
                    if(pointSign)
                        throw new IllegalArgumentException("小数点使用不当！");
                    else if(rightBracketSign){
                        throw new IllegalArgumentException("右括号后不能有小数点！");
                    }
                    else{
                        operatorSign=false;
                        rightBracketSign=false;
                        pointSign = true;
                        element+=ch;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    if(rightBracketSign){
                        throw new IllegalArgumentException("右括号后不能有小数点！");
                    }else {
                        operatorSign = false;
                        rightBracketSign=false;
                        element += ch;
                    }
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                case ')':
                case '(':
                    //除非是左括号或负号，否则两个符号不能连在一起
                    if(ch!='('&&ch!='-') {
                        if (operatorSign)
                            throw new IllegalArgumentException("运算符使用不当！");
                    }
                    //将之前的元素存入表中
                    if(!element.equals("")) {
                        elementList.add(element);
                        System.out.println(element);
                    }
                    //清除所有标志，元素清空
                    minusSign=false;
                    pointSign=false;
                    element="";
                    //将该元素存入表中
                    elementList.add(""+ch);
                    System.out.println(ch);
                    //标志置位
                    operatorSign = true;
                    //右括号的特殊处理
                    if(ch==')') {
                            operatorSign=false;
                            rightBracketSign=true;
                            try {
                                //弹栈
                                bracketStack.pop();
                            } catch (EmptyStackException e) {
                                throw new IllegalArgumentException("括号使用不当！");
                            }
                    }
                    //左括号的特殊处理
                    else if(ch=='('){
                        //压栈
                        bracketStack.push('(');
                    }
                    //负号的特殊处理
                    else if(ch=='-'){
                        //连续两个负号
                        if(minusSign)
                            throw new IllegalArgumentException("负号使用不当！");
                        else {
                            minusSign = true;
                        }
                    }
                    if(ch!=')'){
                        rightBracketSign=false;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("非法字符！");
            }
            //最后一个字符
            if(i==expression.length()-1){
                if(operatorSign)
                    throw new IllegalArgumentException("运算符使用不当！");
                else{
                    //将之前的元素存入表中
                    if(!element.equals("")) {
                        elementList.add(element);
                        System.out.println(element);
                    }
                }
            }
        }
        MyLinkedList<String> newElementList=new MyLinkedList<>();
        //将某些特殊的负号与数结合
        for(int i=0;i<elementList.size()-1;i++){
            String ch=elementList.get(i);
            if(ch.equals("-")){
                if(i==0){
                    newElementList.add(ch+elementList.get(i+1));
                    i++;
                }else{
                    String previousCh=elementList.get(i-1);
                    if(isOperator(previousCh)) {
                        newElementList.add(ch + elementList.get(i + 1));
                        i++;
                    }else{
                        newElementList.add(ch);
                    }
                }
            }else{
                newElementList.add(ch);
                if(i==elementList.size()-2){
                    newElementList.add(elementList.get(i+1));
                }
            }
        }
        System.out.println("splitExpression end!");
        return newElementList;
    }
    /**
     *
     * 将中缀表达式转化为后缀表达式
     * @param elementList
     * @return
     */
    public static MyLinkedList<String> convertToPostfix(MyLinkedList<String> elementList){
        return elementList;
    }

    /**
     * 计算后缀表达式的值
     * @param elementList
     * @return
     */
    public static float calcPostfixExpression(MyLinkedList<String> elementList){
        return 0;
    }
    //计算表达式
    public static float calcExpression(String expression){
        //1.分割表达式为合理的数字与符号
        MyLinkedList<String> elementList=splitExpression(expression);
        if(elementList==null){
            throw new IllegalArgumentException("表达式不合法！");
        }
        //2.将中缀表达式转化为后缀表达式
        elementList=convertToPostfix(elementList);
        //3.计算后缀表达式的值
        return calcPostfixExpression(elementList);
    }
    public static void main(String[] args) {
        MyLinkedList<String> list=new MyLinkedList();
        list.add("aaaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddda");
        System.out.println(list.size());
        Iterator iterator=list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        String expression="-2.3*2+4*-5-2.4*3/2+(-2.3/2*-12/2+2)/2+5";
        System.out.println("expression="+expression);
        printList(splitExpression(expression));


    }
}
