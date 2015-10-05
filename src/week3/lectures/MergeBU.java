package week3.lectures;

public class MergeBU {
	
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
	public static void sort(Comparable[] a) {
		int N = a.length;
		Comparable[] aux = new Comparable[N];
		for (int sz = 1; sz < N; sz = sz+sz)
			for (int lo = 0; lo < N-sz; lo += sz+sz) {
				merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
				printArr(a);
			}
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
		Integer[] a = { 92, 62, 19, 28, 42, 59, 35, 87, 34, 41 };
        MergeBU.sort(a);
    }
	
}
