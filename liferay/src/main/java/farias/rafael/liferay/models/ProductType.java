package farias.rafael.liferay.models;

public enum ProductType {

	BOOK(0.0), FOOD(0.0), MEDICAL(0.0), OTHER(10.0);
	
	private double tax;
	
	private ProductType(double tax) {
		this.tax = tax;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}
}
