package chess;

	/********************************************************************
	 * Creates the rules and functionality of Rooks
	 * @author Mitchell Couturier & Gage VanderClay
	 * @version 2/25/14
	 ********************************************************************/

public class Rook extends ChessPiece {

	/****************************************************************
	 * The constructor that assigns a player to the piece
	 * 
	 * @param the current player
	 ****************************************************************/
	public Rook(Player player) {
		super(player);
	}

	@Override
	public String type() {
		return "Rook";
	}

	@Override
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean isValid = super.isValidMove(move, board);

		if (move.fromColumn != move.toColumn && move.fromRow != move.toRow) {
			isValid = false;
			return isValid;
		}

		// doesn't allow move if a piece is in the way
		if (move.toColumn > move.fromColumn) {
			for (int i = move.fromColumn + 1; i < move.toColumn; i++) {
				if (board[move.fromRow][i] != null) {
					isValid = false;
				}
			}
		} else if (move.toColumn < move.fromColumn) {
			for (int i = move.toColumn + 1; i < move.fromColumn; i++) {
				if (board[move.fromRow][i] != null) {
					isValid = false;
				}
			}
		} else if (move.toRow > move.fromRow) {
			for (int i = move.fromRow + 1; i < move.toRow; i++) {
				if (board[i][move.fromColumn] != null) {
					isValid = false;
				}
			}
		} else if (move.toRow < move.fromRow) {
			for (int i = move.toRow + 1; i < move.fromRow; i++) {
				if (board[i][move.fromColumn] != null) {
					isValid = false;
				}
			}
		}

		return isValid;
	}
}
