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

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Provides an Optional based on an Object, a Supplier or just runs an Executable.
 * 
 * @author Janis Schöck - janis@schoeck-netz.de
 */
public class OptionalProvider<T> implements Preparable<T> {
	protected Runnable m_runnable;
	protected Supplier<T> m_supplier;
	protected T m_object;
	
	protected OptionalProvider(Runnable runnable) {
		m_runnable = runnable;
	}
	
	protected OptionalProvider(Supplier<T> supplier) {
		m_supplier = supplier;
	}
	
	protected OptionalProvider(T object) {
		m_object = object;
	}
	
	public static <T> OptionalProvider<T> of(Runnable runnable) {
		return new OptionalProvider<T>(runnable);
	}
	
	public static <T> OptionalProvider<T> of(Supplier<T> supplier) {
		return new OptionalProvider<T>(supplier);
	}
	
	public static <T> OptionalProvider<T> of(T object) {
		return new OptionalProvider<T>(object);
	}
	
	@Override
	public Optional<T> run() {
		if (m_supplier != null) {
			T value = m_supplier.get();
			if (value == null) {
				return Optional.empty();
			} else {
				return Optional.of(value);
			}
		} else if (m_runnable != null) {
			m_runnable.run();
		} else if (m_object != null) {
			return Optional.of(m_object);
		}
		return Optional.empty();
	}
}
