package eu.graphitez.utils;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import eu.graphitez.utils.generic.CompareTest;
import eu.graphitez.utils.generic.ConvertTest;
import eu.graphitez.utils.generic.DoTest;
import eu.graphitez.utils.generic.EitherTest;

@Suite.SuiteClasses({
	CompareTest.class,
	ConvertTest.class,
	DoTest.class,
	EitherTest.class
})

/**
 * @author Janis Schöck - janis@schoeck-netz.de
 */
@RunWith(Suite.class)
public class PackageTestSuite {}
