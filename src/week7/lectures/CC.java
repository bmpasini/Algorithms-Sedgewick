package week7.lectures;

public class CC {
	
	private boolean marked[];
	private int[] id;
	private int count;
	
	public CC(Graph G) {
		this.marked = new boolean[G.V()];
		this.id = new int[G.V()];
		this.count = 0;
		for (int v = 0; v < G.V(); v++)
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
	
	private void dfs(Graph G, int v) {
		marked[v] = true;
		id[v] = count;
		for (int w : G.adj(v))
			if (!marked[w])
				dfs(G, w);
	}
	
}
