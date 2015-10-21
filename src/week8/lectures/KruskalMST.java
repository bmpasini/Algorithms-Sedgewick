package week8.lectures;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

public class KruskalMST {
	
	private Queue<Edge> mst = new Queue<>();
	
	public KruskalMST(EdgeWeightedGraph G) {
		
		MinPQ<Edge> pq = new MinPQ<>();
		for (Edge e : G.edges())
			pq.insert(e);
		
		UF uf = new UF(G.V());
		while (!pq.isEmpty() && mst.size() < G.V()-1) {
			Edge e = pq.delMin();
			int v = e.either(), w = e.other(v);
			if (!uf.connected(v, w)) {
				uf.union(v, w);
				pq.insert(e);
			}
		}
	}
	
	public Iterable<Edge> edges() {
		return mst;
	}

}
