package eu.graphitez.utils.generic;

/**
 * @author Janis Schöck - janis@schoeck-netz.de
 */
public class Null {
	/**
	 * Returns true, if the parameter object is null.
	 * 
	 * @param object Object to be null-checked.
	 * @return True, if the passed object is null.
	 */
	public static boolean is(Object object) {
		return object == null;
	}
	
	/**
	 * Returns true, if the parameter object is not null.
	 * 
	 * @param object Object to be null-checked.
	 * @return True, if the passed object is not null.
	 */
	public static boolean not(Object object) {
		return object != null;
	}
}
