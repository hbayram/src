public class TimerLokum extends Lokum {

	private int type;
	private int posX;
	private int posY;
	private int lokumWidth;
	private int lokumHeight;
	private String source  ;

	/**
	 * @requires Player should be TimeBaseLevel.
	 * @modifies Type, lokum width,lokum height and posX,posY.
	 * @ensures It is last decided that this type is equal to type, posX and
	 *          posY are equal to 0 and lokumWidht and lokumheight are equal to
	 *          40.
	 * 
	 * @param int type. It will be equal to the type of the timerLokum.
	 */
	public TimerLokum(int type) {
		this.type = type;
		lokumWidth = 40;
		lokumHeight = 40;
		posX = 0;
		posY = 0;
		initSourceString();
		
	}

	public void initSourceString() {
		switch (this.type) {
		case 1:
			source = "greenpistcho.png";
			break;
		case 2:
			source = "hazelnut.png";
			break;
		case 3:
			source = "purplecoconut.png";
			break;
		case 4:
			source = "redrose.png";
			break;
		default : source = "";
		}
	}
	
	@Override
	public int getLokumWidth() {
		// TODO Auto-generated method stub
		return lokumWidth;
	}

	@Override
	public int getLokumHeight() {
		// TODO Auto-generated method stub
		return lokumHeight;
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public void setType(int type) {
		this.type = type;
		// TODO Auto-generated method stub

	}

	@Override
	public int getPosX() {
		// TODO Auto-generated method stub
		return posX;
	}

	@Override
	public int getPosY() {
		// TODO Auto-generated method stub
		return posY;
	}

	@Override
	/**
	 * @requires 0<type<=4 as a integer and also images should be taken from the  paths.
	 * @ensures images should be taken true paths and also correct images should be put on the board. 
	 */
	public String getSourceString() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @ensures return value should be true.
	 * 
	 */

	public boolean isTimerLokum() {

		return true;
	}

	@Override
	/**
	 * @ensures return value should be false because this is not timerLokum.
	 */
	public boolean isBasicLokum() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * @ensures return value should be false because this is not timerLokum.
	 */
	public boolean isWrappedLokum() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * @ensures return value should be false because this is not timerLokum.
	 */
	public boolean isStrippedLokum() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * @ensures return value should be false because this is not timerLokum.
	 */
	public boolean isColorBomb() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean equals(Object o) {
		boolean statement = false;
		if (o instanceof TimerLokum) {
			if (repOk()) {
				if (((TimerLokum) o).repOk()) {
					if (((TimerLokum) o).getLokumHeight() == this.lokumHeight
							&& ((TimerLokum) o).getLokumWidth() == this.lokumWidth
							&& ((TimerLokum) o).getPosX() == this.posX
							&& ((TimerLokum) o).getPosY() == this.posY
							&& ((TimerLokum) o).getType() == this.type
							&& ((TimerLokum) o).getSourceString()
									.equals(source))
						statement = true;
					else
						statement = false;
				} else
					throw new NullPointerException(
							"Invalid timerLokum in Ball.equals method");
			}
		}
		return statement;
	}

	/**
	 * 
	 * @ensures Returns true if the rep invariant holds for this; otherwise
	 *          returns false
	 */
	public boolean repOk() {
		boolean statement = false;
		if (this == null || type < 1 || type > 4 || lokumHeight <= 0
				|| lokumWidth <= 0 || posX < 0 || posY < 0 || source == null)
			statement = false;
		else if (lokumWidth < posX)
			statement = false;
		else if (lokumHeight < posY)
			statement = false;
		else if (isTimerLokum() == false)
			statement = false;
		else
			statement = true;
		return statement;
	}

	public void setLokumWidth(int lokumWidth) {
		this.lokumWidth = lokumWidth;
	}

	public void setLokumHeight(int lokumHeight) {
		this.lokumHeight = lokumHeight;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * @ensures All fields of timer lokum should be converted to string type.
	 */
	public String toString() {
		String result = "TimerLokum.toString :\n";
		result += "The type of the timer lokum is :\n" + getType() + "\n";
		result += "X coordinate of the timer lokum is :\n" + getPosX() + "\n";
		result += "Y coordinate of the timer lokum is :\n" + getPosY() + "\n";
		result += "The width of the timer lokum is :\n" + getLokumWidth()
				+ "\n";
		result += "The height of the timer lokum is :\n" + getLokumHeight()
				+ "\n";
		result += "The source string of the timer lokum is :\n"
				+ getSourceString() + "\n";
		return result;
	}

}
