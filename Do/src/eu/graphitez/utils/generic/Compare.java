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

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Janis Schöck - janis@schoeck-netz.de
 */
public class Compare {
	/**
	 * Sequentially compares the elements within two Iterables with each other by applying
	 * {@link Comparable#equals(Object)}.
	 * Nullary objects are also considered elements and are not filtered.
	 * 
	 * @param left Left Iterable.
	 * @param right Right Iterable.
	 * @return True, if the elements of the iterables strictly equal each other pairwise and in sequential order.
	 */
	public static <T extends Comparable<T>> boolean byEquals(Iterable<? extends T> left, Iterable<? extends T> right) {
 		Iterator<? extends T> iLeft = left.iterator();
		Iterator<? extends T> iRight = right.iterator();
		
		// pairwise comparison.
		while (iLeft.hasNext() && iRight.hasNext()) {
			T leftElement = iLeft.next();
			T rightElement = iRight.next();
			
			// null check.
			if (leftElement == null) {
				if (rightElement != null) {
					return false;
				}
				continue;
			} else if (rightElement == null) {
				return false;
			}
			
			// comparison.
			if (leftElement.compareTo(rightElement) != 0) {
				return false;
			}
		}
		
		if (iLeft.hasNext() || iRight.hasNext()) {
			return false;
		}
		return true;
	}
	
	/**
	 * Sequentially compares the elements within two Iterables or Arrays with each other by applying
	 * {@link Comparable#equals(Object)}.
	 * Nullary objects are also considered elements and are not filtered.
	 * 
	 * @param left Left array.
	 * @param right Right Iterable.
	 * @return True, if the elements of the iterables strictly equal each other pairwise and in sequential order.
	 */
	public static <T extends Comparable<T>, E extends T> boolean byEquals(E[] left, Iterable<? extends T> right) {
		return Compare.byEquals(Arrays.asList(left), right);
	}
	
	/**
	 * Sequentially compares the elements within two Iterables or Arrays with each other by applying
	 * {@link Comparable#equals(Object)}.
	 * Nullary objects are also considered elements and are not filtered.
	 * 
	 * @param left Left array.
	 * @param right Right Iterable.
	 * @return True, if the elements of the iterables strictly equal each other pairwise and in sequential order.
	 */
	public static <T extends Comparable<T>, E extends T> boolean byEquals(Iterable<? extends T> left, E[] right) {
		return Compare.byEquals(left, Arrays.asList(right));
	}
	
	/**
	 * Sequentially compares the elements within two Iterables or Arrays with each other by applying
	 * {@link Comparable#equals(Object)}.
	 * Nullary objects are also considered elements and are not filtered.
	 * 
	 * @param left Left array.
	 * @param right Right Iterable.
	 * @return True, if the elements of the iterables strictly equal each other pairwise and in sequential order.
	 */
	public static <T extends Comparable<T>, E extends T, F extends T> boolean byEquals(E[] left, F[] right) {
		return Compare.byEquals(Arrays.asList(left), Arrays.asList(right));
	}
	
	/**
	 * Sequentially compares the elements within two Iterables with each other. Nullary objects are also considered
	 * elements and are not filtered.
	 * 
	 * @param left Left Iterable.
	 * @param right Right Iterable.
	 * @return True, if the elements of the iterables strictly equal each other pairwise and in sequential order.
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
