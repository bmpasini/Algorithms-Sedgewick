package week4.lectures;

import java.util.NoSuchElementException;

public class MinPQ<Key extends Comparable<Key>> {
	
	private Key[] pq;
	private int N = 0;
	
	@SuppressWarnings("unchecked")
	public MinPQ(int capacity) {
		pq = (Key[]) new Comparable[capacity+1];
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public Key min() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }
	
	public void insert(Key key) {
		pq[++N] = key;
		swim(N);
	}
	
	public Key delMin() {
		if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
		Key min = pq[1];
		exch(1, N--);
		sink(1);
		pq[N+1] = null; // prevent loitering
		return min;
	}
	
	private void swim(int k) {
		while (k > 1 && greater(k/2, k)) {
			exch(k/2, k);
			k /= 2;
		}
	}
	
	private void sink(int k) {
		while (2*k <= N) {
			int j = 2*k;
			if (j < N && greater(j, j+1)) j++;
			if (!greater(k, j)) break;
			exch(k, j);
			k = j;
		}
	}
	
	private boolean greater(int i, int j) {
		return pq[i].compareTo(pq[j]) > 0;
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
		
	}
	
}
