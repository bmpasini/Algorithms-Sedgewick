package week5.assignments;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.TreeSet;

public class PointSET {
	
	private TreeSet<Point2D> points;
	
	// construct an empty set of points
	public PointSET() {
		this.points = new TreeSet<>();
	}
	
	// is the set empty?
	public boolean isEmpty() {
		return points.isEmpty();
	}
	
	// number of points in the set
	public int size() {
		return points.size();
	}
	
    // add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if (p == null) throw new NullPointerException();
		points.add(p);
	}
	
	// does the set contain point p?
	public boolean contains(Point2D p) {
		if (p == null) throw new NullPointerException();
		return points.contains(p);
	}
	
	// draw all points to standard draw 
	public void draw() {
		for (Point2D p : points)
			p.draw();
	}
	
	// all points that are inside the rectangle 
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) throw new NullPointerException();
		TreeSet<Point2D> range = new TreeSet<>();
		for (Point2D p : points)
			if (p.x() >= rect.xmin() && p.y() >= rect.ymin() && p.x() <= rect.xmax() && p.y() <= rect.ymax())
				range.add(p);
		return range;
	}
	
	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D q) {
		if (q == null) throw new NullPointerException();
		if (isEmpty()) return null;
		Point2D nearest = null;
		for (Point2D p : points)
			if (nearest == null || q.distanceSquaredTo(p) < q.distanceSquaredTo(nearest)) nearest = p;
		return nearest;
	}
	
	// unit testing of the methods (optional)
	public static void main(String[] args) {
		
	}
	
}
