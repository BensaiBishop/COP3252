//Benjamin Bishop
//18 Mar. 2021
//Assignment 5 cop3252
package assign5;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class RandFrame extends JPanel{
	Random rand = new Random();
	
	public void paintComponent(Graphics g){
		int num = rand.nextInt(5) + 1;
		
		for(int i = 0; i < num; ++i){ // rectangles will change when the window is resized
			g.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
			g.fillRect(rand.nextInt(this.getWidth()), rand.nextInt(this.getHeight()), rand.nextInt((int) (.5 *this.getWidth())) + 1, rand.nextInt((int) (.5 *this.getHeight())) + 1);
		}
	}
}

