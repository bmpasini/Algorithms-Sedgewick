package week2.assignment;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
	
    public static void main(String[] args) {
        
        int k = Integer.parseInt(args[0]);
        
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        
        while (!StdIn.isEmpty())
            rq.enqueue(StdIn.readString());
        
        for (int i = 1; i <= k; i++)
            StdOut.println(rq.dequeue());
        
    }
    
}
