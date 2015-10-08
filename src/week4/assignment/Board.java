package week4.assignment;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;

public final class Board {
	
	private final int N;
	private final int[][] tiles;
	private final int[][] solution;
	
	// construct a board from an N-by-N array of blocks (where blocks[i][j] = block in row i, column j)
	public Board(int[][] blocks) {
		this.N = blocks.length;
		this.tiles = new int[N][N];
		this.solution = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				this.tiles[i][j] = blocks[i][j];
				this.solution[i][j] = 3 * i + j + 1;
			}
		this.solution[N-1][N-1] = 0;
	}
	
	// board dimension N
	public int dimension() {
		return N;
	}
	
	// number of blocks out of place
	public int hamming() {
		int priority = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (tiles[i][j] != solution[i][j] && tiles[i][j] != 0)
					priority++;
		return priority;
	}
	
	// sum of Manhattan distances between blocks and goal
	public int manhattan() {
		int priority = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (tiles[i][j] != solution[i][j] && tiles[i][j] != 0) {
					int h = Math.abs((tiles[i][j] - 1) / 3 - i);
					int v = Math.abs((tiles[i][j] - 1) % 3 - j);
					priority += h + v;
				}
		return priority;
	}
	
	// is this board the goal board?
	public boolean isGoal() {
		return tiles.equals(solution);
	}
	
	// a board that is obtained by exchanging any pair of blocks
	public Board twin() {
		int i, j, k, l;
		int twin[][] = new int[N][N];
		for (int x = 0; x < N; x++)
			for (int y = 0; y < N; y++)
				twin[x][y] = tiles[x][y];
		do {
			i = StdRandom.uniform(0, 3);
			j = StdRandom.uniform(0, 3);
		} while (tiles[i][j] == 0);
		do {
			k = StdRandom.uniform(0, 3);
			l = StdRandom.uniform(0, 3);
		} while (tiles[k][l] == 0 || tiles[k][l] == tiles[i][j]);
		twin[i][j] = tiles[k][l];
		twin[k][l] = tiles[i][j];
		return new Board(twin);
	}
	
	// does this board equal y?
	public boolean equals(Object y) { 
		return toString().equals(y.toString());
	}
	
	// all neighboring boards
	public Iterable<Board> neighbors() {
		Queue<Board> q = new Queue<>();
		int i = 0, j = 0;
		for (int k = 0; k < N*N; k++) {
			i = k / 3;
			j = k % 3;
			if (tiles[i][j] == 0) break;
		}
		if (j >   0) q.enqueue(getNeighbor(i, j, i  , j-1)); // has tile on the left
		if (i >   0) q.enqueue(getNeighbor(i, j, i-1, j  )); // has tile above
		if (j < N-1) q.enqueue(getNeighbor(i, j, i  , j+1)); // has tile on the right
		if (i < N-1) q.enqueue(getNeighbor(i, j, i+1, j  )); // has tile below
		return q;
	}
	
	private Board getNeighbor(int i, int j, int iN, int jN) {
		int[][] copy = new int[N][N];
		for (int x = 0; x < N; x++)
			for (int y = 0; y < N; y++)
				copy[x][y] = tiles[x][y];
		copy[i][j] = tiles[iN][jN];
		copy[iN][jN] = tiles[i][j];
		return new Board(copy);
	}
	
	// string representation of this board
	public String toString() {
	    StringBuilder s = new StringBuilder();
	    s.append(N + "\n");
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	            s.append(String.format("%2d ", tiles[i][j]));
	        }
	        s.append("\n");
	    }
	    return s.toString();
	}
	
	// unit tests (not graded)
	public static void main(String[] args) {
		int[][] tiles = { { 2, 4, 5 }, { 1, 3, 7 }, { 6, 0, 8 } }; 
		Board board = new Board(tiles);
		System.out.println(board.hamming() == 8);
		System.out.println(board.manhattan() == 15);
		System.out.println(board.toString());
		System.out.println(board.twin().toString());
		for (Board neighbor : board.neighbors())
			System.out.println(neighbor.toString());
		System.out.println();
		System.out.println();
		int[][] tiles2 = { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } }; 
		Board board2 = new Board(tiles2);
		System.out.println(board2.hamming() == 5);
		System.out.println(board2.manhattan() == 10);
		System.out.println(board2.toString());
		System.out.println(board2.twin().toString());
		System.out.println(board2.equals(board2) == true);
		System.out.println(board2.equals(board) == false);
		for (Board neighbor : board2.neighbors())
			System.out.println(neighbor.toString());
	}

}
