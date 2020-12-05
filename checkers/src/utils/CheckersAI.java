package utils;

import java.util.ArrayList;

import javafx.util.Pair;
import scene.GameDifficulty;

public class CheckersAI {
	/**
	 * Move function to call the AI.
	 * @param gameBoard int[][] of the gameboard state
	 * @param difficulty difficulty level from GameDifficulty
	 * @return CheckersAIReturn object with from, to and any jumped enemies.
	 */
	public static CheckersAIReturn move (CheckersLogic logic, GameDifficulty difficulty) {
		// These are some dummy variables... 
		
		int depth = 0;
		switch(difficulty) {
		case EASY:
			depth = 2;
			break;
		case HARD:
			depth = 4;
			break;
		// MEDIUM case
		default:
			depth = 3;
			break;
		}
		
		CheckersLogic newlogic = deepCopyLogic(logic);
		Pair<Double, CheckersLogic> minimaxReturn = minimax(newlogic, depth, true);
		CheckersLogic minimaxLogic = minimaxReturn.getValue();
		
		Pair<Vector2i, Vector2i> fromAndToPair = getFromTo(logic, minimaxLogic);
		Vector2i from = fromAndToPair.getKey();
		Vector2i to = fromAndToPair.getValue();
		
		return new CheckersAIReturn(from, to);
	}
	
	private static Pair<Vector2i, Vector2i> getFromTo(CheckersLogic oldBoard, CheckersLogic newBoard) {
		int[][] oldGameBoard = oldBoard.getGameBoard();
		int[][] newGameBoard = newBoard.getGameBoard();
		
		Vector2i from = new Vector2i(0, 0);
		Vector2i to   = new Vector2i(1,1);
		
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (oldGameBoard[i][j] != 0 && (oldGameBoard[i][j] == 2 || oldGameBoard[i][j] == 22) && newGameBoard[i][j] == 0) from = new Vector2i(i, j);
                if (newGameBoard[i][j] != 0 && oldGameBoard[i][j] == 0) to = new Vector2i(i, j);
            }
        }
		
		return new Pair<Vector2i, Vector2i>(from, to);
	}
	
	/**
	 * Deep copies the board from the parameter 'gameLogic'
	 * @param gameLogic
	 * @return
	 */
	private static CheckersLogic deepCopyLogic(CheckersLogic gameLogic) {
		CheckersLogic newLogic = new CheckersLogic();
		newLogic.setGameBoard(gameLogic.getGameBoard());
		return newLogic;
	}
	
	// return maxEval, best_move
	private static Pair<Double, CheckersLogic> minimax(CheckersLogic gameLogic,
																  int depth,
																  boolean isBlackPlayer) {
		if (depth == 0 || gameLogic.hasWonGame() == 2) {
			Double evaluation = evaluateBoard(gameLogic);
			return new Pair<Double, CheckersLogic>(evaluation, deepCopyLogic(gameLogic));
		}
		
		// Case for 'white' player
		if (!isBlackPlayer) {
			Double maxEvaluation = Double.MIN_VALUE;
			
			// Key represents original position, Value represents new position
			Pair<Vector2i, Vector2i> bestMove = null;
			
			// for move in get_all_moves(position, WHITE, game):
			for (Vector2i whitePiece : gameLogic.getAllWhitePieces()) {
				for (Vector2i move : gameLogic.getAllMoves(whitePiece)) {
					CheckersLogic whiteLogic = deepCopyLogic(gameLogic);
					
					whiteLogic.move(whitePiece, new ArrayList<Vector2i>() {
						private static final long serialVersionUID = 1L;
						{
							add(move);
						}});
					
					Double evaluation = minimax(whiteLogic, depth-1, true).getKey();
					
					maxEvaluation = Math.max(maxEvaluation, evaluation);
					if (maxEvaluation.equals(evaluation)) {
						bestMove = new Pair<>(whitePiece, move);
					}
				}
			}
			
			// Prepare the return value
			CheckersLogic newMove = deepCopyLogic(gameLogic);
			ArrayList<Vector2i> newArrayList = new ArrayList<>();
			
			newArrayList.add(bestMove != null ? bestMove.getValue() : null);
			newMove.move(bestMove != null ? bestMove.getKey() : null, newArrayList);
			
			return new Pair<>(maxEvaluation, newMove);
			
		} 
		// Case for 'black' player
		else {
			Double minEvaluation = Double.MAX_VALUE;
			
			// Key represents original position, Value represents new position
			Pair<Vector2i, Vector2i> bestMove = null;
			
			// for move in get_all_moves(position, BLACK, game):
			for (Vector2i blackPiece : gameLogic.getAllBlackPieces()) {
				for (Vector2i move : gameLogic.getAllMoves(blackPiece)) {
					CheckersLogic blackLogic = deepCopyLogic(gameLogic);
					
					blackLogic.move(blackPiece, new ArrayList<Vector2i>() {
						private static final long serialVersionUID = 1L;
						{
							add(move);
						}});
					
					Double evaluation = minimax(blackLogic, depth-1, false).getKey();
					
					minEvaluation = Math.min(minEvaluation, evaluation);
					if (minEvaluation.equals(evaluation)) {
						bestMove = new Pair<>(blackPiece, move);
					}
				}
			}
			
			// Prepare the return value
			CheckersLogic newMove = deepCopyLogic(gameLogic);
			ArrayList<Vector2i> newArrayList = new ArrayList<>();
			
			newArrayList.add(bestMove != null ? bestMove.getValue() : null);
			newMove.move(bestMove != null ? bestMove.getKey() : null, newArrayList);
			
			return new Pair<>(minEvaluation, newMove);
		}
	}
	
	/**
	 * Evaluates the board based on white piece and black piece count
	 * White player wants to maximize the score, while black player wants to minimize it
	 * 
	 * AI wants to minimize it
	 * 
	 * Give extra weight to kings
	 * @param logic
	 * @return
	 */
	private static double evaluateBoard(CheckersLogic logic) {
		double evaluation = logic.getWhitePiecesCount() - logic.getBlackPiecesCount() +
				(logic.getWhiteKingsCount() * 0.5 - logic.getBlackKingsCount() * 0.5);
		return evaluation;
	}
}
