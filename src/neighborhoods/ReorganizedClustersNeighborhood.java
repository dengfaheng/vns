package neighborhoods;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import interfaces.Shakeable;
import model.Cluster;
import model.Point;
import model.Solution;

public class ReorganizedClustersNeighborhood extends Neighborhood implements
		Shakeable {

	private int numberOfRandomClustersToBeReorganized;

	/**
	 * @param initialSolution
	 *            - initial solution
	 * @param neighborhoodStructureNo
	 *            - number of random clusters for which points will be checked
	 *            if they should be reassigned to another cluster
	 * @return - solution with reorganized points in neighborhoodStructureNo+
	 *         clusters
	 */
	@Override
	public Solution shake(Solution initialSolution, int neighborhoodStructureNo) {
		System.out.println("shake");
		Solution newRandomSolution = new Solution(initialSolution);
		int numberOfClusters = newRandomSolution.getClusters().size();
		if (neighborhoodStructureNo <= numberOfClusters) {
			int numberOfRandomClusters = 1;
			Random randomGenerator = new Random();
			List<Integer> alreadyChosenClusters = new ArrayList<>();
			int i;

			while (numberOfRandomClusters <= neighborhoodStructureNo) {
				i = randomGenerator.nextInt(numberOfClusters);
				// Check if random cluster was already chosen in the previous
				// iteration
				while (alreadyChosenClusters.contains(new Integer(i))) {
					i = randomGenerator.nextInt(numberOfClusters);
				}
				alreadyChosenClusters.add(i);

				newRandomSolution = reorganizeClusterIfNeeded(i,
						newRandomSolution);
				numberOfRandomClusters++;
			}
			newRandomSolution.calculateValue();
		}

		return newRandomSolution;
	}

	@Override
	public Solution findBestNeighbor(Solution initialSolution,
			int neighborhoodStructureNo) {
		System.out.println("findBestNeighbor");
		Solution optimalSolution = new Solution(initialSolution);
		int numberOfClusters = initialSolution.getClusters().size();
		if (neighborhoodStructureNo <= numberOfClusters) {
			List<int[]> neighborsStructures = findAllSubsets(numberOfClusters,
					neighborhoodStructureNo);

			for (int[] neighbor : neighborsStructures) {
				Solution nextSolution = new Solution(initialSolution);
				int neighborhoodStructureCounter = 0;
				while (neighborhoodStructureCounter < neighborhoodStructureNo) {
					nextSolution = reorganizeClusterIfNeeded(
							neighbor[neighborhoodStructureCounter],
							nextSolution);
					neighborhoodStructureCounter++;
				}
				nextSolution.calculateValue();
				if (nextSolution.getValue() < optimalSolution.getValue()) {
					optimalSolution = new Solution(nextSolution);
				}
			}
			optimalSolution.calculateValue();
		}

		return optimalSolution;
	}

	private Solution reorganizeClusterIfNeeded(int targetClusterIndex,
			Solution nextSolution) {
		Solution targetSolution = new Solution(nextSolution);
		Cluster targetCluster = targetSolution.getCluster(targetClusterIndex);
		Set<Integer> reorganizedClustersIndices = new HashSet<Integer>();

		for (Iterator<Point> points = targetCluster.getPoints().iterator(); points
				.hasNext();) {
			Point point = points.next();
			double originalDistanceToClosestCentroid = point
					.distance(targetCluster.getCentroid());
			double distanceToClosestCentroid = originalDistanceToClosestCentroid;
			Cluster closestCluster = targetCluster;

			int counter = 0;
			int closestClusterIndex = -1;
			for (Cluster cluster : targetSolution.getClusters()) {
				if (point.distance(cluster.getCentroid()) < distanceToClosestCentroid) {
					distanceToClosestCentroid = point.distance(cluster
							.getCentroid());
					closestCluster = cluster;
					closestClusterIndex = counter;
				}
				counter++;
			}

			// if a closer centroid is found, reassign the point to a cluster with that centroid
			if (distanceToClosestCentroid < originalDistanceToClosestCentroid) {
//				System.out.println("targetSolution c1 size = " + targetSolution.getClusters().get(0).getPoints().size());
//				System.out.println("targetSolution c2 size = " + targetSolution.getClusters().get(1).getPoints().size());
//				System.out.println("targetSolution c3 size = " + targetSolution.getClusters().get(2).getPoints().size());
				closestCluster.addPoint(point);
				reorganizedClustersIndices.add(closestClusterIndex);
				points.remove();
//				System.out.println("targetSolution c1 size = " + targetSolution.getClusters().get(0).getPoints().size());
//				System.out.println("targetSolution c2 size = " + targetSolution.getClusters().get(1).getPoints().size());
//				System.out.println("targetSolution c3 size = " + targetSolution.getClusters().get(2).getPoints().size());
				
			}
		}

		// if something is added to reorganizedClustersList, than it sould be added to randomCluster
		if (!reorganizedClustersIndices.isEmpty()) {
			reorganizedClustersIndices.add(targetClusterIndex);

//			System.out.println("targetSolution c1 size = " + targetSolution.getClusters().get(0).getPoints().size());
//			System.out.println("targetSolution c2 size = " + targetSolution.getClusters().get(1).getPoints().size());
//			System.out.println("targetSolution c3 size = " + targetSolution.getClusters().get(2).getPoints().size());
//			System.out.println("targetSolution c1 sum = " + targetSolution.getClusters().get(0).getSum());
//			System.out.println("targetSolution c2 sum = " + targetSolution.getClusters().get(1).getSum());
//			System.out.println("targetSolution c3 sum = " + targetSolution.getClusters().get(2).getSum());
//			System.out.println("targetSolution c1 centroid = " + targetSolution.getClusters().get(0).getCentroid().toString());
//			System.out.println("targetSolution c2 centroid = " + targetSolution.getClusters().get(1).getCentroid().toString());
//			System.out.println("targetSolution c3 centroid = " + targetSolution.getClusters().get(2).getCentroid().toString());
			for (int index : reorganizedClustersIndices) {
				targetSolution.getCluster(index).calculateCentroid();
				targetSolution.getCluster(index).calculateSum();
			}
//			System.out.println("targetSolution c1 sum = " + targetSolution.getClusters().get(0).getSum());
//			System.out.println("targetSolution c2 sum = " + targetSolution.getClusters().get(1).getSum());
//			System.out.println("targetSolution c3 sum = " + targetSolution.getClusters().get(2).getSum());
//			System.out.println("targetSolution c1 centroid = " + targetSolution.getClusters().get(0).getCentroid().toString());
//			System.out.println("targetSolution c2 centroid = " + targetSolution.getClusters().get(1).getCentroid().toString());
//			System.out.println("targetSolution c3 centroid = " + targetSolution.getClusters().get(2).getCentroid().toString());
		}

		return targetSolution;

	}

	private List<int[]> findAllSubsets(int n, int k) {
		List<int[]> subsets = new ArrayList<int[]>();
		int[] subset = new int[k];

		if (k <= n) {
			// first index sequence: 0, 1, 2, ...
			for (int i = 0; (subset[i] = i) < k - 1; i++)
				;
			subsets.add(getSubset(subset));
			for (;;) {
				int i;
				// find position of item that can be incremented
				for (i = k - 1; i >= 0 && subset[i] == n - k + i; i--)
					;
				if (i < 0) {
					break;
				} else {
					subset[i]++; // increment this item
					for (++i; i < k; i++) { // fill up remaining items
						subset[i] = subset[i - 1] + 1;
					}
					subsets.add(getSubset(subset));
				}
			}
		}

		return subsets;
	}

	int[] getSubset(int[] subset) {
		int[] result = new int[subset.length];
		for (int i = 0; i < subset.length; i++)
			result[i] = subset[i];
		return result;
	}

	@Override
	public int getNeighborhoodNumber() {
		return getNumberOfRandomClustersToBeReorganized();
	}

	@Override
	public void setNeighborhoodNumber(int neighborhoodNumber) {
		setNumberOfRandomClustersToBeReorganized(neighborhoodNumber);
	}

	private int getNumberOfRandomClustersToBeReorganized() {
		return numberOfRandomClustersToBeReorganized;
	}

	private void setNumberOfRandomClustersToBeReorganized(
			int numberOfRandomClustersToBeReorganized) {
		this.numberOfRandomClustersToBeReorganized = numberOfRandomClustersToBeReorganized;
	}

}
