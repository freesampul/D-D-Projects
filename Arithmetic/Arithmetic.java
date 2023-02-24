import java.util.Scanner;

public class Arithmetic {

	
	static Stack<Integer> numbers = new Stack<>();
	static Stack<String> operations = new Stack<>();
	
	
	
	//Evaluates a String exp that has an arithmetic expression, written in classic notation
	public static int evaluate(String exp) {
		String store = convertClassicToStout(exp);
		return evaluateStout(store);
	}
	
	//Returns the result of doing operand1 operation operand2,
	//e.g. operate(5, 2, "-") should return 3
	public static int operate(int operand1, int operand2, String operation) {
		if(operation.equals("+"))
			return operand1 + operand2;
		if(operation.equals("-"))
			return operand1 - operand2;
		if(operation.equals("*"))
			return operand1 * operand2;
		if(operation.equals("/"))
			return operand1 / operand2;
		else
			return 0;
	}
	
	//Evaluates a String exp that has an arithmetic expression written in STOUT notation
	public static int evaluateStout(String exp) {
		Scanner input = new Scanner (exp);
		
		while(input.hasNext()) {
			if(input.hasNextInt())
				numbers.push(input.nextInt());
			else 
			{
				int num2 = numbers.pop();
				int num1 = numbers.pop();
				numbers.push(operate(num1, num2, input.next()));
			}
		}
		input.close();
		return numbers.pop();
	}
	
	public static String convertClassicToStout(String exp) {
		String stout = "";
		Scanner input = new Scanner (exp);
		while(input.hasNext()) {
			if(input.hasNextInt())
			{
				stout += input.next() + " ";
			}
			String singleChar = input.next();
			if(singleChar.equals(")")) 
				stout += operations.pop()+ " ";
			if(!singleChar.equals(")") && !singleChar.equals("("))
				operations.push(singleChar);
		}
		input.close();
		return stout.trim();
	}
	
}
