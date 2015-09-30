package week2.lectures;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Shuffle {

	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a) {
		int N = a.length, r;
		
		for (int i = 0; i < N; i++) {
			r = StdRandom.uniform(i + 1);
			exch(a, i, r);
		}
	}
	 
	@SuppressWarnings("rawtypes")
	private static void exch(Comparable[] a, int i, int j) {
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	public static void main(String[] args) {
		Integer[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
		Shuffle.sort(a);
		for (int i = 0; i < a.length; i++)
			StdOut.println(a[i]);
	}
	
}