package week4.lectures;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Ball {
	private double rx, ry; // position
	private double vx, vy; // velocity
	private final double radius; // radius
	
	public Ball() {
		rx = StdRandom.uniform();
		ry = StdRandom.uniform();
		vx = StdRandom.uniform(0.01, 0.7);
		vy = StdRandom.uniform(0.01, 0.7);
		radius = 0.005;
	}
	
	public Ball(double rx, double ry, double vx, double vy, double radius) {
		this.rx = rx;
		this.ry = ry;
		this.vx = vx;
		this.vy = vy;
		this.radius = radius;
	}
	
	public void move(double dt) {
		if ((rx + vx*dt < radius) || (rx + vx*dt > 1.0 - radius)) vx = -vx;
		if ((ry + vy*dt < radius) || (ry + vy*dt > 1.0 - radius)) vy = -vy;
		rx = rx + vx*dt;
		ry = ry + vy*dt;
	}
	
	public void draw() {
		StdDraw.filledCircle(rx, ry, radius);
	}
}