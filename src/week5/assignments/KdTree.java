package week5.assignments;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
	
	private final static boolean VERTICAL = true;
	private final static boolean HORIZONTAL = false;
	private Node root;
	
	// construct an empty set of points
	public KdTree() {
		this.root = null;
	}
	
	private static class Node {
		
		private Point2D p;      // the point
		private RectHV rect;    // the axis-aligned rectangle corresponding to this node
		private Node lb;        // the left/bottom subtree
		private Node rt;        // the right/top subtree
		
		public Node(Point2D p, RectHV rect, Node lb, Node rt) {
			this.p = p;
			this.rect = rect;
			this.lb = lb;
			this.rt = rt;
		}
		
	}
	
	// is the set empty?
	public boolean isEmpty() {
		return root == null;
	}
	
	// number of points in the set
	public int size() {
		return size(root);
	}
	
	private int size(Node x) {
		if (x == null) return 0;
		return size(x.lb) + 1 + size(x.rt);
	}
	
    // add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if (p == null) throw new NullPointerException();
		root = insert(root, p, new RectHV(0, 0, 1, 1), VERTICAL);
	}
	
	private Node insert(Node x, Point2D p, RectHV rect, boolean orientation) {
		if (x == null) return new Node(p, rect, null, null);
		int cmp = compare(x, p, orientation);
		if      (cmp < 0) {
			if      (orientation == VERTICAL  ) x.lb = insert(x.lb, p, new RectHV(x.rect.xmin(), x.rect.ymin(), x.p.x()      , x.rect.ymax()), !orientation);
			else if (orientation == HORIZONTAL) x.lb = insert(x.lb, p, new RectHV(x.rect.xmin(), x.rect.ymin(), x.rect.xmax(), x.p.y()      ), !orientation);
		}
		else if (cmp > 0) {
			if      (orientation == VERTICAL  ) x.rt = insert(x.rt, p, new RectHV(x.p.x()      , x.rect.ymin(), x.rect.xmax(), x.rect.ymax()), !orientation);
			else if (orientation == HORIZONTAL) x.rt = insert(x.rt, p, new RectHV(x.rect.xmin(), x.p.y()      , x.rect.xmax(), x.rect.ymax()), !orientation);
		}
		else {
						  						x.p     = p;
						  						x.rect  = rect;
		}
		return x;
	}
	
	// does the set contain point p?
	public boolean contains(Point2D p) {
		if (p == null) throw new NullPointerException();
		return p.equals(contains(root, p, VERTICAL));
	}

    private Point2D contains(Node x, Point2D p, boolean orientation) {
        if (x == null) return null;
        int cmp = compare(x, p, orientation);
        if      (cmp < 0) return contains(x.lb, p, !orientation);
        else if (cmp > 0) return contains(x.rt, p, !orientation);
        else              return x.p;
    }
    
    private int compare(Node x, Point2D p, boolean orientation) {
		if      (orientation == VERTICAL)
			if (p.x() < x.p.x()) return -1;
			if (p.x() > x.p.x()) return  1;
		else if (orientation == HORIZONTAL)
			if (p.y() < x.p.y()) return -1;
			if (p.y() > x.p.y()) return  1;
		return 0;
	}
	
	// draw all points to standard draw 
	public void draw() {
		draw(root, new RectHV(0, 0, 1, 1), VERTICAL);
	}
	
	private void draw(Node x, RectHV rect, boolean orientation) {
		if (x == null) return;
		draw(x.lb, x.rect, !orientation);
		StdDraw.setPenRadius(.005);
		if (orientation == VERTICAL  ) {
			StdDraw.setPenColor(StdDraw.RED);
			new RectHV(x.p.x(), x.rect.ymin(), x.p.x() + 0.000001, x.rect.ymax()).draw();
		} else if (orientation == HORIZONTAL) {
			StdDraw.setPenColor(StdDraw.BLUE);
			new RectHV(x.rect.xmin(), x.p.y(), x.rect.xmax(), x.p.y() + 0.000001).draw();
		}
		StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.02);
		x.p.draw();
		draw(x.rt, x.rect, !orientation);
	}
	
	// all points that are inside the rectangle 
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) throw new NullPointerException();
		Queue<Point2D> points = new Queue<>();
		range(root, points, rect);
		return points;
	}
	
	private void range(Node x, Queue<Point2D> points, RectHV rect) {
		if (x == null) return;
		if (rect.intersects(x.rect)) {
			range(x.lb, points, rect);
			if (x.p.x() >= rect.xmin() && x.p.y() >= rect.ymin() && x.p.x() <= rect.xmax() && x.p.y() <= rect.ymax())
				points.enqueue(x.p);
			range(x.rt, points, rect);
		}
	}
	
	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		if (p == null) throw new NullPointerException();
		return nearest(root, root.p, p, VERTICAL);
	}
	
	private Point2D nearest(Node x, Point2D closest, Point2D p, boolean orientation) {
		if (x == null) return closest;
		if (p.distanceSquaredTo(closest) >= distanceSquaredFromPointToRect(p, x.rect)) {
			if (p.distanceSquaredTo(x.p) < p.distanceSquaredTo(closest))
				closest = x.p;
			if        (orientation == VERTICAL  ) {
				if        (p.x() < x.p.x()) {
					closest = nearest(x.lb, closest, p, !orientation);
					closest = nearest(x.rt, closest, p, !orientation);
				} else if (p.x() > x.p.x()) {
					closest = nearest(x.rt, closest, p, !orientation);
					closest = nearest(x.lb, closest, p, !orientation);
				}
			} else if (orientation == HORIZONTAL) {
				if        (p.y() < x.p.y()) {
					closest = nearest(x.lb, closest, p, !orientation);
					closest = nearest(x.rt, closest, p, !orientation);
				} else if (p.y() > x.p.y()) {
					closest = nearest(x.rt, closest, p, !orientation);
					closest = nearest(x.lb, closest, p, !orientation);
				}
			}
		}
		return closest;
	}
	
	private double distanceSquaredFromPointToRect(Point2D p, RectHV rect) {
		if      (p.x() < rect.xmin()) return p.distanceSquaredTo(new Point2D(rect.xmin(), p.y()        ));
		else if (p.x() > rect.xmax()) return p.distanceSquaredTo(new Point2D(rect.xmax(), p.y()        ));
		else if (p.y() < rect.ymin()) return p.distanceSquaredTo(new Point2D(p.x()        , rect.ymin()));
		else if (p.y() > rect.ymax()) return p.distanceSquaredTo(new Point2D(p.x()        , rect.ymax()));
		else                          return 0;
	}
	
	// unit testing of the methods (optional)
	public static void main(String[] args) {
		KdTree t = new KdTree();
		Point2D p1 = new Point2D(0.7, 0.2);
		Point2D p2 = new Point2D(0.5, 0.4);
		Point2D p3 = new Point2D(0.2, 0.3);
		Point2D p4 = new Point2D(0.4, 0.7);
		Point2D p5 = new Point2D(0.9, 0.6);
		Point2D p6 = new Point2D(0.3, 0.7);
		System.out.println(t.isEmpty() == true);
		System.out.println(t.size() == 0);
		t.insert(p1);
		System.out.println(t.isEmpty() == false);
		System.out.println(t.size() == 1);
		t.insert(p2);
		t.insert(p3);
		t.insert(p4);
		t.insert(p5);
//		t.insert(p6);
		System.out.println(t.size() == 5);
		System.out.println(t.contains(p2) == true);
		System.out.println(t.contains(p6) == false);
		t.draw();
	}
	
}
