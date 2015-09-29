package week1.assignment;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    
    private int N;
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) throws IllegalArgumentException {
        if (N <= 0) throw new IllegalArgumentException();
        this.N = N;
        grid = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N*N+2); // +2 due to virtual-top and virtual-bottom
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                grid[i][j] = false;
        }
    }
    
    // open site (row i, column j) if it is not open already
    public void open(int i, int j) throws IndexOutOfBoundsException {
        validateIndices(i--,j--);
        grid[i][j] = true;
        unionAdjacents(i,j);
    }
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) throws IndexOutOfBoundsException {
    	validateIndices(i--,j--);
        return grid[i][j];
    }
    
    // is site (row i, column j) full?
    public boolean isFull(int i, int j) throws IndexOutOfBoundsException {
    	validateIndices(i--,j--);
        return uf.connected(getUFIndex(i, j), N*N);
    }
    
    // does the system percolate?
    public boolean percolates() {
        return uf.connected(N*N, N*N+1);
    }
    
    private void unionAdjacents(int i, int j) {
        int index = getUFIndex(i, j);
        if (i == 0) uf.union(getUFIndex(i, j), N*N);
        if (i == N-1) uf.union(getUFIndex(i, j), N*N+1);
        if (i > 0) unionAdjacent(i-1, j, index);
        if (j > 0) unionAdjacent(i, j-1, index);
        if (i < N-1) unionAdjacent(i+1, j, index);
        if (j < N-1) unionAdjacent(i, j+1, index);
    }
    
    private void unionAdjacent(int i, int j, int index) {
        if (isOpen(i+1, j+1)) uf.union(getUFIndex(i, j), index);
    }
    
    private int getUFIndex(int i, int j) {
        return i * N + j;
    }
    
    private void validateIndices(int i, int j) throws IndexOutOfBoundsException {
        if (i <= 0 || i > N) throw new IndexOutOfBoundsException("Row index i out of bounds.");
        if (j <= 0 || j > N) throw new IndexOutOfBoundsException("Column index j out of bounds.");
    }
    
//    public void printGrid() {
//      for (int i = 0; i < N; i++) {
//          for (int j = 0; j < N; j++) {
//              System.out.print(grid[i][j] + " ");
//          }
//          System.out.println();
//      }
//    }
    
    // test client (optional)
    public static void main(String[] args) {
        Percolation perc = new Percolation(3);
        perc.open(1, 2);
        perc.open(2, 2);
        perc.open(3, 1);
        perc.open(2, 1);
//        perc.printGrid();
        StdOut.println(perc.isFull(2, 1)); // true
        StdOut.println(perc.isFull(3, 2)); // false
        StdOut.println(perc.percolates()); // true
    }
    
}
