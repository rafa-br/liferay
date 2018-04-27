package farias.rafael.liferay.models;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {

	private List<Item> items;
	private BigDecimal totalPrice;
	private BigDecimal totalTax;

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n===================================================\n");
		items.forEach(i -> builder.append(i.toString()));
		builder.append("Sales Taxes: " + totalTax.doubleValue() + "\n");
		builder.append("Total: " + totalPrice.doubleValue());
		builder.append("\n===================================================\n");
		
		return builder.toString();
	}
	
}
