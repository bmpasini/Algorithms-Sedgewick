package week6.lectures;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdIn;

public class WhiteList {
	
	public static void main(String[] args) {
		
		SET<String> set = new SET<>();
		
		In in = new In(args[0]);
		while (!in.isEmpty())
			set.add(in.readString());
		
		while (!StdIn.isEmpty()) {
			String word = StdIn.readString();
			if (set.contains(word))
					System.out.println(word);
		}
		
	}

}
