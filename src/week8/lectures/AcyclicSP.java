package week8.lectures;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Topological;

public class AcyclicSP {
	
	private DirectedEdge[] edgeTo;
	private double[] distTo;
	
	public AcyclicSP(EdgeWeightedDigraph G, int s)
	{
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];
		
		for (int v = 0; v < G.V(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;
		
//		Topological topological = new Topological(G);
		Topological topological = new Topological(new Digraph(1));
		for (int v : topological.order())
			for (DirectedEdge e : G.adj(v))
				relax(e);
	}
	
	private void relax(DirectedEdge e)
	{
		int v = e.from(), w = e.to();
		if (distTo[v] + e.weight() < distTo[w]) {
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
		} 
	}
	
	public double distTo(int v)
	{ return distTo[v]; }
	
	public Iterable<DirectedEdge> pathTo(int v)
	{
		Stack<DirectedEdge> path = new Stack<>();
		for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
			path.push(e);
		return path;
	}
	
}
