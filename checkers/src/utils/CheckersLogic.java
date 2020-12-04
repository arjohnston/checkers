package utils;

import java.util.ArrayList;

import scene.GameDifficulty;

/**
 * 
 */

public class CheckersLogic {
	private int[][] gameBoard;
	
	public CheckersLogic(){
		initializeGameBoard();
	}
	
	public void reset () {
		initializeGameBoard();
	}
	
	private void initializeGameBoard () {
		gameBoard = new int[8][8]; // Checkers has a 8x8 game board configuration
		
		// 0  : space is empty
		// 1  : space has a player 1 piece
		// 2  : space has a player 2 piece
		// 11 : stacked player 1 piece (queen/king)
		// 22 : stacked player 2 piece (queen/king)
		// -1 : space is invalid (cannot move to this space)
		for (int i = 0; i < 8; i++) {
			if (i == 0 || i % 2 == 0) {
				gameBoard[i][0] = 1;
				gameBoard[i][1] = -1;
				gameBoard[i][2] = 1;
				gameBoard[i][3] = -1;
				gameBoard[i][4] = 0;
				gameBoard[i][5] = -1;
				gameBoard[i][6] = 2;
				gameBoard[i][7] = -1;
			} else {
				gameBoard[i][0] = -1;
				gameBoard[i][1] = 1;
				gameBoard[i][2] = -1;
				gameBoard[i][3] = 0;
				gameBoard[i][4] = -1;
				gameBoard[i][5] = 2;
				gameBoard[i][6] = -1;
				gameBoard[i][7] = 2;
			}	
		}
	}
	
	public int[][] getGameBoard () {
		return this.gameBoard;
	}
	
	protected int getWhitePiecesCount()
	{
		int count = 0;
		for(int[] s:gameBoard)
		{
			for(int isValue:s)
			{
				if(isValue==1)
				{
					count++;
				}
			}
		}
		return count;
	}
	
	protected int getBlackPiecesCount()
	{
		int count = 0;
		for(int[] s:gameBoard)
		{
			for(int isValue:s)
			{
				if(isValue==2)
				{
					count++;
				}
			}
		}
		return count;
	}
	
	protected int getWhiteKingsCount()
	{
		int count = 0;
		for(int[] s:gameBoard)
		{
			for(int isValue:s)
			{
				if(isValue==11)
				{
					count++;
				}
			}
		}
		return count;
	}
	
	protected int getBlackKingsCount()
	{
		int count = 0;
		for(int[] s:gameBoard)
		{
			for(int isValue:s)
			{
				if(isValue==22)
				{
					count++;
				}
			}
		}
		return count;
	}
	
	public ArrayList<Vector2i> getAllMoves(Vector2i coord)
	{		
		ArrayList<Vector2i> moves = new ArrayList<Vector2i>();
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				if(gameBoard[i][j] ==0)
				{
					try {
						if(canMove(coord, new Vector2i(i, j)) != null)
						{
							moves.add(new Vector2i(i, j));
						}
					} catch (Exception e) {
						e.printStackTrace();
						System.exit(-1);
					}
				}
			}
		}
		return moves;
	}
	
	/**
	 * Checks whether or not the space is selectable by the current player (e.g. their gamepiece)
	 * 
	 * @param gameBoard The gameboards current state
	 * @param playerTurn The integer value for who's turn it is (1 or 2)
	 * @param coord The x, y coordinate of the space being selected by the current player.
	 * @return boolean
	 */
	public boolean isSelectable (int playerTurn, Vector2i coord) {
		if(playerTurn ==1)
		{
			return gameBoard[coord.x][coord.y] ==1 || gameBoard[coord.x][coord.y] ==11;
		}
		else if(playerTurn ==2)
		{
			return gameBoard[coord.x][coord.y]==2 || gameBoard[coord.x][coord.y]==22;
		}
		return false;
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
	public Vector2i canMove (Vector2i from, Vector2i to) throws Exception {
		if(from.x<0 ||from.y<0||to.x<0||to.y<0||from.x>7||from.y>7||to.x>7||to.y>7)
		{
			throw new Exception("Selected space is outside gameboard");
		}
		
		if(gameBoard[from.x][from.y] ==1)
		{
			if(to.y > from.y)
			{
				if(gameBoard[to.x][to.y]==0)
				{
					if(to.y-from.y ==1 && Math.abs(to.x-from.x)==1)
					{
						return from;
					}
					else if(to.y-from.y ==2 && Math.abs(to.x-from.x)==2)
					{
						if(to.x>from.x)
						{
							return gameBoard[from.x+1][from.y+1] ==2 || gameBoard[from.x+1][from.y+1] ==22 ? new Vector2i(from.x + 1, from.y + 1) : null;
						}
						else {
							return gameBoard[from.x-1][from.y+1] ==2 || gameBoard[from.x-1][from.y+1] ==22 ? new Vector2i(from.x - 1, from.y + 1) : null;
						}
					}
				}
			}
		}
		else if(gameBoard[from.x][from.y] ==11)
		{
			if(to.y != from.y)
			{
				if(gameBoard[to.x][to.y]==0)
				{
					if(Math.abs(to.y-from.y) ==1 && Math.abs(to.x-from.x)==1)
					{
						return from;
					}
					else if(Math.abs(to.y-from.y) ==2 && Math.abs(to.x-from.x)==2)
					{
						if(to.x>from.x && to.y>from.y)
						{
							return gameBoard[from.x+1][from.y+1] ==2 || gameBoard[from.x+1][from.y+1] ==22 ? new Vector2i(from.x + 1, from.y + 1) : null;
						}
						else if(to.x<from.x && to.y>from.y)
						{
							return gameBoard[from.x-1][from.y+1] ==2 || gameBoard[from.x-1][from.y+1] ==22 ? new Vector2i(from.x - 1, from.y + 1) : null;
						}
						else if(to.x>from.x && to.y<from.y)
						{
							return gameBoard[from.x+1][from.y-1] ==2 || gameBoard[from.x+1][from.y-1] ==22 ? new Vector2i(from.x + 1, from.y - 1) : null;
						}
						else if(to.x<from.x && to.y<from.y)
						{
							return gameBoard[from.x-1][from.y-1] ==2 || gameBoard[from.x-1][from.y-1] ==22 ? new Vector2i(from.x - 1, from.y - 1) : null;
						}
					}
				}
			}
		}
		else if(gameBoard[from.x][from.y] ==2)
		{
			if(to.y < from.y)
			{
				if(gameBoard[to.x][to.y]==0)
				{
					if(from.y-to.y ==1 && Math.abs(to.x-from.x)==1)
					{
						return from;
					}
					else if(from.y-to.y ==2 && Math.abs(to.x-from.x)==2)
					{
						if(to.x>from.x)
						{
							return gameBoard[from.x+1][from.y-1] ==1 || gameBoard[from.x+1][from.y-1] ==11 ? new Vector2i(from.x + 1, from.y - 1) : null;
						}
						else {
							return gameBoard[from.x-1][from.y-1] ==1 || gameBoard[from.x-1][from.y-1] ==11 ? new Vector2i(from.x - 1, from.y - 1) : null;
						}
					}
				}
			}
		}
		else if(gameBoard[from.x][from.y] ==22)
		{
			if(to.y != from.y)
			{
				if(gameBoard[to.x][to.y]==0)
				{
					if(Math.abs(to.y-from.y) ==1 && Math.abs(to.x-from.x)==1)
					{
						return from;
					}
					else if(Math.abs(to.y-from.y) ==2 && Math.abs(to.x-from.x)==2)
					{
						if(to.x>from.x && to.y>from.y)
						{
							return gameBoard[from.x+1][from.y+1] ==1 || gameBoard[from.x+1][from.y+1] ==11 ? new Vector2i(from.x + 1, from.y + 1) : null;
						}
						else if(to.x<from.x && to.y>from.y)
						{
							return gameBoard[from.x-1][from.y+1] ==1 || gameBoard[from.x-1][from.y+1] ==11 ? new Vector2i(from.x - 1, from.y + 1) : null;
						}
						else if(to.x>from.x && to.y<from.y)
						{
							return gameBoard[from.x+1][from.y-1] ==1 || gameBoard[from.x+1][from.y-1] ==11 ? new Vector2i(from.x + 1, from.y - 1) : null;
						}
						else if(to.x<from.x && to.y<from.y)
						{
							return gameBoard[from.x-1][from.y-1] ==1 || gameBoard[from.x-1][from.y-1] ==11 ? new Vector2i(from.x - 1, from.y - 1) : null;
						}
					}
				}
			}
		}		
		return null;
	}
	
	private boolean checkIfShouldBecomeKing (int playerTurn, Vector2i coord) {
		if (playerTurn == 1) {
			return coord.y == 7;
		} else {
			return coord.y == 0;
		}
	}
	
	/**
	 * Handles the logic for moving. The move can be singular (e.g. move diagnal one space) to
	 * a multitude of a chain of jumps. The gameboard should be updated with any eliminated pieces
	 * as well as the ending location of all moved pieces.
	 * 
	 * @param gameBoard The current gameboard
	 * @param currentPiece A Vector2i of the coordinates for the gamepiece that is being moved.
	 * @param moves Vector2i array of moves
	 * @return an updated gameBoard
	 * @throws Exception If the values are out of range or the move is invalid
	 */
	public ArrayList<Vector2i> move (Vector2i currentPiece, ArrayList<Vector2i> moves)
	{
		if(gameBoard[moves.get(0).x][moves.get(0).y] ==0)
		{
			try {
				Vector2i move = canMove(currentPiece, moves.get(0));
				if(move != null)
				{
					int currentPieceValue = gameBoard[currentPiece.x][currentPiece.y];
//					gameBoard[moves.get(0).x][moves.get(0).y] = currentPieceValue;
					
					if (currentPieceValue == 1 || currentPieceValue == 11) {
						gameBoard[moves.get(0).x][moves.get(0).y] = checkIfShouldBecomeKing(1, moves.get(0)) ? 11 : currentPieceValue;
					} else if (currentPieceValue == 2 || currentPieceValue == 22) {
						gameBoard[moves.get(0).x][moves.get(0).y] = checkIfShouldBecomeKing(2, moves.get(0)) ? 22 : currentPieceValue;
					}
					
					gameBoard[currentPiece.x][currentPiece.y]=0;
					
					if (move != currentPiece) removeGamePiece(move);
					
					return new ArrayList<Vector2i>() {
						private static final long serialVersionUID = 1L;
						{
							add(move);
						}};
				} else {
					return new ArrayList<Vector2i>();
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		
		return new ArrayList<Vector2i>();
	}
	
	public CheckersAIReturn moveAI (GameDifficulty gameDifficulty) {
		CheckersAIReturn aiMove = CheckersAI.move(this, gameDifficulty);
		
		gameBoard[aiMove.getTo().x][aiMove.getTo().y] = gameBoard[aiMove.getFrom().x][aiMove.getFrom().y];
		gameBoard[aiMove.getFrom().x][aiMove.getFrom().y] = 0;
		
		return aiMove;
	}
	
	private void removeGamePiece (Vector2i removeMe) {
		gameBoard[removeMe.x][removeMe.y] = 0;
	}
	
	private void checkIfDraw () {
		int amountOfMovesAvailable = 0;
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				// TODO !!!
			}
		}
	}
	
	/**
	 * Checks if the game has won. Should be pretty simply implemented (e.g. check if all 1's and 11's are
	 * eliminated. If so, then the game is over. The return can be boolean or an integer (e.g. -1 for no, 1 for player 1
	 * has won, 2 for player 2 has won) depending on how this function is designed.
	 * @param gameBoard The gameboard with its current state
	 * @return Whether or not the game is over
	 */
	public int hasWonGame () {
		if(getWhitePiecesCount()+ getWhiteKingsCount()<=0)
		{
			return 2;
		}
		else if(getBlackPiecesCount()+ getBlackKingsCount()<=0)
		{
			return 1;
		}
		else {
			return -1;
		}
	}
}
