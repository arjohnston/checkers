package subscene;


import application.Configs;
import gui.CheckersButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import scene.CheckersScene;

/**
 * Subscene for the Game Board.
 * 
 * @author Andrew Johnston
 *
 */
public class GameBoardSubScene extends CheckersSubScene {

	/**
	 * Initialize the sub-scene.
	 * @param scene CheckersScene
	 */
	public GameBoardSubScene(CheckersScene scene) {
		super(scene);
		createMenuButton();
	}
	
	
	/**
	 * Initialize a placeholder menu button to transition to the in-game menu
	 * subscene.
	 */
	private void createMenuButton () {
		CheckersButton button = new CheckersButton("MENU", CheckersButton.ButtonSizes.MEDIUM);
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					segueToSubScene(SubScenes.IN_GAME_MENU);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}
		});
		
		button.setLayoutX(900);
		button.setLayoutY(30);
		add(button);
	}

	@Override
	public void transitionScene(boolean isSubSceneActive) {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.2));
		transition.setNode(this);
		transition.setToX(isSubSceneActive ? 0 : -Configs.WINDOW_WIDTH);
		
		transition.play();
	}

}
