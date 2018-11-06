package com.xinyan.mms.material.pojo;

public class Material {
	private String material_id;
	private int category_id;
	private int unit_id;
	private String material_name;
	private String material_spec;
	private String material_notes;
	private int material_state;
	
	
	public Material() {
		super();
	}
	
	public Material(String material_id, int category_id, int unit_id, String material_name, String material_spec,
			String material_notes, int material_state) {
		super();
		this.material_id = material_id;
		this.category_id = category_id;
		this.unit_id = unit_id;
		this.material_name = material_name;
		this.material_spec = material_spec;
		this.material_notes = material_notes;
		this.material_state = material_state;
	}

	public String getMaterial_id() {
		return material_id;
	}
	public void setMaterial_id(String material_id) {
		this.material_id = material_id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public int getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(int unit_id) {
		this.unit_id = unit_id;
	}
	public String getMaterial_name() {
		return material_name;
	}
	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
	}
	public String getMaterial_spec() {
		return material_spec;
	}
	public void setMaterial_spec(String material_spec) {
		this.material_spec = material_spec;
	}
	public String getMaterial_notes() {
		return material_notes;
	}
	public void setMaterial_notes(String material_notes) {
		this.material_notes = material_notes;
	}
	public int getMaterial_state() {
		return material_state;
	}
	public void setMaterial_state(int material_state) {
		this.material_state = material_state;
	}

	@Override
	public String toString() {
		return "Material [material_id=" + material_id + ", category_id=" + category_id + ", unit_id=" + unit_id
				+ ", material_name=" + material_name + ", material_spec=" + material_spec + ", "
						+ "material_notes=" + material_notes + ", material_state=" + material_state
				+ "]";
	}
	
	
}
