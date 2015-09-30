package week2.lectures;

import edu.princeton.cs.algs4.StdOut;

public class Shell {
	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a) {
		int N = a.length, h;
		
		for (h = 1; h < N/3; h = 3*h+1);
		
		while (h >= 1) {
			for (int i = h; i < N; i++) {
				for (int j = i; j >= h && less(a[j], a[j-h]) ; j -= h)
					exch(a, j, j-h);
			}
			System.out.println("h = " + h + ":"); printArr(a);
			h /= 3;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static boolean less(Comparable i, Comparable j) {
		return i.compareTo(j) < 0;
	}
	 
	@SuppressWarnings("rawtypes")
	private static void exch(Comparable[] a, int i, int j) {
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	private static void printArr(Object[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Integer[] a = { 26, 90, 10, 96, 16, 22, 93, 87, 60, 14 };
		Shell.sort(a);
		for (int i = 0; i < a.length; i++)
			StdOut.println(a[i]);
	}
	
}
