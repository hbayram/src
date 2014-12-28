import static org.junit.Assert.*;

import org.junit.Test;

public class TimerLokumTest {
	
	
	
	@Test (expected = NullPointerException.class)
	public void testNullTimerLokum() {
		TimerLokum lok = null;
		assertTrue(!lok.repOk());
	}
	
	@Test
	public void testZeroTypeTimerLokum() {
		TimerLokum lok = new TimerLokum(0);
		lok.setSource("abdc");
		System.out.println("testing zero type: "+lok.getType()+" "+lok.getSourceString());
		assertTrue(!lok.repOk());
	}
	
	@Test
	public void testBiggerThan4TypeTimerLokum() {
		TimerLokum lok = new TimerLokum(5);
		lok.setSource("abdc");
		System.out.println("testing bigger than 4 type: "+lok.getType()+" "+lok.getSourceString());
		assertTrue(!lok.repOk());
	}
	
	@Test
	public void testNormalTypeTimerLokum() {
		TimerLokum lok = new TimerLokum(3);
		System.out.println("testing normal type:"+lok.toString());
		assertTrue(lok.repOk());
	}
	
	@Test
	public void testZeroCoordinatesTimerLokum() {
		TimerLokum lok = new TimerLokum(1);
		assertTrue("Zero coordinates timer lokum repok is true", lok.repOk());
	}
	
	@Test
	public void testNegativeXCoordinateTimerLokum() {
		TimerLokum lok = new TimerLokum(2);
		lok.setPosX(-1);
		assertTrue(!lok.repOk());
	}
	
	@Test
	public void testNegativeYCoordinateTimerLokum() {
		TimerLokum lok = new TimerLokum(2);
		lok.setPosY(-1);
		assertTrue(!lok.repOk());
	}
	
	@Test
	public void testZeroHeightTimerLokum() {
		TimerLokum lok = new TimerLokum(1);
		lok.setLokumHeight(0);
		assertTrue(!lok.repOk());
	}
	
	@Test
	public void testZeroWidthTimerLokum() {
		TimerLokum lok = new TimerLokum(1);
		lok.setLokumWidth(0);
		assertTrue(!lok.repOk());
	}
	
	@Test
	public void testNegativeHeightTimerLokum() {
		TimerLokum lok = new TimerLokum(2);
		lok.setLokumHeight(-1);
		assertTrue(!lok.repOk());
	}
	
	@Test
	public void testNegativeWidthTimerLokum() {
		TimerLokum lok = new TimerLokum(2);
		lok.setLokumWidth(-1);
		assertTrue(!lok.repOk());
	}
	
	@Test
	public void testNormalTimerLokum() {
		TimerLokum lok = new TimerLokum(2);
		lok.setPosX(5);
		lok.setPosY(5);
		assertTrue("normal ball repok is true", lok.repOk());
	}
	
	@Test
	public void testNullSourceTimerLokum() {
		TimerLokum lok = new TimerLokum(-1);
		System.out.println(lok.getSourceString());
		//lok.setType(2);
		assertTrue(!lok.repOk());
	}
	
	@Test
	public void testCloneTimerLokum() {
		TimerLokum lok = new TimerLokum(2);
		TimerLokum cloneLok = new TimerLokum(2);
		TimerLokum otherLok = new TimerLokum(3);
		assertTrue("lok equals its clone called cloneLok.",
				lok.equals(cloneLok));
		assertTrue("lok does not equal otherLok.",
				!lok.equals(otherLok));
		assertTrue("otherLok does not equal lok.",
				!otherLok.equals(lok));
	}

}
