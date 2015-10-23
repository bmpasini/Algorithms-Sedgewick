package week10.lectures;

public class TrieST<Value>
{
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
	
}
