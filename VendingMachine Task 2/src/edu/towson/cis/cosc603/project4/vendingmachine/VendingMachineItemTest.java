package edu.towson.cis.cosc603.project4.vendingmachine;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>VendingMachineItemTest</code> contains tests for the class <code>{@link VendingMachineItem}</code>.
 *
 * @generatedBy CodePro at 4/11/16 1:21 AM
 * @author willcosulich
 * @version $Revision: 1.0 $
 */
public class VendingMachineItemTest {
	/**
	 * Run the VendingMachineItem(String,double) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 4/11/16 1:21 AM
	 */
	@Test
	public void testVendingMachineItem_1()
		throws Exception {
		String name = "";
		double price = 1.0;

		VendingMachineItem result = new VendingMachineItem(name, price);

		// add additional test code here
		assertNotNull(result);
		assertEquals("", result.getName());
		assertEquals(1.0, result.getPrice(), 1.0);
	}

	/**
	 * Run the VendingMachineItem(String,double) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 4/11/16 1:21 AM
	 */
	@Test(expected = edu.towson.cis.cosc603.project4.vendingmachine.VendingMachineException.class)
	public void testVendingMachineItem_2()
		throws Exception {
		String name = "";
		double price = -4.9E-324;

		VendingMachineItem result = new VendingMachineItem(name, price);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the String getName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 4/11/16 1:21 AM
	 */
	@Test
	public void testGetName_1()
		throws Exception {
		VendingMachineItem fixture = new VendingMachineItem("", 1.0);

		String result = fixture.getName();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the double getPrice() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 4/11/16 1:21 AM
	 */
	@Test
	public void testGetPrice_1()
		throws Exception {
		VendingMachineItem fixture = new VendingMachineItem("", 1.0);

		double result = fixture.getPrice();

		// add additional test code here
		assertEquals(1.0, result, 0.1);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 4/11/16 1:21 AM
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 4/11/16 1:21 AM
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 4/11/16 1:21 AM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(VendingMachineItemTest.class);
	}
}