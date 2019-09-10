package com.example.drools.domain;

public class ProductPrice {
	private Integer basePrice;
	
	
	public ProductPrice() {
		super();
	}

	public ProductPrice(Integer basePrice) {
		this.basePrice=basePrice;
	}

	public Integer getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Integer basePrice) {
		this.basePrice = basePrice;
	}

	@Override
	public String toString() {
		return "ProductPrice [basePrice=" + basePrice + "]";
	}	
}
