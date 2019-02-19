package utility.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import model.Point;
import model.Problem;
import model.point.Point4D;
import utility.PointFactory;

public class PointFactory4DImpl extends PointFactory {

	@Override
	public Problem generateProblem() {
		List<Point> points = new ArrayList<Point>();
		
		final ResourceBundle rb = ResourceBundle.getBundle("config\\config");
	    String datasetDir = rb.getString("datasetDir");
	    String datasetName = rb.getString("irisDataset");

		try (BufferedReader br = new BufferedReader(new FileReader(Paths.get(datasetDir + datasetName).toAbsolutePath().toString()))) {
			for (String line; (line = br.readLine()) != null;) {
				String[] coords = line.trim().split(",");
				Double x1 = Double.parseDouble(coords[0]);
				Double x2 = Double.parseDouble(coords[1]);
				Double x3 = Double.parseDouble(coords[2]);
				Double x4 = Double.parseDouble(coords[3]);
				points.add(new Point4D(x1, x2, x3, x4));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}
		return new Problem(points);
	}

	@Override
	public Point calculateCentroid(List<Point> points) {

		double x1 = 0;
		double x2 = 0;
		double x3 = 0;
		double x4 = 0;
		double k = 0;
		for (Point point : points) {
			x1 += ((Point4D) point).getX1();
			x2 += ((Point4D) point).getX2();
			x3 += ((Point4D) point).getX3();
			x4 += ((Point4D) point).getX4();
			k++;
		}
		return new Point4D(x1 / k, x2 / k, x3 / k, x4 / k);
	}

	@Override
	public double calculateSum(List<Point> points, Point c) {
		double sum = 0;
		Point4D p;
		Point4D centroid = (Point4D) c;
		if (points != null && points.size() != 0 && centroid != null) {
			for (int i = 0; i < points.size(); i++) {
				p = (Point4D) points.get(i);
				sum += (p.getX1() - centroid.getX1()) * (p.getX1() - centroid.getX1())
						+ (p.getX2() - centroid.getX2()) * (p.getX2() - centroid.getX2())
						+ (p.getX3() - centroid.getX3()) * (p.getX3() - centroid.getX3())
						+ (p.getX4() - centroid.getX4()) * (p.getX4() - centroid.getX4());
			}
		}
		return sum;
	}	
}
