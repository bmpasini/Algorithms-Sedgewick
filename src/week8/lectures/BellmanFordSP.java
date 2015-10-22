package week8.lectures;

import edu.princeton.cs.algs4.Stack;

public class BellmanFordSP {
	
	private DirectedEdge[] edgeTo;
	private double[] distTo;
	
	public BellmanFordSP(EdgeWeightedDigraph G, int s)
	{
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];
		
		for (int i = 0; i < G.V(); i++)
			for (int v = 0; v < distTo.length; v++)
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
