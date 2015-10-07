package week3.assignments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	
	private LineSegment[] segments;
	
	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points) {
		Point[] pts = Arrays.copyOf(points, points.length);
		checkPoints(pts);
		int N = pts.length;
		List<LineSegment> segmentsList = new ArrayList<>();
		for (int p = 0; p < N; p++) {
			for (int q = p + 1; q < N; q++) {
				double slopePQ = pts[p].slopeTo(pts[q]);
				for (int r = q + 1; r < N; r++) {
					double slopePR = pts[p].slopeTo(pts[r]);
					if (slopePQ == slopePR)
						for (int s = r + 1; s < N; s++)
							if (slopePQ == pts[p].slopeTo(pts[s]))
								segmentsList.add(new LineSegment(pts[p], pts[s]));
				}
			}
		}
		segments = segmentsList.toArray(new LineSegment[segmentsList.size()]);
	}
	
	// the number of line segments
	public int numberOfSegments() {
		return segments.length;
	}
	
	// the line segments
	public LineSegment[] segments() {
		return Arrays.copyOf(segments, numberOfSegments());
	}
	
	private void checkPoints(Point[] p) {
		if (p == null) throw new NullPointerException();
		for (int i = 0; i < p.length; i++)
			if (p[i] == null)
				throw new NullPointerException();
		if (duplicates(p)) throw new IllegalArgumentException();
	}
	
	private boolean duplicates(Point[] p) {
		Arrays.sort(p);
		for (int i = 0; i < p.length - 1; i++)
			if (p[i].equals(p[i+1]))
				return true;
		return false;
	}
	
//	public static void printArr(Object[] a) {
//		for (int i = 0; i < a.length; i++)
//			System.out.print(a[i] + " ");
//		System.out.println();
//	}
	
	public static void main(String[] args) {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(1, 1);
		Point p3 = new Point(2, 2);
		Point p4 = new Point(3, 3);
		Point[] points = { p1, p2, p3, p4 };
		BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	    }
	}
	
}
