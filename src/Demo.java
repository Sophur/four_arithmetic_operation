import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


class Demo {
	//计算后缀表达式
    public static int evaluateExpression(String expression) {

        Stack<Integer> operandStack = new Stack<Integer>();// 存放操作数的栈
        Stack<Character> operatorStack = new Stack<Character>();// 存放运算符的栈
        // 分割算术表达式
        ArrayList<String> result = new ArrayList<String>();
        String num = "";
        for (int i = 0; i < expression.length(); i++) {
            if (Character.isDigit(expression.charAt(i))) {
                num = num + expression.charAt(i);
            } else {
                result.add(num);
                result.add(expression.charAt(i) + "");
                num = "";
            }
        }
        result.add(num);
        // 测试分割后的算术表达式
//       for(String str:result){
//       System.out.print(str+" ");
//       }
        // 后缀表达式
        for (int i = 0; i < result.size(); i++) {

            if (result.get(i).equals("+") || result.get(i).equals("-")) {
                // 如果字符串为"+"或者"-"，则执行栈中已存数据的加减乘除计算
                while (!operatorStack.isEmpty() && (operatorStack.peek() == '+' || operatorStack.peek() == '-'
                        || operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
                    processOneOperator(operandStack, operatorStack);
                }
                operatorStack.push(result.get(i).charAt(0));// 将操作符压入操作符栈中
            } else if (result.get(i).equals("*") || result.get(i).equals("/")) {
                // 如果字符串为"*"或者"/"，则执行栈中已存数据的乘除计算
                while (!operatorStack.isEmpty() && (operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
                    processOneOperator(operandStack, operatorStack);
                }
                operatorStack.push(result.get(i).charAt(0));
            } else if (result.get(i).equals("(")) {
                // 如果遇到左括号，则将左括号压入操作符栈中
                operatorStack.push('(');
            } else if (result.get(i).equals(")")) {
                // 如果遇到右括号，则计算栈中的数据，直到遇到左括号为止
                while (operatorStack.peek() != '(') {
                    processOneOperator(operandStack, operatorStack);
                }
                operatorStack.pop();// 将进行过计算的左括号弹出
            } else {
                // 如果遇到的是操作数，则将操作数直接压入操作数栈中
                operandStack.push(Integer.parseInt(result.get(i)));
            }
        }
        // 对栈中数据进行计算，知道栈为空为止
        while (!operatorStack.isEmpty()) {
            processOneOperator(operandStack, operatorStack);
        }
        // 此时操作数栈中的栈顶元素也就是计算结果
        return operandStack.pop();
    }
    /**
     * 对操作符栈顶的一个操作符进行计算
     * 
     */
    public static void processOneOperator(Stack<Integer> operandStack, Stack<Character> operatorStack) {
        char op = operatorStack.pop();
        // 取操作符的栈顶元素
        int op1 = operandStack.pop();
        // 取操作数的栈顶元素
        int op2 = operandStack.pop();
        // 取操作数的栈顶元素
        if (op == '+') {
            // 如果操作数为+，则执行两个操作数的求和操作，并将结果压入操作数栈中
            operandStack.push(op2 + op1);
        } else if (op == '-') {
            operandStack.push(op2 - op1);
        } else if (op == '*') {
            operandStack.push(op2 * op1);
        } else if (op == '/') {
            operandStack.push(op2 / op1);
        }
    }
    //主函数
    public static void main(String[] args) {
    	
    	
        System.out.println("输入要生成算术表达式的个数：");
        
        Scanner scan_input=new Scanner(System.in);
        int  n = scan_input.nextInt();
        int writer1=0;
			try {       
				
				FileWriter writer = new FileWriter("result.txt",true); 
				 //FileWriter fw = new FileWriter(writer, true); 
                 
                 
                 PrintWriter pw = new PrintWriter(writer); 
                  if(writer1==0) 
                  { 
                      pw.println("201571030132"); 
                      writer1=1; 
                  }
                  writer.close();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        for(int i=0;i<n;i++){
        	
        	ArrayList<String> list=new ArrayList<String>();  
            list.add("+");  
            list.add("-");  
            list.add("*");
            list.add("/");
            int size=list.size();  
            String[] array = (String[])list.toArray(new String[size]);  
            //for(int i=0;i<array.length;i++){  
               // System.out.println(list);  
           // }  
                //随机生成100以内的数字
                int number=(int) (Math.random()*100);
                int number1=(int) (Math.random()*100);
                int number2=(int) (Math.random()*100);
                int number3=(int) (Math.random()*100);
               
           //随机生成运算符
            int x=(int)(Math.random()*array.length);
            int x1=(int)(Math.random()*array.length);
            int x2=(int)(Math.random()*array.length);
            //拼接成算术式子
            String str=number+array[x]+number1+array[x1]+number2+array[x2]+number3;
            
        	
        	
            Scanner cin = new Scanner(str);	
            while (cin.hasNext()) {
                 String s = cin.next();
                 // System.out.println("分割后的字符为：");
                 if(evaluateExpression(s)<0){
                	 	i--;
                	 	continue;
                 }
                 
                 String zuihou=s + "=" + evaluateExpression(s);
                 System.out.println(zuihou);
                 //ArrayList strArray = new ArrayList (); 
                 //strArray.add(zuihou);
               //输出到文件
                try { 
                	
                	 FileWriter writer = new FileWriter("result.txt",true); 
                	 PrintWriter pw1 = new PrintWriter(writer); 
                	 pw1.println(zuihou); 
          			
          			writer.close();

          		} catch (IOException e) { 

          				e.printStackTrace(); 

          		} 
                 
                 }//while
            
        cin.close();
        
        }//for
        
    }
}
