package week8.lectures;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import week7.lectures.Graph;

public class EdgeWeightedDigraph {
	
	private final int V;
	private final Bag<DirectedEdge>[] adj;
	private int E;
	
	@SuppressWarnings("unchecked")
	public EdgeWeightedDigraph(int V) {
		this.V = V; this.E = 0;
		adj = (Bag<DirectedEdge>[]) new Bag[V];
		for (int v = 0; v < V; v++)
			adj[v] = new Bag<DirectedEdge>();
	}
	
	public EdgeWeightedDigraph(In in) {
		this(in.readInt());                           // Read V and construct this graph.
		int E = in.readInt();                         // Read E.
		for (int i = 0; i < E; i++) {                 // Add an edge.
			int v = in.readInt();                     // Read a vertex,
			int w = in.readInt();                     // read another vertex,
			double weight = in.readDouble();          // read weight,
			addEdge(new DirectedEdge(v, w, weight));  // and add edge connecting them.
		}
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public void addEdge(DirectedEdge e) {
		int v = e.from();
		adj[v].add(e);
		E++;
	}
	
	public Iterable<DirectedEdge> adj(int v) {
		return adj[v];
	}
	
	public Iterable<DirectedEdge> edges() {
		Bag<DirectedEdge> graphEdges = new Bag<>();
		for (Bag<DirectedEdge> vertexEdges : adj)
			for (DirectedEdge edge : vertexEdges)
				graphEdges.add(edge);
		return graphEdges;
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
