package model;

import java.util.ArrayList;
import java.util.List;

import utility.Factory;

public class Cluster {
    
    private List<Point> points;
    private Point centroid;
    private double sum;

    public Cluster(List<Point> points) {
		if(points != null) {
	    	this.points = new ArrayList<Point>(points);
			calculateCentroid();
			calculateSum();
		} else {
			this.points = new ArrayList<Point>();
			this.centroid = (Point) new Object();
			this.sum = 0;
		}
	}
    
    public Cluster(Cluster c) {
    	this(c.getPoints());
    }
    
    public Cluster() {
    	this.points = new ArrayList<Point>();
		this.sum = 0;    	
    }

	public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
    
    public Point getPoint(int i) {
    	return points.get(i);
    }
    
    public void removePoint(int i) {
    	points.remove(i);
    }
    
    public void addPoint(Point p) {
    	points.add(p);
    }

    public Point getCentroid() {
        return centroid;
    }

    public void setCentroid(Point centroid) {
        this.centroid = centroid;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
    
    public void calculateCentroid() {
    	this.centroid = Factory.getPointFactory().calculateCentroid(points);
    }
    
    public void calculateSum() {
        this.sum = Factory.getPointFactory().calculateSum(points, centroid);
    }
    
    public int size() {
    	return points != null ? points.size() : 0;
    }
    
}
