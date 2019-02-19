package utility.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import model.Point;
import model.Problem;
import model.point.PointMultiDimension;
import utility.PointFactory;

public class PointFactoryMultiDimensionImp extends PointFactory {

	@Override
	public Problem generateProblem() {
		List<Point> points = new ArrayList<Point>();
		
		final ResourceBundle rb = ResourceBundle.getBundle("config\\config");
	    String datasetDir = rb.getString("datasetDir");
	    String datasetName = rb.getString("pixelsDataset");

	    try (BufferedReader br = new BufferedReader(new FileReader(Paths.get(datasetDir + datasetName).toAbsolutePath().toString()))) {
			for (String line; (line = br.readLine()) != null;) {
				String[] stringCoords = line.trim().split(",");
				double[] coords = new double[stringCoords.length];
				for(int i = 0; i <= stringCoords.length - 1; i++ ) {
					coords[i] = Double.parseDouble(stringCoords[i]);
				}
				points.add(new PointMultiDimension(coords));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return new Problem(points);
	}

	@Override
	public Point calculateCentroid(List<Point> points) {

		int dimensionNum = ((PointMultiDimension) points.get(0)).getDimensionNum();  
		double[] attributesSum = new double[dimensionNum];
		long k = 0;
		
		for (Point point : points) {
			double[] attributes = ((PointMultiDimension) point).getAttributes();
			for(int i = 0; i <= dimensionNum - 1; i++) {
				attributesSum[i] += attributes[i];
			}
			k++;
		}
		double[] centroidAttributes = new double[dimensionNum];
		for(int i = 0; i <= dimensionNum - 1; i++) {
			centroidAttributes[i] = attributesSum[i]/k;
		}
		return new PointMultiDimension(centroidAttributes);
	}

	@Override
	public double calculateSum(List<Point> points, Point c) {
		double sum = 0;
		PointMultiDimension p;
		PointMultiDimension centroid = (PointMultiDimension) c;
		if (points != null && points.size() != 0 && centroid != null) {
			int dimensionNum = ((PointMultiDimension) points.get(0)).getDimensionNum();
			for (int i = 0; i < points.size() - 1; i++) {
				p = (PointMultiDimension) points.get(i);
				for(int k = 0; k <= dimensionNum - 1; k++) {
					double pointMinusCentroid = p.getAttribute(k) - centroid.getAttribute(k);
					sum += pointMinusCentroid * pointMinusCentroid;			
				}
			}
		}
		return sum;
	}	
}
