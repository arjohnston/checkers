package gui;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * This class handles the logic for a button on the main menu.
 * 
 * @author Andrew Johnston
 *
 */

public class MenuButton extends Button {
	/**
	 * Initialize a menu button.
	 * @param label String text for what the button should have labeled on it.
	 */
	public MenuButton (String label) {
		setText(label);
		initializeButtonListeners();
	}
	
	/**
	 * Initialize any event listeners (e.g. OnMouseEnter, Click, etc.) for global
	 * effects, such as drop shadow, etc.
	 */
	private void initializeButtonListeners () {
		setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// If the left button is pressed
				if (event.getButton().equals(MouseButton.PRIMARY)) {
					System.out.println("Button pressed!");
				}
			}
		});
	}
}