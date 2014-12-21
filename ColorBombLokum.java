public class ColorBombLokum extends Lokum{
	private int type;
	private int posX;
	private int posY;
	private int lokumWidth;
	private int lokumHeight;
	
	public ColorBombLokum() {
		type = 5;
		lokumWidth = 40;
		lokumHeight = 40;
		posX = 0;
		posY = 0;
		
		getSourceString();
	}

	
	public String getSourceString(){
		String source = "";
		
		if (type == 5)
			source ="bonibon.png";
		return source;		
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
		return true;
	}


	
}
