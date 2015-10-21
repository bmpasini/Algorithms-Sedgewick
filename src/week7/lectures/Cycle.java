package week7.lectures;

public class Cycle {

	private boolean[] marked;
	private boolean hasCycle;
	
	public Cycle(Graph G, int s) {
		this.marked = new boolean[G.V()];
		dfs(G, s, s);
	}
	
	private void dfs(Graph G, int v, int u) {
		marked[v] = true;
		for (int w : G.adj(v))
			if (!marked[w]) {
				dfs(G, w, v);
			} else if (w != u)
				hasCycle = true;
	}
	
	public boolean hasCycle() {
		return hasCycle;
	}
	
}
