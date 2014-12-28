public class TimeBaseLevel extends Level {
	
	
	private int target;
	private int highScore;
	private int currentLevel;
	private  int timer;
	
	
	/**
	 * @requires Choosing to specific level wihich ic timeBaseLevel.
 	 * @modifies target,highScore and timer and currentLevel.
	 * @ensures  According toinformation from xml, initialization of target=22000, highScore=0, currentLevel=4 and timer=180sec. 
	 */
	public TimeBaseLevel ( ) {
		
		target=22000;
		highScore=0;
		timer=180;
		currentLevel=1;
		
		
	}
	/**
	 * @requires timer=0 and target=>22000.
	 * @modifies highScore and timer
	 * @ensures the begining of the highScore and at the end of the highScore will compare then it will change 
	 * if last highScore will be bigger than the beginning score. 
	 */
	
	public void saveCompletedLevel(){
	
		

}
	/**
	 * @requires selection of the specific level
	 * @ensures target, highScore, currentLevel and timer will be set on the board from xml file. 
	 */
	
	public void loadLevel() {
		
		
		
	}
	
	
	
	public int getTimer() {
		return timer;
	}
	/**
	 * @requires obtaining to TimerLokum
	 * @modifies timer
	 * @ensures timer will increase after every crush with +5 seconds. 
	 * @param timer int
	 */
	public void incrementtimer() {
		timer=+ 5;
		
	}
	public void setTimer(int timer) {
		
		this.timer=timer;
		
	}
	
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public int getHighScore() {
		return highScore;
	}
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	public int getCurrentLevel() {
		return currentLevel;
	}
	
	
	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}
	public boolean equals(Object o) {
		boolean statement = false;
		if (o instanceof TimeBaseLevel) {
			if (repOk()) {
				if (((TimeBaseLevel) o).repOk()) {
					if (((TimeBaseLevel) o).getTarget() == this.target
							&& ((TimeBaseLevel) o).getCurrentLevel() == this.currentLevel
							&& ((TimeBaseLevel) o).getHighScore() == this.highScore
							&& ((TimeBaseLevel) o).getTimer() == this.timer)
						statement = true;
				
					else
						statement = false;
				} else
					throw new NullPointerException(
							"Invalid TimeBaseLevel Object in TimeBaseLevel.equals method");
			}
		}
		return statement;
	}

	public boolean repOk() {
		boolean statement = false;
		if ( currentLevel<=0 || currentLevel>5 || timer <0  ||  target<=0 || highScore<0 || this== null )
			statement = false;
		else
			statement = true;
		return statement;
	}
	
	
	public String toString() {
		String result = "TimeBaseLevel.toString :\n";
		result += "The target of TimeBaseLevel is :\n" + getTarget()+ "\n";
		result += "The highscore of TimeBaseLevel is :\n" + getHighScore()+ "\n";
		result += "The timer of TimeBaseLevel is :\n" + getTimer()+ "\n";
		return result ;
		
	}
	

}