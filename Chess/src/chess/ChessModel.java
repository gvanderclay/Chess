package chess;

import javax.swing.JOptionPane;

/*********************************************************
 * Creates the functionality and rules of the Chess Game
 * @author Mitchell Couturier & Gage VanderClay
 * @version 2/27/14
 *********************************************************/

public class ChessModel implements IChessModel {

	/** the game board that holds all of the chess pieces **/
	private IChessPiece[][] board;
	
	/** the current player **/
	private Player player;
	
	/** the number of rows on the board **/
	private final int ROWS = 8;
	
	/** the number of columns on the board **/
	private final int COLUMNS = 8;
	
	/** used for testing purposes to change the board back to the original **/
	private IChessPiece undoPiece;
	
	/** counts the number of wins each player has **/
	private int wWins, bWins;

	/*********************************************************
	 * Constructor for the Chess Model Class
	 *********************************************************/
	public ChessModel() {
		board = new IChessPiece[ROWS][COLUMNS];
		player = Player.WHITE;
		fillBoard();
		wWins = 0;
		bWins = 0;
	}
	
	/******************************************************************
	 * returns the number of white player wins.
	 ******************************************************************/
	public int whiteWins(){
		return wWins;
	}
	
	/******************************************************************
	 * returns the number of black player wins.
	 ******************************************************************/
	public int blackWins(){
		return bWins;
	}
	@Override
	public boolean isComplete() {
		boolean complete = true;

		for (int fromRow = 0; fromRow < ROWS; fromRow++) {
			for (int fromCol = 0; fromCol < COLUMNS; fromCol++) {
				for (int toRow = 0; toRow < ROWS; toRow++) {
					for (int toCol = 0; toCol < COLUMNS; toCol++) {
						if (pieceAt(fromRow, fromCol) != null) {
							if (pieceAt(fromRow, fromCol).player() == player) {
								Move move = new Move(fromRow, fromCol, toRow,
										toCol);
								if (pieceAt(fromRow, fromCol).isValidMove(move,
										board)) {
									// tests a move to see if it puts it
									// into check
									testMove(move);
									// if this move can save the king, it
									// returns false and changes the board
									// back to the original
									if (!inCheck(player)) {
										untestMove(move);
										return false;
									} else {
										// changes the board back to the
										// original
										untestMove(move);
									}
								}
							}
						}
					}
				}
			}
		}

		return complete;
	}

		@Override
	public boolean isValidMove(Move move) {
		boolean isValid = board[move.fromRow][move.fromColumn].isValidMove(
				move, board);

		return isValid;

	}

		@Override
	public void move(Move move) {
		if (player == board[move.fromRow][move.fromColumn].player()) {
			if (isValidMove(move)) {
				// test to see if the move puts the player into Check
				testMove(move);
				if (inCheck(currentPlayer())) {
					JOptionPane
							.showMessageDialog(null,
									"This move would put you into Check. \nTry another move.");
					untestMove(move);
					return;
				} else {
					// changes player
					player = player.next();

					if (inCheck(player) && isComplete()) {
						JOptionPane
								.showMessageDialog(null,
										"The game is in CheckMate. \nThe game is over.");
						if(player == Player.BLACK)
							wWins++;
						else
							bWins++;
						
						player = player.WHITE;
						fillBoard();
					} else if (inCheck(player)) {
						JOptionPane.showMessageDialog(null,
								"The game is in Check");
					}
				}
			}
		}
	}

	/*****************************************************************************************
	 * Used for testing if the game is inCheck or isComplete. Because this alters the actual
	 * board, untestMove must be used to change it back if the move isn't wanted.
	 * 
	 * @param move a {@link Move} object describing the move to be made.
	 *****************************************************************************************/
	public void testMove(Move move) {
		undoPiece = board[move.toRow][move.toColumn];
		board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
		board[move.fromRow][move.fromColumn] = null;
	}

	/*******************************************************************************************
	 * Used after the testMove() method to move pieces back to their original positioning
	 * 
	 * @param move a {@link Move} object describing the move to be made.
	 *******************************************************************************************/
	public void untestMove(Move move) {
		board[move.fromRow][move.fromColumn] = board[move.toRow][move.toColumn];
		board[move.toRow][move.toColumn] = undoPiece;
	}
	
	@Override
	public boolean inCheck(Player player) {
		// goes through every piece on the board
		for (int fromRow = 0; fromRow < ROWS; fromRow++) {
			for (int fromCol = 0; fromCol < COLUMNS; fromCol++) {
				for (int toRow = 0; toRow < ROWS; toRow++) {
					for (int toCol = 0; toCol < COLUMNS; toCol++) {
						if (pieceAt(fromRow, fromCol) != null
								&& pieceAt(toRow, toCol) != null) {
							// only checks moves for the opponents pieces
							if (pieceAt(fromRow, fromCol).player() == player
									.next()) {
								// tests every possible move
								Move move = new Move(fromRow, fromCol, toRow,
										toCol);
								if (pieceAt(fromRow, fromCol).isValidMove(move,
										board)) {
									// checks if the opponent's king is in check
									if (pieceAt(toRow, toCol).type().equals(
											"King")
											&& pieceAt(toRow, toCol).player() == player) {
										return true;
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

		@Override
	public Player currentPlayer() {
		return player;
	}

	/*****************************************************************************
	 * Return the number of rows on the board
	 * 
	 * @return number of rows
	 *****************************************************************************/
	public int numRows() {
		return ROWS;
	}
	
	/*****************************************************************************
	 * Return the number of columns on the board
	 * 
	 * @return number of columns
	 *****************************************************************************/
	public int numColumns() {
		return COLUMNS;
	}

	@Override
	public IChessPiece pieceAt(int row, int column) {
		return board[row][column];
	}

	/******************************************************************************************
	 * Fills the board with all of the pieces in the appropriate places at the start
	 * of the game
	 ******************************************************************************************/
	private void fillBoard() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLUMNS; col++) {
				board[row][col] = null;
			}
		}

		// places all of the pawns on the board
		for (int i = 0; i < 8; i++) {
			board[1][i] = new Pawn(Player.BLACK);
			board[6][i] = new Pawn(Player.WHITE);
		}

		// places all of the Rooks on the board
		board[0][0] = new Rook(Player.BLACK);
		board[0][7] = new Rook(Player.BLACK);
		board[7][0] = new Rook(Player.WHITE);
		board[7][7] = new Rook(Player.WHITE);

		// places all of the Knights on the board
		board[0][1] = new Knight(Player.BLACK);
		board[0][6] = new Knight(Player.BLACK);
		board[7][1] = new Knight(Player.WHITE);
		board[7][6] = new Knight(Player.WHITE);

		// places all of the Bishops on the board
		board[0][2] = new Bishop(Player.BLACK);
		board[0][5] = new Bishop(Player.BLACK);
		board[7][2] = new Bishop(Player.WHITE);
		board[7][5] = new Bishop(Player.WHITE);

		// places all of the Kings and Queens on the board
		board[0][3] = new Queen(Player.BLACK);
		board[0][4] = new King(Player.BLACK);
		board[7][3] = new Queen(Player.WHITE);
		board[7][4] = new King(Player.WHITE);

	}

	/*****************************************************************
	 * method used for JUnit Testing purposes
	 * 
	 * @param the current board
	 *****************************************************************/
	public IChessPiece[][] currentBoard(){
		return board;
	}
}
