package subscene;

import gui.MenuButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import main.Configs;
import scene.CheckersScene;

/**
 * Subscene for the How To Play screen in the Main Menu.
 * 
 * @author Andrew Johnston
 *
 */
public class HowToPlaySubScene extends CheckersSubScene {

	/**
	 * Initialize the sub-scene.
	 * @param scene
	 */
	public HowToPlaySubScene(CheckersScene scene) {
		super(scene);
		setLayoutX(Configs.WINDOW_WIDTH);
		createBackButton();
	}
	
	/**
	 * Initialize a back button to transition back to the main-menu
	 * subscene.
	 */
	private void createBackButton () {
		MenuButton button = new MenuButton("Back");
		
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
