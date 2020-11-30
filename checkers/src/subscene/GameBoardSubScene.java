package subscene;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import application.Configs;
import gui.CheckersButton;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.util.Duration;
import scene.CheckersScene;
import utils.CheckersLogic;
import utils.GameTimer;
import utils.Vector2i;

/**
 * Subscene for the Game Board.
 * 
 * @author Andrew Johnston
 *
 */
public class GameBoardSubScene extends CheckersSubScene {
	private CheckersScene scene;
	private int[][] gameBoard;
	
	private Label title;
	private Label timerLabel;
	
	private boolean isSinglePlayer;
	private String playerOneName;
	private String playerTwoName;
	
	private int playerTurn;
	
	private Vector2i gamePieceCoordSelected;
	private ImageView gamePieceImageSelected;
	private ImageView highlightedSpace;
	
	private List<ImageView> gameBoardImages;
	
	/**
	 * Initialize the sub-scene.
	 * @param scene CheckersScene
	 */
	public GameBoardSubScene(CheckersScene scene) {
		super(scene);
		
		this.scene = scene;
		
		gameBoardImages = new ArrayList<ImageView>();
		setup();
	}
	
	/**
	 * Start the game. Should be called when segue'd to
	 */
	public void start () {
		if (highlightedSpace != null) {
			remove(highlightedSpace);
			highlightedSpace = null;
		}
		
		GameTimer.reset();
		resetGameboard();
		
		isSinglePlayer = scene.getIsSinglePlayer();
		playerOneName = scene.getPlayerOneName();
		playerTwoName = scene.getPlayerTwoName();
		
		changeToPlayersTurn(1);
		startTimer();
	}
	
	private void resetGameboard () {
		for (ImageView image : gameBoardImages) {
			remove(image);
		}
		
		initializeGameBoard();
	}
	
	private void setup () {
		initializeImages();
		initializeText();
		createMenuButton();
	}
	
	private void initializeImages () {
		ImageView timerImage = new ImageView("file:resources/timer.png");
		timerImage.setLayoutX(50); 
		timerImage.setLayoutY(15);
		add(timerImage);
	}
	
	private void initializeText () {
		title = createLabel("", 0, 20, true, "header", 52);
		add(title);
		
		add(createLabel("P1", 95, 98, false, "header", 52));
		add(createLabel("P2", 925, 98, false, "header", 52));
		
		timerLabel = createLabel("5:03", 100, 20, false, "paragraph", 30);
		add(timerLabel);
	}
	
	private void changeToPlayersTurn (Integer player) {
		playerTurn = player;
		gamePieceCoordSelected = null;
		gamePieceImageSelected = null;
		highlightedSpace = null;
		
		if (player == 1) {
			title.setText(playerOneName + "'s Turn");
		} else if (player == 2 && isSinglePlayer) {
			title.setText("Opponent's Turn");
			// TODO do some AI stuff
		} else {
			title.setText(playerTwoName + "'s Turn");
		}
	}
	
	private Label createLabel (String text, Integer x, Integer y, boolean maxWidth, String className, Integer fontSize) {
		Label label = new Label(text);
		label.getStyleClass().add(className);
		
		label.setLayoutX(x);
		label.setLayoutY(y);
		
		if (maxWidth) label.setMinWidth(Configs.WINDOW_WIDTH);
		
		try {
			label.setFont(Font.loadFont(new FileInputStream(Configs.Font.MONTSERRAT_REGULAR), fontSize));
		} catch (FileNotFoundException e) {
			label.setFont(Font.font("Verdana", fontSize));
		}
		
		return label;
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
				int x = i;
				int y = j; 

				// Place a background on the playable spaces
				if (gameBoard[i][j] > -1) {
					ImageView space = new ImageView("file:resources/playable-space.png");
					
					space.setLayoutX(240 + i * 75); // 240 = (Configs.WINDOW_WIDTH / 2) - (total game board width / 2). 75 = width of PNG used
					space.setLayoutY(100 + j * 75); // 100 constant value to push the game board down 100px from the top, preserving 20px padding on bottom
					
					space.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
						onClick(new Vector2i(x, y), null);
						event.consume();
					});
					
					add(space);
					gameBoardImages.add(space);
				}
				
				if (gameBoard[i][j] == 1) {
					ImageView playerOnePiece = new ImageView("file:resources/player-1-piece.png");
					playerOnePiece.setLayoutX(240 + i * 75); 
					playerOnePiece.setLayoutY(100 + j * 75);
					playerOnePiece.setPickOnBounds(true); // allows clicking on transparent part of PNG
					playerOnePiece.setViewOrder(-1.0);
					
					playerOnePiece.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
						int playerOnePieceXCoord = (int) ((playerOnePiece.getLayoutX() - 240) / 75);
						int playerOnePieceYCoord = (int) ((playerOnePiece.getLayoutY() - 100) / 75);
						onClick(new Vector2i(playerOnePieceXCoord, playerOnePieceYCoord), playerOnePiece);
						event.consume();
					});
					
					add(playerOnePiece);
					gameBoardImages.add(playerOnePiece);
				}
				
				// put the game pieces down for player 2
				if (gameBoard[i][j] == 2) {
					ImageView playerTwoPiece = new ImageView("file:resources/player-2-piece.png");
					playerTwoPiece.setLayoutX(240 + i * 75); 
					playerTwoPiece.setLayoutY(100 + j * 75);
					playerTwoPiece.setPickOnBounds(true); // allows clicking on transparent part of PNG
					playerTwoPiece.setViewOrder(-1.0);
					
					playerTwoPiece.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
						int playerTwoPieceXCoord = (int) ((playerTwoPiece.getLayoutX() - 240) / 75);
						int playerTwoPieceYCoord = (int) ((playerTwoPiece.getLayoutY() - 100) / 75);
						onClick(new Vector2i(playerTwoPieceXCoord, playerTwoPieceYCoord), playerTwoPiece);
						event.consume();
					});
					
					add(playerTwoPiece);
					gameBoardImages.add(playerTwoPiece);
				}
			}
			
			// Add a border to the game board. The asset is 2px wide, so the placement
			// accounts for it needing to be 2px up and left.
			ImageView border = new ImageView("file:resources/gameboard-border.png");
			border.setLayoutX(238);
			border.setLayoutY(98);
			add(border);
			gameBoardImages.add(border);
		}
	}
	
	private void updateTimerText (long timeElapsedInSeconds) {
		Integer minutes = (int) Math.floor(timeElapsedInSeconds / 60);
		Integer seconds = (int) (timeElapsedInSeconds % 60);
		
		String secString = seconds < 10 ? "0" + seconds : seconds.toString();
		
		Platform.runLater(() -> timerLabel.setText(minutes + ":" + secString));
	}
	
	private void startTimer() {
		GameTimer.start((timeElapsedInSeconds) -> {
			updateTimerText(timeElapsedInSeconds);
		});
	}
	
	/**
	 * Check to see where we're clicking. If on the game board and a space that's selectable,
	 * then select it (e.g. piece that we're going to move). Then subsequent selections
	 * should be where the piece will move to.
	 */
	private void onClick (Vector2i coord, ImageView playerPiece) {
		if (CheckersLogic.isSelectable(gameBoard, playerTurn, coord)) {
			// If a player piece is already selected and now we're trying to move
			if (gamePieceCoordSelected != null && playerPiece == null) {
				// take the game piece at the x,y and move its coords to the new coord
				gamePieceImageSelected.setLayoutX(240 + coord.x * 75);
				gamePieceImageSelected.setLayoutY(100 + coord.y * 75);
				
				// remove selection
				gamePieceCoordSelected = null;
				gamePieceImageSelected = null;
				
				// remove the highlight
				if (highlightedSpace != null) {
					remove(highlightedSpace);
					highlightedSpace = null;
				}
			} else if (playerPiece != null) {
				gamePieceCoordSelected = coord;
				gamePieceImageSelected = playerPiece;
				
				if (highlightedSpace != null) {
					remove(highlightedSpace);
					highlightedSpace = null;
				}

				highlightedSpace = new ImageView("file:resources/space-highlight.png");
				highlightedSpace.setLayoutX(playerPiece.getLayoutX()); 
				highlightedSpace.setLayoutY(playerPiece.getLayoutY());
				
				add(highlightedSpace);
			}
		}
	}
	

	/**
	 * Initialize a placeholder menu button to transition to the in-game menu
	 * subscene.
	 */
	private void createMenuButton () {
		CheckersButton button = new CheckersButton(new ImageView("file:resources/hamburger-menu.png"));
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					GameTimer.pause();
					segueToSubScene(SubScenes.IN_GAME_MENU);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}
		});
		
		button.setLayoutX(970);
		button.setLayoutY(5);
		add(button);
	}

	@Override
	public void transitionScene(boolean isSubSceneActive) {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.2));
		transition.setNode(this);
		transition.setToX(isSubSceneActive ? 0 : -Configs.WINDOW_WIDTH);
		
		if (isSubSceneActive) startTimer();
		//else GameTimer.pause();
		
		transition.play();
	}

}
