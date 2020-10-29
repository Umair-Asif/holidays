package com.ms.helperservice;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
/**
 * Unit test for simple App.
 */
class HelperserviceApplicationTests
		extends TestCase
{
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public HelperserviceApplicationTests( String testName )
	{
		super( testName );
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite()
	{
		return new TestSuite( HelperserviceApplicationTests.class );
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp()
	{
		assertTrue( true );
	}
}