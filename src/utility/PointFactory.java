package utility;

import java.util.List;

import model.Point;
import model.Problem;

public abstract class PointFactory {
	
	public abstract Problem generateProblem();
	
	public abstract Point calculateCentroid(List<Point> points);
	
	public abstract double calculateSum(List<Point> points, Point c);
}
