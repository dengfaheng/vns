package model.point;

import model.Point;

public class Point2D extends Point {

	private long x;
	private long y;
	
	public Point2D(long l, long m) {
		this.x = l;
		this.y = m;
	}

	public Point2D() {
	}

	public Point2D(Point2D p) {
		this(p.getX(), p.getY());
	}

	@Override
	public double distance(Point p) {
		Point2D point = (Point2D) p;
		return Math.sqrt((this.x - point.getX()) * (this.x - point.getX())
				+ (this.y - point.getY()) * (this.y - point.getY()));
	}
	
	@Override
	public Point copyPoint() {
		return new Point2D(this);
	}
	
	public long getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public long getY() {
		return y;
	}
}
