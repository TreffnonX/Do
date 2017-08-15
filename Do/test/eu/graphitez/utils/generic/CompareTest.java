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
		assertTrue(Compare.elements(a, a));
		assertTrue(Compare.elements(b, b));
		assertTrue(Compare.elements(c, c));
		assertTrue(Compare.elements(a, b));
		assertTrue(Compare.elements(b, c));
		assertTrue(Compare.elements(a, c));
		
		// Arrays and Iterables
		assertTrue(Compare.elements(Arrays.asList(a), a));
		assertTrue(Compare.elements(b, Arrays.asList(b)));
		assertTrue(Compare.elements(Arrays.asList(c), c));
		assertTrue(Compare.elements(Arrays.asList(a), Arrays.asList(b)));
		assertTrue(Compare.elements(b, Arrays.asList(c)));
		assertTrue(Compare.elements(Arrays.asList(a), c));
	}
	
	@Test
	public void test_simpleUnequals() {
		A[] a = new A[]{ new A(1), new A(2), new A(3) };
		B[] b = new B[]{ new B(1), new B(3), new B(3) };
		C[] c = new C[]{ new C(1), new C(4), new C(3) };
		
		// Arrays
		assertFalse(Compare.elements(a, b));
		assertFalse(Compare.elements(b, c));
		assertFalse(Compare.elements(a, c));
		
		// Arrays and Iterables
		assertFalse(Compare.elements(Arrays.asList(a), Arrays.asList(b)));
		assertFalse(Compare.elements(b, Arrays.asList(c)));
		assertFalse(Compare.elements(Arrays.asList(a), c));
	}
	
	@Test
	public void test_advancedUnequals() {
		A[] a = new A[]{ new A(1), new A(2), new A(3) };
		B[] b = new B[]{ new B(1), new B(3) };
		C[] c = new C[]{ new C(1), new C(2) };
		
		// Arrays
		assertFalse(Compare.elements(a, b));
		assertFalse(Compare.elements(b, c));
		assertFalse(Compare.elements(a, c));
		
		// Arrays and Iterables
		assertFalse(Compare.elements(Arrays.asList(a), Arrays.asList(b)));
		assertFalse(Compare.elements(b, Arrays.asList(c)));
		assertFalse(Compare.elements(Arrays.asList(a), c));
	}
	
	@Test
	public void test_compareForced() {
		Object someObject = new Object();
		Object someOtherObject = new Object();
		Object[] a = { null, "tiff", 4, someObject };
		String[] b = { null, "test", "4" };
		Object[] c = { null, "test", "4" };
		Object[] d = { null, "test", 4, someObject };
		Object[] e = { null, "test", 4, someObject };
		Object[] f = { null, "test", 4, someOtherObject };
		
		// true
		assertTrue(Compare.elementsForced(b, c));
		assertTrue(Compare.elementsForced(d, e));
		
		// false
		assertFalse(Compare.elementsForced(a, b));
		assertFalse(Compare.elementsForced(b, d));
		assertFalse(Compare.elementsForced(a, e));
		assertFalse(Compare.elementsForced(a, c));
		assertFalse(Compare.elementsForced(d, f));
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
