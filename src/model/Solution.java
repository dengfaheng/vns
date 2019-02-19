package model;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    List<Cluster> clusters;
    double value;
    
    public Solution(List<Cluster> solution) {
    	this.clusters = new ArrayList<Cluster>(solution);
    }
    
    public Solution(Solution s) {
    	this(s.getClusters());
    	this.value = s.getValue();
    }

    public Solution() {
		this.clusters = new ArrayList<Cluster>();
		this.value = 0;
	}

	public List<Cluster> getClusters() {
        return clusters;
    }

    public void setClusters(List<Cluster> solution) {
        this.clusters = solution;
        calculateValue();
    }

    public double getValue() {
        return value;
    }

    public void setValue(long l) {
        this.value = l;
    }
    
    public void calculateValue() {
        double value = 0;
        for(Cluster c : this.clusters) {
            value += c.getSum();
        }
        this.value = value;
    }
    
    public Cluster getCluster(int i) {
    	return clusters.get(i);
    }
        
    public void removeCluster(int k) {
    	clusters.remove(k);
    }
    
    public void addCluster(Cluster cluster) {
    	clusters.add(cluster);
    }
    
    public void addCluster(int i, Cluster cluster) {
    	clusters.add(i, cluster);
    }
    
    public void addPointToCluster(Point p, int k) {
    	clusters.get(k).addPoint(p);
    }
}
