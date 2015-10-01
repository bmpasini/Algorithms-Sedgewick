package week2.assignment;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    
    private Node first, last;
    private int N;
    
    private class Node {
        Item item;
        Node previous;
        Node next;
    }
    
    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        N = 0;
    }
    
    // is the deque empty?
    public boolean isEmpty() {
        return N == 0;
    }
    
    // return the number of items on the deque
    public int size() {
        return N;
    }
    
    // add the item to the front
    public void addFirst(Item item) throws NullPointerException {
        if (item == null) throw new NullPointerException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.previous = null;
        first.next = oldFirst;
        if (isEmpty()) last = first;
        else oldFirst.previous = first;
        N++;
    }
    
    // add the item to the end
    public void addLast(Item item) throws NullPointerException {
        if (item == null) throw new NullPointerException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.previous = oldLast;
        last.next = null;
        if (isEmpty()) first = last;
        else oldLast.next = last;
        N++;
    }
    
    // remove and return the item from the front
    public Item removeFirst() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException();
        N--;
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        else first.previous = null;
        return item;
    }
    
    // remove and return the item from the end
    public Item removeLast() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException();
        N--;
        Item item = last.item;
        last = last.previous;
        if (isEmpty()) first = null;
        else last.next = null;
        return item;
    }
    
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    
    private class ListIterator implements Iterator<Item> {
        
        private Node current;
        
        public ListIterator() {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }
        
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() throws NoSuchElementException {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
        
    }
      
    // unit testing
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<>();
//      for (int i = 1; i <= 10; i++) d.addFirst(i);
//      for (int i = 1; i <= 10; i++) System.out.println(d.removeLast());
//      for (int i = 1; i <= 10; i++) d.addLast(i);
//      for (int i = 1; i <= 10; i++) System.out.println(d.removeFirst());
        for (int i = 1; i <= 10; i++) d.addFirst(i);
        Iterator<Integer> it = d.iterator();
        for (int i = 1; i <= 10; i++) System.out.println(it.next());
    }
    
}
