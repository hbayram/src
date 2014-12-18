public abstract class Piece {
	
   public Piece() {
		

	}	
	
	public void setPosX(int posX) {}

	public void setPosY(int posY) {}
	
	public abstract int getType();
	
	public abstract void setType(int type);

	public abstract int getPosX();
	
	public abstract int getPosY();
	
	public abstract String getSourceString();

}
