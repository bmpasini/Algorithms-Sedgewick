package week2.lectures;

import java.util.Arrays;

public class GrahamScan {
	
	private Stack<Point2D> hull = new Stack<Point2D>();
	private int N;
	
	public GrahamScan(Point2D[] p) {
		
		N = p.length;
		Point2D top;
		
		Arrays.sort(p, Point2D.Y_ORDER);
		Arrays.sort(p, p[0].BY_POLAR_ORDER);
		
		hull.push(p[0]);
		hull.push(p[1]);
		
		for (int i = 2; i < N; i++) {
			top = hull.pop();
			while (Point2D.ccw(hull.peek(), top, p[i]) <= 0)
				top = hull.pop();
			hull.push(top);
			hull.push(p[i]);
		}
		
	}
	
}
