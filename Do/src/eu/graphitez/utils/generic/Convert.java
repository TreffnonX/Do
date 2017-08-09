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

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Janis Schöck - janis@schoeck-netz.de
 */
public class Convert {
	/**
	 * Converts a {@link Stream} of elements to a List of R where R is the return type of 
	 * parameter transformer.
	 * 
	 * @param stream Stream, for which to convert contained elements.
	 * @param transformer Function transforming streamed objects to R.
	 * @param <E> Stream element type.
	 * @param <R> return {@link List} element type.
	 * @return {@link List} of R elements.
	 */
	public static <E, R> List<R> map(Stream<E> stream, Function<? super E, R> transformer) {
		return stream.map(transformer).collect(Collectors.toList());
	}
	
	/**
	 * Converts a {@link Collection} of elements to a List of R where R is the return type of 
	 * parameter transformer.
	 * 
	 * @param collection {@link Collection}, for which to convert contained elements.
	 * @param transformer Function transforming streamed objects to R.
	 * @param <E> {@link Collection} element type.
	 * @param <R> return List element type.
	 * @return List of R elements.
	 */
	public static <E, R> List<R> map(Collection<E> collection, Function<? super E, R> transformer) {
		return map(collection.stream(), transformer);
	}
	
	/**
	 * Converts an Array of E elements to a List of R where R is the return type of 
	 * parameter transformer.
	 * 
	 * @param array Array of E, for which to convert contained elements.
	 * @param transformer Function transforming streamed objects to R.
	 * @param <E> Array element type.
	 * @param <R> return List element type.
	 * @return List of R elements.
	 */
	public static <E, R> List<R> map(E[] array, Function<? super E, R> transformer) {
		return map(Stream.of(array), transformer);
	}
	
	/**
	 * Converts a {@link Stream} of elements to a List of R where R is the return type of 
	 * parameter transformer, filtering any <code>null</code> objects before and after the
	 * mapping.
	 * 
	 * @param stream Stream, for which to convert contained elements.
	 * @param transformer Function transforming streamed objects to R.
	 * @param <E> Stream element type.
	 * @param <R> return {@link List} element type.
	 * @return {@link List} of R elements.
	 */
	public static <E, R> List<R> inner(Stream<E> stream, Function<? super E, R> transformer) {
		return stream.filter(Null::not)
					 .map(transformer)
					 .filter(Null::not)
					 .collect(Collectors.toList());
	}
	
	/**
	 * Converts a {@link Collection} of elements to a List of R where R is the return type of 
	 * parameter transformer, filtering any <code>null</code> objects before and after the
	 * mapping.
	 * 
	 * @param collection {@link Collection}, for which to convert contained elements.
	 * @param transformer Function transforming streamed objects to R.
	 * @param <E> {@link Collection} element type.
	 * @param <R> return List element type.
	 * @return List of R elements.
	 */
	public static <E, R> List<R> inner(Collection<E> collection, Function<? super E, R> transformer) {
		return inner(collection.stream(), transformer);
	}
	
	/**
	 * Converts an Array of E elements to a List of R where R is the return type of 
	 * parameter transformer, filtering any <code>null</code> objects before and after the
	 * mapping.
	 * 
	 * @param array Array of E, for which to convert contained elements.
	 * @param transformer Function transforming streamed objects to R.
	 * @param <E> Array element type.
	 * @param <R> return List element type.
	 * @return List of R elements.
	 */
	public static <E, R> List<R> inner(E[] array, Function<? super E, R> transformer) {
		return inner(Stream.of(array), transformer);
	}
}
