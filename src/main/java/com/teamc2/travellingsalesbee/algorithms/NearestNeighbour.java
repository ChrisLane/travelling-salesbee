package com.teamc2.travellingsalesbee.algorithms;

import java.util.ArrayList;
import java.util.Observable;
import com.teamc2.travellingsalesbee.gui.data.Map;
import com.teamc2.travellingsalesbee.gui.data.cells.Cell;
import com.teamc2.travellingsalesbee.gui.data.cells.CellFlower;

public class NearestNeighbour extends Observable {

	private final Cell hive;
	private final Map map;
	private ArrayList<Cell> path = new ArrayList<>();
	private double cost = Double.MAX_VALUE;

	/**
	 * Constructor
	 *
	 * @param map         Map object for storing cells
	 */
	public NearestNeighbour(Map map) {
		this.map = map;
		hive = map.getHive();
	}

	/**
	 * Run the
	 * 
	 * A greedy like algorithm, the bee initially carries out a naive run where it visits
	 * the nearest non-visited neighbour until every flower has been visited, following 
	 * that it then returns to the hive
	 */
	public void run() {
		if (!(hive == null)) {
			ArrayList<Cell> newPath = new ArrayList<>();
			ArrayList<CellFlower> flowers = map.getFlowers();

			newPath.add(hive);
			
			// Loop over flowers missing from path
			CellFlower closest = null;
			
			while (!flowers.isEmpty()) {
				double bestDistance = Double.MAX_VALUE;
				closest = null;
		
				// Find the closest flower to the previous
				for (CellFlower flower : flowers) {
					double distance = flower.distance(newPath.get(newPath.size() - 1));
					if (distance < bestDistance) {
						closest = flower;
						bestDistance = distance;
					}
				}
				//Remove the flower that is closest from the set
				flowers.remove(closest);
				newPath.add(closest);
			}
			
			newPath.add(hive);

			double cost = calculatePathCost(newPath);
			setPath(newPath, cost);
		}
	}

	/**
	 * A method for retrieving the path of the current path
	 *
	 * @return Cost of the current path
	 */
	public double getPathCost() {
		return cost;
	}

	/**
	 * Calculate the cost for a given path
	 *
	 * @param path The path to calculate the cost for
	 * @return Cost of the path
	 */
	public double calculatePathCost(ArrayList<Cell> path) {
		double cost = 0;
		for (int i = 0; i < path.size()-1; i++) {
			Cell pos1 = path.get(i);
			Cell pos2 = path.get(i + 1);

			cost += pos1.distance(pos2);
		}
		return cost;
	}
	
	/**
	 * Set the current path
	 *
	 * @param path The path to be set
	 * @param cost The total cost of the path being set
	 */
	public void setPath(ArrayList<Cell> path, double cost) {
		this.path = path;
		this.cost = cost;

		setChanged();
		notifyObservers(path);
	}

	/**
	 * Return the current path
	 *
	 * @return path. The Current path.
	 */
	public ArrayList<Cell> getPath() {
		return path;
	}
	
}