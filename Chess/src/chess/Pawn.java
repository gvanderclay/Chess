package chess;

/********************************************************************
 * Creates the rules and functionality of Pawns
 * 
 * @author Mitchell Couturier & Gage VanderClay
 * @version 2/25/14
 ********************************************************************/

public class Pawn extends ChessPiece {

	/** if true, it is this pawn's first move **/
	private boolean isFirstMove;

	/****************************************************************
	 * The constructor that assigns a player to the piece
	 * 
	 * @param the
	 *            current player
	 ****************************************************************/
	public Pawn(Player player) {
		super(player);
	}

	@Override
	public String type() {
		return "Pawn";
	}

	@Override
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean isValid = super.isValidMove(move, board);

		if (this.player() == Player.BLACK) {
			// checks to see if it is the pawns first move
			if (move.fromRow == 1) {
				isFirstMove = true;
			} else {
				isFirstMove = false;
			}
			// if it's the pawn's first move, it can move 1 or 2 spaces forward
			if (isFirstMove == true) {
				if (move.toRow < 2 || move.toRow > 3) {
					isValid = false;
				}
			}
			if (isFirstMove == false) {
				// checks if normal move is only one space
				if (move.toRow != move.fromRow + 1) {
					isValid = false;
				}
			}
			// checks if column changes
			if (move.fromColumn != move.toColumn) {
				//can only claim another player's piece if it moves forward one
				if(move.toRow != move.fromRow + 1){
					isValid = false;
				}
				// cannot move diagonally to a empty space
				if (board[move.toRow][move.toColumn] == null) {
					isValid = false;
				} else {
					// if column does change, it can only be to claim
					// another
					// player's piece
					if (move.fromColumn != 7) {
						if (board[move.toRow][move.fromColumn + 1] != null) {
							if (board[move.toRow][move.fromColumn + 1].player() != Player.WHITE) {
								if (move.toColumn > move.fromColumn + 1) {
									isValid = false;
								}
							}
						}
					}
					if (move.fromColumn != 0) {
						if (board[move.toRow][move.fromColumn - 1] != null) {
							if (board[move.toRow][move.fromColumn - 1].player() != Player.WHITE) {
								if (move.toColumn < move.fromColumn - 1) {
									isValid = false;
								}
							}
						}
					}
					if (move.toColumn != move.fromColumn + 1
							&& move.toColumn != move.fromColumn - 1) {
						isValid = false;
					}
					// cannot move directly forward if enemy pawn is in the
					// way
				}
			} else {
				if (board[move.toRow][move.toColumn] != null) {
					if (board[move.toRow][move.toColumn].player() == Player.WHITE) {
						isValid = false;
					}
				}
			}
		} else if (this.player() == Player.WHITE) {
			// checks to see if it is the pawns first move
			if (move.fromRow == 6) {
				isFirstMove = true;
			} else {
				isFirstMove = false;
			}
			// if it's the pawn's first move, it can move 1 or 2 spaces forward
			if (isFirstMove == true) {
				if (move.toRow > 5 || move.toRow < 4) {
					isValid = false;
				}
			}
			if (isFirstMove == false) {
				// checks if normal move is only one space
				if (move.toRow != move.fromRow - 1) {
					isValid = false;
				}
			}
			// checks if column changes
			if (move.fromColumn != move.toColumn) {
				// can only claim another player's piece if it moves forward one
				if (move.toRow != move.fromRow - 1) {
					isValid = false;
				}
				// cannot move diagonally to a empty space
				if (board[move.toRow][move.toColumn] == null) {
					isValid = false;
				} else {
					// if column does change, it can only be to claim
					// another
					// player's piece
					if (move.fromColumn != 7) {
						if (board[move.toRow][move.fromColumn + 1] != null) {
							if (board[move.toRow][move.fromColumn + 1].player() != Player.BLACK) {
								if (move.toColumn > move.fromColumn + 1) {
									isValid = false;
								}
							}
						}
					}
					if (move.fromColumn != 0) {
						if (board[move.toRow][move.fromColumn - 1] != null) {
							if (board[move.toRow][move.fromColumn - 1].player() != Player.BLACK) {
								if (move.toColumn < move.fromColumn - 1) {
									isValid = false;
								}
							}
						}
					}
					if (move.toColumn != move.fromColumn + 1
							&& move.toColumn != move.fromColumn - 1) {
						isValid = false;
					}

				}

			} else {
				if (board[move.toRow][move.toColumn] != null) {
					if (board[move.toRow][move.toColumn].player() == Player.BLACK) {
						isValid = false;
					}
				}
			}
		}
		return isValid;
	}
}
