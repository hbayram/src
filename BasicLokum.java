public class BasicLokum extends Lokum {
	private int type;
	private int posX;
	private int posY;
	private int lokumWidth;
	private int lokumHeight;
	

	public BasicLokum(int n) {
		type = n;
		lokumWidth = 40;
		lokumHeight = 40;
		posX = 0;
		posY = 0;
	}
	
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return type;
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getLokumWidth() {
		return lokumWidth;
	}

	public void setLokumWidth(int lokumWidth) {
		this.lokumWidth = lokumWidth;
	}

	public int getLokumHeight() {
		return lokumHeight;
	}

	public void setLokumHeight(int lokumHeight) {
		this.lokumHeight = lokumHeight;
	}

	public String getSourceString( ) {
		String source = "";
		
		switch (type) {
		case 1:
			source ="greenpistcho.png";
			break;
		case 2:
			source ="hazelnut.png";
			break;
		case 3:
			source ="purplecoconut.png";
			break;
		case 4:
			source ="redrose.png";
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
		return true;
	}

	@Override
	public boolean isWrappedLokum() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isStrippedLokum() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isColorBombLokum() {
		// TODO Auto-generated method stub
		return false;
	}


}
