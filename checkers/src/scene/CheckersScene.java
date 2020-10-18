package scene;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import main.Configs;
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
	
	/**
	 * Initialize a new JavaFX scene object.
	 */
	public CheckersScene () {
		super(new AnchorPane(), Configs.WINDOW_WIDTH, Configs.WINDOW_HEIGHT);
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
	
	/**
	 * Add a JavaFX Node to the scene.
	 * @param e JavaFX Node
	 */
	public void add (Node e) {
		pane.getChildren().add(e);
	}
}
