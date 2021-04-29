//Benjamin Bishop
//18 Mar. 2021
//Assignment 5 cop3252
package assign5;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JCheckBoxMenuItem;


public class DesktopFrame extends JFrame{
	private JDesktopPane desktop;
	
	public DesktopFrame() {
		super ("Assignment 5");
		
		//create menu bar and add to pane
		JMenuBar menu = new JMenuBar();
		desktop = new JDesktopPane();
		add(desktop);
		
		//create menu and adding menu to bar
		JMenu menu1 = new JMenu ("Create");
		JMenu menu2 = new JMenu ("Quit");
		menu.add(menu1);
		menu.add(menu2);
		setJMenuBar(menu);
		
		//creating menu items
		JMenuItem picFrame = new JMenuItem("Picture Frame");
		JMenuItem dragFrame = new JMenuItem("Changeable Picture Frame");
		JMenuItem randFrame = new JMenuItem("Randomized Picture");
		JMenuItem quit = new JMenuItem("Quit Program");
		
		//adding menu items
		menu1.add(picFrame);
		menu1.add(dragFrame);
		menu1.add(randFrame);
		menu2.add(quit);
		
		picFrame.addActionListener(new ActionListener() { //code for the panel of picture frame.
			public void actionPerformed(ActionEvent e) {
				JInternalFrame frame = new JInternalFrame("Picture Frame",true,true,true,true);
				
				PicFrame panel = new PicFrame();
				
				frame.setBackground(Color.blue);
				
				frame.setVisible(true);
				frame.setSize((int)(.5 * desktop.getWidth()),(int)(.5 * desktop.getHeight())); 
				frame.add(panel);
				desktop.add(frame);
			}
		}
	); //end of picFrame

		dragFrame.addActionListener(new ActionListener() { //code for the panel of picture frame.
			public void actionPerformed(ActionEvent e) {
				JInternalFrame frame = new JInternalFrame("Movable Picture Frame",true,true,true,true);
				
				DragFrame panel = new DragFrame();
				HandlerClass handler = new HandlerClass(panel);
				
				JMenuBar panelMenu = new JMenuBar();//menu bar to hold my check box
				JCheckBoxMenuItem checkbox = new JCheckBoxMenuItem("Move on drag");
				panelMenu.add(checkbox);
				
				checkbox.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(checkbox.isSelected()) {
								panel.isToggled = true;
								panel.addMouseMotionListener(handler);
							}
							else {
								panel.isToggled = false;
								panel.removeMouseMotionListener(handler);
								panel.setX((.5 * panel.getWidth()) - (.15 * panel.getWidth()));
								panel.setY((.5 * panel.getHeight()) - (.15 * panel.getHeight()));
							}
							panel.repaint();
						}
					}
				);
				
				frame.setJMenuBar(panelMenu);
				frame.setVisible(true);;
				frame.setSize((int)(.5 * desktop.getWidth()),(int)(.5 * desktop.getHeight()));
				frame.add(panel);
				desktop.add(frame);
			}
		}
	); //end of dragFrame
		
		randFrame.addActionListener(new ActionListener() { //code for the panel of picture frame.
			public void actionPerformed(ActionEvent e) {
				JInternalFrame frame = new JInternalFrame("Randomized Frame",true,true,true,true);
				
				RandFrame panel = new RandFrame();
				
				frame.setVisible(true);
				frame.setSize((int)(.5 * desktop.getWidth()),(int)(.5 * desktop.getHeight()));
				frame.add(panel);
				desktop.add(frame);
			}
		}
	); //end of randFrame
		
		quit.addActionListener(new ActionListener() { //code for the panel of picture frame.
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
	); //end of quit
	
	}//end of public DesktopFrame
	
	
	//class to handle the tracking of mouse pos and repainting.
	class HandlerClass extends DragFrame implements MouseMotionListener{
		DragFrame frame;
		public HandlerClass(DragFrame f) {
			frame = f;
		}
		public void mouseDragged(MouseEvent e) {
			frame.setX(e.getX());
			frame.setY(e.getY());
			frame.repaint();
		}
		public void mouseMoved(MouseEvent e) {
			
		}
	}
}//end of public class DesktopFrame

