package com.yang.bean.element;

import java.io.Serializable;

public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final String TYPE = "ProductType";
	public static final String TYPE_PRINT = "Prints";
	public static final String TYPE_PHOTOBOOK = "Photobook";
	public static final String FLAG_ID = "Id";
	public static final String FLAG_PRO_DESC_ID = "ProductDescriptionId";
	
	public String id = "";
	public String proDescId = "";
	public int quantity = 1;
	
	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof Product) {
			Product product = (Product) o;
			if (product.id.equals(this.id)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
