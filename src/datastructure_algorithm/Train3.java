package datastructure_algorithm;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Scanner;
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
     * 计算符号的优先级
     * @param op
     * @return
     */
    public static short getOperatorPriority(String op){
        switch (op){
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                throw new IllegalArgumentException("参数不是正确的运算符号！");
        }
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
            }
            if(i==elementList.size()-2){
                newElementList.add(elementList.get(i+1));
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
        MyLinkedList<String> outputList=new MyLinkedList<>();
        Stack<String> stack=new Stack<>();
        Iterator<String> iterator=elementList.iterator();
        while(iterator.hasNext()){
            String ch=iterator.next();
            if(isOperator(ch)||ch.equals(")")){
                //如果是符号的话
                //如果栈为空
                if(stack.size()==0){
                    //压栈
                    stack.push(ch);
                }
                //栈不为空
                else{
                    if(ch.equals("(")){
                        //左括号特殊处理，直接压栈
                        stack.push(ch);
                    }
                    else if(ch.equals(")")){
                        //一遇到右括号应该马上弹栈
                        String tempCh;
                        do {
                            tempCh=stack.pop();
                            if(!tempCh.equals("(")) {
                                outputList.add(tempCh);
                            }
                            //不是>而是>=，因为只要当前优先级不是比栈中第一个的优先级小，就应该先算栈中的，从左到右
                        } while (!tempCh.equals("("));
                    }
                    else {
                        //比较符号与栈中上一个符号的优先级
                        String previousCh = stack.peek();
                        if(previousCh.equals("(")) {
                            //栈里的上一个是左括号，直接压栈
                            stack.push(ch);
                        }
                        else{
                            short currentPriority = getOperatorPriority(ch);
                            //如果优先级比上一个符号高
                            if (currentPriority > getOperatorPriority(previousCh)) {
                                //压栈
                                stack.push(ch);
                            } else {
                                //弹栈直到当前优先级比栈中高为止
                                do {
                                    outputList.add(stack.pop());
                                    if (stack.size() > 0) {
                                        previousCh = stack.peek();
                                        if(previousCh.equals("("))
                                            break;
                                    } else {
                                        break;
                                    }
                                    //不是>而是>=，因为只要当前优先级不是比栈中第一个的优先级小，就应该先算栈中的，从左到右
                                } while (currentPriority >= getOperatorPriority(previousCh));
                                //再把当前符号压栈
                                stack.push(ch);
                            }
                        }
                    }
                }
            }else{
                outputList.add(ch);
            }
        }
        //最后全部弹栈
        while(stack.size()>0){
            outputList.add(stack.pop());
        }
        return outputList;
    }

    /**
     * 计算后缀表达式的值
     * @param elementList
     * @return
     */
    public static float calcPostfixExpression(MyLinkedList<String> elementList){
        Stack<Float> elementStack=new Stack<>();
        for(int i=0;i<elementList.size();i++){
            String element=elementList.get(i);
            if(isOperator(element)){
                if(elementStack.size()>=2){
                    //弹出两个数用这个符号作运算
                    float num2=elementStack.pop();
                    float num1=elementStack.pop();
                    float num;
                    switch (element){
                        case "+":
                            num=num1+num2;
                            break;
                        case "-":
                            num=num1-num2;
                            break;
                        case "*":
                            num=num1*num2;
                            break;
                        case "/":
                            num=num1/num2;
                            break;
                        default:
                            throw new IllegalArgumentException("传入后缀表达式中符号有误！");
                    }
                    //再压进去
                    elementStack.push(num);
                }else{
                    throw new IllegalArgumentException("传入的后缀表达式非法！");
                }
            }else{
                //如果是数字则直接压栈
                elementStack.push(Float.parseFloat(element));
            }
        }
        return elementStack.pop();
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
        expression="1+2*3-5*2+2/1+1";
        expression="-1.2+2*-3.5-4.5/3";
        expression="1*((2+3)*4+5)+25";
        System.out.println("expression="+expression);
        System.out.println("---------------------------------------------------");
        System.out.println("after split:");
        MyLinkedList<String> elementList=splitExpression(expression);
        printList(elementList);
        System.out.println("---------------------------------------------------");
        System.out.println("after convert:");
        elementList=convertToPostfix(elementList);
        printList(elementList);
        System.out.println("---------------------------------------------------");
        System.out.println("after calc:");
        System.out.println(expression + "=" + calcPostfixExpression(elementList));
        System.out.println("---------------------------------------------------");
        System.out.println("cyclic test:");
        Scanner scanner=new Scanner(System.in);
        while(true){
            System.out.println("请输入表达式：");
            expression=scanner.next();
            try {
                System.out.println(expression + "=" + calcExpression(expression));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
