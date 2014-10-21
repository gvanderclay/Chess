package chess;

/********************************************************************
 * Creates the rules and functionality of Queens
 * @author Mitchell Couturier & Gage VanderClay
 * @version 2/25/14
 ********************************************************************/

public class Queen extends ChessPiece{

	/****************************************************************
	 * The constructor that assigns a player to the piece
	 * 
	 * @param the current player
	 ****************************************************************/
	public Queen (Player player){
		super(player);
	}
	
	@Override
	public String type(){
		return "Queen";
	}
	
	@Override
	public boolean isValidMove (Move move, IChessPiece[][] board){
		boolean isValid = super.isValidMove(move, board);
		
		//finds the amount changed horizontally and vertically
		int columnChange = Math.abs(move.fromColumn - move.toColumn);
		int rowChange = Math.abs(move.fromRow - move.toRow);
				
		//combines if statements from rook and bishop classes 
		if(columnChange != rowChange
			&& (move.fromColumn != move.toColumn 
			&& move.fromRow != move.toRow)){
				isValid = false;
				return isValid;
		}
		
		//doesn't allow move if a piece is in the way
		//if the piece moves horizontally or vertically
		if(move.fromColumn==move.toColumn || move.fromRow==move.toRow){
			if(move.toColumn > move.fromColumn){
				for(int i = move.fromColumn + 1; i < move.toColumn; i++){
					if(board[move.fromRow][i] != null){
						isValid = false;
					}
				}
			}
			else if(move.toColumn < move.fromColumn){
				for(int i = move.toColumn + 1; i < move.fromColumn; i++){
					if(board[move.fromRow][i] != null){
						isValid = false;
					}
				}
			}
			else if(move.toRow > move.fromRow){
				for(int i = move.fromRow + 1; i < move.toRow; i++){
					if(board[i][move.fromColumn] != null){
						isValid = false;
					}
				}
			}
			else if(move.toRow < move.fromRow){
				for(int i = move.toRow + 1; i < move.fromRow; i++){
					if(board[i][move.fromColumn] != null){
						isValid = false;
					}
				}
			}
		//if the piece moves diagonally
		}else if(columnChange == rowChange){
			if(move.toColumn>move.fromColumn && move.toRow>move.fromRow){
				int count = 1;
				while(count < columnChange){
					if(board[move.fromRow+count][move.fromColumn+count] != null){
						isValid = false;
					}
					count++;
				}
			}
			else if(move.toColumn<move.fromColumn && move.toRow>move.fromRow){
				int count = 1;
				while(count < columnChange){
					if(board[move.fromRow+count][move.fromColumn-count] != null){
						isValid = false;
					}
					count++;
				}
			}
			else if(move.toColumn>move.fromColumn && move.toRow<move.fromRow){
				int count = 1;
				while(count < columnChange){
					if(board[move.fromRow-count][move.fromColumn+count] != null){
						isValid = false;
					}
					count++;
				}
			}
			else if(move.toColumn<move.fromColumn && move.toRow<move.fromRow){
				int count = 1;
				while(count < columnChange){
					if(board[move.fromRow-count][move.fromColumn-count] != null){
						isValid = false;
					}
					count++;
				}
			}
			
		}		
			
		return isValid;
	}
}
