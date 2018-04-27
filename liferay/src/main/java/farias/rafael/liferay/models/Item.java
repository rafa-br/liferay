package farias.rafael.liferay.models;

import java.math.BigDecimal;

import farias.rafael.liferay.exceptions.InvalidProductException;
import farias.rafael.liferay.utils.MathUtils;

public class Item {

	private Product product;
	private int amount;
	private BigDecimal tax;
	private BigDecimal finalPrice;
	private boolean calculated;
	
	public Item(Product product, int amount) {
		this.product = product;
		this.amount = amount;
		this.tax = BigDecimal.ZERO;
		this.finalPrice = BigDecimal.ZERO;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public BigDecimal getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}

	public void calculateTax() throws InvalidProductException {
		if(product != null) {
			BigDecimal totalPriceBeforeTaxes = product.getUnitValue().multiply(BigDecimal.valueOf(this.amount));
			
			this.tax = this.tax.add(totalPriceBeforeTaxes.multiply(BigDecimal.valueOf(product.getType().getTax()).divide(BigDecimal.valueOf(100))));
			
			if(product.isImported()) {
				this.tax = this.tax.add(totalPriceBeforeTaxes.multiply(BigDecimal.valueOf(0.05)));
			}
			
			this.tax = MathUtils.formatTaxValue(this.tax);
			this.finalPrice = MathUtils.formatValue(totalPriceBeforeTaxes.add(this.tax));

			calculated = true;
		} else {
			throw new InvalidProductException();
		}
	}
	
	public void myWrappedCalculate() {
	    try {
	        calculateTax(); 
	    }
	    catch(InvalidProductException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		
		if(!calculated) {
			try {
				calculateTax();
			} catch (InvalidProductException e) {
				builder.append(e.getMessage());
			}
		} else {
			builder.append(amount + " ");
			builder.append(product.getName() + ": ");
			builder.append(finalPrice + "\n");
		}
		
		return builder.toString();
	}
}
