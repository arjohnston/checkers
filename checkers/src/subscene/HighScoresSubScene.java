package subscene;

import gui.MenuButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import main.Configs;
import scene.CheckersScene;

/**
 * Subscene to handle the view for the high scores menu.
 * 
 * @author Andrew Johnston
 *
 */

public class HighScoresSubScene extends CheckersSubScene {
	/**
	 * Initialize the high score subscene
	 * 
	 * @param scene The parent scene so we can handle segue's
	 */
	public HighScoresSubScene(CheckersScene scene) {
		super(scene);

		setLayoutX(Configs.WINDOW_WIDTH);		
		createBackButton();
	}
	
	/**
	 * Create a back-button to transition from the high scores subscene
	 * back to the main-menu.
	 */
	private void createBackButton () {
		MenuButton button = new MenuButton("Back");
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					segueToSubScene(SubScenes.MAIN_MENU);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}
		});
		
		button.setLayoutX(100);
		button.setLayoutY(150);
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
