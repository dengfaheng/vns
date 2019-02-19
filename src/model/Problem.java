package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Problem {
	private List<Point> points;

	public Problem(List<Point> points) {
		this.points = points;
	}

	public Solution generateInitialSolution(int numberOfClusters) {
		Solution solution = new Solution();
		Cluster cluster;
		Point p;
		Random randomGenerator = new Random();
		int numberOfPoints = points.size();
		int clusterCounter = 1;
		List<Integer> alreadyChosenPoints = new ArrayList<Integer>();
		int randomPointIndex = randomGenerator.nextInt(numberOfPoints - 1);

		while (clusterCounter <= numberOfClusters) {
			while (alreadyChosenPoints.contains(randomPointIndex)) {
				randomPointIndex = randomGenerator.nextInt(numberOfPoints - 1);
			}
			alreadyChosenPoints.add(randomPointIndex);

			cluster = new Cluster();
			p = points.get(randomPointIndex).copyPoint();
			cluster.setCentroid(p);
			solution.addCluster(cluster);
			clusterCounter++;
		}
		for (Point point : points) {
			Cluster closestCluster = solution.getCluster(0);
			double closestCentroidDistance = point.distance(closestCluster
					.getCentroid());
			for (Cluster c : solution.getClusters()) {
				if (point.distance(c.getCentroid()) < closestCentroidDistance) {
					closestCentroidDistance = point.distance(c.getCentroid());
					closestCluster = c;
				}
			}
			closestCluster.addPoint(point.copyPoint());
		}
		for(Cluster c : solution.getClusters()) {
			c.calculateSum();
			c.calculateCentroid();
		}
		solution.calculateValue();
		return solution;
	}
}
