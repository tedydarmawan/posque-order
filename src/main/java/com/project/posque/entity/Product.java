package com.project.posque.entity;

public class Product {
	private int pcode;
	private String pcodeName;
	private String uom;
	private int sellPrice;

	public int getPcode() {
		return pcode;
	}

	public void setPcode(int pcode) {
		this.pcode = pcode;
	}

	public String getPcodeName() {
		return pcodeName;
	}

	public void setPcodeName(String pcodeName) {
		this.pcodeName = pcodeName;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public int getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

}
