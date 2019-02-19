package vnsvariants;

import interfaces.Executable;
import model.Solution;
import neighborhoods.Neighborhood;

public class ReducedVNS implements Executable {
	
	private Solution initialSolution;
	private Neighborhood neighborhood;
	private int neighborhoodStructureNoMax;
	private boolean isStoppingConditionMet; // TODO implement stopping condition

	public ReducedVNS(Solution initialSolution, Neighborhood neighborhood, int neighborhoodStructureNoMax) {
		this.initialSolution = initialSolution;
		this.neighborhood = neighborhood;
		this.neighborhoodStructureNoMax = neighborhoodStructureNoMax;
	}

	@Override
	public Solution execute() {
		while(!isStoppingConditionMet){
			int neighborhoodStructureNo = 1;
			while(neighborhoodStructureNo <= neighborhoodStructureNoMax) {
				Solution solution_prime = neighborhood.shake(initialSolution, neighborhoodStructureNo);
				if(solution_prime.getValue() < initialSolution.getValue()) {
					initialSolution = solution_prime;
					neighborhoodStructureNo = 1;
				} else {
					if(neighborhoodStructureNo == neighborhoodStructureNoMax) {
						neighborhoodStructureNo = 1;
					} else {
						neighborhoodStructureNo++;
					}
				}
			}
			
		}
		return null;
	}

}
