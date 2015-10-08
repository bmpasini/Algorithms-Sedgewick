package week4.lectures;

public class MaxPQ<Key extends Comparable<Key>> {
	
	private Key[] pq;
	private int N = 0;
	
	@SuppressWarnings("unchecked")
	public MaxPQ(int capacity) {
		pq = (Key[]) new Comparable[capacity+1];
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public void insert(Key key) {
		pq[++N] = key;
		swim(N);
	}
	
	public Key delMax() {
		Key max = pq[1];
		exch(1, N--);
		sink(1);
		pq[N+1] = null; // prevent loitering
		return max;
	}
	
	private void swim(int k) {
		while (k > 1 && less(k/2, k)) {
			exch(k/2, k);
			k /= 2;
		}
	}
	
	private void sink(int k) {
		while (2*k <= N) {
			int j = 2*k;
			if (j < N && less(j, j+1)) j++;
			if (!less(k, j)) break;
			exch(k, j);
			k = j;
		}
	}
	
	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	private void exch(int i, int j) {
		Key t = pq[i]; pq[i] = pq[j]; pq[j] = t;
	}
	
	public void print() {
		for (int i = 1; i < pq.length; i++)
			System.out.print(pq[i] + " ");
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		MaxPQ<Integer> pq1 = new MaxPQ<>(13);
		Integer[] a = { 93, 75, 84, 53, 72, 23, 70, 37, 48, 42 };
		for (int i = 0; i < a.length; i++) pq1.insert(a[i]);
		
		Integer[] b = { 39, 86, 47 };
		for (int i = 0; i < b.length; i++) pq1.insert(b[i]);
		pq1.print();
		
		Integer[] c = { 84, 78, 72, 67, 56, 47, 27, 59, 43, 45 };
		MaxPQ<Integer> pq2 = new MaxPQ<>(10);
		for (int i = 0; i < c.length; i++) pq2.insert(c[i]);
		for (int i = 0; i < 3; i++) pq2.delMax();
		pq2.print();
	}
	
}
