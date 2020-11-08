package subscene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import gui.CheckersButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.util.Duration;
import main.Configs;
import scene.CheckersScene;

public class NewGamePVPSubScene extends CheckersSubScene {

	public NewGamePVPSubScene(CheckersScene scene) {
		super(scene);
		setLayoutX(Configs.WINDOW_WIDTH);
		createBackButton();
		createSoloButton();
		createNameTexts();
		createPlayerFields();
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
	 * Create a button to switch to a Solo game creation scene
	 */
	private void createSoloButton () {
		CheckersButton button = new CheckersButton("SOLO", CheckersButton.ButtonSizes.MEDIUM);
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
					segueToSubScene(SubScenes.NEW_GAME);
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
	 * Creates text for Player 1 and Player 2
	 */
	private void createNameTexts () {
		Label p1Name = new Label("PLAYER 1 NAME");
		Label p2Name = new Label("PLAYER 2 NAME");
		p1Name.getStyleClass().add("header");
		p2Name.getStyleClass().add("header");
		
		p1Name.setLayoutX(340);
		p1Name.setLayoutY(100);
		
		p2Name.setLayoutX(332);
		p2Name.setLayoutY(300);
		
		try {
			p1Name.setFont(Font.loadFont(new FileInputStream(Configs.Font.MONTSERRAT_REGULAR), 52));
			p2Name.setFont(Font.loadFont(new FileInputStream(Configs.Font.MONTSERRAT_REGULAR), 52));
		} catch (FileNotFoundException e) {
			p1Name.setFont(Font.font("Verdana", 52));
			p2Name.setFont(Font.font("Verdana", 52));
		}
		
		add(p1Name);
		add(p2Name);
	}
	
	/**
	 * Creates the name entry fields for both players
	 */
	private void createPlayerFields () {
		TextField p1NameField = new TextField();
		TextField p2NameField = new TextField();
		
		p1NameField.getStyleClass().add("name-text-field");
		p2NameField.getStyleClass().add("name-text-field");
		
		p1NameField.setLayoutX(290);
		p1NameField.setLayoutY(180);
		p1NameField.setPrefWidth(500);
		
		p2NameField.setLayoutX(290);
		p2NameField.setLayoutY(380);
		p2NameField.setPrefWidth(500);
		
		try {
			p1NameField.setFont(Font.loadFont(new FileInputStream(Configs.Font.MONTSERRAT_REGULAR), 30));
			p2NameField.setFont(Font.loadFont(new FileInputStream(Configs.Font.MONTSERRAT_REGULAR), 30));
		} catch (FileNotFoundException e) {
			p1NameField.setFont(Font.font("Verdana", 30));
			p2NameField.setFont(Font.font("Verdana", 30));
		}
		
		add(p1NameField);
		add(p2NameField);
	}
	
	/**
	 * Create a button that starts the game
	 */
	private void createStartButton () {
		CheckersButton button = new CheckersButton("START", CheckersButton.ButtonSizes.MEDIUM);
		try {
			button.setFont(Font.loadFont(new FileInputStream(Configs.Font.MONTSERRAT_REGULAR), 30));
		} catch (FileNotFoundException e1) {
			button.setFont(Font.font("Verdana", 30));
			e1.printStackTrace();
		}
		
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
		
		button.setLayoutX(440);
		button.setLayoutY(500);
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
