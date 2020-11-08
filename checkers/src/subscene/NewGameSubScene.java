package subscene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import gui.CheckersButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import main.Configs;
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
		createPVPButton();
		createYourNameText();
		createYourNameField();
		createDifficultyText();
		createDifficultyButtons();
		createStartButton();
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
	
	/**
	 * Create a button to switch to a PVP creation scene
	 */
	private void createPVPButton () {
		CheckersButton button = new CheckersButton("PVP", CheckersButton.ButtonSizes.MEDIUM);
		button.setStyle("-fx-border-color: white; -fx-alignment: center; -fx-min-width: 150;");
		
		button.setOnMouseEntered(event -> {
			button.setStyle("-fx-border-color: lightgreen;"
					+ " -fx-text-fill: lightgreen;"
					+ " -fx-alignment: center;"
					+ " -fx-min-width: 150;");
		});
		
		button.setOnMouseExited(event -> {
			button.setStyle("-fx-border-color: white;"
					+ " -fx-text-fill: white;"
					+ " -fx-alignment: center;"
					+ " -fx-min-width: 150;");
		});
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					segueToSubScene(SubScenes.NEW_GAME_PVP);
					//sceneManager.segueTo(Scenes.GAME);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}
		});
		
		button.setLayoutX(880);
		button.setLayoutY(20);
		add(button);
	}
	
	/**
	 * Creates text for the 'Your Name' label
	 */
	private void createYourNameText () {
		Label yourName = new Label("YOUR NAME");
		yourName.getStyleClass().add("header");
		
		yourName.setLayoutX(380);
		yourName.setLayoutY(100);
		
		try {
			yourName.setFont(Font.loadFont(new FileInputStream(Configs.Font.MONTSERRAT_REGULAR), 52));
		} catch (FileNotFoundException e) {
			yourName.setFont(Font.font("Verdana", 52));
		}
		
		add(yourName);
	}
	
	/**
	 * Creates text for the 'Difficulty' label
	 */
	private void createDifficultyText () {
		Label difficulty = new Label("DIFFICULTY");
		difficulty.getStyleClass().add("header");
		
		difficulty.setLayoutX(400);
		difficulty.setLayoutY(320);
		
		try {
			difficulty.setFont(Font.loadFont(new FileInputStream(Configs.Font.MONTSERRAT_REGULAR), 52));
		} catch (FileNotFoundException e) {
			difficulty.setFont(Font.font("Verdana", 52));
		}
		
		add(difficulty);
	}
	
	/**
	 * Creates the name entry field
	 */
	private void createYourNameField () {
		TextField yourNameField = new TextField();
		
		yourNameField.getStyleClass().add("name-text-field");
		
		yourNameField.setLayoutX(290);
		yourNameField.setLayoutY(180);
		yourNameField.setPrefWidth(500);
		
		try {
			yourNameField.setFont(Font.loadFont(new FileInputStream(Configs.Font.MONTSERRAT_REGULAR), 30));
		} catch (FileNotFoundException e) {
			yourNameField.setFont(Font.font("Verdana", 30));
		}
		
		add(yourNameField);
	}
	
	/**
	 * Creates 3 separate difficulty buttons.
	 * Only one may be selected at any time.
	 */
	private void createDifficultyButtons () {
		CheckersButton easy = new CheckersButton("EASY", CheckersButton.ButtonSizes.MEDIUM);
		CheckersButton medium = new CheckersButton("MEDIUM", CheckersButton.ButtonSizes.MEDIUM);
		CheckersButton hard = new CheckersButton("HARD", CheckersButton.ButtonSizes.MEDIUM);
		
		CheckersButton[] difficultyButtons = {easy, medium, hard};
		
		for(CheckersButton button : difficultyButtons)
			button.setStyle("-fx-border-color: white; -fx-alignment: center;");
			
		easy.setOnMouseClicked(event -> {
			medium.setStyle("-fx-border-color: white; -fx-alignment: center;");
			hard.setStyle("-fx-border-color: white; -fx-alignment: center;");
			easy.setStyle("-fx-border-color: lightgreen;"
					+ " -fx-text-fill: lightgreen;"
					+ " -fx-alignment: center;");
		});
		
		medium.setOnMouseClicked(event -> {
			easy.setStyle("-fx-border-color: white; -fx-alignment: center;");
			hard.setStyle("-fx-border-color: white; -fx-alignment: center;");
			medium.setStyle("-fx-border-color: lightgreen;"
					+ " -fx-text-fill: lightgreen;"
					+ " -fx-alignment: center;");
		});
		
		hard.setOnMouseClicked(event -> {
			easy.setStyle("-fx-border-color: white; -fx-alignment: center;");
			medium.setStyle("-fx-border-color: white; -fx-alignment: center;");
			hard.setStyle("-fx-border-color: lightgreen;"
					+ " -fx-text-fill: lightgreen;"
					+ " -fx-alignment: center;");
		});
		
		easy.setLayoutX(230);
		easy.setLayoutY(400);
		
		medium.setLayoutX(450);
		medium.setLayoutY(400);
		
		hard.setLayoutX(670);
		hard.setLayoutY(400);
		
		for(CheckersButton button : difficultyButtons)
			add(button);
	}
	
	/**
	 * Create a button that starts the game
	 */
	private void createStartButton () {
		CheckersButton button = new CheckersButton("START", CheckersButton.ButtonSizes.MEDIUM);
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					// Temp code, but will segue to Game scene in the future
					segueToSubScene(SubScenes.MAIN_MENU);
					//sceneManager.segueTo(Scenes.GAME);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}
		});
		
		button.setStyle("-fx-border-color: white; -fx-alignment: center;");
		
		button.setOnMouseEntered(event -> {
			button.setStyle("-fx-border-color: lightgreen;"
					+ " -fx-text-fill: lightgreen;"
					+ " -fx-alignment: center;");
		});
		
		button.setOnMouseExited(event -> {
			button.setStyle("-fx-border-color: white;"
					+ " -fx-text-fill: white;"
					+ " -fx-alignment: center;");
		});
		
		button.setLayoutX(450);
		button.setLayoutY(520);
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
