package interfaces;

import model.Solution;

public interface Shakeable {

	/**
	 * @param initialSolution
	 * @param neighborhoodStructureNo
	 * @return new solution
	 */
	public Solution shake(Solution initialSolution, int neighborhoodStructureNo);
	
	public Solution findBestNeighbor(Solution initialSolution, int neighborhoodStructureNo);
}
