import static org.junit.Assert.*;




import org.junit.Assert;

import org.junit.Before;

import org.junit.BeforeClass;

import org.junit.Test;


public class BoardTest {


	private static boolean combinationLeft = false;
	private static boolean seqLeft = false;
	private static boolean seqRight = false;
	private static boolean seqVer = false;
	private static boolean seqHor = false;
	private static boolean seqDown = false;
	private static boolean seqUp = false;
	private static Piece[][] sample = null;
	private static Board Board;
	private static boolean bool;
	private static boolean bool2;
	private int counts;
	private BasicLokum randomLokum;
	private Obstacle randomObstacle;

	/**

	 * @throws java.lang.Exception

	 */

	@BeforeClass

	public static void setUpBeforeClass() throws Exception {

		System.out.println("Before Test");

		// runs before each test
		Level level = new Level();
		Board = new Board(5,5,level);
		
       
        
	}

	/**

	 * @throws java.lang.Exception

	 */

	@Before

	public void setUp() throws Exception {

		

		//Board = new Board(10,20);
		 sample = Board.getArrayOfPieces();

	     BasicLokum temp = (BasicLokum) Board.getArrayOfPieces()[2][4];
	     bool = Board.isWithinBoard(temp);
	     BasicLokum temp2 = new BasicLokum(1);
	     temp2.setPosX(-1);
	     temp2.setPosY(0);
	     bool2 = Board.isWithinBoard(temp2);
	     System.out.println("After Test");

	     combinationLeft = Board.isAnyCombinationLeft();
	     seqRight = Board.checkSequenceRight(Board.getArrayOfPieces()[2][2]);
	     seqLeft = Board.checkSequenceLeft(Board.getArrayOfPieces()[2][2]);
	     seqUp = Board.checkSequenceUp(Board.getArrayOfPieces()[2][2]);
	     seqDown = Board.checkSequenceDown(Board.getArrayOfPieces()[2][2]);
	     seqVer = Board.checkSequenceVerticalMiddle(Board.getArrayOfPieces()[2][2]);
	     seqHor = Board.checkSequenceHorizontalMiddle(Board.getArrayOfPieces()[2][2]);
	     
	     Board.removeRange((Lokum) Board.getArrayOfPieces()[2][2], 1);
	     counts = Board.removedByUsingColorBomb(new BasicLokum(2), temp2);
	     Board.removeRow((Lokum) Board.getArrayOfPieces()[2][2]);
	     Board.removeColumn((Lokum) Board.getArrayOfPieces()[2][2]);
	     randomLokum = Board.createRandomLokum();
	     randomObstacle = Board.createRandomObstacle();
	}




	/**

	 * Test method for {@link Board#Board(int, int)}.

	 */

	@Test

	public void testBoard() {

		

		    assertNotNull(Board);

		    assertTrue(Board.getWidth() == 3);

		    assertTrue(Board.getHeight() == 5);

	}




	/**

	 * Test method for {@link Board#initBoard(Level)}.

	 */

	@Test

	public void testInitBoard() {

		//assertNotNull(Board);
		
		int w = Board.getWidth();

		int h = Board.getHeight();
		
		for(int i = 0; i < w; w++)

			for(int j = 0; j < h; h++)

				assertTrue(Board.getArrayOfPieces()[j][i] != null);
		
	}




	/**

	 * Test method for {@link Board#saveGameBoard()}.

	 */

	@Test

	public void testSaveGameBoard() {

		fail("Not yet implemented");

	}




	/**

	 * Test method for {@link Board#loadGameBoard()}.

	 */

	@Test

	public void testLoadGameBoard() {

		fail("Not yet implemented");

	}

	/**

	 * Test method for {@link Board#updateCrush()}.

	 */

	@Test

	public void testUpdateCrush() {
		sample[1][0]=null;
        sample[1][1]=null;
        sample[1][2]=null;
        
		int w = Board.getWidth();

		int h = Board.getHeight();
		
		for(int i = 0; i < w; w++)

			for(int j = 0; j < h; h++)

				assertTrue(Board.getArrayOfPieces()[j][i] != null);
		
		

	}




	/**

	 * Test method for {@link Board#replaceBoard()}.

	 */

	@Test

	public void testReplaceBoard() {

		assertNotNull(Board);

		assertArrayEquals(sample, Board.getArrayOfPieces());

	}




	/**

	 * Test method for {@link Board#randomlyFallDown()}.

	 */

	@Test

	public void testRandomlyFallDown() {
        Board.getArrayOfPieces()[0][0] = null;
        Board.getArrayOfPieces()[0][1] = null;
        Board.getArrayOfPieces()[0][2] = null;
		
		assertTrue(Board.getArrayOfPieces()[0][0] != null);
		assertTrue(Board.getArrayOfPieces()[0][2] != null);
		assertTrue(Board.getArrayOfPieces()[0][1] != null);

	}





	/**

	 * Test method for {@link Board#isWithinBoard(Piece)}.

	 */

	@Test

	public void testIsWithinBoard() {
		assertTrue(bool == true);
		assertTrue(bool2 = false);
	}




	/**

	 * Test method for {@link Board#isAnyCombinationLeft()}.

	 */

	@Test

	public void testIsAnyCombinationLeft() {
		Piece [][] test = new Piece[][]{
				{new BasicLokum(1),new BasicLokum(2),new BasicLokum(1),new BasicLokum(3),new BasicLokum(4)},
				{new BasicLokum(4),new BasicLokum(3),new BasicLokum(1),new BasicLokum(2),new BasicLokum(2)},
				{new BasicLokum(2),new BasicLokum(1),new BasicLokum(3),new BasicLokum(1),new BasicLokum(1)},
				{new BasicLokum(1),new BasicLokum(2),new BasicLokum(4),new BasicLokum(3),new BasicLokum(4)},
				{new BasicLokum(3),new BasicLokum(2),new BasicLokum(1),new BasicLokum(1),new BasicLokum(4)}
		};
		Board.setArrayOfPieces(test);
		assertTrue(combinationLeft == true);
	}




	/**

	 * Test method for {@link Board#checkSequenceDown(Piece)}.

	 */

	@Test

	public void testCheckSequenceDown() {

		assertTrue(seqDown == true);

	}




	/**

	 * Test method for {@link Board#checkSequenceUp(Piece)}.

	 */

	@Test

	public void testCheckSequenceUp() {

		assertTrue(seqUp == true);

	}




	/**

	 * Test method for {@link Board#checkSequenceRight(Piece)}.

	 */

	@Test

	public void testCheckSequenceRight() {

		assertTrue(seqRight == true);

	}




	/**

	 * Test method for {@link Board#checkSequenceLeft(Piece)}.

	 */

	@Test

	public void testCheckSequenceLeft() {

		assertTrue(seqLeft == true);

	}




	/**

	 * Test method for {@link Board#checkSequenceHorizontalMiddle(Piece)}.

	 */

	@Test

	public void testCheckSequenceHorizontalMiddle() {

		assertTrue(seqHor == true);

	}




	/**

	 * Test method for {@link Board#checkSequenceVerticalMiddle(Piece)}.

	 */

	@Test

	public void testCheckSequenceVerticalMiddle() {

		assertTrue(seqVer == true);

	}




	/**

	 * Test method for {@link Board#removeLeft(Lokum)}.

	 */

	@Test

	public void testRemove() {

		Board.getArrayOfPieces()[2][2] = new BasicLokum(1);
		Board.getArrayOfPieces()[2][0] = new BasicLokum(1);
		Board.getArrayOfPieces()[3][2] = new BasicLokum(1);
		for (int i=0; i<5; i++) {
			assertTrue(Board.getArrayOfPieces()[2][i] == null);
		}
		for (int i=0; i<5; i++) {
			assertTrue(Board.getArrayOfPieces()[i][2] == null);
		}

	}





	/**

	 * Test method for {@link Board#removeRange(Lokum, int)}.

	 */

	@Test

	public void testRemoveRange() {

		for (int i=1; i<4; i++) {
			for (int j=1; j<4; j++) {
				assertTrue(Board.getArrayOfPieces()[j][i] == null);
			}
		}

	}




	/**

	 * Test method for {@link Board#removedByUsingColorBomb(Lokum)}.

	 */

	@Test

	public void testRemovedByUsingColorBomb() {
		int counter = 0;
		for (int i=0; i<5; i++) {
			for (int j=0; j<5; j++) {
				if (Board.getArrayOfPieces()[j][i].getType() == 2) {
					counter++;
					assertTrue (Board.getArrayOfPieces()[j][i] == null);
				}
			}
		}
		assertTrue(counter == counts);

	}




	/**

	 * Test method for {@link Board#removeRow(Lokum)}.

	 */

	@Test

	public void testRemoveRow() {
		for (int i=0; i<5; i++) {
			assertTrue(Board.getArrayOfPieces()[2][i] == null);
		}		

	}




	/**

	 * Test method for {@link Board#removeColumn(Lokum)}.

	 */

	@Test

	public void testRemoveColumn() {

		for (int i=0; i<5; i++) {
			assertTrue(Board.getArrayOfPieces()[2][i] == null);
		}

	}




	/**

	 * Test method for {@link Board#clear()}.

	 * @throws Exception 

	 */

	@Test

	public void testClear() throws Exception {

		Board.clear();

		int w = Board.getWidth();

		int h = Board.getHeight();

		for(int i = 0; i < w; w++)

			for(int j = 0; j < h; h++)

				assertTrue(Board.getArrayOfPieces()[j][i] == null);

	}




	/**

	 * Test method for {@link Board#createRandomLokum()}.

	 */

	@Test

	public void testCreateRandomLokum() {

		assertNotNull(randomLokum);
		assertTrue(randomLokum == Board.createRandomLokum());
	}




	/**

	 * Test method for {@link Board#createRandomObstacle()}.

	 */

	@Test

	public void testCreateRandomObstacle() {

		assertNotNull(randomObstacle);
		assertTrue(randomObstacle == Board.createRandomObstacle());
	}

	




}