package model.point;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointMultiDimensionTest {

	@Test
	public void distanceTest() {
		PointMultiDimension p1 = new PointMultiDimension(4);
		PointMultiDimension p2 = new PointMultiDimension(new double[] {1, 1, 1, 1});
		assertEquals(2, p1.distance(p2), 0.000001);
	}

}
