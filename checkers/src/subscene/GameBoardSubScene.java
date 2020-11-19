package subscene;


import application.Configs;
import gui.CheckersButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import scene.CheckersScene;

/**
 * Subscene for the Game Board.
 * 
 * @author Andrew Johnston
 *
 */
public class GameBoardSubScene extends CheckersSubScene {
	private CheckersScene scene;
	private int[][] gameBoard;
	/**
	 * Initialize the sub-scene.
	 * @param scene CheckersScene
	 */
	public GameBoardSubScene(CheckersScene scene) {
		super(scene);
		createMenuButton();
		
		initializeGameBoard();
		
		this.scene = scene;
	}
	
	private void initializeGameBoard () {
		gameBoard = new int[8][8]; // Checkers has a 8x8 game board configuration
		
		// 0  : space is empty
		// 1  : space has a player 1 piece
		// 2  : space has a player 2 piece
		// 11 : stacked player 1 piece (queen/king)
		// 22 : stacked player 2 piece (queen/king)
		// -1 : space is invalid (cannot move to this space)
		for (int i = 0; i < 8; i++) {
			if (i == 0 || i % 2 == 0) {
				gameBoard[i][0] = 1;
				gameBoard[i][1] = -1;
				gameBoard[i][2] = 1;
				gameBoard[i][3] = -1;
				gameBoard[i][4] = 0;
				gameBoard[i][5] = -1;
				gameBoard[i][6] = 2;
				gameBoard[i][7] = -1;
			} else {
				gameBoard[i][0] = -1;
				gameBoard[i][1] = 1;
				gameBoard[i][2] = -1;
				gameBoard[i][3] = 0;
				gameBoard[i][4] = -1;
				gameBoard[i][5] = 2;
				gameBoard[i][6] = -1;
				gameBoard[i][7] = 2;
			}
			
			
			for (int j = 0; j < 8; j++) {
				// Place a background on the playable spaces
				if (gameBoard[i][j] > -1) {
					ImageView space = new ImageView("file:resources/playable-space.png");
					space.setLayoutX(240 + i * 75); // 240 = (Configs.WINDOW_WIDTH / 2) - (total game board width / 2). 75 = width of PNG used
					space.setLayoutY(100 + j * 75); // 100 constant value to push the game board down 100px from the top, preserving 20px padding on bottom
					add(space);
				}
				
				// Put the game pieces down for player 1. The 240 + 7 is to center the piece in the game space, since
				// the game pieces are 60px wide, or 15px difference from the playable space. So floor(15 / 2) = 7
				if (gameBoard[i][j] == 1) {
					ImageView playerOnePiece = new ImageView("file:resources/player-1-piece.png");
					playerOnePiece.setLayoutX(247 + i * 75); 
					playerOnePiece.setLayoutY(107 + j * 75);
					add(playerOnePiece);
				}
				
				// put the game pieces down for player 2
				if (gameBoard[i][j] == 2) {
					ImageView playerTwoPiece = new ImageView("file:resources/player-2-piece.png");
					playerTwoPiece.setLayoutX(247 + i * 75); 
					playerTwoPiece.setLayoutY(107 + j * 75);
					add(playerTwoPiece);
				}
			}
			
			// Add a border to the game board. The asset is 2px wide, so the placement
			// accounts for it needing to be 2px up and left.
			ImageView border = new ImageView("file:resources/gameboard-border.png");
			border.setLayoutX(238);
			border.setLayoutY(98);
			add(border);
		}
	}
	
	/**
	 * Initialize a placeholder menu button to transition to the in-game menu
	 * subscene.
	 */
	private void createMenuButton () {
		CheckersButton button = new CheckersButton("MENU", CheckersButton.ButtonSizes.MEDIUM);
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					segueToSubScene(SubScenes.IN_GAME_MENU);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}
		});
		
		button.setLayoutX(900);
		button.setLayoutY(30);
		add(button);
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
