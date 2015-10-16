package week5.assignments;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Concordance {

	public static void main(String[] args) {
		
		In in = new In(args[0]);
		String[] words = in.readAll().split("\\s+");
		ST<String, SET<Integer>> st = new ST<>();
		
		for (int i = 0; i < words.length; i++) {
			String s = words[i];
			if (!st.contains(s))
				st.put(s, new SET<Integer>());
			SET<Integer> set = st.get(s);
			set.add(i);
		}
		
		while (!StdIn.isEmpty()) {
			String query = StdIn.readString();
			SET<Integer> set = st.get(query);
			for (int k : set) {
				for (int i = k - 4; i <= k + 4; i++) {
					StdOut.println(words[i]);
				}
			}
		}
				
		
	}
}
