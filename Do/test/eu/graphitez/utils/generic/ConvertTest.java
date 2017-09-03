package eu.graphitez.utils.generic;

import static org.junit.Assert.assertFalse;
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
		
		Integer[] intArray2 = { 0, 1, 2, 4, 7, 11, 11, 13, 14, 21 };
		String intString2 = "0, 1, 2, 4, 7, 11, 11, 13, 14, 21";
		String[] stringArray2 = { "0", "1", "2", "4", "7", "11", "11", "13", "14", "21" }; 
		
		// map some values from E to R
		List<String> result_0 = Convert.map(intArray, Number::toString);
		assertTrue(Compare.elements(result_0, stringArray));
		assertTrue(Compare.elements(stringArray, result_0));
		assertFalse(Compare.elements(result_0, stringArray2));
		
		List<Integer> result_1 = Convert.map(intString.split("[\\, ]+"), Integer::parseInt);
		assertTrue(Compare.elements(result_1, intArray));
		assertTrue(Compare.elements(intArray, result_1));
		assertFalse(Compare.elements(result_1, intArray2));
		
		List<Integer> result_2 = Convert.map(intString2.split("[\\, ]+"), Integer::parseInt);
		assertTrue(Compare.elements(result_2, intArray2));
		assertTrue(Compare.elements(intArray2, result_2));
		assertFalse(Compare.elements(result_2, intArray));
	}
	
	@Test
	public void test_convertInner() {
		Integer[] intArray = { 0, 1, 2, null, 7, 11, null, 11, 14, 21 };
		String[] stringArrayResult = { "0", "1", "2", "7", "11", "11", "14", "21" }; 
		
		// map some values from E to R
		List<String> result_0 = Convert.inner(intArray, Number::toString);
		assertTrue(Compare.elements(result_0, stringArrayResult));
		assertTrue(Compare.elements(stringArrayResult, result_0));
		
		String intString = "0, 1, 2, 7, 11, 11, 14, 21";
		Integer[] intArrayResult = { 0, 1, 2, 7, 11, 11, 14, 21 };
		
		List<Integer> result_1 = Convert.inner(intString.split("[\\, ]+"), Integer::parseInt);
		assertTrue(Compare.elements(result_1, intArrayResult));
		assertTrue(Compare.elements(intArrayResult, result_1));
	}
}
