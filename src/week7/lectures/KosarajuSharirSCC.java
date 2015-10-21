package week7.lectures;

public class KosarajuSharirSCC {
	
	private boolean marked[];
	private int[] id;
	private int count;
	
	public KosarajuSharirSCC(Digraph G) {
		this.marked = new boolean[G.V()];
		this.id = new int[G.V()];
		this.count = 0;
		DepthFirstOrder dfo = new DepthFirstOrder(G);
		for (int v : dfo.reversePost())
			if (!marked[v]) {
				dfs(G, v);
				count++;
			}
	}
	
	public int count() {
		return count;
	}
	
	public int id(int v) {
		return id[v];
	}
	
	private void dfs(Digraph G, int v) {
		marked[v] = true;
		id[v] = count;
		for (int w : G.adj(v))
			if (!marked[w])
				dfs(G, w);
	}
	
	public boolean stronglyConnected(int v, int w) {
		return id[v] == id[w];
	}
	
}
