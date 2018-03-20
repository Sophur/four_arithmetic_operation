import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


class Demo {
	//�����׺���ʽ
    public static int evaluateExpression(String expression) {

        Stack<Integer> operandStack = new Stack<Integer>();// ��Ų�������ջ
        Stack<Character> operatorStack = new Stack<Character>();// ����������ջ
        // �ָ��������ʽ
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
        // ���Էָ����������ʽ
//       for(String str:result){
//       System.out.print(str+" ");
//       }
        // ��׺���ʽ
        for (int i = 0; i < result.size(); i++) {

            if (result.get(i).equals("+") || result.get(i).equals("-")) {
                // ����ַ���Ϊ"+"����"-"����ִ��ջ���Ѵ����ݵļӼ��˳�����
                while (!operatorStack.isEmpty() && (operatorStack.peek() == '+' || operatorStack.peek() == '-'
                        || operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
                    processOneOperator(operandStack, operatorStack);
                }
                operatorStack.push(result.get(i).charAt(0));// ��������ѹ�������ջ��
            } else if (result.get(i).equals("*") || result.get(i).equals("/")) {
                // ����ַ���Ϊ"*"����"/"����ִ��ջ���Ѵ����ݵĳ˳�����
                while (!operatorStack.isEmpty() && (operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
                    processOneOperator(operandStack, operatorStack);
                }
                operatorStack.push(result.get(i).charAt(0));
            } else if (result.get(i).equals("(")) {
                // ������������ţ���������ѹ�������ջ��
                operatorStack.push('(');
            } else if (result.get(i).equals(")")) {
                // ������������ţ������ջ�е����ݣ�ֱ������������Ϊֹ
                while (operatorStack.peek() != '(') {
                    processOneOperator(operandStack, operatorStack);
                }
                operatorStack.pop();// �����й�����������ŵ���
            } else {
                // ����������ǲ��������򽫲�����ֱ��ѹ�������ջ��
                operandStack.push(Integer.parseInt(result.get(i)));
            }
        }
        // ��ջ�����ݽ��м��㣬֪��ջΪ��Ϊֹ
        while (!operatorStack.isEmpty()) {
            processOneOperator(operandStack, operatorStack);
        }
        // ��ʱ������ջ�е�ջ��Ԫ��Ҳ���Ǽ�����
        return operandStack.pop();
    }
    /**
     * �Բ�����ջ����һ�����������м���
     * 
     */
    public static void processOneOperator(Stack<Integer> operandStack, Stack<Character> operatorStack) {
        char op = operatorStack.pop();
        // ȡ��������ջ��Ԫ��
        int op1 = operandStack.pop();
        // ȡ��������ջ��Ԫ��
        int op2 = operandStack.pop();
        // ȡ��������ջ��Ԫ��
        if (op == '+') {
            // ���������Ϊ+����ִ����������������Ͳ������������ѹ�������ջ��
            operandStack.push(op2 + op1);
        } else if (op == '-') {
            operandStack.push(op2 - op1);
        } else if (op == '*') {
            operandStack.push(op2 * op1);
        } else if (op == '/') {
            operandStack.push(op2 / op1);
        }
    }
    //������
    public static void main(String[] args) {
    	
    	
        System.out.println("����Ҫ�����������ʽ�ĸ�����");
        
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
                //�������100���ڵ�����
                int number=(int) (Math.random()*100);
                int number1=(int) (Math.random()*100);
                int number2=(int) (Math.random()*100);
                int number3=(int) (Math.random()*100);
               
           //������������
            int x=(int)(Math.random()*array.length);
            int x1=(int)(Math.random()*array.length);
            int x2=(int)(Math.random()*array.length);
            //ƴ�ӳ�����ʽ��
            String str=number+array[x]+number1+array[x1]+number2+array[x2]+number3;
            
        	
        	
            Scanner cin = new Scanner(str);	
            while (cin.hasNext()) {
                 String s = cin.next();
                 // System.out.println("�ָ����ַ�Ϊ��");
                 if(evaluateExpression(s)<0){
                	 	i--;
                	 	continue;
                 }
                 
                 String zuihou=s + "=" + evaluateExpression(s);
                 System.out.println(zuihou);
                 //ArrayList strArray = new ArrayList (); 
                 //strArray.add(zuihou);
               //������ļ�
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
