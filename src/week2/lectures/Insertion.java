package week2.lectures;

import edu.princeton.cs.algs4.StdOut;

public class Insertion {
	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			for (int j = i; j > 0; j--) {
				if (less(a[j], a[j-1])) {
					exch(a, j, j-1);
					printArr(a);
				}
				else break;
			}
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
		Integer[] a = { 43, 46, 64, 84, 87, 88, 74, 85, 57, 98 };
		Insertion.sort(a);
		for (int i = 0; i < a.length; i++)
			StdOut.println(a[i]);
	}
}
