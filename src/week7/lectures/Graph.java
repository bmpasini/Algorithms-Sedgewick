package week7.lectures;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Graph {
	
	private final int V;
	private int E;
	private Bag<Integer>[] adj;
	
	@SuppressWarnings("unchecked")
	public Graph(int V) {
		this.V = V; this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++)
			adj[v] = new Bag<Integer>();
	}
	
	public Graph(In in) {
		this(in.readInt());           // Read V and construct this graph.
		int E = in.readInt();         // Read E.
		for (int i = 0; i < E; i++) { // Add an edge.
			int v = in.readInt();     // Read a vertex,
			int w = in.readInt();     // read another vertex,
			addEdge(v, w);            // and add edge connecting them.
		}
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
	
	@SuppressWarnings("unused")
	public static int degree(Graph G, int v) {
		int degree = 0;
		for (int w : G.adj(v)) degree++;
		return degree;
	}
	
	public static int maxDegree(Graph G) {
		int max = 0;
		for (int v = 0; v < G.V(); v++)
			if (degree(G, v) > max)
				max = degree(G, v);
		return max;
	}
	
	public static double averageDegree(Graph G) {
		return 2.0 * G.E() / G.V();
	}
	
	public static int numberOfSelfLoops(Graph G) {
		int count = 0;
		for (int v = 0; v < G.V(); v++)
			for (int w : G.adj(v))
				if (v == w) count ++;
		return count;
	}
	
}
