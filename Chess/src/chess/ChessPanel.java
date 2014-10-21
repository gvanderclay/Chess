package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.*;

import javax.swing.*;

/*********************************************************
 * The GUI that creates the display for the Chess Game.
 * @author Mitchell Couturier & Gage VanderClay
 * @version 3/10/14
 *********************************************************/

public class ChessPanel extends JPanel {

	/** the game board of JButtons **/
	private JButton[][] board;
	
	/** the model that adds functionality to the game **/
	private ChessModel model;
	
	/** becomes true when a piece is selected to be moved **/
	private boolean isSelected;
	
	/** the Row of the selected piece on the board **/
	private int selectedRow;
	
	/** the Column of the selected piece on the board **/
	private int selectedCol;
	
	/** the ActionListener for the JButtons **/
	private ButtonListener buttonListener;
	
	/** the main panel that the game board is in **/
	private JPanel gamePanel;
	
	/** the panel that displays whose turn it is **/
	private JPanel turnPanel;
	
	/** holds the number of wins **/
	private JPanel winPanel;
	
	/** the panel that displays the title of the game **/
	private JPanel titlePanel;
	
	/** bottom panel **/
	private JPanel bottomPanel;
	
	/** the master panel that holds all of the other panels **/
	private JPanel masterPanel;
	
	/** the label that contains the title **/
	private JLabel title;
	
	/** the label that contains whose turn it is **/
	private JLabel turn;
	
	/** displays the number of wins of each player **/
	private JLabel wWins, bWins;
	
	/** the Icons for the black and white pawn pieces **/
	private ImageIcon PawnIcon1, PawnIcon2;
	
	/** the Icons for the black and white rook pieces **/
	private ImageIcon RookIcon1, RookIcon2;
	
	/** the Icons for the black and white bishop pieces **/
	private ImageIcon BishopIcon1, BishopIcon2;
	
	/** the Icons for the black and white knight pieces **/
	private ImageIcon KnightIcon1, KnightIcon2;
	
	/** the Icons for the black and white queen pieces **/
	private ImageIcon QueenIcon1, QueenIcon2;
	
	/** the Icons for the black and white king pieces **/
	private ImageIcon KingIcon1, KingIcon2;
	
	/** the numbers of rows on the board **/
	private final int ROWS = 8;
	
	/** the number of columns on the board **/
	private final int COLUMNS = 8;

	/********************************************************************************
	 * The constructor that creates the Chess game
	 ********************************************************************************/
	public ChessPanel() {
		// instantiates all instance variables
		board = new JButton[ROWS][COLUMNS];
		model = new ChessModel();
		gamePanel = new JPanel();
		turnPanel = new JPanel();
		titlePanel = new JPanel();
		winPanel = new JPanel();
		bottomPanel = new JPanel();
		masterPanel = new JPanel();
		title = new JLabel("Mitch & Gage's Chess");
		title.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
		title.setForeground(Color.WHITE);
		turn = new JLabel("WHITE'S TURN");
		wWins = new JLabel("White Wins:" + model.whiteWins());
		bWins = new JLabel("Black Wins:" + model.blackWins());
		PawnIcon1 = new ImageIcon("WhitePawn.gif");
		PawnIcon2 = new ImageIcon("BlackPawn.gif");
		RookIcon1 = new ImageIcon("WhiteRook.gif");
		RookIcon2 = new ImageIcon("BlackRook.gif");
		BishopIcon1 = new ImageIcon("WhiteBishop.gif");
		BishopIcon2 = new ImageIcon("BlackBishop.gif");
		KnightIcon1 = new ImageIcon("WhiteKnight.gif");
		KnightIcon2 = new ImageIcon("BlackKnight.gif");
		QueenIcon1 = new ImageIcon("WhiteQueen.gif");
		QueenIcon2 = new ImageIcon("BlackQueen.gif");
		KingIcon1 = new ImageIcon("WhiteKing.gif");
		KingIcon2 = new ImageIcon("BlackKing.gif");
		buttonListener = new ButtonListener();
		isSelected = false;

		// creates the game board
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLUMNS; col++) {
				board[row][col] = new JButton();
				board[row][col].setPreferredSize(new Dimension(75, 75));
				board[row][col].addActionListener(buttonListener);
				gamePanel.add(board[row][col]);
			}
		}
		displayBoard();

		// creates the other JPanels
		titlePanel.add(title);
		turnPanel.add(turn);
		winPanel.add(wWins);
		winPanel.add(bWins);
		bottomPanel.add(turnPanel);
		bottomPanel.add(winPanel);

		// sets the layout of the GUI
		bottomPanel.setLayout(new GridLayout(2,1));
		gamePanel.setLayout(new GridLayout(ROWS, COLUMNS));
		masterPanel.setLayout(new BorderLayout(10, 10));
		masterPanel.add(BorderLayout.CENTER, gamePanel);
		masterPanel.add(BorderLayout.SOUTH, bottomPanel);
		titlePanel.setBackground(Color.BLACK);
		masterPanel.setBackground(Color.BLACK);
		setBackground(Color.BLACK);
		add(masterPanel);

	}

	/***********************************************************************
	 * Shows where each piece is on the game board using ImageIcons
	 ***********************************************************************/
	private void displayBoard() {
		// adds the chess pieces to the board
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLUMNS; col++) {
				if (model.pieceAt(row, col) != null) {
					if (model.pieceAt(row, col).player() == Player.WHITE) {
						if (model.pieceAt(row, col).type().equals("Pawn")) {
							board[row][col].setIcon(PawnIcon1);
						} else if (model.pieceAt(row, col).type()
								.equals("Rook")) {
							board[row][col].setIcon(RookIcon1);
						} else if (model.pieceAt(row, col).type()
								.equals("Knight")) {
							board[row][col].setIcon(KnightIcon1);
						} else if (model.pieceAt(row, col).type()
								.equals("Bishop")) {
							board[row][col].setIcon(BishopIcon1);
						} else if (model.pieceAt(row, col).type()
								.equals("Queen")) {
							board[row][col].setIcon(QueenIcon1);
						} else if (model.pieceAt(row, col).type()
								.equals("King")) {
							board[row][col].setIcon(KingIcon1);
						}
					}else if (model.pieceAt(row, col).player() == Player.BLACK) {
						if (model.pieceAt(row, col).type().equals("Pawn")) {
							board[row][col].setIcon(PawnIcon2);
						} else if (model.pieceAt(row, col).type()
								.equals("Rook")) {
							board[row][col].setIcon(RookIcon2);
						} else if (model.pieceAt(row, col).type()
								.equals("Knight")) {
							board[row][col].setIcon(KnightIcon2);
						} else if (model.pieceAt(row, col).type()
								.equals("Bishop")) {
							board[row][col].setIcon(BishopIcon2);
						} else if (model.pieceAt(row, col).type()
								.equals("Queen")) {
							board[row][col].setIcon(QueenIcon2);
						} else if (model.pieceAt(row, col).type()
								.equals("King")) {
							board[row][col].setIcon(KingIcon2);
						}
					}
				} else {
					board[row][col].setIcon(null);
				}

				// makes a checkered board
				if ((row % 2) == (col % 2))
					board[row][col].setBackground(Color.WHITE);
				else
					board[row][col].setBackground(Color.DARK_GRAY);
			}
			wWins.setText("White Wins:" + model.whiteWins());
			bWins.setText("Black Wins:" + model.blackWins());
		}

		// changes the JLabel that displays the turn
		if (model.currentPlayer() == Player.BLACK) {
			turn.setText("BLACK'S TURN");
			turn.setForeground(Color.WHITE);
			turnPanel.setBackground(Color.BLACK);
		} else {
			turn.setText("WHITE'S TURN");
			turn.setForeground(Color.BLACK);
			turnPanel.setBackground(Color.WHITE);
		}
	}

	/***************************************************************************
	 * The ActionListener class for the game board of JButtons
	 ***************************************************************************/
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int row = 0; row < ROWS; row++) {
				for (int col = 0; col < COLUMNS; col++) {
					if (e.getSource() == board[row][col]) {
						if (!isSelected) {
							if (model.pieceAt(row, col) == null
									|| model.pieceAt(row, col).player() == model
											.currentPlayer().next()) {
								return;
							} else {
								isSelected = true;
								board[row][col].setBackground(Color.CYAN);
								selectedRow = row;
								selectedCol = col;
							}
						} else {
							Move m1 = new Move(selectedRow, selectedCol, row,
									col);
							if (model.isValidMove(m1)) {
								model.move(m1);
							}
							displayBoard();
							isSelected = false;
						}
					}
				}
			}
		}
	}
}
