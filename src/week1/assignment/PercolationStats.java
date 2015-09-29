package week1.assignment;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    
    private int T;
    private double[] xt;
    
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) throws IllegalArgumentException {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();
        this.T = T;
        xt = new double[T];
        Percolation perc;
        double threshold; int rand1; int rand2;
        for (int i = 0; i < T; i++) {
            perc = new Percolation(N);
            threshold = 0;
            while (!perc.percolates()) {
                do {
                    rand1 = 1 + StdRandom.uniform(N); rand2 = 1 + StdRandom.uniform(N);
                } while (perc.isOpen(rand1, rand2));
                perc.open(rand1,rand2);
                threshold++;
            }
            xt[i] = threshold / (N*N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(xt);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(xt);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }
    
    // test client (described below)
    public static void main(String[] args) {
        PercolationStats percStats = new PercolationStats(2, 100000);
        System.out.println("mean                    = " + percStats.mean());
        System.out.println("stddev                  = " + percStats.stddev());
        System.out.println("95% confidence interval = " + percStats.confidenceLo() + ", " + percStats.confidenceHi());
    }
    
}
