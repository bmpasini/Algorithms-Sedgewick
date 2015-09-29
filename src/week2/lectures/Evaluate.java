package week2.lectures;

public class Evaluate {
	
	public static void main(String[] args) {
		
		Stack<String> ops = new Stack<>();
		Stack<Double> vals = new Stack<>();
		
		String[] input = "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )".split(" ");
		
		for (int i = 0; i < input.length; i++) {
			String s = input[i];
			if (s.equals("("));
			else if (s.equals("+")) ops.push(s);
			else if (s.equals("*")) ops.push(s);
			else if (s.equals(")")) {
				String op = ops.pop();
				if (op.equals("+")) vals.push(vals.pop() + vals.pop());
				else if (op.equals("*")) vals.push(vals.pop() * vals.pop());
			}
			else vals.push(Double.parseDouble(s));
		}
		System.out.println(vals.pop());
	}

}
