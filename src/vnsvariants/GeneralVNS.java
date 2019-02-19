package vnsvariants;

import java.util.ResourceBundle;

import interfaces.Executable;
import model.Cluster;
import model.Point;
import model.Solution;
import neighborhoods.Neighborhood;

public class GeneralVNS implements Executable {

	private Solution initialSolution;
	private Neighborhood neighborhood;
	private int neighborhoodStructureNoMax;
	private int VNDNeighborhoodStructureNoMax;
	private boolean isStoppingConditionMet; // TODO implement stopping condition
	final ResourceBundle rb = ResourceBundle.getBundle("config\\config");
    int maxIteration = Integer.parseInt(rb.getString("maxIteration"));

	public GeneralVNS(Solution initialSolution, Neighborhood neighborhood,
			int neighborhoodStructureNoMax, int VNDNeighborhoodStructureNoMax) {
		this.initialSolution = initialSolution;
		this.neighborhood = neighborhood;
		this.neighborhoodStructureNoMax = neighborhoodStructureNoMax;
		this.VNDNeighborhoodStructureNoMax = VNDNeighborhoodStructureNoMax;
	}

	@Override
	public Solution execute() {
		int iterationCounter = 0;
		while (!isStoppingConditionMet) {
			System.out.println("GeneralVNS iter = " + iterationCounter);
			int neighborhoodStructureNo = 1;
			while (neighborhoodStructureNo <= neighborhoodStructureNoMax) {
				Solution solution_prime = neighborhood.shake(initialSolution,
						neighborhoodStructureNo);

				VND vnd = new VND(solution_prime, neighborhood,
						VNDNeighborhoodStructureNoMax);
				Solution solution_double_prime = vnd.execute();

				if (solution_double_prime.getValue() < initialSolution
						.getValue()) {
					initialSolution = solution_double_prime;
					neighborhoodStructureNo = 1;
				} else {
					neighborhoodStructureNo++;
				}
			}
			iterationCounter++;
			if (iterationCounter >= maxIteration) {
				isStoppingConditionMet = true;
			}
		}
		System.out.println("CS measure = " + CSMeasure(initialSolution));
		return initialSolution;
	}

	private double CSMeasure(Solution finalSolution) {
		double sum = 0;
		for (Cluster c : finalSolution.getClusters()) {
			double clusterSum = 0;
			for (Point p : c.getPoints()) {
				double maxDist = p.distance(c.getPoints().get(0));
				for (Point p1 : c.getPoints()) {
					if (p.distance(p1) > maxDist) {
						maxDist = p.distance(p1);
					}
				}
				clusterSum += maxDist;
			}
			sum += (clusterSum / c.size());
		}

		double minDistSum = 0;
		double minDist = 0;
		for (int i = 0; i < finalSolution.getClusters().size(); i++) {
			if (i == 0) {
				minDist = finalSolution
						.getClusters()
						.get(i)
						.getCentroid()
						.distance(
								finalSolution.getClusters().get(i + 1)
										.getCentroid());
			} else {
				minDist = finalSolution
						.getClusters()
						.get(i - 1)
						.getCentroid()
						.distance(
								finalSolution.getClusters().get(i)
										.getCentroid());
			}
			for (int j = 0; j < finalSolution.getClusters().size(); j++) {
				if (i != j
						&& finalSolution
								.getClusters()
								.get(i)
								.getCentroid()
								.distance(
										finalSolution.getClusters().get(j)
												.getCentroid()) < minDist) {
					minDist = finalSolution
							.getClusters()
							.get(i)
							.getCentroid()
							.distance(
									finalSolution.getClusters().get(j)
											.getCentroid());
				}
			}
			minDistSum += minDist;
		}

		return sum / minDistSum;
	}

}
