package com.teamc2.travellingsalesbee;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class TSB extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int width = 50;
	private int height = 50;
	private boolean hiveSet = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				TSB frame = new TSB();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TSB() {
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel_toolbox = new JPanel();
		panel_toolbox.setBackground(Color.WHITE);

		JPanel panel_settings = new JPanel();
		panel_settings.setBackground(Color.LIGHT_GRAY);

		
		//ADD GRID TO THE GRIDMAP
		JPanel panel_gridmap = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel_toolbox, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_settings, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
								.addComponent(panel_gridmap, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel_gridmap, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_settings, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
				.addComponent(panel_toolbox, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
				);


		panel_gridmap = genGrid(panel_gridmap);
		
		Image background;
		try {
			background = ImageIO.read(new File("target/classes/backgrounds/grass.jpg"));
			JLabel bg = new JLabel(new ImageIcon(background));
			bg.setBounds(0,0,2000,2000);
			panel_gridmap.add(bg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		panel_settings.setLayout(null);

		JLabel lblSettingsBoxWe = new JLabel("Settings box, we also need to choose a background image for this");
		lblSettingsBoxWe.setBounds(10, 11, 350, 14);
		panel_settings.add(lblSettingsBoxWe);
		panel_gridmap.setLayout(null);


		JTextArea txtrDragElementsOnto = new JTextArea();
		txtrDragElementsOnto.setEditable(false);
		txtrDragElementsOnto.setWrapStyleWord(true);
		txtrDragElementsOnto.setLineWrap(true);
		txtrDragElementsOnto.setText("Drag elements onto the gridmap!");



		CellDrag flowerToolCell = new CellDrag("", width, height, "FLOWER");
		CellDrag hiveToolCell = new CellDrag("", width, height, "HIVE");
		try { 
			Image flowerImg = ImageIO.read(new File("target/classes/icons/Flower.png"));
			Image scaledFlowerImg = flowerImg.getScaledInstance( width, height,  java.awt.Image.SCALE_SMOOTH ) ;

			Image hiveImg = ImageIO.read(new File("target/classes/icons/Hive.png"));
			Image scaledHiveImg = hiveImg.getScaledInstance( width, height,  java.awt.Image.SCALE_SMOOTH ) ;

			
			flowerToolCell.setIcon(new ImageIcon(scaledFlowerImg));
			hiveToolCell.setIcon(new ImageIcon(scaledHiveImg));

		} catch (IOException ex) {
		}
		flowerToolCell.setPanel(panel_gridmap);
		flowerToolCell.setBounds(0, 150, width, height);		
		hiveToolCell.setPanel(panel_gridmap);
		hiveToolCell.setBounds(0, 155+height, width, height);
		panel_toolbox.add(flowerToolCell);
		panel_toolbox.add(hiveToolCell);
		System.out.println(panel_gridmap.getX());

		GroupLayout gl_panel_toolbox = new GroupLayout(panel_toolbox);
		gl_panel_toolbox.setHorizontalGroup(
				gl_panel_toolbox.createParallelGroup(Alignment.LEADING)
				.addComponent(txtrDragElementsOnto, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
				);
		gl_panel_toolbox.setVerticalGroup(
				gl_panel_toolbox.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_toolbox.createSequentialGroup()
						.addComponent(txtrDragElementsOnto, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addContainerGap(330, Short.MAX_VALUE))
				);
		panel_toolbox.setLayout(gl_panel_toolbox);
		contentPane.setLayout(gl_contentPane);
		
		/*frame.addComponentListener(new ComponentListener() {
		    public void componentResized(ComponentEvent e) {
		        // do stuff           
		    	System.out.println("Window is re-sized");
		    }

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});*/
	}

	public JPanel genGrid(JPanel panel_gridmap){
		int widthCount = 0; //Keeps track of current horizontal line we're drawing
		int heightCount = 0;//Keeps track of current vertical we're drawing
		
		//Gets the width and height of the users screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;

		//While the widthCount is less than the width of the users screen, draw horizontal lines
		while (widthCount < screenWidth){


			@SuppressWarnings("serial")
			GridLine gridLine = new GridLine(widthCount,0,widthCount, Integer.MAX_VALUE);
			gridLine.setBackground(Color.black);
			gridLine.setBounds(widthCount, 0, 3, Integer.MAX_VALUE);
			panel_gridmap.add(gridLine);



			widthCount = widthCount + width;
		}

		//While the heightCount is less than the height of the users screen, draw vertical lines
		while (heightCount < screenHeight){


			@SuppressWarnings("serial")
			GridLine gridLine = new GridLine(0,Integer.MAX_VALUE,heightCount,heightCount);
			gridLine.setBackground(Color.black);
			gridLine.setBounds(0, heightCount, Integer.MAX_VALUE, 3);
			panel_gridmap.add(gridLine);


			heightCount = heightCount + height;
		}
		return panel_gridmap;
	}
}
