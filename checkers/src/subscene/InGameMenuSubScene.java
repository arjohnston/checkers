package subscene;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Timer;
import java.util.ArrayList;
import java.util.List;

import application.Configs;
import gui.CheckersButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import scene.CheckersScene;

public class InGameMenuSubScene extends CheckersSubScene {
	private List<CheckersButton> buttons;
	
	private static final int MENU_BUTTON_STARTING_POS_X = 100;
	private static final int MENU_BUTTON_STARTING_POS_Y = 150;

	public InGameMenuSubScene(CheckersScene x) {
		super(x);
		buttons = new ArrayList<>();
		createButtons();
		setHeader();
		setLogo();
	//	setTimer();
	}
/*
	private void setTimer() {
		Timer timer = new Timer();
		add(timer);
	}
	*/
	private void setHeader () {
		Label header = new Label("GAME PAUSED");
		header.getStyleClass().add("header");

		header.setLayoutX(100);
		header.setLayoutY(50);
		try {
			header.setFont(Font.loadFont(new FileInputStream(Configs.Font.MONTSERRAT_SEMIBOLD), 52));
		} catch (FileNotFoundException e) {
			header.setFont(Font.font("Verdana", 52));
		}
		
		add(header);
	}
	private void setLogo () {
		ImageView logo = new ImageView("file:resources/logo.png");
		logo.setLayoutX(Configs.WINDOW_WIDTH - 375);
		logo.setLayoutY(Configs.WINDOW_HEIGHT - 300);
		add(logo);
	}

	private void createButtons() {
		ResumeButton();
		ForfeitButton();
	}
	private void ResumeButton() {
		CheckersButton button = new CheckersButton("RESUME");
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					//segueToSubScene(SubScenes.GAME);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}
		});
		
		addMenuButton(button);
	}
	private void ForfeitButton() {
		CheckersButton button = new CheckersButton("FORFEIT GAME");
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					//segueToSubScene(SubScenes.WINNING_CONDITION);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}
		});
		
		addMenuButton(button);
	}
	
	private void addMenuButton (CheckersButton button) {
		button.setLayoutX(MENU_BUTTON_STARTING_POS_X);
		button.setLayoutY(MENU_BUTTON_STARTING_POS_Y + buttons.size() * 100);
		buttons.add(button);
		add(button);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

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
