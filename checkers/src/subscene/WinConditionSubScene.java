package subscene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Configs;
import gui.CheckersButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.util.Duration;
import scene.CheckersScene;

/**
 * 
 * @author Shaion Habibvand
 *
 */

public class WinConditionSubScene extends CheckersSubScene{
	/**
	 * Initialize the sub-scene.
	 * @param scene CheckersScene
	 */
	public WinConditionSubScene(CheckersScene scene) {
		super(scene);
		setLayoutX(Configs.WINDOW_WIDTH);
		createButtons();
		setHeader();
		setSubHeader();
	}
	
	/**
	 * Set the subscene's header.
	 */
	private void setHeader () {
		Label header = new Label("P L A Y E R  X  W O N!");
		header.getStyleClass().add("header");

		header.setLayoutX(0);
		header.setLayoutY(75);
		header.setMinWidth(Configs.WINDOW_WIDTH);
		try {
			header.setFont(Font.loadFont(new FileInputStream(Configs.Font.MONTSERRAT_SEMIBOLD), 52));
		} catch (FileNotFoundException e) {
			header.setFont(Font.font("Verdana", 52));
		}
		
		add(header);
	}
	
	private void setSubHeader () {
		Label header = new Label("Time: 10:23");
		header.getStyleClass().add("header");

		header.setLayoutX(0);
		header.setLayoutY(200);
		header.setMinWidth(Configs.WINDOW_WIDTH);
		
		try {
			header.setFont(Font.loadFont(new FileInputStream(Configs.Font.MONTSERRAT_SEMIBOLD), 45));
		} catch (FileNotFoundException e) {
			header.setFont(Font.font("Verdana", 45));
		}
		
		add(header);
	}
	
	/**
	 * Initialize a back button to transition back to the main-menu
	 * subscene.
	 */
	private void createButtons() {
		createReturnHomeButton();
		createPlayAgainButton();
	}
	
	private void createReturnHomeButton () {
		CheckersButton button = new CheckersButton("RETURN HOME", CheckersButton.ButtonSizes.LARGE);
		
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
		
		button.setLayoutX(353);
		button.setLayoutY(500);
		button.setAlignment(Pos.BASELINE_CENTER);
		add(button);
	}
	
	private void createPlayAgainButton () {
		CheckersButton button = new CheckersButton("PLAY AGAIN", CheckersButton.ButtonSizes.LARGE);
		
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
		
		button.setLayoutX(353);
		button.setLayoutY(400);
		button.setAlignment(Pos.BASELINE_CENTER);
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
