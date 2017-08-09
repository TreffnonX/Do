package eu.graphitez.utils.generic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import eu.graphitez.utils.generic.Compare;

/**
 * @author Janis Schöck - janis@schoeck-netz.de
 */
public class CompareTest {
	@Test
	public void test_equals() {
		A[] a = new A[]{ new A(1), new A(2), new A(3) };
		B[] b = new B[]{ new B(1), new B(2), new B(3) };
		C[] c = new C[]{ new C(1), new C(2), new C(3) };
		
		// Arrays
		assertTrue(Compare.byEquals(a, a));
		assertTrue(Compare.byEquals(b, b));
		assertTrue(Compare.byEquals(c, c));
		assertTrue(Compare.byEquals(a, b));
		assertTrue(Compare.byEquals(b, c));
		assertTrue(Compare.byEquals(a, c));
		
		// Arrays and Iterables
		assertTrue(Compare.byEquals(Arrays.asList(a), a));
		assertTrue(Compare.byEquals(b, Arrays.asList(b)));
		assertTrue(Compare.byEquals(Arrays.asList(c), c));
		assertTrue(Compare.byEquals(Arrays.asList(a), Arrays.asList(b)));
		assertTrue(Compare.byEquals(b, Arrays.asList(c)));
		assertTrue(Compare.byEquals(Arrays.asList(a), c));
	}
	
	@Test
	public void test_simpleUnequals() {
		A[] a = new A[]{ new A(1), new A(2), new A(3) };
		B[] b = new B[]{ new B(1), new B(3), new B(3) };
		C[] c = new C[]{ new C(1), new C(4), new C(3) };
		
		// Arrays
		assertFalse(Compare.byEquals(a, b));
		assertFalse(Compare.byEquals(b, c));
		assertFalse(Compare.byEquals(a, c));
		
		// Arrays and Iterables
		assertFalse(Compare.byEquals(Arrays.asList(a), Arrays.asList(b)));
		assertFalse(Compare.byEquals(b, Arrays.asList(c)));
		assertFalse(Compare.byEquals(Arrays.asList(a), c));
	}
	
	@Test
	public void test_advancedUnequals() {
		A[] a = new A[]{ new A(1), new A(2), new A(3) };
		B[] b = new B[]{ new B(1), new B(3) };
		C[] c = new C[]{ new C(1), new C(2) };
		
		// Arrays
		assertFalse(Compare.byEquals(a, b));
		assertFalse(Compare.byEquals(b, c));
		assertFalse(Compare.byEquals(a, c));
		
		// Arrays and Iterables
		assertFalse(Compare.byEquals(Arrays.asList(a), Arrays.asList(b)));
		assertFalse(Compare.byEquals(b, Arrays.asList(c)));
		assertFalse(Compare.byEquals(Arrays.asList(a), c));
	}
	
	public static class A implements Comparable<A> {
		private Integer m_val = 0;
		
		public A(Integer val) {
			m_val = val;
		}
		
		@Override
		public int compareTo(A o) {
			return this.m_val.compareTo(o.m_val);
		}
	}
	
	public static class B extends A {
		public B(Integer val) {
			super(val);
		}
	}
	
	public static class C extends A {
		public C(Integer val) {
			super(val);
		}
	}
}
