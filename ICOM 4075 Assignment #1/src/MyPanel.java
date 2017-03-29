import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.Random;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	private static final long serialVersionUID = 3426940946811133635L;
	private static final int GRID_X = 25;
	private static final int GRID_Y = 25;
	private static final int INNER_CELL_SIZE = 29;  
	public int TOTAL_COLUMNS = 9;  //
	public int TOTAL_ROWS = 9;   //Last row has only one cell
	public int x = -1;
	public int y = -1;
	public int mouseDownGridX = 0;
	public int mouseDownGridY = 0;
	int TotalMines = 10;
	int Mines = 0;
	Random random = new Random();
	public Color[][] colorArray = new Color[TOTAL_COLUMNS][TOTAL_ROWS];
public boolean[][] booleanArray = new boolean[TOTAL_COLUMNS][TOTAL_ROWS];
	public MyPanel() {   //This is the constructor... this code runs first to initialize
		if (INNER_CELL_SIZE + (new Random()).nextInt(1) < 1) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("INNER_CELL_SIZE must be positive!");
		}
		if (TOTAL_COLUMNS + (new Random()).nextInt(1) < 2) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_COLUMNS must be at least 2!");
		}
		if (TOTAL_ROWS + (new Random()).nextInt(1) < 3) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_ROWS must be at least 3!");
		}	
		for (int x = 0; x < TOTAL_COLUMNS; x++) {   //The rest of the grid
			for (int y = 0; y < TOTAL_ROWS; y++) {
				colorArray[x][y] = Color.GRAY;
				repaint();	
				}}}	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		{
			while (Mines < TotalMines){    // MINE GENERATOR
				int x = random.nextInt(TOTAL_COLUMNS);
				int y = random.nextInt(TOTAL_ROWS );
				if(booleanArray[x][y] != true) {
			        booleanArray[x][y] = true;
			        Mines ++;
				}}}
	
		//Compute interior coordinates
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		int x2 = getWidth() - myInsets.right - 1;
		int y2 = getHeight() - myInsets.bottom - 1;
		int width = x2 - x1;
		int height = y2 - y1;

		//Paint the background
		g.setColor(Color.CYAN);
		g.fillRect(x1, y1, width + 1, height + 1); 	
		g.setColor(Color.BLACK);
		
		for (int y = 0; y <= TOTAL_ROWS; y++) {
			g.drawLine(x1 + GRID_X, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)), x1 + GRID_X + ((INNER_CELL_SIZE + 1) * TOTAL_COLUMNS), y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)));
		}
		for (int x = 0; x <= TOTAL_COLUMNS ; x++) {
			g.drawLine(x1 + GRID_X  + (x * (INNER_CELL_SIZE + 1)) , y1 + GRID_Y , x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), y1 + GRID_Y + ((INNER_CELL_SIZE + 1) * (TOTAL_ROWS )));
		}
		//Paint cell colors
		for (int x = 0; x < TOTAL_COLUMNS; x++) {
			for (int y = 0; y < TOTAL_ROWS; y++) {
				if ((x == 0) || (y != TOTAL_ROWS )) {
					Color c = colorArray[x][y];
					g.setColor(c);
					g.fillRect(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 1, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 1, INNER_CELL_SIZE, INNER_CELL_SIZE);
				}}}}
	public int getGridX(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);

		if (x < 0 || x > TOTAL_COLUMNS - 1 || y < 0 || y > TOTAL_ROWS - 1) {   //Outside the rest of the grid
			return -1;
		}
		return x;
	}
	public int getGridY(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		
		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);
		if (x < 0 || x > TOTAL_COLUMNS - 1 || y < 0 || y > TOTAL_ROWS - 1) {   //Outside the rest of the grid
			return -1;
		}
		return y;
	}
	public Color paintCheck(int a, int b)	{
		 
		 if(a==0 && b==0) // esquina izquierda arriba
		 {
			 if(colorArray[a+1][b] != Color.LIGHT_GRAY){
				 colorArray[a+1][b] = Color.LIGHT_GRAY;  // derecha
				 if(checkCount(a+1,b)==0){
					 paintCheck(a+1,b);
				 }}
			 
			 if(colorArray[a+1][b+1] != Color.LIGHT_GRAY){
				  colorArray[a+1][b+1] = Color.LIGHT_GRAY;  // abajo derecha
				 if(checkCount(a+1,b+1)==0){
					 paintCheck(a+1,b+1);
				 }}
			 
			 if(colorArray[a][b+1] != Color.LIGHT_GRAY){
				 colorArray[a][b+1] = Color.LIGHT_GRAY;  // abajo
				 if(checkCount(a,b+1)==0){
					 paintCheck(a,b+1);
				 }}
			 repaint();
		 } else 
			 if(a==TOTAL_COLUMNS-1 && b==0){    //esquina arriba izquierda
				 if(colorArray[a-1][b] != Color.LIGHT_GRAY){
					 colorArray[a-1][b] = Color.LIGHT_GRAY;  // izquerda
					 if(checkCount(a-1,b)==0){
						 paintCheck(a-1,b);
					 }}
				 if(colorArray[a-1][b+1] != Color.LIGHT_GRAY){
					 colorArray[a-1][b+1] = Color.LIGHT_GRAY;  // abajo izquerda
					 if(checkCount(a-1,b+1)==0){
						 paintCheck(a-1,b+1);
					 }}
				 if(colorArray[a][b+1] != Color.LIGHT_GRAY){
					 colorArray[a][b+1] = Color.LIGHT_GRAY;  // abajo
					 if(checkCount(a,b+1)==0){
						 paintCheck(a,b+1);
					 }}
				 repaint();
			 } else
				 if(a==TOTAL_COLUMNS -1 && b==TOTAL_ROWS-1){   //esquina abajo derecha
					 if(colorArray[a][b-1] != Color.LIGHT_GRAY){
						 colorArray[a][b-1] = Color.LIGHT_GRAY;  // Arriba
						 if(checkCount(a,b-1)==0){
							 paintCheck(a,b-1);
						 }}

					 if(colorArray[a-1][b-1] != Color.LIGHT_GRAY){
						 colorArray[a-1][b-1] = Color.LIGHT_GRAY; //Arriba izquerda
						      if(checkCount(a-1,b-1)==0){
							 paintCheck(a-1,b-1);
						     }}
				      if(colorArray[a-1][b] != Color.LIGHT_GRAY){
				 		 colorArray[a-1][b] = Color.LIGHT_GRAY;  // izquerda
				 		 if(checkCount(a-1,b)==0){
				 			 paintCheck(a-1,b);
				 		 }}
					 repaint();
				 } else
					 if(a==0 && b==TOTAL_ROWS-1){     //esquina izquierda abajo
						 if(colorArray[a+1][b] != Color.LIGHT_GRAY){
							 colorArray[a+1][b] = Color.LIGHT_GRAY;  // derecha
							 if(checkCount(a+1,b)==0){
								 paintCheck(a+1,b);
							 }}
						 if(colorArray[a+1][b-1] != Color.LIGHT_GRAY){
							 colorArray[a+1][b-1] = Color.LIGHT_GRAY;  // Arriba Derecha
							 if(checkCount(a+1,b-1)==0){
								 paintCheck(a+1,b-1);
							 }}
						 if(colorArray[a][b-1] != Color.LIGHT_GRAY){
							 colorArray[a][b-1] = Color.LIGHT_GRAY;  // Arriba
							 if(checkCount(a,b-1)==0){
								 paintCheck(a,b-1);
							 }}

							 repaint();
						} else
		 if (a==a && b==0){      //linea arriba
			 if(colorArray[a-1][b] != Color.LIGHT_GRAY){
				 colorArray[a-1][b] = Color.LIGHT_GRAY;  // izquerda
				 if(checkCount(a-1,b)==0){
					 paintCheck(a-1,b);
				 }}
			 if(colorArray[a-1][b+1] != Color.LIGHT_GRAY){
				 colorArray[a-1][b+1] = Color.LIGHT_GRAY;  // abajo izquerda
				 if(checkCount(a-1,b+1)==0){
					 paintCheck(a-1,b+1);
				 }}
			 if(colorArray[a][b+1] != Color.LIGHT_GRAY){
				 colorArray[a][b+1] = Color.LIGHT_GRAY;  // abajo
				 if(checkCount(a,b+1)==0){
					 paintCheck(a,b+1);
				 }}
			 if(colorArray[a+1][b+1] != Color.LIGHT_GRAY){
				  colorArray[a+1][b+1] = Color.LIGHT_GRAY;  // abajo derecha
				 if(checkCount(a+1,b+1)==0){
					 paintCheck(a+1,b+1);
				 }}
			 if(colorArray[a+1][b] != Color.LIGHT_GRAY){
				 colorArray[a+1][b] = Color.LIGHT_GRAY;  // derecha
				 if(checkCount(a+1,b)==0){
					 paintCheck(a+1,b);
				 }}
		 } else
		
		 if(a==TOTAL_COLUMNS-1 && b==b){ // linea derecha
			 if(colorArray[a][b-1] != Color.LIGHT_GRAY){
				 colorArray[a][b-1] = Color.LIGHT_GRAY;  // Arriba
				 if(checkCount(a,b-1)==0){
					 paintCheck(a,b-1);
				 }}
			 if(colorArray[a-1][b-1] != Color.LIGHT_GRAY){
				 colorArray[a-1][b-1] = Color.LIGHT_GRAY; //Arriba izquerda
				      if(checkCount(a-1,b-1)==0){
					 paintCheck(a-1,b-1);
				     }}
		      if(colorArray[a-1][b] != Color.LIGHT_GRAY){
		 		 colorArray[a-1][b] = Color.LIGHT_GRAY;  // izquerda
		 		 if(checkCount(a-1,b)==0){
		 			 paintCheck(a-1,b);
		 		 }}
		      if(colorArray[a-1][b+1] != Color.LIGHT_GRAY){
		 		 colorArray[a-1][b+1] = Color.LIGHT_GRAY;  // abajo izquerda
		 		 if(checkCount(a-1,b+1)==0){
		 			 paintCheck(a-1,b+1);
		 		 }}
		      if(colorArray[a][b+1] != Color.LIGHT_GRAY){
		 		 colorArray[a][b+1] = Color.LIGHT_GRAY;  // abajo
		 		 if(checkCount(a,b+1)==0){
		 			 paintCheck(a,b+1);
		 		 }}
			 repaint();
		 } else
		 
		if(a==a && b==TOTAL_ROWS-1){    //linea abajo
			 if(colorArray[a+1][b] != Color.LIGHT_GRAY){
				 colorArray[a+1][b] = Color.LIGHT_GRAY;  // derecha
				 if(checkCount(a+1,b)==0){
					 paintCheck(a+1,b);
				 }}
			 if(colorArray[a+1][b-1] != Color.LIGHT_GRAY){
				 colorArray[a+1][b-1] = Color.LIGHT_GRAY;  // Arriba Derecha
				 if(checkCount(a+1,b-1)==0){
					 paintCheck(a+1,b-1);
				 }}
			 if(colorArray[a][b-1] != Color.LIGHT_GRAY){
				 colorArray[a][b-1] = Color.LIGHT_GRAY;  // Arriba
				 if(checkCount(a,b-1)==0){
					 paintCheck(a,b-1);
				 }}

			 if(colorArray[a-1][b-1] != Color.LIGHT_GRAY){
				 colorArray[a-1][b-1] = Color.LIGHT_GRAY; //Arriba izquerda
				      if(checkCount(a-1,b-1)==0){
					 paintCheck(a-1,b-1);
				     }}
		      if(colorArray[a-1][b] != Color.LIGHT_GRAY){
		 		 colorArray[a-1][b] = Color.LIGHT_GRAY;  // izquerda
		 		 if(checkCount(a-1,b)==0){
		 			 paintCheck(a-1,b);
		 		 }}
				 repaint();
				 }else
		
		if(a==0 && b==b){    // linea izquierda
			 if(colorArray[a][b+1] != Color.LIGHT_GRAY){
				 colorArray[a][b+1] = Color.LIGHT_GRAY;  // abajo
				 if(checkCount(a,b+1)==0){
					 paintCheck(a,b+1);
				 }}
			 if(colorArray[a+1][b+1] != Color.LIGHT_GRAY){
				  colorArray[a+1][b+1] = Color.LIGHT_GRAY;  // abajo derecha
				 if(checkCount(a+1,b+1)==0){
					 paintCheck(a+1,b+1);
				 }}
			 if(colorArray[a+1][b] != Color.LIGHT_GRAY){
				 colorArray[a+1][b] = Color.LIGHT_GRAY;  // derecha
				 if(checkCount(a+1,b)==0){
					 paintCheck(a+1,b);
				 }}
			 if(colorArray[a+1][b-1] != Color.LIGHT_GRAY){
				 colorArray[a+1][b-1] = Color.LIGHT_GRAY;  // Arriba Derecha
				 if(checkCount(a+1,b-1)==0){
					 paintCheck(a+1,b-1);
				 }}
			 if(colorArray[a][b-1] != Color.LIGHT_GRAY){
				 colorArray[a][b-1] = Color.LIGHT_GRAY;  // Arriba
				 if(checkCount(a,b-1)==0){
					 paintCheck(a,b-1);
				 }}

			 repaint();
		} else
		 if(a==a && b==b){  //todos los demas
			 if(colorArray[a-1][b] != Color.LIGHT_GRAY){
		 colorArray[a-1][b] = Color.LIGHT_GRAY;  // izquerda
		 if(checkCount(a-1,b)==0){
			 paintCheck(a-1,b);
		 }}
			 if(colorArray[a-1][b+1] != Color.LIGHT_GRAY){
		 colorArray[a-1][b+1] = Color.LIGHT_GRAY;  // abajo izquerda
		 if(checkCount(a-1,b+1)==0){
			 paintCheck(a-1,b+1);
		 }}
			 if(colorArray[a][b+1] != Color.LIGHT_GRAY){
		 colorArray[a][b+1] = Color.LIGHT_GRAY;  // abajo
		 if(checkCount(a,b+1)==0){
			 paintCheck(a,b+1);
		 }}
			 if(colorArray[a+1][b+1] != Color.LIGHT_GRAY){
		  colorArray[a+1][b+1] = Color.LIGHT_GRAY;  // abajo derecha
		 if(checkCount(a+1,b+1)==0){
			 paintCheck(a+1,b+1);
		 }}
			 if(colorArray[a+1][b] != Color.LIGHT_GRAY){
		 colorArray[a+1][b] = Color.LIGHT_GRAY;  // derecha
		 if(checkCount(a+1,b)==0){
			 paintCheck(a+1,b);
		 }}
			 if(colorArray[a+1][b-1] != Color.LIGHT_GRAY){
		 colorArray[a+1][b-1] = Color.LIGHT_GRAY;  // Arriba Derecha
		 if(checkCount(a+1,b-1)==0){
			 paintCheck(a+1,b-1);
		 }}
			 if(colorArray[a][b-1] != Color.LIGHT_GRAY){
		 colorArray[a][b-1] = Color.LIGHT_GRAY;  // Arriba
		 if(checkCount(a,b-1)==0){
			 paintCheck(a,b-1);
		 }}
		 if(colorArray[a-1][b-1] != Color.LIGHT_GRAY){
	 colorArray[a-1][b-1] = Color.LIGHT_GRAY; //Arriba izquerda
	      if(checkCount(a-1,b-1)==0){
		 paintCheck(a-1,b-1);
	     }}
	repaint();}
		 return Color.LIGHT_GRAY;}
	 public int checkCount(int i, int j){  //Check to see how many mines around
		 int count = 0;
        if(i == 0 && j == 0) { //Esquina Arriba Izquierda
       	 if(booleanArray[i ][j +1]){ //Check Abajo
				 count++;
			 } if(booleanArray[i + 1][j]){ //Check Derecha
				 count++; 
			 } if(booleanArray[i +1][j +1]){ //Check Derecha Abajo
				 count++;
			 } 
		 } else
			 if(i == 0 && j == TOTAL_ROWS-1 ) { //Esquina Abajo Izquierda
		       	 if(booleanArray[i ][j -1]){ //Check arriba
						 count++;
					 } if(booleanArray[i + 1][j]){ //Check Derecha
						 count++; 
					 } if(booleanArray[i +1][j -1]){ //Check Derecha arriba
						 count++;
					 } 
				 } else
         if(i == TOTAL_COLUMNS-1 && j == 0) { //Esquina Arriba Derecha
       	  if(booleanArray[i - 1][j]){ //Check Izquierda
					 count++; 
				 }  if(booleanArray[i ][j +1]){ //Check Abajo
					 count++;
				 }if(booleanArray[i -1][j+1]){ //Check Izquierda Abajo
					 count++;
				 }
			 } else
				 if(i == TOTAL_COLUMNS-1 && j == TOTAL_ROWS-1) { //Esquina abajo Derecha
			       	  if(booleanArray[i - 1][j]){ //Check Izquierda
								 count++; 
							 }  if(booleanArray[i ][j -1]){ //Check Arriba
								 count++;
							 }if(booleanArray[i -1][j-1]){ //Check Izquierda Arriba
								 count++;
							 }
						 } else
			 if(i == TOTAL_COLUMNS-1 && j == j) { //Columna de la Derecha
				 if(booleanArray[i ][j +1]){ //Check Abajo
					 count++;
				 }  if(booleanArray[i -1][j -1]){ //Check Izquierda Arriba
					 count++;
				 }if(booleanArray[i - 1][j]){ //Check Izquierda
					 count++; 
				 }if(booleanArray[i][j - 1]){ //Check Arriba
					 count++;
				 } if(booleanArray[i -1][j+1]){ //Check Izquierda Abajo
					 count++;
				 }
			 } else
		if(i == 0 && j == j) { //Columna de la Izquierda
			 if(booleanArray[i][j - 1]){ //Check Arriba
				 count++;
			 } if(booleanArray[i +1][j-1]){ //Check Derecha Arriba
				 count++;
			 } if(booleanArray[i + 1][j]){ //Check Derecha
				 count++; 
			 } if(booleanArray[i +1][j +1]){ //Check Derecha Abajo
				 count++;
			 } if(booleanArray[i ][j +1]){ //Check Abajo
				 count++;
			 }
		 } else
		 if(i == i && j == 0) { //Fila de Arriba
			 if(booleanArray[i - 1][j]){ //Check Izquierda
				 count++; 
			 } if(booleanArray[i -1][j+1]){ //Check Izquierda Abajo
				 count++;
			 } if(booleanArray[i ][j +1]){ //Check Abajo
				 count++;
			 }if(booleanArray[i +1][j +1]){ //Check Derecha Abajo
				 count++;
			 }
			 if(booleanArray[i + 1][j]){ //Check Derecha
				 count++; 
			 }
		 } else
			 if(i == i && j == TOTAL_ROWS-1) { //Fila de abajo
				 if(booleanArray[i - 1][j]){ //Check Izquierda
					 count++; 
				 } if(booleanArray[i -1][j-1]){ //Check Izquierda Arriba
					 count++;
				 } if(booleanArray[i ][j -1]){ //Check arriba
					 count++;
				 }if(booleanArray[i +1][j -1]){ //Check Derecha arriba
					 count++;
				 }
				 if(booleanArray[i + 1][j]){ //Check Derecha
					 count++; 
				 }
			 } else
		 if(i == i && j == j){
			  //Check Alrededor por minas
		
				 if(booleanArray[i + 1][j]){ //Check Derecha
					 count++; 
				 } else {}
				 if(booleanArray[i - 1][j]){ //Check Izquierda
					 count++; 
				 } else{}
				 if(booleanArray[i ][j +1]){ //Check Abajo
					 count++;
				 } else{}
				 if(booleanArray[i][j - 1]){ //Check Arriba
					 count++;
				 }else{}
				 if(booleanArray[i -1][j -1]){ //Check Izquierda Arriba
					 count++;
				 } else{}
				 if(booleanArray[i +1][j +1]){ //Check Derecha Abajo
					 count++;
				 } else{}
				 if(booleanArray[i -1][j+1]){ //Check Izquierda Abajo
					 count++;
				 } else{}
				 if(booleanArray[i +1][j-1]){ //Check Derecha Arriba
					 count++;
				 } else{}
			 }return count;}

}
