package chess;

import static org.junit.Assert.*;

import org.junit.Test;

/************************************************
 * Class that tests if the pieces move correctly
 * @author Gage Vander Clay and Mitch Couterier
 ************************************************/
public class ChessTest {

	/***************************************************************
	 * Checks to see if the pawns moves correctly on the first move
	 ***************************************************************/
	@Test
	public void pawnTestFirstMove() {
		ChessModel model = new ChessModel();

		// tests moving pieces on the first move
		for (int col = 0; col < 8; col++) {
			Move move = new Move(1, col, 2, col);
			assertTrue(model.pieceAt(1, col).isValidMove(move,
					model.currentBoard()));
			move = new Move(6, col, 5, col);
			assertTrue(model.pieceAt(6, col).isValidMove(move,
					model.currentBoard()));
			move = new Move(1, col, 3, col);
			assertTrue(model.pieceAt(1, col).isValidMove(move,
					model.currentBoard()));
			move = new Move(6, col, 4, col);
			assertTrue(model.pieceAt(6, col).isValidMove(move,
					model.currentBoard()));
		}
	}

	/******************************************
	 * Checks if the pawns can jump diagonally
	 ******************************************/
	@Test
	public void pawnTestJumpMove() {
		ChessModel model = new ChessModel();
		
		// moves all of the pawns two forward
		for (int col = 0; col < 8; col++) {
			Move testBlackMove = new Move(1, col, 3, col);
			model.testMove(testBlackMove);
			Move testWhiteMove = new Move(6, col, 4, col);
			model.testMove(testWhiteMove);
		}
		
		// tests to see if each pawn can jump over the other pawns
		for (int col = 0; col < 7; col++) {
			Move testBlackMove = new Move(3, col, 4, col + 1);
			assertTrue(model.pieceAt(3, col).isValidMove(testBlackMove,
					model.currentBoard()));
			Move testWhiteMove = new Move(4, col, 3, col + 1);
			assertTrue(model.pieceAt(4, col).isValidMove(testWhiteMove,
					model.currentBoard()));
		}
	}


	/****************************************************
	 * Tests to see if the pawn can move with a piece in
	 * front of it
	 ****************************************************/
	@Test
	public void pawnTestBlock() {
		ChessModel model = new ChessModel();
		
		// moves all of the pawns two forward
		for (int col = 0; col < 8; col++) {
			Move testBlackMove = new Move(1, col, 3, col);
			model.testMove(testBlackMove);
			Move testWhiteMove = new Move(6, col, 4, col);
			model.testMove(testWhiteMove);
		}
		for (int col = 0; col < 8; col++) {
			Move testBlackMove = new Move(3, col, 4, col);
			assertFalse(model.pieceAt(3, col).isValidMove(testBlackMove,
				
	model.currentBoard()));
			Move testWhiteMove = new Move(4, col, 3, col);
			assertFalse(model.pieceAt(4, col).isValidMove(testWhiteMove,
					model.currentBoard()));
		}
	}

	/***********************************************************
	 * Tests to see if the rook can move with a piece in front 
	 * of it
	 ***********************************************************/
	@Test
	public void rookTestBlock() {
		ChessModel model = new ChessModel();
		Move testWhiteMove = new Move(6, 0, 4, 0);
		// tests if the rook will move on top of one of it's own pieces
		model.testMove(testWhiteMove);
		testWhiteMove = new Move(7, 0, 4, 0);
		assertFalse(model.pieceAt(7, 0).isValidMove(testWhiteMove,
				model.currentBoard()));
	}
	
	/**
	 * Tests to see if the rook can move front and back and 
	 * side to side
	 */
	@Test
	public void rookTestMove() {
		ChessModel model = new ChessModel();
		Move testWhiteMove = new Move(6, 0, 4, 0);
		model.testMove(testWhiteMove);
		testWhiteMove = new Move(7, 0, 5, 0);
		assertTrue(model.pieceAt(7, 0).isValidMove(testWhiteMove,
				model.currentBoard()));
		model.testMove(testWhiteMove);
		testWhiteMove = new Move(5, 0, 5, 7);
		assertTrue(model.pieceAt(5, 0).isValidMove(testWhiteMove,
				model.currentBoard()));
		model.testMove(testWhiteMove);
		testWhiteMove = new Move(5, 7, 3, 7);
		assertTrue(model.pieceAt(5, 7).isValidMove(testWhiteMove,
				model.currentBoard()));
	}

	/***********************************************
	 * Tests to see if the king can make its moves
	 ***********************************************/
	@Test
	public void testKing() {
		ChessModel model = new ChessModel();
		Move testWhiteMove = new Move(6, 4, 4, 4);
		model.testMove(testWhiteMove);
		testWhiteMove = new Move(7, 4, 6, 4);
		assertTrue(model.pieceAt(7, 4).isValidMove(testWhiteMove,
				model.currentBoard()));
		model.testMove(testWhiteMove);
		testWhiteMove = new Move(6, 4, 5, 4);
		model.testMove(testWhiteMove);
		testWhiteMove = new Move(5, 4, 5, 5);
		assertTrue(model.pieceAt(5, 4).isValidMove(testWhiteMove,
				model.currentBoard()));

	}

	/*****************************************
	 * Tests if the queen can move diagonally
	 *****************************************/
	@Test
	public void testQueenDiagonal() {
		ChessModel model = new ChessModel();
		Move testWhiteMove = new Move(6, 4, 4, 4);
		model.testMove(testWhiteMove);
		testWhiteMove = new Move(7, 3, 4, 6);
		assertTrue(model.pieceAt(7, 3).isValidMove(testWhiteMove,
				model.currentBoard()));
	}


	/******************************************************
	 * Test to see if the queen can move in straight lines
	 ******************************************************/
	@Test
	public void testQueenStraight() {
		ChessModel model = new ChessModel();
		Move testWhiteMove = new Move(6, 3, 4, 3);
		model.testMove(testWhiteMove);
		testWhiteMove = new Move(7, 3, 5, 3);
		assertTrue(model.pieceAt(7, 3).isValidMove(testWhiteMove,
				model.currentBoard()));
		model.testMove(testWhiteMove);
		testWhiteMove = new Move(5, 3, 5, 6);
		assertTrue(model.pieceAt(5, 3).isValidMove(testWhiteMove,
				model.currentBoard()));
	}

	/*************************************************************
	 * Tests to see if the knight can move in the proper l shape
	 *************************************************************/
	@Test
	public void testKnight() {
		ChessModel model = new ChessModel();
		Move testWhiteMove = new Move(7, 6, 3, 5);
		model.testMove(testWhiteMove);
		testWhiteMove = new Move(3, 5, 1, 4);
		assertTrue(model.pieceAt(3, 5).isValidMove(testWhiteMove,
				model.currentBoard()));
		testWhiteMove = new Move(3, 5, 1, 6);
		assertTrue(model.pieceAt(3, 5).isValidMove(testWhiteMove,
				model.currentBoard()));
		testWhiteMove = new Move(3, 5, 2, 3);
		assertTrue(model.pieceAt(3, 5).isValidMove(testWhiteMove,
				model.currentBoard()));
		testWhiteMove = new Move(3, 5, 4, 7);
		assertTrue(model.pieceAt(3, 5).isValidMove(testWhiteMove,
				model.currentBoard()));
		testWhiteMove = new Move(3, 5, 5, 6);
		assertTrue(model.pieceAt(3, 5).isValidMove(testWhiteMove,
				model.currentBoard()));
		testWhiteMove = new Move(3, 5, 5, 4);
		assertTrue(model.pieceAt(3, 5).isValidMove(testWhiteMove,
				model.currentBoard()));
		testWhiteMove = new Move(3, 5, 4, 3);
		assertTrue(model.pieceAt(3, 5).isValidMove(testWhiteMove,
				model.currentBoard()));
		testWhiteMove = new Move(3, 5, 2, 7);
		assertTrue(model.pieceAt(3, 5).isValidMove(testWhiteMove,
				model.currentBoard()));
	}

	/************************************************
	 * Tests to see if the bishop can move correctly
	 ************************************************/
	@Test
	public void testBishop() {
		ChessModel model = new ChessModel();
		Move testWhiteMove = new Move(6, 3, 4, 3);
		model.testMove(testWhiteMove);
		testWhiteMove = new Move(7, 2, 4, 5);
		assertTrue(model.pieceAt(7, 2).isValidMove(testWhiteMove,
				model.currentBoard()));
	
	model.testMove(testWhiteMove);
		testWhiteMove = new Move(4, 5, 2, 3);
		assertTrue(model.pieceAt(4, 5).isValidMove(testWhiteMove,
				model.currentBoard()));
		model.testMove(testWhiteMove);
		testWhiteMove = new Move(2, 3, 4, 1);
		assertTrue(model.pieceAt(2, 3).isValidMove(testWhiteMove,
				model.currentBoard()));
		model.testMove(testWhiteMove);
		testWhiteMove = new Move(4, 1, 6, 3);
		assertTrue(model.pieceAt(4, 1).isValidMove(testWhiteMove,
				model.currentBoard()));
	}

}