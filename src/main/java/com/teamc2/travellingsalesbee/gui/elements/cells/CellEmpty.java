package com.teamc2.travellingsalesbee.gui.elements.cells;

import java.awt.image.BufferedImage;

public class CellEmpty extends Cell {

	/**
	 * Create a new empty cell
	 *
	 * @param x X position of cell
	 * @param y Y position of cell
	 */
	public CellEmpty(double x, double y) {
		super(x, y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CellType getType() {
		return CellType.EMPTY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BufferedImage getImage() {
		return null;
	}

}