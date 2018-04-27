package farias.rafael.liferay;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import farias.rafael.liferay.controllers.ReceiptController;
import farias.rafael.liferay.exceptions.InvalidProductException;
import farias.rafael.liferay.models.Item;
import farias.rafael.liferay.models.Product;
import farias.rafael.liferay.models.ProductType;
import farias.rafael.liferay.models.Receipt;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ReceiptTest extends TestCase {
	
	public ReceiptTest(String testName){
        super(testName);
    }

    public static Test suite(){
        return new TestSuite(ReceiptTest.class);
    }

    public void testFirstScenario(){
    	Product book = new Product("Book", ProductType.BOOK, BigDecimal.valueOf(12.49), false);
    	Product musicCD = new Product("Music CD", ProductType.OTHER, BigDecimal.valueOf(14.99), false);
    	Product chocolateBar = new Product("Chocolate Bar", ProductType.FOOD, BigDecimal.valueOf(0.85), false);
    	
    	List<Item> items = new ArrayList<>();
    	items.add(new Item(book, 1));
    	items.add(new Item(musicCD, 1));
    	items.add(new Item(chocolateBar, 1));
    	
    	Receipt receipt = null;
    	
		try {
			receipt = ReceiptController.getInstance().generateReceipt(items);
		} catch (InvalidProductException e) {
			Assert.fail(e.getMessage());
		}
		
    	assertNotNull(receipt);
    	assertEquals(1.5, receipt.getTotalTax().doubleValue());
    	assertEquals(29.83, receipt.getTotalPrice().doubleValue());
    }
    
    public void testSecondScenario(){
    	Product importedChocolate = new Product("Imported box of chocolates", ProductType.FOOD, BigDecimal.valueOf(10.00), true);
    	Product importedPerfume = new Product("Imported bottle of perfume", ProductType.OTHER, BigDecimal.valueOf(47.50), true);
    	
    	List<Item> items = new ArrayList<>();
    	items.add(new Item(importedChocolate, 1));
    	items.add(new Item(importedPerfume, 1));
    	
    	Receipt receipt = null;
    	
		try {
			receipt = ReceiptController.getInstance().generateReceipt(items);
		} catch (InvalidProductException e) {
			Assert.fail(e.getMessage());
		}
    	
    	assertNotNull(receipt);
    	assertEquals(7.65, receipt.getTotalTax().doubleValue());
    	assertEquals(65.15, receipt.getTotalPrice().doubleValue());
    }
    
    public void testThirdScenario(){
    	
    	Product importedPerfume = new Product("Imported bottle of perfume", ProductType.OTHER, BigDecimal.valueOf(27.99), true);
    	Product nationalPerfume = new Product("National bottle of perfume", ProductType.OTHER, BigDecimal.valueOf(18.99), false);
    	Product packetOfPills = new Product("Packet of headache pills", ProductType.MEDICAL, BigDecimal.valueOf(9.75), false);
    	Product importedChocolate = new Product("Imported box of chocolates", ProductType.FOOD, BigDecimal.valueOf(11.25), true);
    	
    	List<Item> items = new ArrayList<>();
    	items.add(new Item(importedPerfume, 1));
    	items.add(new Item(nationalPerfume, 1));
    	items.add(new Item(packetOfPills, 1));
    	items.add(new Item(importedChocolate, 1));
    	
    	Receipt receipt = null;
    	
		try {
			receipt = ReceiptController.getInstance().generateReceipt(items);
		} catch (InvalidProductException e) {
			Assert.fail(e.getMessage());
		}

    	assertNotNull(receipt);
    	assertEquals(6.65, receipt.getTotalTax().doubleValue());
    	assertEquals(74.63, receipt.getTotalPrice().doubleValue());
    }
    
    public void testAditionalScenario(){
    	Product importedChocolate = new Product("2 Imported box of chocolates", ProductType.OTHER, BigDecimal.valueOf(10.00), true);
    	
    	List<Item> items = new ArrayList<>();
    	items.add(new Item(importedChocolate, 2));
    	
    	Receipt receipt = null;
    	
		try {
			receipt = ReceiptController.getInstance().generateReceipt(items);
		} catch (InvalidProductException e) {
			Assert.fail(e.getMessage());
		}
    	
    	assertNotNull(receipt);
    	assertEquals(3.0, receipt.getTotalTax().doubleValue());
    	assertEquals(23.00, receipt.getTotalPrice().doubleValue());
    }
    
    public void testInvalidProductScenario(){
    	List<Item> items = new ArrayList<>();
    	items.add(new Item(null, 2));
    	
		try {
			ReceiptController.getInstance().generateReceipt(items);
			Assert.fail("Exception should be thrown above.");
		} catch (Exception e) {
			Assert.assertEquals("An Exception was thrown correctly", true, true);
		}
    }
    
}
