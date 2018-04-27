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

public class GenerateReceipts {

	public static void main(String[] args) {
		firstScenario();
		secondScenario();
		thirdScenario();
	}
	
	public static void firstScenario(){
    	Product book = new Product("Book", ProductType.BOOK, BigDecimal.valueOf(12.49), false);
    	Product musicCD = new Product("Music CD", ProductType.OTHER, BigDecimal.valueOf(14.99), false);
    	Product chocolateBar = new Product("Chocolate Bar", ProductType.FOOD, BigDecimal.valueOf(0.85), false);
    	
    	List<Item> items = new ArrayList<>();
    	items.add(new Item(book, 1));
    	items.add(new Item(musicCD, 1));
    	items.add(new Item(chocolateBar, 1));
    	
    	Receipt receipt = null;
    	ReceiptController controller = ReceiptController.getInstance();
    	
		try {
			receipt = controller.generateReceipt(items);
			System.out.println(receipt);
			
		} catch (InvalidProductException e) {
			System.out.println(e.getMessage());
		}
    }
    
    public static void secondScenario(){
    	Product importedChocolate = new Product("Imported box of chocolates", ProductType.FOOD, BigDecimal.valueOf(10.00), true);
    	Product importedPerfume = new Product("Imported bottle of perfume", ProductType.OTHER, BigDecimal.valueOf(47.50), true);
    	
    	List<Item> items = new ArrayList<>();
    	items.add(new Item(importedChocolate, 1));
    	items.add(new Item(importedPerfume, 1));
    	
    	Receipt receipt = null;
    	ReceiptController controller = ReceiptController.getInstance();
    	
    	try {
			receipt = controller.generateReceipt(items);
			System.out.println(receipt);
			
		} catch (InvalidProductException e) {
			System.out.println(e.getMessage());
		}
    }
    
    public static void thirdScenario(){
    	
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
    	ReceiptController controller = ReceiptController.getInstance();
    	
    	try {
			receipt = controller.generateReceipt(items);
			System.out.println(receipt);
			
		} catch (InvalidProductException e) {
			System.out.println(e.getMessage());
		}
    }
	
}
