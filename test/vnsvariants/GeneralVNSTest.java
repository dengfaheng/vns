package vnsvariants;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import model.Point;
import model.Problem;
import model.Solution;
import model.point.Point2D;
import neighborhoods.DistanceToClosestClusterNeighborhood;
import utility.Factory;

public class GeneralVNSTest {

	@Test
	public void test() {
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point2D(0, 0));
		points.add(new Point2D(0, 2));
		points.add(new Point2D(2, 2));
		points.add(new Point2D(2, 0));
		points.add(new Point2D(10, 10));
		Factory.setDimension(2);
		Problem problem = new Problem(points);
		Solution initialSolution = problem.generateInitialSolution(3);
		GeneralVNS generalVNS = new GeneralVNS(initialSolution,
				new DistanceToClosestClusterNeighborhood(), 3, 3);
		Solution finalSolution = generalVNS.execute();
		assertEquals(132, finalSolution.getValue(), 0.000001);
	}

}
