package subscene;

import application.Configs;
import gui.CheckersButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import scene.CheckersScene;

/**
 * The sub-scene for the New Game screen to select difficulty, Player vs Player, etc.
 * 
 * @author Andrew Johnston
 *
 */

public class NewGameSubScene extends CheckersSubScene {

	/**
	 * Initialize new game sub-scene.
	 * @param scene CheckersScene
	 */
	public NewGameSubScene(CheckersScene scene) {
		super(scene);
		setLayoutX(Configs.WINDOW_WIDTH);
		createBackButton();
	}
	
	/**
	 * Create a back button to the main menu sub-scene.
	 */
	private void createBackButton () {
		CheckersButton button = new CheckersButton("BACK", CheckersButton.ButtonSizes.MEDIUM);
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					segueToSubScene(SubScenes.MAIN_MENU);
					//sceneManager.segueTo(Scenes.GAME);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}
		});
		
		button.setLayoutX(30);
		button.setLayoutY(30);
		add(button);
	}

	@Override
	public void transitionScene(boolean isSubSceneActive) {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.2));
		transition.setNode(this);
		transition.setToX(isSubSceneActive ? -Configs.WINDOW_WIDTH : Configs.WINDOW_WIDTH);
		
		transition.play();
	}

}
