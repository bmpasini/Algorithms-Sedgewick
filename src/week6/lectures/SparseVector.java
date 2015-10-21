package week6.lectures;

import java.util.HashMap;

public class SparseVector {
	
	private HashMap<Integer, Double> v;
	
	public SparseVector() {
		v = new HashMap<>();
	}
	
	public void put(int i, double x) {
		v.put(i, x);
	}
	
	public double get(int i) {
		if (!v.containsKey(i)) return 0.0;
		else return v.get(i);
	}
	
	public Iterable<Integer> indices() {
		return v.keySet();
	}
	
	public double dot(double[] that) {
		double sum = 0.0;
		for (int i : indices())
			sum += get(i) * that[i];
		return sum;
	}

}
