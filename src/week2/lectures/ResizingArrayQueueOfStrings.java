package week2.lectures;

public class ResizingArrayQueueOfStrings {
	
	private String[] q;
	private int head = 0, tail = 0;
	
	public ResizingArrayQueueOfStrings() {
		q = new String[1];
	}
	
	public boolean isEmpty() {
		return head == tail;
	}
	
	public void enqueue(String item) {
		if (tail == q.length) rearranging();
		q[tail++] = item;
	}
	
	public String dequeue() {
		String item = q[head++];
		q[head-1] = null; // avoid loitering 
		return item;
	}
	
	private void rearranging() {
		if (head == 0) resizing(2 * q.length);
		tail -= head;
		for (int i = 0; i < tail; i++)
			q[i] = q[head+i];
		head = 0;
		if (tail < q.length / 4) resizing(q.length / 2);
	}
	
	private void resizing(int capacity) {
		String[] copy = new String[capacity];
		for (int i = 0; i < capacity / 2; i++)
			copy[i] = q[i];
		q = copy;
	}
	
}
