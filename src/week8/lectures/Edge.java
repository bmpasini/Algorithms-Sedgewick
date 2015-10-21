package week8.lectures;

public class Edge implements Comparable<Edge> {
	
	private final int v;
	private final int w;
	private final double weight;

	public Edge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public int either() {
		return v;
	}
	
	public int other(int v) {
		if      (v == this.v) return w;
		else if (v == w     ) return this.v;
		else                  throw new IllegalArgumentException();
	}
	
	public int compareTo(Edge that) {
		if      (this.weight < that.weight) return -1;
		else if (this.weight > that.weight) return  1;
		else                                return  0;
	}
	
	public double weight() {
		return weight;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(v);
		s.append("-");
		s.append(w);
		s.append(" ");
		s.append(weight);
		return s.toString();
	}

}
