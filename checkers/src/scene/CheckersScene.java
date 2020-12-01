package scene;

import application.Configs;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import subscene.SubScenes;

/**
 * A utility class to represent a Scene, ensuring we have the
 * same global configurations for all Scene's.
 * 
 * @author Andrew Johnston
 *
 */

public abstract class CheckersScene extends Scene {
	private AnchorPane pane;
	protected SceneManager sceneManager;
	
	/**
	 * Initialize a new JavaFX scene object.
	 */
	public CheckersScene (SceneManager sceneManager) {
		super(new AnchorPane(), Configs.WINDOW_WIDTH, Configs.WINDOW_HEIGHT);
		this.sceneManager = sceneManager;
		this.pane = (AnchorPane) this.getRoot();
	}
	
	/**
	 * Set the background image of the scene.
	 * 
	 * @param resourcePath String absolute path of the resource.
	 */
	public void setBackground(String resourcePath) {
		Image backgroundImage = new Image(resourcePath);
		BackgroundImage background = new BackgroundImage(
				backgroundImage, 
				BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT, 
				BackgroundPosition.CENTER, 
				BackgroundSize.DEFAULT
		);

		this.pane.setBackground(new Background(background));
	}
	
	public abstract void segueToSubScene(SubScenes subScene) throws Exception;
	public abstract void segueToScene(Scenes scene);
	
	public void setSettings (String playerOneName, String playerTwoName, boolean isSinglePlayer, GameDifficulty gameDifficulty) {
		this.sceneManager.setSettings(playerOneName, playerTwoName, isSinglePlayer, gameDifficulty);
	}
	
	public void setWinner(String playerNameWhoWon, long timeElapsed) {
		this.sceneManager.setWinner(playerNameWhoWon, timeElapsed);
		
	}

	public String getPlayerOneName() {
		return this.sceneManager.getPlayerOneName();
	}

	public String getPlayerTwoName() {
		return this.sceneManager.getPlayerTwoName();
	}

	public String getPlayerWhoWonName() {
		return this.sceneManager.getPlayerWhoWonName();
	}

	public boolean getIsSinglePlayer() {
		return this.sceneManager.getIsSinglePlayer();
	}

	public long getTimeElapsed() {
		return this.sceneManager.getTimeElapsed();
	}
	
	/**
	 * Add a JavaFX Node to the scene.
	 * @param e JavaFX Node
	 */
	public void add (Node e) {
		pane.getChildren().add(e);
	}
}
