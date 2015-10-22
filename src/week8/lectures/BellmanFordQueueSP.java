package week8.lectures;

import edu.princeton.cs.algs4.EdgeWeightedDirectedCycle;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BellmanFordQueueSP {

	private double[] distTo;
	private DirectedEdge[] edgeTo;
	private boolean[] onQ;
	private Queue<Integer> q;
	private int cost;
	private Iterable<DirectedEdge> cycle;
	
	public BellmanFordQueueSP(EdgeWeightedDigraph G, int s)
	{
		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		onQ = new boolean[G.V()];
		q = new Queue<Integer>();
		for (int v = 0; v < G.V(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;
		q.enqueue(s);
		onQ[s] = true;
		while (!q.isEmpty() && !this.hasNegativeCycle())
		{
			int v = q.dequeue();
			onQ[v] = false;
			relax(G, v);
		}
	}
	
	private void relax(EdgeWeightedDigraph G, int v)
	{
		for (DirectedEdge e : G.adj(v))
		{
			int w = e.to();
			if (distTo[w] > distTo[v] + e.weight())
			{
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if (!onQ[w])
				{
					q.enqueue(w);
					onQ[w] = true;
				}
			}
			if (cost++ % G.V() == 0)
				findNegativeCycle();
				if (hasNegativeCycle()) return;  // found a negative cycle
		}
	}
	
	private void findNegativeCycle()
	{
		int V = edgeTo.length;
		EdgeWeightedDigraph spt;
		spt = new EdgeWeightedDigraph(V);
		
		for (int v = 0; v < V; v++)
			if (edgeTo[v] != null)
				spt.addEdge(edgeTo[v]);
		
		EdgeWeightedDirectedCycle cf = new EdgeWeightedDirectedCycle(spt);
		cycle = cf.cycle();
	}
	public boolean hasNegativeCycle()
	{ return cycle != null; }
	
	public Iterable<DirectedEdge> negativeCycle()
	{ return cycle; }
	
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
