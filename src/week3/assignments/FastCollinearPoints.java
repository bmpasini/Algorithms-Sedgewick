package week3.assignments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	
	private List<LineSegment> segmentsList = new ArrayList<>();
	private Map<Double, List<Point>> lastPointsHash = new HashMap<>();
	
	// finds all line segments containing 4 or more points
	public FastCollinearPoints(Point[] points) {
		Point[] pts = Arrays.copyOf(points, points.length);
		checkPoints(pts);
		for (Point p : points) {
			Arrays.sort(pts, p.slopeOrder());
			double slope;
			double lastSlope = Double.NEGATIVE_INFINITY;
			List<Point> collinearPoints = new ArrayList<>();
			for (int i = 1; i < pts.length; i++) {
				slope = p.slopeTo(pts[i]);
				if (slope != lastSlope) {
					if (collinearPoints.size() >= 3) {
						collinearPoints.add(p);
						addNewLineSegment(collinearPoints, lastSlope);
					}
					collinearPoints.clear();
				}
				collinearPoints.add(pts[i]);
				lastSlope = slope;
			}
			if (collinearPoints.size() >= 3) {
				collinearPoints.add(p);
				addNewLineSegment(collinearPoints, lastSlope);
			}
		}
	}
	
	private void addNewLineSegment(List<Point> collinearPoints, double slope) {
		List<Point> lastPointsList = lastPointsHash.get(slope);
		Collections.sort(collinearPoints);
		Point firstPoint = collinearPoints.get(0);
		Point lastPointCandidate = collinearPoints.get(collinearPoints.size()-1);
		if (lastPointsList == null) {
			lastPointsList = new ArrayList<>();
			lastPointsList.add(lastPointCandidate);
			lastPointsHash.put(slope, lastPointsList);
			segmentsList.add(new LineSegment(firstPoint, lastPointCandidate));
		} else {
			for (Point previousLastPoint : lastPointsList)
				if (lastPointCandidate.compareTo(previousLastPoint) == 0)
					return;
			lastPointsList.add(lastPointCandidate);
			segmentsList.add(new LineSegment(firstPoint, lastPointCandidate));
		}
	}
 	
	// the number of line segments
	public int numberOfSegments() {
		return segmentsList.size();
	}
	
	// the line segments
	public LineSegment[] segments() {
		return segmentsList.toArray(new LineSegment[segmentsList.size()]);
	}
	
	private void checkPoints(Point[] p) {
		if (p == null) throw new NullPointerException();
		for (int i = 0; i < p.length; i++)
			if (p[i] == null)
				throw new NullPointerException();
		if (duplicates(p)) throw new IllegalArgumentException();
	}
	
	private boolean duplicates(final Point[] p) {
		Arrays.sort(p);
		for (int i = 0; i < p.length - 1; i++)
			if (p[i].equals(p[i+1]))
				return true;
		return false;
	}
	
//		public static void printArr(Object[] a) {
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
		FastCollinearPoints collinear = new FastCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	    }
	}
	
}
