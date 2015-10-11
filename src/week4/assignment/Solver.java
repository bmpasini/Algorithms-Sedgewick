package week4.assignment;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
	
	private Stack<Board> solution;
	private boolean isSolvable;
	
	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial) {
		if (initial == null) throw new NullPointerException();
		puzzleSolver(initial);
	}
	
	private void puzzleSolver(Board initial) {
		
		MinPQ<Node> pq = new MinPQ<>();
		pq.insert(new Node(initial, 0, null));
		Node current;
		
		MinPQ<Node> pqTwin = new MinPQ<>();
		pqTwin.insert(new Node(initial.twin(), 0, null));

		while (!pq.min().board.isGoal() && !pqTwin.min().board.isGoal()) {
			current = pq.delMin();
			for (Board neighbor : current.board.neighbors())
				if (!stepVisited(neighbor, current))
					pq.insert(new Node(neighbor, current.moves+1, current));
			Node currentTwin = pqTwin.delMin();
			for (Board neighbor : currentTwin.board.neighbors())
				if (!stepVisited(neighbor, currentTwin))
					pqTwin.insert(new Node(neighbor, currentTwin.moves+1, currentTwin));
		}
		current = pq.delMin();
		if (isSolvable = current.board.isGoal()) {
			solution = new Stack<>();
			do solution.push(current.board);
			while ((current = current.previous) != null);
		} else
			solution = null;
	}
	
	private boolean stepVisited(Board board, Node last) {
		do
			if (board.equals(last.board))
				return true;
		while ((last = last.previous) != null);
		return false;
	}
	
	private class Node implements Comparable<Node> {
		
		private final Board board;
		private final int moves;
		private final Node previous;
		
		public Node(Board board, int moves, Node previous) {
			this.board = board;
			this.moves = moves;
			this.previous = previous;
		}
		
		public int compareTo(Node that) {
			if      (this.board.manhattan() + this.moves < that.board.manhattan() + that.moves) return -1;
			else if (this.board.manhattan() + this.moves > that.board.manhattan() + that.moves) return  1;
			else {
				if      (this.board.hamming() + this.moves < that.board.hamming() + that.moves) return -1;
				else if (this.board.hamming() + this.moves > that.board.hamming() + that.moves) return  1;
				else                                                                            return  0;
			}
		}
		
	}
	
	// is the initial board solvable?
    public boolean isSolvable() {
		return isSolvable;
	}
    
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
    	if (!isSolvable) return -1;
        return solution.size()-1;
	}
    
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
		return solution;
	}
    
    // solve a slider puzzle (given below)
    public static void main(String[] args) {
		
	}
	
}
