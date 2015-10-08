package week4.lectures;

import edu.princeton.cs.algs4.Queue;

public class BST<Key extends Comparable<Key>, Value> {
	
	private Node root;
	
	public BST() {}
	
	private class Node {
		
		private Key key;
		private Value val;
		private Node left, right;
		private int count;
		
		public Node (Key key, Value val) {
			this.key = key;
			this.val = val;
		}
		
	}
	
	public void put(Key key, Value val) {
		root = put(root, key, val);
	}
	
	private Node put(Node x, Key key, Value val) {
		if (x == null) return new Node(key, val);
		int cmp = key.compareTo(x.key);
		if      (cmp > 0) return put(x.left, key, val);
		else if (cmp < 0) return put(x.right, key, val);
		else                     x.val = val;
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}
	
	public Value get(Key key) {
		Node x = root;
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if      (cmp < 0) x = x.left;
			else if (cmp > 0) x = x.right;
			else              return x.val;
		}
		return null;
	}
	
	public Key floor(Key key) {
		Node x = floor(root, key);
		if (x == null) return null;
		return x.key;
	}
	
	public Node floor(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x;
		if (cmp < 0)  return floor(x.left, key);
		Node t = floor(x.right, key);
		if (t != null) return t;
		else           return x;
	}
	
	public int size() {
		return size(root);
	}
	
	public int size(Node x) {
		if (x == null) return 0;
		return x.count;
	}
	
	public int rank(Key key) {
		return rank(key, root);
	}
	
	public int rank(Key key, Node x) {
		if (x == null) return 0;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) return rank(key, x.left);
		else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
		else              return size(x.left);
	}
	
	public Iterable<Key> keys() {
		Queue<Key> q = new Queue<>();
		inorder(root, q);
		return q;
	}
	
	private void inorder(Node x, Queue<Key> q) {
		if (x == null) return;
		inorder(x.left, q);
		q.enqueue(x.key);
		inorder(x.right, q);
	}
	
	public void deleteMin() {
		root = deleteMin(root);
	}
	
	public Node deleteMin(Node x) {
		if (x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}
	
	public Node min(Node x) {
		if (x.left == null) return x;
		return min(x.left);
	}
	
	public Node max(Node x) {
		if (x.right == null) return x;
		return max(x.right);
	}
	
	public void delete(Key key) {
		root = delete(key, root);
	}
	
	public Node delete(Key key, Node x) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) return delete(key, x.left);
		else if (cmp > 0) return delete(key, x.right);
		else {
			if (x.left == null) return x.right;
			if (x.right == null) return x.left;
			// Hibbard deletion
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}

	public static void main(String[] args) {
		
		edu.princeton.cs.algs4.BST<Integer,Integer> bst = new edu.princeton.cs.algs4.BST<>();
//		BST<Integer,Integer> bst = new BST<>();
		Integer[] a = { 39, 40, 65, 51, 99, 38, 84, 75, 56, 55 };
		for (int i = 0; i < a.length; i++) bst.put(a[i],i);
		for (Integer key : bst.keys())
			System.out.print(key + " ");
	}
}
