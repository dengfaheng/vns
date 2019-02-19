package utility.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import model.Point;
import model.Problem;
import model.point.Point3D;
import utility.PointFactory;

public class PointFactory3DImpl extends PointFactory {

	@Override
	public Problem generateProblem() {
		List<Point> points = new ArrayList<Point>();
		
		final ResourceBundle rb = ResourceBundle.getBundle("config\\config");
	    String datasetDir = rb.getString("datasetDir");
	    String datasetName = rb.getString("birchDataset");

		try (BufferedReader br = new BufferedReader(new FileReader(Paths.get(datasetDir + datasetName).toAbsolutePath().toString()))) {
			for (String line; (line = br.readLine()) != null;) {
				String[] coords = line.trim().split(" ");
				int x = Integer.parseInt(coords[0]);
				int y = Integer.parseInt(coords[1]);
				int z = Integer.parseInt(coords[2]);
				points.add(new Point3D(x, y, z));
			}
		} catch (Exception e) {
			System.exit(0);
		}
		return new Problem(points);
	}

	@Override
	public Point calculateCentroid(List<Point> points) {
		if (points != null && points.size() != 0) {

			long x = 0;
			long y = 0;
			long z = 0;
			long k = 0;
			for (Point point : points) {
				x += ((Point3D) point).getX();
				y += ((Point3D) point).getY();
				z += ((Point3D) point).getZ();
				k++;
			}
			return new Point3D(x / k, y / k, z / k);
		} else {
			return new Point3D();
		}
	}

	@Override
	public double calculateSum(List<Point> points, Point c) {
		long sum = 0;
		Point3D p;
		Point3D centroid = (Point3D) c;
		if (points != null && points.size() != 0 && centroid != null) {
			for (int i = 0; i < points.size(); i++) {
				p = (Point3D) points.get(i);
				sum += (p.getX() - centroid.getX()) * (p.getX() - centroid.getX())
						+ (p.getY() - centroid.getY()) * (p.getY() - centroid.getY())
						+ (p.getZ() - centroid.getZ()) * (p.getZ() - centroid.getZ());
			}
		}
		return sum;
	}
}
