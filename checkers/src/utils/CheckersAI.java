package utils;

import java.util.ArrayList;

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
		return new CheckersAIReturn(new Vector2i(5, 5), new Vector2i(4, 4), new ArrayList<Vector2i>());
	}
}
