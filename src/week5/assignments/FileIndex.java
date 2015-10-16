package week5.assignments;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FileIndex {

	public static void main(String[] args) {
		
		ST<String, SET<File>> st = new ST<>();
		
		for (String filename : args) {
			File file = new File(filename);
			In in = new In(file);
			while (!in.isEmpty()) {
				String key = in.readString();
				if (!st.contains(key))
					st.put(key, new SET<File>());
				SET<File> set = st.get(key); 
				set.add(file);
			}
		}
		
		while (!StdIn.isEmpty()) {
			String query = StdIn.readString();
			StdOut.println(st.get(query));
		}
		
	}
	
}
