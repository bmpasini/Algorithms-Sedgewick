package week2.lectures;

public class FixedCapacityQueueOfStrings {
	
	private String[] q;
	private int head = 0, tail = 0;
	
	public FixedCapacityQueueOfStrings(int capacity) {
		q = new String[capacity];
	}
	
	public boolean isEmpty() {
		return head == tail;
	}
	
	public void enqueue(String item) {
		q[tail++] = item;
	}
	
	public String dequeue() {
		String item = q[head++];
		q[head-1] = null; // avoid loitering 
		return item;
	}

}
