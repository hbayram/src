import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.w3c.dom.Attr;

import org.w3c.dom.Document;

import org.w3c.dom.Element;

public class Level {

	private int target;
	private int highScore;
	private int numberOfMovement;
	private int currentLevel;
	private final PropertyChangeSupport PCS = new PropertyChangeSupport(this);

	public Level() {
		target = 22000;
		highScore = 0;
		numberOfMovement = 25;
		handleMovementModification();
		currentLevel = 5;
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		PCS.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		PCS.removePropertyChangeListener(l);
	}

	public void handleMovementModification() {
		PCS.firePropertyChange("numberOfMovement", -1, numberOfMovement);
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public int gethighScore() {
		return highScore;
	}

	public void sethighScore(int highScore) {
		this.highScore = highScore;
	}

	public int getNumberOfMovement() {
		return numberOfMovement;
	}

	public void setNumberOfMovement() {
		numberOfMovement--;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public void saveCompletedLevel(Level level) {

	}

	public void loadLevel() {

	}

	public void updateNumOfMovements(int parseInt) {
		// TODO Auto-generated method stub
		numberOfMovement = parseInt;
	}

	

}
