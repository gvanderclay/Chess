package chess;

/********************************************************************
 * Creates the rules and functionality of Kings
 * @author Mitchell Couturier & Gage VanderClay
 * @version 2/25/14
 ********************************************************************/

public class King extends ChessPiece{

	/****************************************************************
	 * The constructor that assigns a player to the piece
	 * 
	 * @param the current player
	 ****************************************************************/
	public King(Player player){
		super(player);
	}
	
	@Override
	public String type(){
		return "King";
	}
	
	@Override
	public boolean isValidMove (Move move, IChessPiece[][] board){
		boolean isValid = super.isValidMove(move, board);
		
		if(Math.abs(move.toColumn - move.fromColumn) > 1){
			isValid = false;
		}
		else if(Math.abs(move.toRow - move.fromRow) > 1){
			isValid = false;
		}
		
		return isValid;
	}
}
