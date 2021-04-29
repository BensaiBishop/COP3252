//Benjamin Bishop
//18 Mar. 2021
//Assignment 5 cop3252
package assign5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

public class PicFrame extends JPanel{	
	public void paintComponent(Graphics g){
		
		Graphics2D g2d = (Graphics2D)g;
		
		//draw yellow circle
		Ellipse2D.Double circle;
		g2d.setColor(Color.YELLOW);
		
		if(this.getHeight() < this.getWidth()){ //circle will change is size with the window
			circle = new Ellipse2D.Double(.7 * this.getWidth(), .1 * this.getHeight(), .25 * this.getHeight(), .25 * this.getHeight());
		}
		else
			circle = new Ellipse2D.Double(.7 * this.getWidth(), .1 * this.getHeight(), .25 * this.getWidth(), .25 * this.getWidth());
	
		g2d.fill(circle);	
		
		
		//draw rectangle
		g2d.setColor(new Color(164,64,64));
		
		//height of rectangle is defined with window's size. will change with it.
		double x = 0;
		double y = (.9 * this.getHeight());
		double w = this.getWidth();
		double h = (.1 * this.getHeight());
		
		Rectangle2D.Double rectangle = new Rectangle2D.Double(x, y, w, h);
	
		g2d.fill(rectangle);
	}
}