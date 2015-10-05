package week3.lectures;

//import edu.princeton.cs.algs4.Insertion;

public class Merge {
	
//	private static final int CUTOFF = 0;
	
	@SuppressWarnings("rawtypes")
	public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];
		
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if      (i > mid)              a[k] = aux[j++];
			else if (j > hi)               a[k] = aux[i++];
			else if (less(aux[j], aux[i])) a[k] = aux[j++];
			else                           a[k] = aux[i++];
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		// OPTIMIZATION - Use Insertion sort for arrays less than a CUTOFF value
//		if (hi <= lo + CUTOFF - 1) {
//			Insertion.sort(a, lo, hi);
//			return;
//		}
		if (hi <= lo) return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid+1, hi);
//		if (!less(a[mid+1],a[mid])) return;
		merge(a, aux, lo, mid, hi);
		printArr(a);
	}
	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a) {
		Comparable aux[] = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static boolean less(Comparable i, Comparable j) {
		return i.compareTo(j) < 0;
	}
	
	public static void printArr(Object[] a) {
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}
	
	public static void main(String[] args) {
        Integer[] a = { 35, 78, 40, 94, 80, 45, 90, 31, 32, 17, 52, 24 };
        Merge.sort(a);
    }
	
}
