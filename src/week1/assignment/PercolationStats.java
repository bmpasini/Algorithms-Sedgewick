package week1.assignment;

import java.lang.IllegalArgumentException;

public class PercolationStats {
	
	private int N, T;
	
	// perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) throws IllegalArgumentException {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();
        this.N = N;
        this.T = T;
    }

    // sample mean of percolation threshold
    public double mean() {
    	
    }

    // sample standard deviation of percolation threshold
	public double stddev() {
		
	}

	// low  endpoint of 95% confidence interval
	public double confidenceLo() {
		
	}

	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		
	}
	
	// test client (described below)
	public static void main(String[] args) {
		
	}
	
}
