package com.xinyan.mvc.query;

import java.util.Date;

/**
 * 查询对象类
 * 	封装查询条件
 *
 */
public class CustomerCondition {

	private String name;
	private String gender;
	private Date minBirthday;
	private Date maxBirthday;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getMinBirthday() {
		return minBirthday;
	}
	public void setMinBirthday(Date minBirthday) {
		this.minBirthday = minBirthday;
	}
	public Date getMaxBirthday() {
		return maxBirthday;
	}
	public void setMaxBirthday(Date maxBirthday) {
		this.maxBirthday = maxBirthday;
	}
}
