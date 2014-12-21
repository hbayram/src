
public class StrippedLokum extends Lokum {
	private int type;
	private int posX;
	private int posY;
	private int lokumWidth;
	private int lokumHeight;
	private boolean vertical;
	private boolean horizontal;
	

	public StrippedLokum(int type, String direction) {
		this.type = type;
		if (direction == "vertical")
			vertical = true;
		else if (direction == "horizontal")
			horizontal = true;
		
		
		lokumWidth = 40;
		lokumHeight = 40;
		posX = 0;
		posY = 0;

	}
	

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
	public int getPosX() {
		// TODO Auto-generated method stub
		return posX;
	}

	@Override
	public int getPosY() {
		// TODO Auto-generated method stub
		return posY;
	}
	
	public void setPosX(int posX) {
		// TODO Auto-generated method stub
		this.posX=posX;
	}

	@Override
	public void setPosY(int posY) {
		// TODO Auto-generated method stub
		this.posY= posY;
	}

	public boolean isVertical() {
		return vertical;
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	
	public String getSourceString() {
		String source = "";
		if (isVertical())
			switch (type) {

			case 1:
				source ="greenpistchostrippedvertical.png";
				break;
			case 2:
				source ="hazelnutstrippedvertical.png";
				break;
			case 3:
				source ="coconut.Stripedvertical.png";
				break;
			case 4:
				source ="strippedredvertical.png";
				break;
			}

		if (isHorizontal())
			switch (type) {

			case 1:
				source ="greenpistchostrippedhorizontal.png";
				break;
			case 2:
				source ="hazelnutstrippedhorizontal.png";
				break;
			case 3:
				source ="coconut.Stripedhorizontal.png";
				break;
			case 4:
				source ="strippedredhorizontal.png";
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
		return false;
	}


	@Override
	public boolean isStrippedLokum() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isColorBombLokum() {
		// TODO Auto-generated method stub
		return false;
	}
}
