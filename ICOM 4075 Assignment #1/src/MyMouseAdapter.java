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
			//Do nothing
			Component c1 = e.getComponent();
			while (!(c1 instanceof JFrame)) {
				c1 = c1.getParent();
				if (c1 == null) {
					return;
				}
			}
			JFrame myFrame1 = (JFrame) c1;
			MyPanel myPanel11 = (MyPanel) myFrame1.getContentPane().getComponent(0);
			Insets myInsets1 = myFrame1.getInsets();
			int x11 = myInsets1.left;
			int y11 = myInsets1.top;
			e.translatePoint(-x11, -y11);
			int x2 = e.getX();
			int y2 = e.getY();
			myPanel11.x = x2;
			myPanel11.y = y2;
			myPanel11.mouseDownGridX = myPanel11.getGridX(x2, y2);
			myPanel11.mouseDownGridY = myPanel11.getGridY(x2, y2);
			if ((myPanel11.mouseDownGridX == -1) || (myPanel11.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
				Color newColor = null;
				for (int i=1; i<=9; i++){
					for (int j = 1; j<=9; j++)
				{
					switch (generator.nextInt(5)) {
					case 0:
					if(myPanel11.colorArray[myPanel11.mouseDownGridX][myPanel11.mouseDownGridY].equals(Color.YELLOW)) {
					newColor = Color.BLACK;
					} else {
					newColor = Color.YELLOW;
					break;
					}
				case 1:
					if(myPanel11.colorArray[myPanel11.mouseDownGridX][myPanel11.mouseDownGridY].equals(Color.MAGENTA)) {
						newColor = Color.YELLOW;
					} else {
					newColor = Color.MAGENTA;
					break;
					}
				case 2:
					if(myPanel11.colorArray[myPanel11.mouseDownGridX][myPanel11.mouseDownGridY].equals(Color.BLACK)) {
						newColor = Color.MAGENTA;
					} else {
					newColor = Color.BLACK;
					break;
					}
				case 3:
					Color C2 = new Color(0xB57EDC);						
					Color C1 = new Color(0x964B00);
					if(myPanel11.colorArray[myPanel11.mouseDownGridX][myPanel11.mouseDownGridY].equals(C1)) {
						newColor = C2;
					} else {
					newColor = C1;   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
					break;
					}
				case 4:						
					Color C21 = new Color(0xB57EDC);
					Color C11 = new Color(0x964B00);
					if(myPanel11.colorArray[myPanel11.mouseDownGridX][myPanel11.mouseDownGridY].equals(C21)) {
						newColor = C11;
					} else {
					newColor = C21;   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
					break;}
					}
				
					myPanel11.colorArray[i][j] = newColor;
					myPanel11.repaint();}
				}
			
			} else {}
			
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
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
						if (gridX == 0 && gridY ==10){
							Color newColor = null;
							for (int i=4; i<=6; i++){
								for (int j = 4; j<=6; j++)
							{
								switch (generator.nextInt(5)) {
								case 0:
								if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.YELLOW)) {
								newColor = Color.BLACK;
								} else {
								newColor = Color.YELLOW;
								break;
								}
							case 1:
								if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.MAGENTA)) {
									newColor = Color.YELLOW;
								} else {
								newColor = Color.MAGENTA;
								break;
								}
							case 2:
								if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.BLACK)) {
									newColor = Color.MAGENTA;
								} else {
								newColor = Color.BLACK;
								break;
								}
							case 3:
								Color C2 = new Color(0xB57EDC);						
								Color C1 = new Color(0x964B00);
								if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(C1)) {
									newColor = C2;
								} else {
								newColor = C1;   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
								break;
								}
							case 4:						
								Color C21 = new Color(0xB57EDC);
								Color C11 = new Color(0x964B00);
								if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(C21)) {
									newColor = C11;
								} else {
								newColor = C21;   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
								break;}
								}
							
								myPanel.colorArray[i][j] = newColor;
								myPanel.repaint();}
							}
						} else
						if (gridX == 0 && gridY == 0){
							Color newColor = null;
							int i = 1;
								while (i <= 9){
								switch (generator.nextInt(5)) {
								case 0:
								if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.YELLOW)) {
								newColor = Color.BLACK;
								} else {
								newColor = Color.YELLOW;
								break;
								}
							case 1:
								if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.MAGENTA)) {
									newColor = Color.YELLOW;
								} else {
								newColor = Color.MAGENTA;
								break;
								}
							case 2:
								if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.BLACK)) {
									newColor = Color.MAGENTA;
								} else {
								newColor = Color.BLACK;
								break;
								}
							case 3:
								Color C2 = new Color(0xB57EDC);						
								Color C1 = new Color(0x964B00);
								if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(C1)) {
									newColor = C2;
								} else {
								newColor = C1;   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
								break;
								}
							case 4:						
								Color C21 = new Color(0xB57EDC);
								Color C11 = new Color(0x964B00);
								if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(C21)) {
									newColor = C11;
								} else {
								newColor = C21;   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
								break;}
							} 
								myPanel.colorArray[i][i] = newColor;
								i++;
								myPanel.repaint();
								}	
						} else
						if (gridY == 0){
						Color newColor = null;
						int i = 1;
							while (i <= 9){
							switch (generator.nextInt(5)) {
							case 0:
							if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.YELLOW)) {
							newColor = Color.BLACK;
							} else {
							newColor = Color.YELLOW;
							break;
							}
						case 1:
							if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.MAGENTA)) {
								newColor = Color.YELLOW;
							} else {
							newColor = Color.MAGENTA;
							break;
							}
						case 2:
							if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.BLACK)) {
								newColor = Color.MAGENTA;
							} else {
							newColor = Color.BLACK;
							break;
							}
						case 3:
							Color C2 = new Color(0xB57EDC);						
							Color C1 = new Color(0x964B00);
							if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(C1)) {
								newColor = C2;
							} else {
							newColor = C1;   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
							break;
							}
						case 4:						
							Color C21 = new Color(0xB57EDC);
							Color C11 = new Color(0x964B00);
							if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(C21)) {
								newColor = C11;
							} else {
							newColor = C21;   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
							break;}
						} 
						
							myPanel.colorArray[myPanel.mouseDownGridX][i] = newColor;
							i++;
							
							
							myPanel.repaint();}
						
							
						} else
						if ((gridX == 0)) {
							Color newColor = null;
							int i = 1;
							while (i <= 9){
							switch (generator.nextInt(5)) {
							case 0:
								if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.YELLOW)) {
								newColor = Color.BLACK;
								} else {
								newColor = Color.YELLOW;
								break;
								}
							case 1:
								if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.MAGENTA)) {
									newColor = Color.YELLOW;
								} else {
								newColor = Color.MAGENTA;
								break;
								}
							case 2:
								if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.BLACK)) {
									newColor = Color.MAGENTA;
								} else {
								newColor = Color.BLACK;
								break;
								}
							case 3:
								Color C2 = new Color(0xB57EDC);
								Color C1 = new Color(0x964B00);
								if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(C1)) {
									newColor = C2;
								} else {
								newColor = C1;   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
								break;
								}
							case 4:
								Color C21 = new Color(0xB57EDC);
								Color C11 = new Color(0x964B00);
								if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(C21)) {
									newColor = C11;
								} else {
								newColor = C21;   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
								break;}
							} 
						
							myPanel.colorArray[i][myPanel.mouseDownGridY] = newColor;
							i++;
							
							
							myPanel.repaint();}
						}	//On the left column and on the top row... do nothing
						 else {
							//On the grid other than on the left column and on the top row:
							Color newColor = null;
							switch (generator.nextInt(5)) {
							case 0:
								if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.YELLOW)) {
								newColor = Color.BLACK;
								} else {
								newColor = Color.YELLOW;
								break;
								}
							case 1:
								if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.MAGENTA)) {
									newColor = Color.YELLOW;
								} else {
								newColor = Color.MAGENTA;
								break;
								}
							case 2:
								if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.BLACK)) {
									newColor = Color.MAGENTA;
								} else {
								newColor = Color.BLACK;
								break;
								}
							case 3:
								Color C2 = new Color(0xB57EDC);
								Color C1 = new Color(0x964B00);
								if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(C1)) {
									newColor = C2;
								} else {
								newColor = C1;   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
								break;
								}
							case 4:
								Color C21 = new Color(0xB57EDC);
								Color C11 = new Color(0x964B00);
								if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(C21)) {
									newColor = C11;
								} else {
								newColor = C21;   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
								break;}
							} 
							
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
							myPanel.repaint();
						}
					}
				}
			}
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			//Do nothing
			
			break;
		}
	}
}
