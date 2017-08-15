package eu.graphitez.utils.generic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import eu.graphitez.utils.generic.First;

/**
 * @author Janis Schöck - janis@schoeck-netz.de
 */
public class FirstTest {
	@Test
	public void test_firstOfEmpty() {
		Optional<Object> result = First.of(() -> null)
										.or((Integer) null)
										.opt();
		assertFalse(result.isPresent());
	}
	
	@Test
	public void test_firstOfActualFirst() {
		Optional<Integer> result = First.of(() -> new Integer(1))
										 .or(2)
										 .or(() -> new Integer(3))
										 .opt();
		assertTrue(result.isPresent());
		assertTrue(result.get() == 1);
	}
	
	@Test
	public void test_firstOfEmptyAndNonempty() {
		Optional<Integer> result = First.of(() -> (Integer) null)
										 .orElse(() -> new Integer(1));
		assertTrue(result.isPresent());
		assertTrue(result.get() == 1);
	}
	
	@Test
	public void test_firstOfMultipleAttempts() {
		Optional<Integer> result = First.of((Integer) null)
										 .or((Integer) null)
										 .or(() -> new Integer(1))
										 .or(() -> new Integer(2))
										 .opt();
		assertTrue(result.isPresent());
		assertTrue(result.get() == 1);
	}
	
	@Test
	public void test_firstOfObject() {
		Optional<Integer> result = First.of(1)
										 .or(() -> null)
										 .or(() -> new Integer(3))
										 .or(() -> new Integer(4))
										 .opt();
		assertTrue(result.isPresent());
		assertTrue(result.get() == 1);
	}
	
	@Test
	public void test_firstOfDefault() {
		Integer result = First.of(1)
							  .or(() -> null)
							  .or(() -> new Integer(3))
							  .orDef(() -> new Integer(4));
		assertTrue(result == 1);
	}
	
	@Test
	public void test_firstOfPractical() {
		Integer someValue = null;
		Integer otherValue = 1;
		/*
		 * Equivalent:
		 * 
		 * Integer result = 2;
		 * if (someValue != null) {
		 * 	 result = someValue;
		 * } else if (otherValue != null) {
		 * 	 result = otherValue;
		 * }
		 */
		Integer result = First.of(someValue).or(otherValue).orElse(2).get();
		assertTrue(result == 1);
	}
}
