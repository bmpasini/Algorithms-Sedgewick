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
		if (tail - head == q.length) resizing(2 * q.length);
		q[tail++ % q.length] = item;
	}
	
	public String dequeue() {
		String item = q[head++ % q.length];
		q[head % q.length - 1] = null; // avoid loitering
		if (tail - head < q.length / 4) resizing(q.length / 2);
		return item;
	}
	
	private void resizing(int capacity) {
		String[] copy = new String[capacity];
		for (int i = 0; i < capacity / 2; i++)
			copy[i] = q[(i + head % q.length) % q.length];
		q = copy; head %= q.length; tail = capacity / 2;
	}
	
	public void printQ() {
		for (int i = 0; i < q.length; i++) {
			System.out.println(q[i]);
		}
	}
	
	public static void main(String[] args) {
		ResizingArrayQueueOfStrings q = new ResizingArrayQueueOfStrings();
    	q.enqueue("a");
    	q.enqueue("b");
    	q.enqueue("c");
    	q.enqueue("d");
    	q.enqueue("e");
    	q.printQ();
	}
	
}
