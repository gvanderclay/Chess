package chess;

/********************************************************************
 * Creates the rules and functionality of Knights
 * @author Mitchell Couturier & Gage VanderClay
 * @version 2/25/14
 ********************************************************************/

public class Knight extends ChessPiece{

	/****************************************************************
	 * The constructor that assigns a player to the piece
	 * 
	 * @param the current player
	 ****************************************************************/
	public Knight (Player player){
		super(player);
	}
	
	@Override
	public String type(){
		return "Knight";
	}
	
	@Override
	public boolean isValidMove (Move move, IChessPiece[][] board){
		boolean isValid = super.isValidMove(move, board);
		
		int columnChange = Math.abs(move.fromColumn - move.toColumn);
		int rowChange = Math.abs(move.fromRow - move.toRow);
		
		//makes sure the rook moves one space in one direction
		//and two spaces in another direction
		if(columnChange != 1 && rowChange != 1){
			isValid = false;
		}
		if(rowChange == 1 && columnChange != 2){
			isValid = false;
		}
		if(columnChange == 1 && rowChange != 2){
			isValid = false;
		}
		
		return isValid;
	}
}
