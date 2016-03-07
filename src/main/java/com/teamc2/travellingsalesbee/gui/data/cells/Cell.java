package com.teamc2.travellingsalesbee.gui.data.cells;

import java.awt.Image;
import java.awt.geom.Point2D;

import com.teamc2.travellingsalesbee.gui.view.AlgorithmType;

public abstract class Cell extends Point2D.Double {

	@Override
	public String toString() {
		return "[(" + (int) x + ", " + (int) y + ") " + getType() + "]";
	}

	/**
	 * Create a new cell
	 */
	public Cell() {
	}

	/**
	 * Create a new cell
	 *
	 * @param x X position of cell
	 * @param y Y position of cell
	 */
	public Cell(double x, double y) {
		super(x, y);
	}

	/**
	 * Get the type of the cell
	 *
	 * @return Type of the cell
	 */
	public abstract CellType getType();

	/**
	 * Get the cell image
	 *
	 * @return Cell image
	 */
	protected abstract Image getImage(AlgorithmType type);
}