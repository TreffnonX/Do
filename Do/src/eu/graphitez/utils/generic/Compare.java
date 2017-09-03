/**
 * MIT License
 *
 * Copyright (c) 2017 TreffnonX (Janis Schöck)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eu.graphitez.utils.generic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Janis Schöck - janis@schoeck-netz.de
 */
public class Compare {
	/**
	 * Sequentially compares the elements within two Iterables with each other
	 * by applying {@link Comparable#equals(Object)}. Nullary objects are also
	 * considered elements and are not filtered.
	 * 
	 * @param left
	 *            Left Iterable.
	 * @param right
	 *            Right Iterable.
	 * @return True, if the elements of the iterables strictly equal each other
	 *         pairwise and in sequential order.
	 */
	public static <T extends Comparable<T>> boolean elements(Iterable<? extends T> left, Iterable<? extends T> right) {
		Iterator<? extends T> iLeft = left.iterator();
		Iterator<? extends T> iRight = right.iterator();

		// pairwise comparison.
		while (iLeft.hasNext() && iRight.hasNext()) {
			T leftElement = iLeft.next();
			T rightElement = iRight.next();

			if (!Compare.comparables(leftElement, rightElement)) {
				return false;
			}
		}

		if (iLeft.hasNext() || iRight.hasNext()) {
			return false;
		}
		return true;
	}

	/**
	 * Sequentially compares the elements within two Iterables or Arrays with
	 * each other by applying {@link Comparable#equals(Object)}. Nullary objects
	 * are also considered elements and are not filtered.
	 * 
	 * @param left
	 *            Left array.
	 * @param right
	 *            Right Iterable.
	 * @return True, if the elements of the iterables strictly equal each other
	 *         pairwise and in sequential order.
	 */
	public static <T extends Comparable<T>, E extends T> boolean elements(E[] left, Iterable<? extends T> right) {
		return Compare.elements(Arrays.asList(left), right);
	}

	/**
	 * Sequentially compares the elements within two Iterables or Arrays with
	 * each other by applying {@link Comparable#equals(Object)}. Nullary objects
	 * are also considered elements and are not filtered.
	 * 
	 * @param left
	 *            Left array.
	 * @param right
	 *            Right Iterable.
	 * @return True, if the elements of the iterables strictly equal each other
	 *         pairwise and in sequential order.
	 */
	public static <T extends Comparable<T>, E extends T> boolean elements(Iterable<? extends T> left, E[] right) {
		return Compare.elements(left, Arrays.asList(right));
	}

	/**
	 * Sequentially compares the elements within two Iterables or Arrays with
	 * each other by applying {@link Comparable#equals(Object)}. Nullary objects
	 * are also considered elements and are not filtered.
	 * 
	 * @param left
	 *            Left array.
	 * @param right
	 *            Right Iterable.
	 * @return True, if the elements of the iterables strictly equal each other
	 *         pairwise and in sequential order.
	 */
	public static <T extends Comparable<T>, E extends T, F extends T> boolean elements(E[] left, F[] right) {
		return Compare.elements(Arrays.asList(left), Arrays.asList(right));
	}

	/**
	 * Sequentially compares the elements within two Iterables or Arrays, even
	 * though the element types might not be strictly comparable. <br>
	 * <br>
	 * Two elements are considered equal by the rules of this method, if one of
	 * the following is true:
	 * <ol>
	 * <li>both elements are <code>null</code></li>
	 * <li>the elements are of the same class, comparable, and compareTo returns
	 * <code>true</code>.</li>
	 * <li>the two elements are strictly the same (a == b).</li>
	 * </ol>
	 * If two elements are compared which are not equal by the above definition,
	 * or if the two iterables do not have the same length, this method returns
	 * <code>false</code>.
	 * 
	 * @param left
	 *            Left {@link Iterable}.
	 * @param right
	 *            Right {@link Iterable}.
	 * @return True, if the objects where a pairwise match.
	 */
	public static boolean elementsForced(Iterable<?> left, Iterable<?> right) {
		Iterator<?> iLeft = left.iterator();
		Iterator<?> iRight = right.iterator();

		// pairwise comparison.
		while (iLeft.hasNext() && iRight.hasNext()) {
			Object leftElement = iLeft.next();
			Object rightElement = iRight.next();
			if (leftElement instanceof Comparable<?> && rightElement instanceof Comparable<?>) {
				if (!asMatchingSuperclass(leftElement, rightElement)) {
					return false;
				}
			} else if (leftElement != rightElement) {
				return false;
			}
		}

		if (iLeft.hasNext() || iRight.hasNext()) {
			return false;
		}
		return true;
	}

	/**
	 * Sequentially compares the elements within two Iterables or Arrays, even
	 * though the element types might not be strictly comparable. <br>
	 * <br>
	 * Two elements are considered equal by the rules of this method, if one of
	 * the following is true:
	 * <ol>
	 * <li>both elements are <code>null</code></li>
	 * <li>the elements are of the same class, comparable, and compareTo returns
	 * <code>true</code>.</li>
	 * <li>the two elements are strictly the same (a == b).</li>
	 * </ol>
	 * If two elements are compared which are not equal by the above definition,
	 * or if the two iterables do not have the same length, this method returns
	 * <code>false</code>.
	 * 
	 * @param left
	 *            Left array.
	 * @param right
	 *            Right Iterable.
	 * @return True, if the elements of the iterables strictly equal each other
	 *         pairwise and in sequential order.
	 */
	public static <L, R> boolean elementsForced(L[] left, Iterable<R> right) {
		return Compare.elementsForced(Arrays.asList(left), right);
	}

	/**
	 * Sequentially compares the elements within two Iterables or Arrays, even
	 * though the element types might not be strictly comparable. <br>
	 * <br>
	 * Two elements are considered equal by the rules of this method, if one of
	 * the following is true:
	 * <ol>
	 * <li>both elements are <code>null</code></li>
	 * <li>the elements are of the same class, comparable, and compareTo returns
	 * <code>true</code>.</li>
	 * <li>the two elements are strictly the same (a == b).</li>
	 * </ol>
	 * If two elements are compared which are not equal by the above definition,
	 * or if the two iterables do not have the same length, this method returns
	 * <code>false</code>.
	 * 
	 * @param left
	 *            Left array.
	 * @param right
	 *            Right Iterable.
	 * @return True, if the elements of the iterables strictly equal each other
	 *         pairwise and in sequential order.
	 */
	public static <L, R> boolean elementsForced(Iterable<L> left, R[] right) {
		return Compare.elementsForced(left, Arrays.asList(right));
	}

	/**
	 * Sequentially compares the elements within two Iterables or Arrays, even
	 * though the element types might not be strictly comparable. <br>
	 * <br>
	 * Two elements are considered equal by the rules of this method, if one of
	 * the following is true:
	 * <ol>
	 * <li>both elements are <code>null</code></li>
	 * <li>the elements are of the same class, comparable, and compareTo returns
	 * <code>true</code>.</li>
	 * <li>the two elements are strictly the same (a == b).</li>
	 * </ol>
	 * If two elements are compared which are not equal by the above definition,
	 * or if the two iterables do not have the same length, this method returns
	 * <code>false</code>.
	 * 
	 * @param left
	 *            Left array.
	 * @param right
	 *            Right Iterable.
	 * @return True, if the elements of the iterables strictly equal each other
	 *         pairwise and in sequential order.
	 */
	public static <L, R> boolean elementsForced(L[] left, R[] right) {
		return Compare.elementsForced(Arrays.asList(left), Arrays.asList(right));
	}

	/**
	 * Forces two objects to be compared as if they where comparable. If they
	 * are not comparable or do not match, <code>false</code> is returned. If
	 * both objects are <code>null</code>, <code>true</code> is returned,
	 * however any other constellation involving <code>null</code> returns
	 * false.
	 * 
	 * @param left
	 *            Left object.
	 * @param right
	 *            Right object.
	 * @return True, if the objects are comparable, and where a match.
	 */
	public static boolean asMatchingSuperclass(Object left, Object right) {
		// trivial check.
		if (left == right) {
			return true;
		}
		// null checks.
		if (left == null || right == null) {
			return false;
		}
		Class<? extends Object> leftClass = left.getClass();
		Class<? extends Object> rightClass = right.getClass();
		if (leftClass.equals(rightClass) && Comparable.class.isAssignableFrom(leftClass)) {
			try {
				Method compareTo = leftClass.getMethod("compareTo", Object.class);
				return ((Integer) compareTo.invoke(left, right)) == 0;
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				return false;
			}
		}
		return false;
	}

	/**
	 * Compares to {@link Comparable}s with each other.
	 * 
	 * @param left
	 *            Left {@link Comparable}.
	 * @param right
	 *            Right {@link Comparable}.
	 * @return True, if the {@link Comparable#compareTo(?)} returned 0, or if
	 *         both objects where null.
	 */
	public static <T extends Comparable<T>> boolean comparables(T left, T right) {
		// trivial check.
		if (left == right) {
			return true;
		}
		// null checks.
		if (left == null || right == null) {
			return false;
		}

		// comparison.
		if (left.compareTo(right) != 0) {
			return false;
		}
		return true;
	}

	/**
	 * Sequentially compares the elements within two Iterables with each other.
	 * Nullary objects are also considered elements and are not filtered.
	 * 
	 * @param left
	 *            Left Iterable.
	 * @param right
	 *            Right Iterable.
	 * @return True, if the elements of the iterables strictly equal each other
	 *         pairwise and in sequential order.
	 */
	public static <T extends Comparable<T>> boolean exactly(Iterable<? extends T> left, Iterable<? extends T> right) {
		Iterator<? extends T> iLeft = left.iterator();
		Iterator<? extends T> iRight = right.iterator();

		// pairwise comparison.
		while (iLeft.hasNext() && iRight.hasNext()) {
			T leftElement = iLeft.next();
			T rightElement = iRight.next();

			if (leftElement != rightElement) {
				return false;
			}
		}

		if (iLeft.hasNext() || iRight.hasNext()) {
			return false;
		}
		return true;
	}
}
