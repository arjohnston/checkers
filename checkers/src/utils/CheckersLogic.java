package utils;

public class CheckersLogic {
	/**
	 * Checks whether or not the space is selectable by the current player (e.g. their gamepiece)
	 * 
	 * @param gameBoard The gameboards current state
	 * @param playerTurn The integer value for who's turn it is (1 or 2)
	 * @param coord The x, y coordinate of the space being selected by the current player.
	 * @return boolean
	 */
	public static boolean isSelectable (int[][] gameBoard, int playerTurn, Vector2i coord) {
		return true;
	}

	/**
	 * Checks whether a move is valid from a spot to another. This method should check against
	 * common checkers rules for moving in the correct direction and jumping over enemy pieces.
	 * The focus of this method should be singular, meaning the move should be one move or one
	 * jump. Checkers allow for a multitude of jumps / moves, but that should be handled 
	 * elsewhere.
	 * 
	 * @param gameBoard The gameboard with the game's current state
	 * @param from x,y coordinate of a players piece
	 * @param to x,y coordinate of a players piece
	 * @return boolean if its a valid move
	 * @throws Exception if the from or to vectors land outside the gameBoard size
	 */
	public static boolean canMove (int[][] gameBoard, Vector2i from, Vector2i to) throws Exception {
		return true;
	}
	
	/**
	 * Handles the logic for moving. The move can be singular (e.g. move diagnol one space) to
	 * a multitude of a chain of jumps. 
	 * 
	 * @param gameBoard The current gameboard
	 * @param gamePiece A Vector2i of the coordinates for the gamepiece that is being moved.
	 * @param moves Vector2i array of moves
	 * @return an updated gameBoard
	 * @throws Exception If the values are out of range or the move is invalid
	 */
	public static int[][] move (int[][] gameBoard, Vector2i gamePiece, Vector2i[] moves) throws Exception {
		return gameBoard;
	}
}
