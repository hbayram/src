public abstract class Piece {
	
   public Piece() {
		

	}	
	
	public void setPosX(int posX) {}

	public void setPosY(int posY) {}
	
	public abstract int getType();
	
	public abstract void setType(int type);

	public abstract int getPosX();
	
	public abstract int getPosY();
	public abstract boolean isBasicLokum();
	public abstract boolean isWrappedLokum ();
	public abstract boolean isStrippedLokum ();
	public abstract boolean isColorBomb ();
	public abstract boolean isTimerLokum();
	public abstract String getSourceString();

}
