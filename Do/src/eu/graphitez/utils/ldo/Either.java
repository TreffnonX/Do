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
package eu.graphitez.utils.ldo;

import java.util.LinkedList;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Allows for a cascade of Executables to be run or objects to be null-checked until a non-empty
 * Optional can be returned, or the end of the chain is reached.
 * 
 * @author Janis Schöck - janis@schoeck-netz.de
 */
public class Either<T> implements Preparable<T> {
	/**
	 * Chain of executables run after one another, until a non-empty Optional is returned.
	 */
	protected LinkedList<OptionalProvider<T>> m_options = new LinkedList<>();
	
	/**
	 * Constructors.
	 */
	protected Either(Supplier<T> supplier) {
		m_options.add(OptionalProvider.of(supplier));
	}
	protected Either(Runnable runnable) {
		m_options.add(OptionalProvider.of(runnable));
	}
	protected Either(T object) {
		m_options.add(OptionalProvider.of(object));
	}
	
	/**
	 * Starts a cascade of Executables which return the first non-empty Optional found,
	 * or null, if the cascade ends.<br>
	 * Usage:<br>
	 * Either.of(supplier/runnable/object).or(supplier/runnable/object)...run();<br>
	 * The first call returning a non-null {@link Optional} will terminate the cascade and be returned.
	 * 
	 * @param supplier Supplier executed first.
	 * @return This Either-Object.
	 */
	public static <T> Either<T> of(Supplier<T> supplier) {
		return new Either<T>(supplier);
	}
	
	/**
	 * Starts a cascade of Executables which return the first non-empty Optional found,
	 * or null, if the cascade ends.<br>
	 * Usage:<br>
	 * Either.of(supplier/runnable/object).or(supplier/runnable/object)...run();<br>
	 * The first call returning a non-null {@link Optional} will terminate the cascade and be returned.
	 * 
	 * @param supplier Supplier executed first.
	 * @return This Either-Object.
	 */
	public static <T> Either<T> of(Runnable runnable) {
		return new Either<T>(runnable);
	}
	
	/**
	 * Starts a cascade of Executables which return the first non-empty Optional found,
	 * or null, if the cascade ends.<br>
	 * Usage:<br>
	 * Either.of(supplier/runnable/object).or(supplier/runnable/object)...run();<br>
	 * The first call returning a non-null {@link Optional} will terminate the cascade and be returned.
	 * 
	 * @param object Object checked first.
	 * @return This Either-Object.
	 */
	public static <T> Either<T> of(T object) {
		return new Either<T>(object);
	}
	
	/**
	 * Appends an executable to the end of the cascade.<br>
	 * Usage:<br>
	 * Either.of(supplier/runnable/object).or(supplier/runnable/object)...run();<br>
	 * The first call returning a non-null {@link Optional} will terminate the cascade and be returned.
	 * 
	 * @param supplier Supplier executed next.
	 * @return This Either-Object.
	 */
	public Either<T> or(Supplier<T> supplier) {
		m_options.add(OptionalProvider.of(supplier));
		return this;
	}
	
	/**
	 * Appends an executable to the end of the cascade.<br>
	 * Usage:<br>
	 * Either.of(supplier/runnable/object).or(supplier/runnable/object)...run();<br>
	 * The first call returning a non-null {@link Optional} will terminate the cascade and be returned.
	 * 
	 * @param runnable Runnable executed next.
	 * @return This Either-Object.
	 */
	public Either<T> or(Runnable runnable) {
		m_options.add(OptionalProvider.of(runnable));
		return this;
	}
	
	/**
	 * Appends an object as option to the end of the cascade.<br>
	 * Usage:<br>
	 * Either.of(supplier/runnable/object).or(supplier/runnable/object)...run();<br>
	 * The first call returning a non-null {@link Optional} will terminate the cascade and be returned.
	 * 
	 * @param object Object checked first.
	 * @return This Either-Object.
	 */
	public Either<T> or(T object) {
		m_options.add(OptionalProvider.of(object));
		return this;
	}
	
	/**
	 * Appends an executable to the end of the cascade and starts it.<br>
	 * Usage:<br>
	 * Either.of(supplier/runnable/object).or(supplier/runnable/object)...run();<br>
	 * The first call returning a non-null {@link Optional} will terminate the cascade and be returned.
	 * 
	 * @param supplier Supplier executed next.
	 * @return Optional containing the first non-null object returned by the cascade,
	 * 		or an empty {@link Optional}, if only null was encountered.
	 */
	public Optional<T> orElse(Supplier<T> supplier) {
		return or(supplier).run();
	}
	
	/**
	 * Appends an executable to the end of the cascade and starts it.<br>
	 * Usage:<br>
	 * Either.of(supplier/runnable/object).or(supplier/runnable/object)...run();<br>
	 * The first call returning a non-null {@link Optional} will terminate the cascade and be returned.
	 * 
	 * @param runnable Runnable executed next.
	 * @return Optional containing the first non-null object returned by the cascade,
	 * 		or an empty {@link Optional}, if only null was encountered.
	 */
	public Optional<T> orElse(Runnable runnable) {
		return or(runnable).run();
	}
	
	/**
	 * Appends an object as option to the end of the cascade and starts it.<br>
	 * Usage:<br>
	 * Either.of(supplier/runnable/object).or(supplier/runnable/object)...run();<br>
	 * The first call returning a non-null {@link Optional} will terminate the cascade and be returned.
	 * 
	 * @param object Object checked first.
	 * @return Optional containing the first non-null object returned by the cascade,
	 * 		or an empty {@link Optional}, if only null was encountered.
	 */
	public Optional<T> orElse(T object) {
		return or(object).run();
	}

	/**
	 * Starts the execution of the cascade, and returns the first non-empty {@link Optional}, or an empty Optional,
	 * if no non-empty Optionals where found.
	 * 
	 * @return First non-empty Optional encountered, or an empty Optional, if no non-empty Optional was found.
	 */
	@Override
	public Optional<T> run() {
		for (OptionalProvider<T> executable : m_options) {
			Optional<T> optional = executable.run();
			if (optional.isPresent()) {
				return optional;
			}
		}
		return Optional.empty();
	}
}
