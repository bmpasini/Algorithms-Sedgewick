package week6.lectures;

public class LinearProbingHashST<Key,Value> {
	
	private int M = 30001;
	@SuppressWarnings("unchecked")
	private Value[] vals = (Value[]) new Object[M];
	@SuppressWarnings("unchecked")
	private Key[] keys = (Key[]) new Object[M];
	
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	public void put(Key key, Value val) {
		int i;
		for (i = hash(key); keys[i] != null; i = (i+1) % M)
			if (key.equals(keys[i]))
				break;
		keys[i] = key;
		vals[i] = val;
	}
	
	public Value get(Key key) {
		for (int i = hash(key); keys[i] != null; i = (i+1) % M)
			if (key.equals(keys[i]))
				return vals[i];
		return null;
	}

}
