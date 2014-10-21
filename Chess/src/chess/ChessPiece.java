package chess;

/********************************************************************
 * Creates the basis for all Chess Pieces
 * @author Mitchell Couturier & Gage VanderClay
 * @version 2/25/14
 ********************************************************************/

public abstract class ChessPiece implements IChessPiece {

	/** the owner of the current chess piece **/
	private Player owner;

	/****************************************************************
	 * The constructor that assigns a player to the piece
	 * 
	 * @param the current player
	 ****************************************************************/
	protected ChessPiece(Player player) {
		this.owner = player;
	}

	@Override
	public abstract String type();

	@Override
	public Player player() {
		return owner;
	}

	@Override
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean isValid = true;
		if (move.fromRow == move.toRow && move.fromColumn == move.toColumn) {
			isValid = false;
		}
		if (board[move.fromRow][move.fromColumn].type() != this.type()) {
			if (board[move.fromRow][move.fromColumn].player() != this.player()) {
				isValid = false;
			}
		}
		if (board[move.toRow][move.toColumn] != null) {
			if (board[move.toRow][move.toColumn].player() == this.player()) {
				isValid = false;
			}
		}

		return isValid;
	}
}
