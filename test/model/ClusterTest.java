package model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import model.point.Point2D;
import utility.Factory;

public class ClusterTest {

	@Test
	public void calculateCentroidTest() {
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point2D(0, 0));
		points.add(new Point2D(0, 2));
		points.add(new Point2D(2, 2));
		points.add(new Point2D(2, 0));
		Factory.setDimension(2);
		Cluster c1 = new Cluster(points);
		Point2D centroid = (Point2D)c1.getCentroid();
		assertEquals("X coordinate must match", 1, centroid.getX());
		assertEquals("Y coordinate must match", 1, centroid.getY());
	}
	
	@Test
	public void calculateSum() {
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point2D(0, 0));
		points.add(new Point2D(0, 2));
		points.add(new Point2D(2, 2));
		points.add(new Point2D(2, 0));
		Factory.setDimension(2);
		Cluster c1 = new Cluster(points);
		assertEquals(8, c1.getSum(), 0.000001);
	}

}
