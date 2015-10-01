package week2.assignment;

import java.util.NoSuchElementException;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] q;
    private int N;
    
    // construct an empty randomized queue
    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        q = (Item[]) new Object[1];
        N = 0;
    }
    
    // is the queue empty?
    public boolean isEmpty() {
        return N == 0;
    }
    
    // return the number of items on the queue
    public int size() {
        return N;
    }
    
    // add the item
    public void enqueue(Item item) throws NullPointerException {
        if (item == null) throw new NullPointerException();
        q[N++] = item;
        if (N == q.length) resizing(2 * q.length);
    }
    
    // remove and return a random item
    public Item dequeue() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException();
        int i = StdRandom.uniform(N--);
        Item item = q[i];
        q[i] = q[N];
        q[N] = null;
        if (N == q.length / 4) resizing(q.length / 2);
        return item;
    }
    
    // return (but do not remove) a random item
    public Item sample() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException();
        return q[StdRandom.uniform(N)];
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator(q);
    }
    
    private class ListIterator implements Iterator<Item> {
        
        private int current;
        private int[] idx = new int[N];
        
        public ListIterator(Item[] q) {
            current = 0;
            for (int i = 0; i < N; i++) idx[i] = i;
            StdRandom.shuffle(idx);
        }

        @Override
        public boolean hasNext() {
            return current != N;
        }
        
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() throws NoSuchElementException {
            if (!hasNext()) throw new NoSuchElementException();
            return q[idx[current++]];
        }
        
    }
    
    private void resizing(int capacity) {
        @SuppressWarnings("unchecked")
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < capacity / 2; i++)
            copy[i] = q[i];
        N = capacity / 2;
        q = copy;
    }
    
//  public void printQ() {
//      for (int i = 0; i < q.length; i++) {
//          System.out.println(q[i]);
//      }
//  }
    
    // unit testing
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        for (int i = 1; i <= 10000; i++) q.enqueue(i);
//      q.printQ();
        for (int i = 1; i <= 10000; i++) System.out.println(q.dequeue());
        for (int i = 1; i <= 10000; i++) q.enqueue(i);
    }
    
}
