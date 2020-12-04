package test.gui;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import gui.CheckersButton;
import gui.CheckersButton.ButtonSizes;
import javafx.scene.image.ImageView;

/**
 * In order to test JavaFX-related classes, the stage itself needs to be started in order to
 * run any of these tests. Therefore we need to add that to the start of each test as well
 * as destroy it at the end of each test.
 * @author Andrew Johnston
 *
 */

public class CheckersButtonTest {
	@Test
	public void instantiateWithString () {
		CheckersButton button = new CheckersButton("test");
		
		assertTrue(button != null);
	}
	
	@Test
	public void instantiateWithImage () {
		CheckersButton button = new CheckersButton(new ImageView("file:/dummy-resource.png"));
		
		assertTrue(button != null);
	}
	
	@Test
	public void instantateWithStringAndSize () {
		CheckersButton button = new CheckersButton("test", ButtonSizes.LARGE);
		
		assertTrue(button != null);
	}
	
	@Test
	public void testSetButtonSize () {
		CheckersButton button = new CheckersButton("test");
		button.setButtonSize(ButtonSizes.LARGE);
		
		assertTrue(button != null);
	}
}
