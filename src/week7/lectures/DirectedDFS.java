package week7.lectures;

import edu.princeton.cs.algs4.Stack;

public class DirectedDFS {
	
	private boolean[] marked;
	private int[] edgeTo;
	private int s;
	private int count;
	
	public DirectedDFS(Digraph G, int s) {
		this.marked = new boolean[G.V()];
		for (int i = 0; i < G.V(); i++) marked[i] = false;
		this.edgeTo = new int[G.V()];
		this.s = s;
		dfs(G, s);
	}
	
	private void dfs(Digraph G, int v) {
		marked[v] = true;
		count++;
		for (int w : G.adj(v))
			if (!marked[w]) {
				dfs(G, w);
				edgeTo[w] = v;
			}
	}
	
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v) {
		if (!hasPathTo(v)) return null;
		Stack<Integer> path = new Stack<>();
		for (int x = v; x != s; x = edgeTo[x])
			path.push(x);
		path.push(s);
		return path;
	}
	
	public int count() {
		return count;
	}
	
}
