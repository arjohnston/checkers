package subscene;

import java.util.ArrayList;
import java.util.List;

import gui.MenuButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Configs;
import scene.CheckersScene;

/**
 * The main-menu sub-scene in the MenuScene.
 * 
 * @author Andrew Johnston
 * 
 */

public class MainMenuSubScene extends CheckersSubScene {
	private static final int MENU_BUTTON_STARTING_POS_X = 100;
	private static final int MENU_BUTTON_STARTING_POS_Y = 150;
	
	/**
	 * A list of all the buttons on this sub-scene.
	 */
	List<MenuButton> buttons;
	
	/**
	 * Initialize the sub-scene.
	 * @param scene
	 */
	public MainMenuSubScene (CheckersScene scene) {
		super(scene);
		buttons = new ArrayList<>();
		createButtons();
	}
	
	/**
	 * Create the menu buttons.
	 */
	private void createButtons() {
		createNewGameButton();
		createHighScoresButton();
		createHowToPlayButton();
		createExitButton();
	}
	
	/**
	 * Add a menu button and calculate it's position.
	 * @param button Node button to be added.
	 */
	private void addMenuButton (MenuButton button) {
		button.setLayoutX(MENU_BUTTON_STARTING_POS_X);
		button.setLayoutY(MENU_BUTTON_STARTING_POS_Y + buttons.size() * 100);
		buttons.add(button);
		add(button);
	}
	
	/**
	 * Create the New Game button.
	 */
	private void createNewGameButton () {
		MenuButton button = new MenuButton("New Game");
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					segueToSubScene(SubScenes.NEW_GAME);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}
		});
		
		addMenuButton(button);
	}
	
	/**
	 * Create the High Scores button.
	 */
	private void createHighScoresButton () {
		MenuButton button = new MenuButton("High Scores");
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					segueToSubScene(SubScenes.HIGH_SCORES);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}
		});
		
		addMenuButton(button);
	}
	
	/**
	 * Create the How to Play button.
	 */
	private void createHowToPlayButton () {
		MenuButton button = new MenuButton("How to Play");
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					segueToSubScene(SubScenes.HOW_TO_PLAY);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}
		});
		
		addMenuButton(button);
	}
	
	/**
	 * Create the Exit button.
	 */
	private void createExitButton () {
		MenuButton button = new MenuButton("Exit");
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					Node source = (Node) event.getSource();
					Stage stage = (Stage) source.getScene().getWindow();
					stage.close();
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}
		});

		addMenuButton(button);
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
