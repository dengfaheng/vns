package model.point;

import model.Point;

public class Point3D extends Point {

	private long x;
	private long y;
	private long z;
	
	public Point3D(long l, long m, long n) {
		this.x = l;
		this.y = m;
		this.z = n;
	}

	public Point3D() {

	}

	public Point3D(Point3D p) {
		this(p.getX(), p.getY(), p.getZ());
	}

	@Override
	public double distance(Point p) {
		Point3D point = (Point3D) p;
		return Math.sqrt((this.x - point.getX()) * (this.x - point.getX())
				+ (this.y - point.getY()) * (this.y - point.getY())
				+ (this.z - point.getZ()) * (this.z - point.getZ()));
	}

	@Override
	public Point copyPoint() {
		return new Point3D(this);
	}
	
	public long getX() {
		return x;
	}

	public void setX(long x) {
		this.x = x;
	}

	public long getY() {
		return y;
	}

	public void setY(long y) {
		this.y = y;
	}

	public long getZ() {
		return z;
	}

	public void setZ(long z) {
		this.z = z;
	}
}
