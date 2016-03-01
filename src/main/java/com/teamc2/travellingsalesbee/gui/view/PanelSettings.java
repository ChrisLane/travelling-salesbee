package com.teamc2.travellingsalesbee.gui.view;

import static com.teamc2.travellingsalesbee.gui.data.cells.CellType.FLOWER;
import static com.teamc2.travellingsalesbee.gui.data.cells.CellType.HIVE;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.teamc2.travellingsalesbee.algorithms.Bee;
import com.teamc2.travellingsalesbee.gui.CellDrag;
import com.teamc2.travellingsalesbee.gui.data.Map;
import com.teamc2.travellingsalesbee.gui.data.cells.Cell;
import com.teamc2.travellingsalesbee.visualisation.BeeVisualiser;

public class PanelSettings extends JPanel {
	
	private final PanelMap panelMap;


	private JLabel infoLabel;

	private int experimentalRuns = 26; //Set to 26 by default
	private int stepNum = 0;

	// Remove later
	protected boolean debug = true;

	/**
	 * Create a settings panel
	 *
	 * @param panelMap Map JPanel
	 */
	public PanelSettings(PanelMap panelMap) {
		this.panelMap = panelMap;

		setBackground(Color.LIGHT_GRAY);
		addSettingsInfo();
		addButtons();
	}

	/**
	 * Add the background to the settings panel
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		try {
			BufferedImage img = ImageIO.read(this.getClass().getResource("/assets/backgrounds/GreyBack150.png"));
			TexturePaint paint = new TexturePaint(img, new Rectangle(0, 0, img.getWidth(), img.getHeight()));
			g2.setPaint(paint);
			g2.fill(new Rectangle(0, 0, getWidth(), getHeight()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Add information on how to use the settings panel
	 */
	public void addSettingsInfo() {
		infoLabel = new JLabel("Settings box, we also need to choose a background image for this");
	}

	/**
	 * Add the settings buttons to the panel
	 */
	public void addButtons() {
		JButton btnRun = new JButton("RUN");
		btnRun.addActionListener(arg0 -> {

			//Get parent width and height
			int panelWidth = panelMap.getWidth();
			int panelHeight = panelMap.getHeight();

			if (debug) System.out.print(panelWidth);
			if (debug) System.out.print(panelHeight);
			if (debug) System.out.println("Button pressed!");

			//Gets the amount of cells respective to the width and height of the map
			Map map = new Map(panelWidth, panelHeight); //Initialising Map with cellsX and cellsY as width and height of map

			//Add all cells to the map
			for (Component c : panelMap.getComponents()) {
				if (c instanceof CellDrag) {
					if (c.isEnabled() && ((CellDrag) c).getType().equals(FLOWER)) {
						map.setCell(c.getX(), c.getY(), FLOWER); //Add flower positions to map
					} else if (c.isEnabled() && ((CellDrag) c).getType().equals(HIVE)) {
						map.setCell(c.getX(), c.getY(), HIVE); //Add hive position to map
					}
				}
			}

			Bee bee = new Bee(map, experimentalRuns);
			BeeVisualiser visualise = new BeeVisualiser();
			bee.naiveRun();
			panelMap.getPathComponent().setNaiveSteps(visualise.getNaiveSteps(bee.getPath()));
			bee.experimentalRun();
			panelMap.getPathComponent().setPath(bee.getPath());

			//System.out.println("Naive Step 0 size: " + bee.getNaiveSteps().get(0).getAvailable().size());
			//System.out.println("Path Cost: " + bee.getPathCost());

		});

		btnRun.setFont(new Font("Tahoma", Font.PLAIN, 17));

		JSlider slider = new JSlider();
		slider.setValue(experimentalRuns);
		JLabel lblExperimentRuns = new JLabel("Experiment Runs:");
		JLabel lblNoOfRuns = new JLabel("" + experimentalRuns);

		slider.addChangeListener(arg0 -> {
			if (debug) System.out.println("Slider Changed");
			slider.setValue(slider.getValue());
			lblNoOfRuns.setText("" + slider.getValue());
			experimentalRuns = slider.getValue();
		});

		JTextPane txtPaneTextWillAppear = new JTextPane();
		txtPaneTextWillAppear.setText("Text will appear here as you step through the algorithm, explaining how it works at each step");

		JButton btnPrev = new JButton("<-");
		btnPrev.addActionListener(arg0 -> {
			stepNum--;
			panelMap.getPathComponent().setStepNumber(stepNum);
		});
		JButton btnNext = new JButton("->");
		btnNext.addActionListener(arg0 -> {
			stepNum++;
			panelMap.getPathComponent().setStepNumber(stepNum);
		});

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(infoLabel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblExperimentRuns)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lblNoOfRuns))
										.addComponent(slider, GroupLayout.PREFERRED_SIZE, 457, GroupLayout.PREFERRED_SIZE))
								.addGap(10)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(btnRun, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
												.addComponent(btnPrev)
												.addGap(18)
												.addComponent(btnNext))
										.addComponent(txtPaneTextWillAppear, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
								.addContainerGap())
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(infoLabel)
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblExperimentRuns)
										.addComponent(lblNoOfRuns))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(slider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(211, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnPrev, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnRun))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(txtPaneTextWillAppear, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addGap(69))
		);
		setLayout(groupLayout);
	}
}
