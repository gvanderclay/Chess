package chess;

/********************************************************************
 * Creates the rules and functionality of Bishops
 * @author Mitchell Couturier & Gage VanderClay
 * @version 2/25/14
 ********************************************************************/

public class Bishop extends ChessPiece {

	/****************************************************************
	 * The constructor that assigns a player to the piece
	 * 
	 * @param the current player
	 ****************************************************************/
	public Bishop(Player player) {
		super(player);
	}

	@Override
	public String type() {
		return "Bishop";
	}

	@Override
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean isValid = super.isValidMove(move, board);

		// finds the amount changed horizontally and vertically
		int columnChange = Math.abs(move.fromColumn - move.toColumn);
		int rowChange = Math.abs(move.fromRow - move.toRow);

		// checks to see if piece moved the same amount horizontally
		// as it did vertically
		if (columnChange != rowChange) {
			isValid = false;
			return isValid;
		}

		// doesn't allow move if a piece is in the way
		if (move.toColumn > move.fromColumn && move.toRow > move.fromRow) {
			int count = 1;
			while (count < columnChange) {
				if (board[move.fromRow + count][move.fromColumn + count] != null) {
					isValid = false;
				}
				count++;
			}
		} else if (move.toColumn < move.fromColumn && move.toRow > move.fromRow) {
			int count = 1;
			while (count < columnChange) {
				if (board[move.fromRow + count][move.fromColumn - count] != null) {
					isValid = false;
				}
				count++;
			}
		} else if (move.toColumn > move.fromColumn && move.toRow < move.fromRow) {
			int count = 1;
			while (count < columnChange) {
				if (board[move.fromRow - count][move.fromColumn + count] != null) {
					isValid = false;
				}
				count++;
			}
		} else if (move.toColumn < move.fromColumn && move.toRow < move.fromRow) {
			int count = 1;
			while (count < columnChange) {
				if (board[move.fromRow - count][move.fromColumn - count] != null) {
					isValid = false;
				}
				count++;
			}
		}

		return isValid;
	}
}
