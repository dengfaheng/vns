package model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.point.Point2D;
import utility.Factory;

public class SolutionTest {

	@Test
	public void test() {
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point2D(0, 0));
		points.add(new Point2D(0, 2));
		points.add(new Point2D(2, 2));
		points.add(new Point2D(2, 0));
		Factory.setDimension(2);
		Problem problem = new Problem(points);
		Solution initialRandomSolution1 = problem.generateInitialSolution(2);
		Solution initialRandomSolution2 = problem.generateInitialSolution(3);
		assertEquals(initialRandomSolution1.getClusters().size(), 2);
		assertEquals(initialRandomSolution2.getClusters().size(), 3);
	}

}
