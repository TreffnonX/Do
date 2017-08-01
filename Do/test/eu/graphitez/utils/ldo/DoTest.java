package eu.graphitez.utils.ldo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import eu.graphitez.utils.ldo.Do;

/**
 * @author Janis Schöck - janis@schoeck-netz.de
 */
public class DoTest {
	@SuppressWarnings("unused")
	@Test
	public void test_asVoid() {
		// This method actually is a compiler test and needs no execution to prove validity.
		
		// An expression can return some value object, even though the lambda expression should infer void.
		// hence this call serves as "cast to void".
		Do.asVoid(true ? /* any expression returning anything */null : /* another expression */null);
		
		// The statement behind the "&&" is only executed after the left side has been checked. This allows for
		// a more convenient if-expression instead of an if-statement.
		Do.asVoid(true && Do.asTrue(/* any expression returning anything */null));
	}
	
	@Test
	public void test_onTrue() {
		VBoolean executed = new VBoolean();
		// Is the runnable executed?
		Do.on(true, () -> executed.m_value = true);
		assertTrue(executed.m_value);
	}
	
	@Test
	public void test_onFalse() {
		VBoolean executed = new VBoolean();
		// Is the runnable executed?
		Do.on(false, () -> executed.m_value = true);
		assertFalse(executed.m_value);
	}
	
	@Test
	public void test_onThen() {
		VBoolean then = new VBoolean();
		VBoolean elze = new VBoolean();
		// Is the correct runnable executed?
		Do.on(true, () -> then.m_value = true, () -> elze.m_value = true);
		assertTrue(then.m_value);
		assertFalse(elze.m_value);
	}
	
	@Test
	public void test_onElse() {
		VBoolean then = new VBoolean();
		VBoolean elze = new VBoolean();
		// Is the correct runnable executed?
		Do.on(false, () -> then.m_value = true, () -> elze.m_value = true);
		assertFalse(then.m_value);
		assertTrue(elze.m_value);
	}
	
	public static class VBoolean {
		public boolean m_value = false;
	}
}
