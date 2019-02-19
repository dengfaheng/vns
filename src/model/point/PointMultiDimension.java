package model.point;

import model.Point;

public class PointMultiDimension extends Point {

	private double[] attributes;
	private int dimensionNum;
	
	public PointMultiDimension(int dimensionNum, double[] attributes) {
		this.dimensionNum = dimensionNum;
		this.attributes = attributes;
	}

	public PointMultiDimension(int dimensionNum) {
		this.dimensionNum = dimensionNum;
		this.attributes = new double[dimensionNum];
	}

	public PointMultiDimension(double[] attributes) {
		this.dimensionNum = attributes.length;
		this.attributes = attributes;
	}
	
	public PointMultiDimension() {

	}

	public PointMultiDimension(PointMultiDimension p) {
		this(p.getDimensionNum(), p.getAttributes());
	}

	@Override
	public double distance(Point p) {
		PointMultiDimension point = (PointMultiDimension) p;
		double[] pointAttributes = point.getAttributes();
		
		double sum = 0;
		for(int i = 0; i <= dimensionNum - 1; i++) {
			double thisMinusPoint = this.attributes[i] - pointAttributes[i];
			sum += thisMinusPoint * thisMinusPoint;			
		}
		return Math.sqrt(sum);
	}
	
	@Override
	public Point copyPoint() {
		return new PointMultiDimension(this);
	}

	public double getAttribute(int i) {
		return attributes[i];
	}
	
	public double[] getAttributes() {
		return attributes;
	}

	public void setAttributes(double[] attributes) {
		this.attributes = attributes;
	}

	public int getDimensionNum() {
		return dimensionNum;
	}

	public void setDimensionNum(int dimensionNum) {
		this.dimensionNum = dimensionNum;
	}
}
