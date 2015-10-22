package week8.lectures;

public class DirectedEdge implements Comparable<DirectedEdge> {
	
	private final int v, w;
	private final double weight;

	public DirectedEdge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public int from() {
		return v;
	}
	
	public int to() {
		return w;
	}
	
	public int compareTo(DirectedEdge that) {
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
		s.append("->");
		s.append(w);
		s.append(" ");
		s.append(weight);
		return s.toString();
	}

}
