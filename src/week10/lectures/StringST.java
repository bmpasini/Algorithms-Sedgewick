package week10.lectures;

import edu.princeton.cs.algs4.Queue;

public class StringST<Value> {
	
	private static final int R = 256; // extended ASCII
	private Node root = new Node();
	
	private static class Node
	{
		private Object val;
		private Node[] next = new Node[R];
	}
	
	public void put(String key, Value val)
	{ root = put(root, key, val, 0); }
	
	public Node put(Node x, String key, Value val, int d)
	{
		if (x == null) x = new Node();
		if (d == key.length()) { x.val = val; return x; }
		char c = key.charAt(d);
		x.next[c] = put(x.next[c], key, val, d+1);
		return x;
	}
	
	public boolean contains(String key)
	{ return get(key) != null; }
	
	@SuppressWarnings("unchecked")
	public Value get(String key)
	{
		Node x = get(root, key, 0);
		if (x == null) return null;
		return (Value) x.val;
	}
	
	public Node get(Node x, String key, int d)
	{
		if (x == null) return null;
		if (d == key.length()) return x;
		char c = key.charAt(d);
		return get(x.next[c], key, d+1);
	}
	
	public Iterable<String> keys()
	{
		Queue<String> keys = new Queue<>();
		collect(root, new StringBuilder(), keys);
		return keys;
	}
	
	private void collect(Node x, StringBuilder prefix, Queue<String> keys)
	{
		if (x == null) return;
		if (x.val != null) keys.enqueue(prefix.toString());
		for (char c = 0; c < R; c++)
			collect(x.next[c], prefix.append(c), keys);
	}
	
	public Iterable<String> keysWithPrefix(String prefix)
	{
		Queue<String> keys = new Queue<>();
		Node x = get(root, prefix, 0);
		collect(x, new StringBuilder(prefix), keys);
		return keys;
	}
	
	public String longestPrefixOf(String query)
	{
		int c = search(root, query, 0, 0);
		return query.substring(0, c);
	}
	
	private int search(Node x, String query, int d, int length)
	{
		if (x == null) return length;
		if (x.val != null) length = d;
		if (d == query.length()) return length;
		char c = query.charAt(d);
		return search(x.next[c], query, d+1, length);
	}
	
}
