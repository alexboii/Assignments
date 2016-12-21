import java.util.Scanner;
import java.util.Stack;

public class EvaluateExpression {

	public static void main(String[] args)
	{
		
		while(true){
			
			System.out.println("Enter an expression or press Enter to exit:");
			
			Scanner keyboard = new Scanner(System.in);
			
			String temp = keyboard.nextLine();
		
		
			if(temp.equals(""))
			{
				System.exit(0);
			}
			else{
				System.out.println("Input expression: " + temp);
				evaluateExpression(temp);
			}
	}
		
	}
	

	public static int evaluateExpression(String expression){
		
		Stack<Integer> operandStack = new Stack<Integer>();
		Stack <Character> operatorStack = new Stack<Character>();
		
		expression = insertBlanks(expression);
		
		String[] tokens = expression.split(" ");
		
		for(String token: tokens){
			if(token.length() == 0)
				continue;
			else if(token.charAt(0) == '+' || token.charAt(0) == '-'){
				while(!operatorStack.isEmpty() && operatorStack.peek() == '+' && operatorStack.peek() == '-' && operatorStack.peek() == '*' && operatorStack.peek() == '/'){
					processOperator(operandStack, operatorStack);
				}
				
				operatorStack.push(token.charAt(0));
				
			}
			else if(token.charAt(0) == '*' || token.charAt(0) == '/'){
				while(!operatorStack.isEmpty() && operatorStack.peek() == '*' && operatorStack.peek() == '/'){
					processOperator(operandStack, operatorStack);
				}
			}
			else if(token.trim().charAt(0) == '('){
				operatorStack.push('(');
			}
			else if(token.trim().charAt(0) == ')'){
				while(operatorStack.peek() != '('){
					processOperator(operandStack, operatorStack);
				}
				
				operatorStack.pop();
			}
			else{
				operandStack.push(new Integer(token));
			}
		}
		
		return operandStack.pop();
	}

	private static String insertBlanks(String expression) {
		String result = "";
		
		for (int i = 0; i< expression.length(); i++){
			if(expression.charAt(i) == '(' || expression.charAt(i) == ')' || expression.charAt(i) == '+' || 
			   expression.charAt(i) == '-' || expression.charAt(i) == '*' || expression.charAt(i) == '/')
			result += "" + expression.charAt(i) + " ";
		else
			result += expression.charAt(i);
		}
		
		return result;
	}

	private static void processOperator(Stack<Integer> operandStack,
			Stack<Character> operatorStack) {
			char op = operatorStack.pop();
			int op1 = operandStack.pop();
			int op2 = operandStack.pop();
			
			if (op == '+')
				operandStack.push(op2 + op1);
			else if(op == '-')
				operandStack.push(op2 - op1);
			else if(op == '*')
				operandStack.push(op2 * op1);	
			else if(op == '/')
				operandStack.push(op2 / op1);	
		
	}

}