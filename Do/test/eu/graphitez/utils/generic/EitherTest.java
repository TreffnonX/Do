package eu.graphitez.utils.generic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import eu.graphitez.utils.generic.Either;

/**
 * @author Janis Schöck - janis@schoeck-netz.de
 */
public class EitherTest {
	@Test
	public void test_eitherOfEmpty() {
		Optional<Object> result = Either.of(() -> null)
										.or((Integer) null)
										.run();
		assertFalse(result.isPresent());
	}
	
	@Test
	public void test_eitherOfFirst() {
		Optional<Integer> result = Either.of(() -> new Integer(1))
										 .or(2)
										 .or(() -> new Integer(3))
										 .run();
		assertTrue(result.isPresent());
		assertTrue(result.get() == 1);
	}
	
	@Test
	public void test_eitherOfEmptyAndNonempty() {
		Optional<Integer> result = Either.of(() -> (Integer) null)
										 .orElse(() -> new Integer(1));
		assertTrue(result.isPresent());
		assertTrue(result.get() == 1);
	}
	
	@Test
	public void test_eitherOfMultipleAttempts() {
		Optional<Integer> result = Either.of((Integer) null)
										 .or((Integer) null)
										 .or(() -> new Integer(1))
										 .or(() -> new Integer(2))
										 .run();
		assertTrue(result.isPresent());
		assertTrue(result.get() == 1);
	}
	
	@Test
	public void test_eitherOfObject() {
		Optional<Integer> result = Either.of(1)
										 .or(() -> null)
										 .or(() -> new Integer(3))
										 .or(() -> new Integer(4))
										 .run();
		assertTrue(result.isPresent());
		assertTrue(result.get() == 1);
	}
	
	@Test
	public void test_eitherOfPractical() {
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
		Integer result = Either.of(someValue).or(otherValue).orElse(2).get();
		assertTrue(result == 1);
	}
}
