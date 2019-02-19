package model.point;

import model.Point;

public class Point4D extends Point {

	private double x1;
	private double x2;
	private double x3;
	private double x4;
	
	public Point4D(double x1, double x2, double x3, double x4 ) {
		this.x1 = x1;
		this.x2 = x2;
		this.x3 = x3;
		this.x4 = x4;
	}

	public Point4D() {

	}

	public Point4D(Point4D p) {
		this(p.getX1(), p.getX2(), p.getX3(), p.getX4());
	}

	@Override
	public double distance(Point p) {
		Point4D point = (Point4D) p;
		return Math.sqrt((this.x1 - point.getX1()) * (this.x1 - point.getX1())
				+ (this.x2 - point.getX2()) * (this.x2 - point.getX2())
				+ (this.x3 - point.getX3()) * (this.x3 - point.getX3())
				+ (this.x4 - point.getX4()) * (this.x4 - point.getX4()));
	}
	
	@Override
	public Point copyPoint() {
		return new Point4D(this);
	}

	public double getX1() {
		return x1;
	}

	public void setX1(double x1) {
		this.x1 = x1;
	}

	public double getX2() {
		return x2;
	}

	public void setX2(double x2) {
		this.x2 = x2;
	}

	public double getX3() {
		return x3;
	}

	public void setX3(double x3) {
		this.x3 = x3;
	}

	public double getX4() {
		return x4;
	}

	public void setX4(double x4) {
		this.x4 = x4;
	}

	@Override
	public String toString() {
		
		return "(" + x1 + ", " + x2 + ", "
				+ x3 + ", " + x4 + ")";
	}
}
