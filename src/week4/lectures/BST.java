package week4.lectures;

import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.Queue;

public class BST<Key extends Comparable<Key>, Value> {
	
	private Node root;
	
	public BST() {}
	
	private class Node {
		
		private Key key;
		private Value val;
		private Node left, right;
		private int count;
		
		public Node (Key key, Value val, int count) {
			this.key = key;
			this.val = val;
			this.count = count;
		}
		
	}
	
	public boolean isEmpty() {
        return size() == 0;
    }
	
	public int size() {
		return size(root);
	}
	
	public int size(Node x) {
		if (x == null) return 0;
		return x.count;
	}
	
	public boolean contains(Key key) {
		return get(key) != null;
	}
	
	public Value get(Key key) {
		return get(root, key);
	}
	
	private Value get(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) return get(x.left, key);
		else if (cmp > 0) return get(x.right, key);
		else              return x.val;
	}
	
	public void put(Key key, Value val) {
		if (val == null) {
			delete(key);
			return;
		}
		root = put(root, key, val);
	}
	
	private Node put(Node x, Key key, Value val) {
		if (x == null) return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) x.left  = put(x.left, key, val);
		else if (cmp > 0) x.right = put(x.right, key, val);
		else              x.val   = val;
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}
	
	public void deleteMin() {
		if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node x) {
		if (x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}
	
	public void deleteMax() {
		if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
		root = deleteMax(root);
	}
	
	private Node deleteMax(Node x) {
		if (x.right == null) return x.left;
		x.right = deleteMax(x.right);
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}
	
	public void delete(Key key) {
		root = delete(root, key);
	}
	
	private Node delete(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) x.left  = delete(x.left, key);
		else if (cmp > 0) x.right = delete(x.right, key);
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
	
	public Key min() {
		if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
		return min(root).key;
	}
	
	private Node min(Node x) {
		if (x.left == null) return x;
		return min(x.left);
	}
	
	public Key max() {
		if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
		return max(root).key;
	}
	
	private Node max(Node x) {
		if (x.right == null) return x;
		return max(x.right);
	}
	
	public Key floor(Key key) {
		if (isEmpty()) throw new NoSuchElementException("called floor() with empty symbol table");
		Node x = floor(root, key);
		if (x == null) return null;
		return x.key;
	}
	
	private Node floor(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x;
		if (cmp <  0) return floor(x.left, key);
		Node t = floor(x.right, key);
		if (t != null) return t;
		else           return x;
	}
	
	public Key ceiling(Key key) {
		if (isEmpty()) throw new NoSuchElementException("called ceiling() with empty symbol table");
		Node x = ceiling(root, key);
		if (x == null) return null;
		return x.key;
	}
	
	private Node ceiling(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x;
		if (cmp >  0) return floor(x.right, key);
		Node t = floor(x.left, key);
		if (t != null) return t;
		else           return x;
	}
	
	public Key select(int k) {
		if (k < 0 || k >= size()) throw new IllegalArgumentException("Symbol table doesn't have such element");
		Node x = select(root, k);
		return x.key;
	}
	
	private Node select(Node x, int k) {
		if (x == null) return null;
		int t = size(x.left);
		if      (t < k) return select(x.left,  k);
		else if (t > k) return select(x.right, k-t-1);
		else            return x;
	}
	
	public int rank(Key key) {
		return rank(key, root);
	}
	
	private int rank(Key key, Node x) {
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
	
	public int height() {
        return height(root);
    }
	
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }
	
	public Iterable<Key> levelOrder() {
        Queue<Key> keys = new Queue<Key>();
        Queue<Node> q = new Queue<Node>();
        q.enqueue(root);
        while(!q.isEmpty()) {
        	Node x = q.dequeue();
        	if (x == null) continue;
        	q.enqueue(x.left);
        	q.enqueue(x.right);
        	keys.enqueue(x.key);
        }
        return keys;
    }

	public static void main(String[] args) {
		BST<Integer,Integer> bst = new BST<>();
		Integer[] a = { 39, 40, 65, 51, 99, 38, 84, 75, 56, 55 };
		for (int i = 0; i < a.length; i++) bst.put(a[i],i);
		for (Integer k : bst.levelOrder())
			System.out.print(k + " ");
		System.out.println();
//		System.out.print(key + "," + bst.get(key) + " ");
	}
}
