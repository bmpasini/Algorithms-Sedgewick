package week7.lectures;

public class TwoColor {
	
	private boolean[] marked;
	private boolean[] color;
	private boolean isTwoColorable = true;
	
	public TwoColor(Graph G, int s) {
		this.marked = new boolean[G.V()];
		this.color = new boolean[G.V()];
		dfs(G, s);
	}
	
	private void dfs(Graph G, int v) {
		marked[v] = true;
		for (int w : G.adj(v))
			if (!marked[w]) {
				dfs(G, w);
				color[w] = !color[v]; 
			} else if (color[w] == color[v])
				isTwoColorable = false;
	}
	
	public boolean isBipartite() {
		return isTwoColorable;
	}
	
}
