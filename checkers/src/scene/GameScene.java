package scene;

import subscene.SubScenes;

/**
 * Scene handler for the game play itself. The checker board, in-game menu should
 * all be handled through this scene.
 * 
 * This file is currently a template, but should be built out for the game.
 * 
 * @author Andrew Johnston
 *
 */

public class GameScene extends CheckersScene {
	private SceneManager sceneManager;
	
	/**
	 * Initialize the scene with a reference to the game's SceneManager so we
	 * can perform segue's between different scene's.
	 * @param sceneManager SceneManager to manage scene changes.
	 */
	public GameScene (SceneManager sceneManager) {
		this.sceneManager = sceneManager;
		
		setup();
	}
	
	/**
	 * Set up Game Scene specific variables.
	 */
	private void setup () {
		super.setBackground("file:resources/background.png");
	}

	@Override
	public void segueToSubScene(SubScenes subScene) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
