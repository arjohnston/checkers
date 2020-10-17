package scene;

import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * The Scene Manager class manages each of the scenes in the game. As
 * we're only utilizing one game window (e.g. Stage), any transitions
 * between major scenes should be handled by this class. SubScene's
 * within a scene (e.g. main-menu selections), should be a SubScene
 * class from the JavaFX library.
 * 
 * @author Andrew Johnston
 *
 */

public class SceneManager {
	private MenuScene menuScene;
	private GameScene gameScene;
	private Stage stage;
	
	/**
	 * Create a stage (window) and initialize the scene manager.
	 */
	public SceneManager () {
		menuScene = new MenuScene(this);
		gameScene = new GameScene(this);
		stage = new Stage();
		stage.setResizable(false); // dont let resizing of the window happen
		stage.setTitle("Checkers"); // application title
		stage.getIcons().add(new Image("file:resources/logo.png")); // application icon
		stage.setScene(menuScene);
	}
	
	/**
	 * Perform a scene transition from the current scene to a target scene.
	 * 
	 * @param scene Target Scene to transition to.
	 * @throws Exception If the scene isn't defined in the Scenes ENUM class, we'll throw an exception.
	 */
	public void segueTo (Scenes scene) throws Exception {
		stage.setScene(gameScene);
		
		switch (scene) {
		case GAME:
			stage.setScene(gameScene);
			break;
			
		case MENU:
			stage.setScene(menuScene);
			break;
			
		default:
			throw new Exception("Invalid segue: " + scene);
		}
	}

	/**
	 * Start the application with show().
	 */
	public void run() {
		stage.show();
	}
}
