import javax.xml.parsers.ParserConfigurationException;


public class GameEngine {
	private Board gameBoard;  
	private Level gameLevel;
	private GUI gui;
	
	
	/**
	 * @requires selectedLevel != null
	 * @param selectedLevel The level,chosen by a player, assigns the features of game such as number of movements, target. 
	 * @modifies gameLevel
	 * @ensures selectedLevel should be assigned to gameLevel.  
	 */
	public GameEngine(Level selectedLevel) {
			gameLevel = selectedLevel;
			gameBoard = new Board(9,9,gameLevel);
			gui = new GUI(gameBoard);
	}
	
	/**
	 * @requires gameBoard = null
	 * @modifies gameBoard
	 * @ensures gameBoard should be initialized. 
	 */
	public void playNewGame() {
		gui.createSettingsFrame();
	}
	
	/**
	 *@requires When numberOfMOvements = 0, targetOfLevel should not be completed or
	 *if targetOfLevel is completed, then starNumber < 1. 
	 *@modifies gameBoard
	 *@ensures gameBoard should be cleared. Settings should be displayed.  
	 */
	public void GameOver() {
		if (gameLevel.getNumberOfMovement() == 0 && gameBoard.getScore() < gameLevel.getTarget()) {
			System.out.println("Game Over");
		}
	}
	/**
	 * @requires exit() and save() methods of Settings , numberOfMovement != 0
	 * @modifies XML file
	 * @ensures if save() method is invoked, the whole feature of gameBoard should be saved, and written to XML file. Also,
	 * the fields of gameLevel should be saved and written to XML file. Afterwards, Settings should be displayed.  
	 */
	public void exitGame() {
		
	}

	/**
	 * @requires loadedBoard != null. gameLevel != null. gameBoard = null. 
	 * Whether gameLevel is written to XML file as a saved game should be checked. 
	 * @param loadedBoard
	 * @modifies gameBoard
	 * @ensures gameBoard should be initialized as a loadedBoard. 
	 */
	public void loadGame() {
		try {
			gameBoard.loadGameBoard();
			gameLevel.loadLevel();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
