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

/**
 * Do is a lambda utility to help minimize the overhead for writing lambda expressions.
 * <br>
 * Lambda expressions help a lot in minimizing formerly bulky and hard to handle code.
 * However the simplicity of the syntax strongly depends on whether or not a lambda can be
 * expressed as a single meaningful expression, rather than a short series of expressions.
 * <br>
 * This utility class provides simple methods to "cast" objects to void or to press
 * an if-statement into a single expression to help keep lambda expressions in one line
 * or more specifically: to avoid having to break a lambda into a statement block.
 * 
 * @author Janis Schöck - janis@schoeck-netz.de
 */
public class Do {
	/**
	 * <i>Sort of casts a boolean to void.</i>
	 * 
	 * Takes a boolean as parameter without doing anything to or with it.
	 * This method is simply there to "destroy" a return value, in order to satisfy a
	 * lambda expression returning void.
	 * 
	 * @param ignored Ignored value.
	 */
	public static void asVoid(boolean ignored) {
		// This method needs to do nothing but swallow the passed object.
	}
	
	/**
	 * <i>Sort of casts an {@link Object} to void.</i>
	 * 
	 * Takes a generic {@link Object} as parameter without doing anything to or with it.
	 * This method is simply there to "destroy" a return value, in order to satisfy a
	 * lambda expression returning void.
	 * 
	 * @param ignored Ignored value.
	 */
	public static <T> void asVoid(T ignored) {
		// This method needs to do nothing but swallow the passed object.
	}
	
	/**
	 * <i>Sort of casts an {@link Object} to null.</i>
	 * 
	 * Takes a generic {@link Object} as parameter without doing anything to or with it.
	 * This method is simply there to "destroy" a return value, in order to satisfy a
	 * lambda expression.
	 * 
	 * @param ignored Ignored value.
	 */
	public static <T> Object asNull(T ignored) {
		return null;
	}
	
	/**
	 * <i>Sort of casts an {@link Object} to primitive boolean true.</i>
	 * 
	 * Takes a generic {@link Object} as parameter without doing anything to or with it.
	 * 
	 * @param ignored Ignored value.
	 */
	public static <T> boolean asTrue(T ignored) {
		return true;
	}
	
	/**
	 * Method to press an if-statement into a single expression.
	 * Conditionally executes either of two lambdas, based on the condition parameter.
	 * 
	 * @param condition Condition defining which lambda is executed.
	 * @param then Executed if the condition is true.
	 * @param elze Executed if the condition is false.
	 */
	public static void on(boolean condition, Runnable then, Runnable elze) {
		if (condition) {
			then.run();
		} else {
			elze.run();
		}
	}
	
	/**
	 * Method to press an if-statement into a single expression.
	 * Conditionally executes a lambdas, based on the condition parameter.
	 * 
	 * @param condition Condition defining if the lambda is executed.
	 * @param then Executed if the condition is true.
	 */
	public static void on(boolean condition, Runnable then) {
		if (condition) {
			then.run();
		}
	}
}
