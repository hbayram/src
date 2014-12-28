public class Score {
	private Score() {
		
	}
	public static int basicCalculation(){
		return 60;
	}
	
	public static int strippedCombinationCalculation(){
		return 120;
	}
	
	public static int wrappedCombinationCalculation(){
		return 200;
	}
	
	public static int colorBombCombinationCalculation(){
		return 200;
	}
	
	public static int usingStrippedCalculation(int n){
		return n * 60;
	}
	
	public static int usingWrappedCalculation(){
		return 1080;
	}
	
	public static int usingColorBombCalculation(int n){
		return n * n * 60;
	}
	
	public static int usingDoubleStrippedCalculation(int n){
		return 2 * usingStrippedCalculation(n);
	}
	
	public static int usingWrappedAndStrippedCalculation(int n){
		return 6 * usingStrippedCalculation(n);
	}
	
	public static int usingDoubleWrappedCalculation(){
		return 3600;
	}
	
	public static int usingStrippedColorBombCalculation(int n) {
		return usingStrippedCalculation(n) * n;
	}
	
	public static int usingWrappedColorBombCalculation(int n) {
		return usingColorBombCalculation(n) * 2;
	}
	
	public static int usingDoubleColorBombCalculation(int n) {
		return n * n * 100; //n is the entire lokums in the board
	}
	
	
}