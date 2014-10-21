package chess;

import javax.swing.JFrame;

/********************************************************************
 * Creates the GUI and displays the Chess Game.
 * @author Mitchell Couturier & Gage VanderClay
 * @version 3/10/14
 ********************************************************************/

public class ChessGUI {
	
	/*****************************************************************
	 * Creates a ChessPanel object and displays the game.
	 *****************************************************************/
	public static void main(String[] args) {
		JFrame frame = new JFrame("Chess Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ChessPanel panel = new ChessPanel();
		frame.getContentPane().add(panel);

		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
