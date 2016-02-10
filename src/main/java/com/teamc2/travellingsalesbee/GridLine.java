package com.teamc2.travellingsalesbee;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class GridLine extends JPanel {

	private float x1;
	private float y1;
	private float x2;
	private float y2;
	
	public GridLine(float x1, float x2, float y1, float y2){
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
	}
	private static final long serialVersionUID = 1L;

	@Override
	public void paintComponent( Graphics g ){
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(10));
		g2.draw(new Line2D.Float(x1, y1, x2, y2));
	}

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }
    
   
}