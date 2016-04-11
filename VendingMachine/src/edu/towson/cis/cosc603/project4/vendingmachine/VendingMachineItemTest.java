package edu.towson.cis.cosc603.project4.vendingmachine;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;

import org.junit.Test;
/*
 * @author William Cosulich
 * This is the class for testing the vending machine items class 
 */
public class VendingMachineItemTest {
	
	VendingMachineItem Item;
	VendingMachineException machineException;

	/*
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		Item = new VendingMachineItem("Snickers", 1.00);
		
	}

	/*
	 * This test the constructor of VendingMachineItem
	 */
	@Test
	public void testVendingMachineItem() {
		
			assertTrue((Item instanceof VendingMachineItem));
		
	}
    @Test
    /**
     * Test custom constructor 
     */
    public void testConstructor() {
    	assertNotNull(Item);
    }
	
	@Test
	public void testVendItemConstructPrice() {
		assertTrue("Value of Item should be greater than 0", Item.getPrice() > 0);
	}
	
	/*
	 * This tests the VendingMachineItem is a negative.
	 */
	@Test(expected = VendingMachineException.class)
    public void testConstructorNegativeValues(){
    	VendingMachineItem ItemN = new VendingMachineItem("Nuts", -1.0);
    }
    
	/*
	 * This tests the getName() method 
	 */
	@Test
	public void testGetName() {
		assertEquals("Snickers", Item.getName());
	}	
	
	/*
	 * This tests the getPrice() method 
	 */
	@Test
	public void testGetPrice() {
		assertEquals(1, Item.getPrice(),0.0);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
		Item = null;
	
	}
}
