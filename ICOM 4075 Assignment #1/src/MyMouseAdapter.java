import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {

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
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
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
			myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			myInsets = myFrame.getInsets();
			x1 = myInsets.left;
			y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			x = e.getX();
			y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
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
			myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			myInsets = myFrame.getInsets();
			x1 = myInsets.left;
			y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			x = e.getX();
			y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
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
						if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.RED) ||myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.BLUE) ){
							// If clicks on red or blue cell, do nothing
						}else{

							int revealed = 0;
							int bombs = 0;

							myPanel.checkCount(myPanel.mouseDownGridX, myPanel.mouseDownGridY); //Funcion que cuenta alrededor las mines

							System.out.println(myPanel.checkCount(myPanel.mouseDownGridX, myPanel.mouseDownGridY));
							if(myPanel.checkCount(myPanel.mouseDownGridX, myPanel.mouseDownGridY)==0){
								myPanel.paintCheck(myPanel.mouseDownGridX,myPanel.mouseDownGridY);
								//Funcion que pinta alrededor de una celda 0	
							}
							if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.GRAY))  //click uncover
							{
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.LIGHT_GRAY;
								myPanel.repaint();
							} 
							for(int n=0; n<myPanel.TOTAL_COLUMNS; n++){
								for(int m=0; m<myPanel.TOTAL_ROWS; m++){
									if(myPanel.colorArray[n][m].equals(Color.LIGHT_GRAY) ){
										revealed++;
									}
									if(myPanel.colorArray[n][m].equals(Color.RED) ){
										bombs++;
									}
									if(revealed == myPanel.TOTAL_COLUMNS*myPanel.TOTAL_ROWS - myPanel.TotalMines && bombs != myPanel.TotalMines ){
										System.out.println("YOU WIN");	

									}
								}
							}
							if(myPanel.booleanArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == true){  
								System.out.println("BOOOM!!!");      //If Hits Bomb
								for(int n = 0; n < myPanel.TOTAL_COLUMNS; n++){
									for(int m = 0; m < myPanel.TOTAL_ROWS; m++){
										if(myPanel.booleanArray[n][m]== true)
										{ myPanel.colorArray[n][m] = Color.RED;
										myPanel.repaint();} else {
											myPanel.colorArray[n][m] = Color.LIGHT_GRAY;
											myPanel.repaint();
										}
									}
								}
							} 
						}break;
					}
				}
			}
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
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing				
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					}else {
						//Released the mouse button on the same cell where it was pressed
						int flags = 0;
						for(int n=0; n<myPanel.TOTAL_COLUMNS; n++){
							for(int m=0; m<myPanel.TOTAL_ROWS; m++){
								if(myPanel.colorArray[n][m].equals(Color.BLUE)){
									flags++;
								}

							}
						}
						if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.GRAY)){
							if(flags < myPanel.TotalMines){
								Color newColor = Color.BLUE;
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
								myPanel.repaint();
							}
						}	else 
							if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.BLUE)){
								Color newColor = Color.GRAY;
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
								myPanel.repaint();

							}

						int flag = 0;
						for(int n=0; n<myPanel.TOTAL_COLUMNS; n++){
							for(int m=0; m<myPanel.TOTAL_ROWS; m++){
								if (myPanel.booleanArray[n][m] && myPanel.colorArray[n][m].equals(Color.BLUE)){
									flag++;
								}
								if(myPanel.booleanArray[n][m] && myPanel.colorArray[n][m].equals(Color.GRAY)){
									flag--;
								}

							}
							if (flag == myPanel.TotalMines){
								System.out.println("You Win!");}
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
						myPanel.repaint();
						for(int n1=0;n1 < myPanel.TOTAL_COLUMNS; n1++){
							for(int m1=0; m1 < myPanel.TOTAL_ROWS; m1++){
								myPanel.booleanArray[n1][m1] = false;
							}
						}myPanel.Mines = 0;
					}
				}
			}
			break;  
		}

	}

}
