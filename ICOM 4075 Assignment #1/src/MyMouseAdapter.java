import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
	private Random generator = new Random();
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame) c;
			MyPanel myPanel1 = (MyPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel1.x = x;
			myPanel1.y = y;
			myPanel1.mouseDownGridX = myPanel1.getGridX(x, y);
			myPanel1.mouseDownGridY = myPanel1.getGridY(x, y);
			myPanel1.repaint();
			break;
		case 3:		//Right mouse button
			c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			myFrame = (JFrame) c;
			myPanel1 = (MyPanel) myFrame.getContentPane().getComponent(0);
			myInsets = myFrame.getInsets();
			x1 = myInsets.left;
			y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			x = e.getX();
			y = e.getY();
			myPanel1.x = x;
			myPanel1.y = y;
			myPanel1.mouseDownGridX = myPanel1.getGridX(x, y);
			myPanel1.mouseDownGridY = myPanel1.getGridY(x, y);
			myPanel1.repaint();
            break;
        default:    //Some other button (2 = Middle mouse button, etc.)
        	c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			myFrame = (JFrame) c;
			myPanel1 = (MyPanel) myFrame.getContentPane().getComponent(0);
			myInsets = myFrame.getInsets();
			x1 = myInsets.left;
			y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			x = e.getX();
			y = e.getY();
			myPanel1.x = x;
			myPanel1.y = y;
			myPanel1.mouseDownGridX = myPanel1.getGridX(x, y);
			myPanel1.mouseDownGridY = myPanel1.getGridY(x, y);
			myPanel1.repaint();
            break;
        }
    }
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame)c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						//Released the mouse button on the same cell where it was pressed
						
						 
						//if (gridX == 11 && gridY == 11){
					//		System.out.println("hey");
					//	} else
					//	if (gridY == 11){
						
					//	} else
					//	if ((gridX == 11)) {
							
					//	}	//On the left column and on the top row... do nothing
					//	 else {
						if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == Color.GRAY)  //click uncover
						{
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.LIGHT_GRAY;
						} 
							 if(myPanel.booleanArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == true){   //If Hits Bomb
						 for(int n = 0; n < myPanel.TOTAL_COLUMNS; n++){
							 for(int m = 0; m < myPanel.TOTAL_ROWS; m++){
								 myPanel.colorArray[n][m] = Color.RED;
							 }}
						 } else {
								 System.out.println("WOW");}
						 }break;}}
		case 3:		//Right mouse button
			//Do nothing
			c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			myFrame = (JFrame)c;
			myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			myInsets = myFrame.getInsets();
			x1 = myInsets.left;
			y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			x = e.getX();
			y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			gridX = myPanel.getGridX(x, y);
			gridY = myPanel.getGridY(x, y);
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} 
			else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing				
					} 
				else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					}
					else {
						//Released the mouse button on the same cell where it was pressed
						if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.GRAY)){
							Color newColor = Color.BLUE;
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
							myPanel.repaint();
						}
						else if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.BLUE)){
							Color newColor = Color.GRAY;
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
							myPanel.repaint();
						}
					}
				}
			}
			break;
        default:    //Some other button (2 = Middle mouse button, etc.)
			c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			myFrame = (JFrame)c;
			myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			myInsets = myFrame.getInsets();
			x1 = myInsets.left;
			y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			x = e.getX();
			y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			gridX = myPanel.getGridX(x, y);
			gridY = myPanel.getGridY(x, y);
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Resets the game.
			
				for(int n=0; n<myPanel.TOTAL_COLUMNS; n++){
					for(int m=0; m<myPanel.TOTAL_ROWS; m++){
						myPanel.colorArray[n][m] = Color.GRAY;
					}
				}
			}
            break;
    }
  }
}
