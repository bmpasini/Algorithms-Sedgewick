package week7.lectures;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;

public class Crawler {
	
	public static void main(String[] args) {
		
		Queue<String> q = new Queue<>();
		SET<String> marked = new SET<>();
		
		String root = "http://www.princeton.edu";
		q.enqueue(root);
		marked.add(root);
		
		while (!q.isEmpty()) {
			
			String v = q.dequeue();
			StdOut.println(v);
			In in = new In(v);
			String input = in.readAll();
			
			String regexp = "http://(\\w+\\.)*(\\w+)";
			Pattern pattern = Pattern.compile(regexp);
			Matcher matcher = pattern.matcher(input);
			
			while (matcher.find()) {
				
				String w = matcher.group();
				
				if (!marked.contains(w)) {
					q.enqueue(w);
					marked.add(w);
				}
				
			}
			
		}
		
	}
	
}
