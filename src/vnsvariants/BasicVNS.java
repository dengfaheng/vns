package vnsvariants;

import interfaces.Executable;
import model.Solution;
import neighborhoods.Neighborhood;

public class BasicVNS implements Executable {

	private Solution initialSolution;
	private Neighborhood neighborhood;
	private int neighborhoodStructureNoMax;
	private boolean isStoppingConditionMet; // TODO implement stopping condition

	public BasicVNS(Solution initialSolution, Neighborhood neighborhood,
			int neighborhoodStructureNoMax) {
		this.initialSolution = initialSolution;
		this.neighborhood = neighborhood;
		this.neighborhoodStructureNoMax = neighborhoodStructureNoMax;
	}

	@Override
	public Solution execute() {
		while (!isStoppingConditionMet) {
			int neighborhoodStructureNo = 1;
			while (neighborhoodStructureNo <= neighborhoodStructureNoMax) {
				Solution solution_prime = neighborhood.shake(initialSolution,
						neighborhoodStructureNo);
				Solution solution_double_prime = solution_prime; // TODO ovde
																	// dolazi
																	// local
																	// search
				if (solution_double_prime.getValue() < initialSolution
						.getValue()) {
					initialSolution = solution_double_prime;
					neighborhoodStructureNo = 1;
				} else {
					neighborhoodStructureNo++;
				}
			}
		}
		return initialSolution;
	}

}
