package week1.lectures;

public class WeightedQuickUnionPathCompressionUF {
	
	private int[] id;
	private int[] sz;
	
	public WeightedQuickUnionPathCompressionUF(int N) {
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; i++) { id[i] = i; sz[i] = 1; }
	}
	
	public int root(int i) {
		while (i != id[i]) { 
			id[i] = id[id[i]]; // path compression
			i = id[i];
		}
		return i;
	}
	
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		if (i == j) return;
		if (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
		else 			   { id[j] = i; sz[i] += sz[j]; }
	}

	public int[] getId() {
		return id;
	}
	
	public void print() {
		int[] id = getId();
		for (int i = 0; i < 10; i++)
			System.out.print(id[i]);
		System.out.println();
	}
	
	public static void main(String[] args) {
		WeightedQuickUnionPathCompressionUF qfuf = new WeightedQuickUnionPathCompressionUF(10);
		qfuf.union(7,2);
		qfuf.print();
		qfuf.union(9,1);
		qfuf.print();
		qfuf.union(9,8);
		qfuf.print();
		qfuf.union(0,8);
		qfuf.print();
		qfuf.union(5,8);
		qfuf.print();
		qfuf.union(4,3);
		qfuf.print();
		qfuf.union(2,4);
		qfuf.print();
		qfuf.union(2,0);
		qfuf.print();
		qfuf.union(7,6);
		qfuf.print();
	}
	
}
