import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.w3c.dom.Attr;

import org.w3c.dom.Document;

import org.w3c.dom.Element;

public class Level {

	private int target;
	private int highscore;
	private int numberOfMovement;
	private int currentLevel;
	private final PropertyChangeSupport PCS = new PropertyChangeSupport(this);

	public Level() {
		target = 15000;
		highscore = 0;
		numberOfMovement = 25;
		handleMovementModification();
		currentLevel = 1;
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

	public int getHighscore() {
		return highscore;
	}

	public void setHighscore(int highscore) {
		this.highscore = highscore;
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

	public void getXmlLevel(Document doc, Element element) {

		String level_val = Integer.toString(getCurrentLevel());

		Attr no = doc.createAttribute("level");

		no.setValue(level_val);

		element.setAttributeNode(no);

	}

}
