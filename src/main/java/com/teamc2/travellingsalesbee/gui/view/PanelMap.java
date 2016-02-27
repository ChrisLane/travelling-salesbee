package com.teamc2.travellingsalesbee.gui.view;

import com.teamc2.travellingsalesbee.gui.NaiveStep;
import com.teamc2.travellingsalesbee.gui.data.cells.Cell;
import com.teamc2.travellingsalesbee.gui.view.pages.AnimalAnimation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


public class PanelMap extends JPanel implements Runnable {
	private final int cellWidth;
	private final int cellHeight;

	private ArrayList<Cell> beePath = new ArrayList<>();
	private ArrayList<NaiveStep> naiveSteps = new ArrayList<>();

	private double beePosX = 0;
	private double beePosY = 0;
	private int stepNum = 0;

	/**
	 * Create the map panel
	 *
	 * @param cellWidth  Width of the grid sections
	 * @param cellHeight Height of the grid sections
	 */
	public PanelMap(int cellWidth, int cellHeight) {
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;

		genGrid();
		setLayout(null);
	}

	/**
	 * Paint the grass texture onto the map and the grid
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		try {
			BufferedImage img = ImageIO.read(this.getClass().getResource("/assets/backgrounds/Grass.jpg"));
			TexturePaint paint = new TexturePaint(img, new Rectangle(0, 0, img.getWidth(), img.getHeight()));
			g2.setPaint(paint);
			g2.fill(new Rectangle(0, 0, getWidth(), getHeight()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (beePath.size() > 100) {

			int x1, x2 = 0, y1, y2 = 0;
			int transparencyIncrement = Math.round(170 / beePath.size());
			int transparency = 0;
			int lineThicknessIncrement = Math.round(4 / beePath.size());
			int lineThickness = 5;
			for (int i = 0; i < beePath.size() - 1; i++) {

				x1 = (int) beePath.get(i).x;
				y1 = (int) beePath.get(i).y;
				x2 = (int) beePath.get(i + 1).x;
				y2 = (int) beePath.get(i + 1).y;
				Color lineColor = new Color(255, 255, 0, 75 + transparency);

				g2.setPaint(lineColor);
				g2.setStroke(new BasicStroke(lineThickness));
				g2.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);
				String position = Integer.toString(i);
				g2.setPaint(Color.white);
				g2.setFont(new Font(null, Font.PLAIN, 15));
				g2.drawString(position, x1 + (50 / 12), y1 + (50 / 4));

				lineThickness = lineThickness - lineThicknessIncrement;
				transparency += transparencyIncrement;
			}
			g2.setPaint(new Color(255, 255, 0, 75 + transparency + 10));
			g2.setStroke(new BasicStroke(5));
			g2.drawLine(x2 + (50 / 2), y2 + (50 / 2), (int) beePath.get(0).x + (50 / 2), (int) beePath.get(0).y + (50 / 2));

		} else if (naiveSteps.size() > 0){
			int x1, x2 = 0, y1, y2 = 0;

			for (int i = 0; i<stepNum+1;i++){
				NaiveStep step = naiveSteps.get(i);
				System.out.println(step.getAvailable().size());
				x1 = (int) step.getStart().x;
				y1 = (int) step.getStart().y;

				ArrayList<Cell> available = step.getAvailable();
				System.out.println("naiveSteps.size() " + naiveSteps.size());
				System.out.println("available size " + available.size());

				if (i==stepNum){
					for (int j=0;j<available.size();j++){
						g2.setStroke(new BasicStroke(5));
						g2.setPaint(Color.red);
						x2 = (int) available.get(j).x;
						y2 = (int) available.get(j).y;
						g2.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);
					}
				}

				g2.setStroke(new BasicStroke(6));
				g2.setPaint(Color.green);
				System.out.println("Green: " + step.getStart() + "->" + step.getEnd());
				x2 = (int) step.getEnd().x;
				y2 = (int) step.getEnd().y;
				g2.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);
			}
		}

		if(naiveSteps.size() > 0) {
			String url = "/assets/icons/SalesBee.png";
			AnimalAnimation aAnimation = new AnimalAnimation(this, url, naiveSteps.get(stepNum), g2);

			//aAnimation.paintComponents(g);
			//aAnimation.repaint();
			//this.repaint();

			this.add(aAnimation);
		}
	}

	/**
	 * Return the height of grid sections
	 *
	 * @return Height of grid sections
	 */
	public int getCellHeight() {
		return cellHeight;
	}

	/**
	 * Return the width of grid sections
	 *
	 * @return Width of grid sections
	 */
	public int getCellWidth() {
		return cellWidth;
	}

	/**
	 * Set the path in algorithms
	 *
	 * @param path Path to set in algorithms
	 */
	public void setPath(ArrayList<Cell> path) {
		this.beePath = path;
		this.repaint();
	}

	/**
	 * Generate the grid lines that together form the grid.
	 */
	public void genGrid() {
		ComponentGrid componentGrid = new ComponentGrid(cellWidth, cellHeight);
		add(componentGrid);
	}


	//Naive visualisation
	public void setNaiveSteps(ArrayList<NaiveStep> steps) {
		this.naiveSteps = steps;
		this.repaint();
	}

	public void setStepNumber(int stepNum){
		this.stepNum = stepNum;
		this.repaint();
	}

	@Override
	public void run() {

	}
}