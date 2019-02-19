package main;

import model.Problem;
import model.Solution;
import neighborhoods.DistanceToClosestClusterNeighborhood;
import representation.ImageProcessor;
import utility.Factory;
import vnsvariants.GeneralVNS;

public class VNSMain {

	public static void main(String[] args) {
		int clusterNumber = 8;
		int stepSize = 5;
		ImageProcessor imageProcessor = new ImageProcessor(stepSize);
		Factory.setDimension("MultiD");
		Problem problem = Factory.getPointFactory().generateProblem();
		Solution initialSolution = problem.generateInitialSolution(clusterNumber);
		imageProcessor.writeImage(initialSolution, "initialSolution");
		
		System.out.println("initialSolution sum = " + initialSolution.getValue());
		
//		GeneralVNS generalVNS = new GeneralVNS(initialSolution,
//				new ReorganizedClustersNeighborhood(), 3, 3);
		GeneralVNS generalVNS = new GeneralVNS(initialSolution,
				new DistanceToClosestClusterNeighborhood(), 3, 3);
		Solution finalSolution = generalVNS.execute();
		
		imageProcessor.writeImage(finalSolution, "finalSolution");
		
		System.out.println("initialSolution sum = " + initialSolution.getValue());
		System.out.println("finalSolution sum = " + finalSolution.getValue());
	}

}
