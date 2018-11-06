package com.xinyan.mms.material.pojo;
/**
 * 库存信息类
 * @author Administrator
 *
 */
public class Stock {
	private int warehouse_id;
	private String material_id;
	private int stock_value;
	private int stock_number;
	private String stock_comments;
	
	
	public Stock() {
		super();
	}
	

	public Stock(int warehouse_id, String material_id, int stock_value, int stock_number, String stock_comments) {
		super();
		this.warehouse_id = warehouse_id;
		this.material_id = material_id;
		this.stock_value = stock_value;
		this.stock_number = stock_number;
		this.stock_comments = stock_comments;
	}


	public int getWarehouse_id() {
		return warehouse_id;
	}


	public void setWarehouse_id(int warehouse_id) {
		this.warehouse_id = warehouse_id;
	}


	public String getMaterial_id() {
		return material_id;
	}


	public void setMaterial_id(String material_id) {
		this.material_id = material_id;
	}


	public int getStock_value() {
		return stock_value;
	}


	public void setStock_value(int stock_value) {
		this.stock_value = stock_value;
	}


	public int getStock_number() {
		return stock_number;
	}


	public void setStock_number(int stock_number) {
		this.stock_number = stock_number;
	}


	public String getStock_comments() {
		return stock_comments;
	}


	public void setStock_comments(String stock_comments) {
		this.stock_comments = stock_comments;
	}


	@Override
	public String toString() {
		return "Stock [warehouse_id=" + warehouse_id + ", material_id=" + material_id + ", stock_value=" + stock_value
				+ ", stock_number=" + stock_number + ", stock_comments=" + stock_comments + "]";
	}

	
	
	
	
	
}
