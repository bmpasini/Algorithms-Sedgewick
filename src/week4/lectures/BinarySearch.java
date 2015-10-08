package week4.lectures;

public class BinarySearch<Key extends Comparable<Key>, Value> {
	
	private int N;
	private Key[] keys;
	private Value[] vals;
	
	public BinarySearch(int N) {
		this.N = N;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	 public Value get(Key key) {
		if (isEmpty()) return null;
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0) return vals[i];
		else return null;
	 }
	 
	public void put(String word, int i) {
		// TODO Auto-generated method stub
	}
	
	public boolean contains(String word) {
		// TODO Auto-generated method stub
		return false;
	}
	 
	 private int rank(Key key) {
		int lo = 0, hi = N-1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if (cmp < 0) hi = mid - 1;
			else if (cmp > 0) lo = mid + 1;
			else if (cmp == 0) return mid;
		}
		return lo;
	 }
	 
	public Key[] keys() {
		return keys;
	}
	 
}
