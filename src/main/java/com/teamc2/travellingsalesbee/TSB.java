package com.teamc2.travellingsalesbee;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JToolBar;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JTextArea;

public class TSB extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TSB frame = new TSB();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TSB() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel_toolbox = new JPanel();
		panel_toolbox.setBackground(Color.WHITE);
		
		JPanel panel_settings = new JPanel();
		panel_settings.setBackground(Color.LIGHT_GRAY);
		
		JPanel panel_gridmap = new JPanel();
		panel_gridmap.setBackground(new Color(0, 204, 51));
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
		panel_settings.setLayout(null);
		
		JLabel lblSettingsBoxWe = new JLabel("Settings box, we also need to choose a background image for this");
		lblSettingsBoxWe.setBounds(10, 11, 350, 14);
		panel_settings.add(lblSettingsBoxWe);
		panel_gridmap.setLayout(null);
		
		JLabel lblWeNeedTo = new JLabel("We need to pick a background image for this");
		lblWeNeedTo.setBounds(121, 34, 287, 14);
		panel_gridmap.add(lblWeNeedTo);
		
		JTextArea txtrDragElementsOnto = new JTextArea();
		txtrDragElementsOnto.setEditable(false);
		txtrDragElementsOnto.setWrapStyleWord(true);
		txtrDragElementsOnto.setLineWrap(true);
		txtrDragElementsOnto.setText("Drag elements onto the gridmap!");
		GroupLayout gl_panel_toolbox = new GroupLayout(panel_toolbox);
		gl_panel_toolbox.setHorizontalGroup(
			gl_panel_toolbox.createParallelGroup(Alignment.LEADING)
				.addComponent(txtrDragElementsOnto, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
		);
		gl_panel_toolbox.setVerticalGroup(
			gl_panel_toolbox.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_toolbox.createSequentialGroup()
					.addComponent(txtrDragElementsOnto, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(237, Short.MAX_VALUE))
		);
		panel_toolbox.setLayout(gl_panel_toolbox);
		contentPane.setLayout(gl_contentPane);
	}
}