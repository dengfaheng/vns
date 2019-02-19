package model.point;

import static org.junit.Assert.*;

import org.junit.Test;

public class Point3DTest {

	@Test
	public void distanceTest() {
		Point3D p1 = new Point3D();
		Point3D p2 = new Point3D(1, 1, 1);
		assertEquals(1.732051, p1.distance(p2), 0.000001);
	}

}
