package eu.graphitez.utils.generic;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import eu.graphitez.utils.generic.Compare;
import eu.graphitez.utils.generic.Convert;

/**
 * @author Janis Schöck - janis@schoeck-netz.de
 */
public class ConvertTest {
	@Test
	public void test_convert() {
		Integer[] intArray = { 0, 1, 2, 4, 7, 11, 11, 11, 14, 21 };
		String intString = "0, 1, 2, 4, 7, 11, 11, 11, 14, 21";
		String[] stringArray = { "0", "1", "2", "4", "7", "11", "11", "11", "14", "21" }; 
		
		// map some values from E to R
		List<String> result_0 = Convert.map(intArray, Number::toString);
		assertTrue(Compare.byEquals(result_0, stringArray));
		assertTrue(Compare.byEquals(stringArray, result_0));
		
		List<Integer> result_1 = Convert.map(intString.split("[\\, ]+"), Integer::parseInt);
		assertTrue(Compare.byEquals(result_1, intArray));
		assertTrue(Compare.byEquals(intArray, result_1));
	}
	
	@Test
	public void test_convertInner() {
		Integer[] intArray = { 0, 1, 2, null, 7, 11, null, 11, 14, 21 };
		String[] stringArrayResult = { "0", "1", "2", "7", "11", "11", "14", "21" }; 
		String intString = "0, 1, 2, 7, 11, 11, 14, 21";
		Integer[] intArrayResult = { 0, 1, 2, 7, 11, 11, 14, 21 };
		
		// map some values from E to R
		List<String> result_0 = Convert.inner(intArray, Number::toString);
		assertTrue(Compare.byEquals(result_0, stringArrayResult));
		assertTrue(Compare.byEquals(stringArrayResult, result_0));
		
		List<Integer> result_1 = Convert.inner(intString.split("[\\, ]+"), Integer::parseInt);
		assertTrue(Compare.byEquals(result_1, intArrayResult));
		assertTrue(Compare.byEquals(intArrayResult, result_1));
	}
}
