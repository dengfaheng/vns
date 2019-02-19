package vnsvariants;

import interfaces.Executable;
import model.Solution;
import neighborhoods.Neighborhood;

public class VND implements Executable {

	private Solution initialSolution;
	private Neighborhood neighborhood;
	private int neighborhoodStructureNoMax;

	public VND(Solution initialSolution, Neighborhood neighborhood, int neighborhoodStructureNoMax) {
		this.initialSolution = initialSolution;
		this.neighborhood = neighborhood;
		this.neighborhoodStructureNoMax = neighborhoodStructureNoMax;
	}

	@Override
	public Solution execute() {
		int neighborhoodStructureNo = 1;
		while(neighborhoodStructureNo <= neighborhoodStructureNoMax) {
			Solution solution_prime = neighborhood.findBestNeighbor(initialSolution, neighborhoodStructureNo);
			if(solution_prime.getValue() < initialSolution.getValue()) {
				initialSolution = solution_prime;
				neighborhoodStructureNo = 1;
			} else {
				neighborhoodStructureNo++;
			}
		}
		initialSolution.calculateValue();
		return initialSolution;
	}

}
