package com.xinyan.mms.material.query;

public class MaterialCondition {
	private String id;
	private String name;
	private int mcategory_id;
	
	
	public MaterialCondition() {
		super();
	}
	public MaterialCondition(String id, String name, int mcategory_id) {
		super();
		this.id = id;
		this.name = name;
		this.mcategory_id = mcategory_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMcategory_id() {
		return mcategory_id;
	}
	public void setMcategory_id(int mcategory_id) {
		this.mcategory_id = mcategory_id;
	}
	@Override
	public String toString() {
		return "MaterialCondition [id=" + id + ", name=" + name + ", mcategory_id=" + mcategory_id + "]";
	}
	
	
	
	
	

}
