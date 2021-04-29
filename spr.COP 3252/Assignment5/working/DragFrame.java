//Benjamin Bishop
//18 Mar. 2021
//Assignment 5 cop3252
package assign5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

public class DragFrame extends JPanel{
	private Ellipse2D.Double oval;
	private double x;
	private double y;
	
	boolean isToggled = false; //checkbox is off by default
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		if(!isToggled) {
			x=(.5 * getWidth()) - (.15 * getWidth());
			y=(.5 * getHeight()) - (.15 * getHeight());
		}
		
		//width and height of the oval
		double w = (.3 * getWidth());
		double h = (.3 * getHeight());
		
		oval = new Ellipse2D.Double(x,y,w,h);
		
		g2d.setColor(Color.green);
		g2d.fill(oval);
	}
	
	public void setX(double xPos) {
		x = xPos - (.15 * getWidth());
	}
	
	public void setY(double yPos) {
		y = yPos - (.15 * getHeight());
	}
}
