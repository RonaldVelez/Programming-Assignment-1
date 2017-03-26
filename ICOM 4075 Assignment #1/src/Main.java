
import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("Mine Sweeper");
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setLocation(800, 350);
		myFrame.setSize(340, 375);

		MyPanel myPanel = new MyPanel();
		myFrame.add(myPanel);

		// Creates a new object of the myMouseAdapter class
		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		
		// Listens for events in the myMouseAdapter class
		myFrame.addMouseListener(myMouseAdapter);
		
	        // Makes the java frame visible
		myFrame.setVisible(true);
	}
}
