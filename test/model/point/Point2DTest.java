package model.point;

import static org.junit.Assert.*;
import org.junit.Test;

public class Point2DTest {

	@Test
	public void distanceTest() {
		Point2D p1 = new Point2D();
		Point2D p2 = new Point2D(1,1);
		Point2D p3 = new Point2D(2,2);
		assertEquals("Distance between (0,0) and (1,1) must be", 1.414213, p1.distance(p2), 0.000001);
		assertEquals("Distance between (2,2) and (1,1) must be", 1.414213, p3.distance(p2), 0.000001);
	}

}
