package farias.rafael.liferay.controllers;

import java.math.BigDecimal;
import java.util.List;

import farias.rafael.liferay.exceptions.InvalidProductException;
import farias.rafael.liferay.models.Item;
import farias.rafael.liferay.models.Receipt;
import farias.rafael.liferay.utils.MathUtils;

public class ReceiptController {

	private ReceiptController() {}
	
	public static ReceiptController getInstance() {
		return new ReceiptController();
	}
	
	public Receipt generateReceipt(List<Item> items) throws InvalidProductException {
		Receipt receipt = new Receipt();
		BigDecimal totalTaxes = BigDecimal.ZERO;
		BigDecimal totalPrice = BigDecimal.ZERO;
		
		items.forEach(i -> i.myWrappedCalculate());
		
		for(Item item: items) {
			totalTaxes = totalTaxes.add(MathUtils.formatTaxValue(item.getTax()));
			totalPrice = totalPrice.add(MathUtils.formatValue(item.getFinalPrice()));
		}
		
		receipt.setItems(items);
		receipt.setTotalTax(MathUtils.formatTaxValue(totalTaxes));
		receipt.setTotalPrice(MathUtils.formatValue(totalPrice));
		
		return receipt;
	}
}
