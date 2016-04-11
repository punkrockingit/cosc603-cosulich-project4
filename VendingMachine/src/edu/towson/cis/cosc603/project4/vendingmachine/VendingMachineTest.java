package edu.towson.cis.cosc603.project4.vendingmachine;

import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 * @author William Cosulich
 * This is the class for testing the vending machine class 
 */
public class VendingMachineTest {
	private static VendingMachine vendingMachine = null;
	private static VendingMachineItem ItemA = null;
	private static VendingMachineItem ItemB = null;
	private static VendingMachineItem ItemC = null;
	private static VendingMachineItem ItemD = null;
	private VendingMachineException machineException;
	
	/*
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		vendingMachine = new VendingMachine();
		ItemA = new VendingMachineItem("Snickers", 1);
		ItemB = new VendingMachineItem("Sun Chips", 1);
		ItemC = new VendingMachineItem("Milky Way", 1);
		ItemD = new VendingMachineItem("Crumb Cake", 1.5);		
	}

	/*
	 *  This tests that the addItem() method to check if the slot is empty
	 */
	@Test
	public void testAddItemForEmptySlot() {
		 assertNull(vendingMachine.getItem(VendingMachine.B_CODE));
	}
	
	/*
	 * This tests that the addItem() method has the correct input.
	 */
	@Test
	public void testAddItem() {
		
		vendingMachine.addItem(ItemA, "A");
		assertTrue((Arrays.asList(vendingMachine.itemArray[0]).contains(ItemA)));
		
		vendingMachine.addItem(ItemB, "B");
		assertTrue((Arrays.asList(vendingMachine.itemArray[1]).contains(ItemB)));
		
		vendingMachine.addItem(ItemC, "C");
		assertTrue((Arrays.asList(vendingMachine.itemArray[2]).contains(ItemC)));

		vendingMachine.addItem(ItemD, "D");
		assertTrue((Arrays.asList(vendingMachine.itemArray[3]).contains(ItemD)));

	}
	
	/*
	 * This tests the addItem() method for cases where cost is not valid 
	 */
	@Test(expected = VendingMachineException.class)
	public void testAddItemInvalidCost() {
		VendingMachineItem ItemC = new VendingMachineItem("Sun Chips", -1.0);
		vendingMachine.addItem(ItemC, "B");
		assertEquals(ItemC, vendingMachine.getItem("B"));
	}
	
	/*
	 * This tests the addItem() method for cases for adding cost into an invalid slot
	 */
	@Test(expected = VendingMachineException.class)
	public void testAddItemInvalidSlot() {
		VendingMachineItem ItemE = new VendingMachineItem("Cheetos", 1.0);
		vendingMachine.addItem(ItemE, "E");
	}
	
	/*
	 * This tests the addItem() method for the case where the add item is already occupied slot.
	 */
	@Test (expected = VendingMachineException.class)
	public void testAddItemOccupied() {
		ItemA = new VendingMachineItem("Snickers", 1);
		vendingMachine.addItem(ItemA,VendingMachine.B_CODE);
		ItemB = new VendingMachineItem("MilkyWay", 1);
		vendingMachine.addItem(ItemB,VendingMachine.B_CODE);
	}
	
	/*
	 * This tests the removeItem() method with good input.
	 */	
	@Test
	public void testRemoveItem() {
		vendingMachine.addItem(ItemA, "A");
		assertSame(ItemA, vendingMachine.removeItem("A"));
	}
	
	/*
	 * This tests the removeItem() method with bad input.
	 */	
	@Test
	public void testRemoveItemReturnValue() {
		VendingMachineItem ItemA = new VendingMachineItem("Nuts", 1.0);
		vendingMachine.addItem(ItemA, "A");
		VendingMachineItem removedItem = vendingMachine.removeItem("A");
		assertEquals(ItemA, removedItem);
	}

	/*
	 * This tests the removeItem() method for the case to remove the item from invalid slot. 
	 */	
	@Test(expected = VendingMachineException.class)
	public void testRemoveItemInvalidSlot() {
		vendingMachine.removeItem("E");
	}
	
	/*
	 * This tests the removeItem() method for the case to remove the item from empty slot. 
	 */	
	@Test (expected = VendingMachineException.class)
	public void testRemoveItemEmptySlot() {
		vendingMachine.removeItem("A");
	}

	
	/*
	 * This tests the insertMoney() method with amount >= 0
	 */
	@Test
	public void testInsertMoney() {
		vendingMachine.insertMoney(0);
		assertEquals(0, vendingMachine.getBalance(), 0.0);
		vendingMachine.insertMoney(1.5);
		assertEquals(1.5, vendingMachine.getBalance(), 0.0);
		vendingMachine.insertMoney(1.5);
		assertEquals(3.0, vendingMachine.getBalance(), 0.0);
	}
	
	/*
	 * This tests the insertMoney() method with amount = 0
	 */
	@Test
	public void testInsertMoneyIsZero() {
		vendingMachine.insertMoney(0);
		assertEquals(0,vendingMachine.getBalance(), 0.001);
	}
	
	/*
	 * This tests the insertMoney() method with amount < 0
	 */
	@Test (expected = VendingMachineException.class)
	public void testInsertMoneyInvalidAmount() throws Exception{
		vendingMachine.insertMoney(-1.0);
	}
	
	/*
	 * This tests the makePurchase() method with to make sure that the balance is correct.
	 */
	@Test
	public void testMakePurchase() {
		vendingMachine.addItem(ItemA, "A");
		vendingMachine.insertMoney(1);
		vendingMachine.makePurchase("A");
		assertEquals(0, vendingMachine.getBalance(), 0.001);
	}
	
	/*
	 * This tests the makePurchase() method for code of empty slot
	 */
	@Test
	public void testMakePurchaseEmptySlot() {
		vendingMachine.insertMoney(1.5);
		assertEquals(false,vendingMachine.makePurchase("B"));
	}
	
	/*
	 * Uses the makePurchase() method to test that the item was removed after a purchase
	 */
	@Test
	public void testMakePurchaseItemRemoved() {
		vendingMachine.insertMoney(1);
		vendingMachine.addItem(ItemA, "A");
		vendingMachine.makePurchase("A");
		assertNull(vendingMachine.getItem("A"));	
	}
	
	/*
	 * This tests the makePurchase() method for code for proper balance.
	 */
	@Test
	public void testMakePurchaseItemEnoughMoney() {
		vendingMachine.addItem(ItemA, "A");
		vendingMachine.insertMoney(1);
		boolean enoughMoney = vendingMachine.makePurchase("A");
		assertTrue(enoughMoney);
	}
	
	/*
	 * This tests the makePurchase() method for code for insufficient balance.
	 */
	@Test
	public void testMakePurchaseInSufficientBalance() {
		vendingMachine.addItem(ItemA, "A");
		vendingMachine.insertMoney(.75);
		assertEquals(false,vendingMachine.makePurchase("A"));
	}
	
	/*
	 * This tests the makePurchase() method for code for bad selection.
	 */
	@Test
	public void testMakePurchaseBadSelection() {
		vendingMachine.addItem(ItemC, "C");
		vendingMachine.insertMoney(1.5);
		assertEquals(false,vendingMachine.makePurchase("A"));
	}
	
	/*
	 * This tests the makePurchase() method for code for same item.
	 */
	@Test
	public void testMakePurchaseSameItem() {
		vendingMachine.addItem(ItemA, "A");
		vendingMachine.insertMoney(1);
		vendingMachine.makePurchase("A");
		assertEquals(false,vendingMachine.makePurchase("A"));
	}
	
	/*
	 * This tests the makePurchase() method for code for inval1d Slot
	 */
	@Test(expected = VendingMachineException.class)
	public final void testMakePurchaseInvalidSelection() {
		vendingMachine.makePurchase("E");
	}
	/*
	 * This tests the getBalance() method
	 */
	@Test
	public void testGetBalance(){
		vendingMachine.insertMoney(1.5);
		assertEquals(1.5, vendingMachine.getBalance(),0.0);
	}
	
	/*
	 * This tests the getBalance() method with bad input 
	 */  
	@Test
	public void testGetBalanceBadInput(){
		vendingMachine.insertMoney(1.5);
		assertFalse(vendingMachine.getBalance() == 3.0);
	}
	 
	
	/*
	 * This tests the returnChange() method 
	 */
	@Test
	public void testReturnChange() {
		vendingMachine.addItem(ItemD, "D");
		vendingMachine.insertMoney(1.5);
		vendingMachine.makePurchase("D");
		assertEquals(0.0, vendingMachine.returnChange(),0.0);
	}
	
	/*
	 * This tests the returnChange() method when the balance is zeroed out
	 */
	@Test
	public void testReturnChangeBalanceZero() {
		vendingMachine.insertMoney(1);		
		vendingMachine.returnChange();
		assertEquals(0,vendingMachine.getBalance(), 0.001);
	}
	
	/*
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		vendingMachine = null;
		ItemA = null;
		ItemB = null;
		ItemC = null;
		ItemD = null;
	}

}
