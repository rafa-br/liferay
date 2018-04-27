package farias.rafael.liferay.models;

import java.math.BigDecimal;

public class Product {

	private String name;
	private ProductType type;
	private BigDecimal unitValue;
	private boolean imported;
	
	public Product() {
		this.unitValue = BigDecimal.ZERO;
	}
	
	public Product(String name, ProductType type, BigDecimal unitvalue, boolean imported) {
		this.name = name;
		this.type = type;
		this.unitValue = unitvalue;
		this.imported = imported;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ProductType getType() {
		return type;
	}
	public void setType(ProductType type) {
		this.type = type;
	}
	public BigDecimal getUnitValue() {
		return unitValue;
	}
	public void setUnitValue(BigDecimal unitValue) {
		this.unitValue = unitValue;
	}
	public boolean isImported() {
		return imported;
	}
	public void setImported(boolean imported) {
		this.imported = imported;
	}
	
	@Override
	public String toString() {
		return "Product [name=" + name + ", type=" + type + ", unitValue=" + unitValue + ", imported=" + imported + "]";
	}
}
