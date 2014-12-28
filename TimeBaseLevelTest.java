import static org.junit.Assert.*;

import org.junit.Test;


public class TimeBaseLevelTest{
	
	
	@Test  (expected = NullPointerException.class)
	public void testNullTimeBaseLevel() {
		TimeBaseLevel a = null;
		assertTrue(!a.repOk());
	}
	
	
	@Test  
	public void testLessThanZeroCurrentLevel() {
			TimeBaseLevel a = new TimeBaseLevel();
			a.setCurrentLevel(-1);
			assertTrue(!a.repOk());
		}
	@Test 
	public void testZeroCurrentLevel() {
		TimeBaseLevel a = new TimeBaseLevel();
		a.setCurrentLevel(0);
		assertTrue(!a.repOk());
	}
	
	@Test 
	public void testBiggerThanFiveCurrentLevel() {
		TimeBaseLevel a = new TimeBaseLevel();
		a.setCurrentLevel(6);
		assertTrue(!a.repOk());
	}
	
	@Test 
	public void testNormalRangeCurrentLevel() {
		TimeBaseLevel a = new TimeBaseLevel();
		a.setCurrentLevel(4);
		assertTrue(a.repOk());
	}

	@Test 
	public void testLessThanZeroTimer() {
		TimeBaseLevel a = new TimeBaseLevel();
		a.setTimer(-4);
		assertTrue(!a.repOk());
	}
	
	@Test 
	public void testNormalTimer() {
		TimeBaseLevel a = new TimeBaseLevel();
		System.out.println("Timer is: "+a.getTimer());
		assertTrue(a.repOk());
	}
	
	@Test 
	public void testLessZeroTarget() {
		TimeBaseLevel a = new TimeBaseLevel();
		a.setTarget(-5);
		assertTrue(!a.repOk());
	}
	

	@Test 
	public void testZeroTarget() {
		TimeBaseLevel a = new TimeBaseLevel();
		a.setTarget(0);
		assertTrue(!a.repOk());
	}
	
	@Test 
	public void testNormalTarget() {
		TimeBaseLevel a = new TimeBaseLevel();
		System.out.println("Before, Target is: "+a.getTarget());
		a.setTarget(5);
		System.out.println("After, Target is: "+a.getTarget());
		assertTrue(a.repOk());
	}
	
	@Test  
	public void testLessThanZeroHighScore() {
			TimeBaseLevel a = new TimeBaseLevel();
			a.setHighScore(-10);
			assertTrue(!a.repOk());
	}
	
	@Test  
	public void testZeroHighScore() {
			TimeBaseLevel a = new TimeBaseLevel();
			a.setHighScore(0);
			assertTrue(a.repOk());
	}
}