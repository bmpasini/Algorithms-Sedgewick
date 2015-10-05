package week3.lectures;

//import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdRandom;

public class Quick {
	
//	private static final int CUTOFF = 0;
	
	@SuppressWarnings("rawtypes")
	public static int partition(Comparable[] a, int lo, int hi) {
		int i = lo, j = hi+1;
		
		while (true) {
			while (less(a[++i], a[lo]))
				if (i == hi) break;
			
			while (less(a[lo], a[--j]))
				if (j == lo) break;
			
			if (i >= j) break;
			exch(a, i, j);
		}
		
		exch(a, lo, j);
		return j;
	}
	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a) {
//		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}
	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a, int lo, int hi) {
		// OPTIMIZATION - Use Insertion sort for arrays less than a CUTOFF value
//		if (hi <= lo + CUTOFF - 1) {
//			Insertion.sort(a, lo, hi);
//			return;
//		}
		if (hi <= lo) return;
		// OPTIMIZATION - find an item approximately in the middle of the array and make it the partitioner
//		int m = medianOf3(a, lo, lo + (hi - lo)/2, hi);
//		swap(a, lo, m);
		int j = partition(a, lo, hi);
		printArr(a);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}
	
	@SuppressWarnings("rawtypes")
	public static void threeWaySort(Comparable[] a) {
//		StdRandom.shuffle(a);
		threeWaySort(a, 0, a.length - 1);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void threeWaySort(Comparable[] a, int lo, int hi) {
		if (hi <= lo) return;
		int lt = lo, gt = hi;
		Comparable v = a[lo];
		int i = lo;
		while (i <= gt) {
			int cmp = a[i].compareTo(v);
			if      (cmp < 0) exch(a, lt++, i++);
			else if (cmp > 0) exch(a, i, gt--);
			else    i++;
 		}
		printArr(a);
		threeWaySort(a, lo, lt - 1);
		threeWaySort(a, gt + 1, hi);
	}
	
	@SuppressWarnings("rawtypes")
	public static Comparable select(Comparable[] a, int k) {
		StdRandom.shuffle(a);
		int lo = 0, hi = a.length - 1;
		while (hi > lo) {
			int j = partition(a, lo, hi);
			if      (j < k) lo = j + 1;
			else if (j > k) hi = j - 1;
			else    return a[k];
		}
		return a[k];
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
	
	public static void printArr(Object[] a) {
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}
	
	public static void main(String[] args) {
        Integer[] a = { 30, 38, 12, 68, 22, 32, 71, 65, 24, 52, 90, 95 };
        Quick.sort(a);
        String[] b = { "A", "A", "B", "B", "A", "A", "B", "B", "B", "B", "A", "B" };
        Quick.sort(b);
        Integer[] c = { 58, 58, 58, 51, 41, 39, 46, 77, 87, 86 };
        Quick.threeWaySort(c);
    }
}
