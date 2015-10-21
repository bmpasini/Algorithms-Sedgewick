package week7.lectures;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BreadthFirstSearch {
	
	private boolean[] marked;
	private int[] edgeTo;
	private final int s;
	
	public BreadthFirstSearch(Graph G, int s) {
		this.marked = new boolean[G.V()];
		this.edgeTo = new int[G.V()];
		this.s = s;
		bfs(G, s);
	}
	
	private void bfs(Graph G, int s) {
		Queue<Integer> q = new Queue<>();
		marked[s] = true;
		q.enqueue(s);
		while (!q.isEmpty()) {
			int v = q.dequeue();
			for (int w : G.adj(v))
				if (!marked[w]) {
					q.enqueue(w);
					marked[w] = true;
					edgeTo[w] = v;
				}
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
	
}
