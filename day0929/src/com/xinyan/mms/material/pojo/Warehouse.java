package com.xinyan.mms.material.pojo;
/**
 * 物料仓库类
 * @author Administrator
 *
 */
public class Warehouse {
	private int warehouse_id;
	private String warehouse_name;
	
	public Warehouse() {
		super();
	}
	public Warehouse(int warehouse_id, String warehouse_name) {
		super();
		this.warehouse_id = warehouse_id;
		this.warehouse_name = warehouse_name;
	}
	public int getWarehouse_id() {
		return warehouse_id;
	}
	public void setWarehouse_id(int warehouse_id) {
		this.warehouse_id = warehouse_id;
	}
	public String getWarehouse_name() {
		return warehouse_name;
	}
	public void setWarehouse_name(String warehouse_name) {
		this.warehouse_name = warehouse_name;
	}
	@Override
	public String toString() {
		return "Warehouse [warehouse_id=" + warehouse_id + ", warehouse_name=" + warehouse_name + "]";
	}
	
	
	
	
}
