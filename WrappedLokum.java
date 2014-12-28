public class WrappedLokum extends Lokum {
	private int type;
	private int posX;
	private int posY;
	private int lokumWidth;
	private int lokumHeight;

	public WrappedLokum(int n) {
		type = n;
		lokumWidth = 40;
		lokumHeight = 40;
		posX = 0;
		posY = 0;
	}

	public int getType() {
		return type;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getLokumWidth() {
		return lokumWidth;
	}

	public int getLokumHeight() {
		return lokumHeight;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setLokumWidth(int lokumWidth) {
		this.lokumWidth = lokumWidth;
	}

	public void setLokumHeight(int lokumHeight) {
		this.lokumHeight = lokumHeight;
	}

	public String getSourceString() {
		String source = "";
		switch (type) {

		case 1:
			source = "greenpistchowrapped.png";
			break;

		case 2:
			source = "hazelnutwrapped.png";
			break;

		case 3:
			source = "purplewrapped.png";
			break;

		case 4:
			source = "redrosewrapped.png";
			break;

		}
		return source;
	}

	@Override
	public void setType(int type) {
		// TODO Auto-generated method stub
		this.type = type;
	}

	@Override
	public boolean isBasicLokum() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWrappedLokum() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isStrippedLokum() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isColorBomb() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTimerLokum() {
		// TODO Auto-generated method stub
		return false;
	}

}