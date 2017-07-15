package com.pojo;

public class Person {
	/** Identifier for the person, can be Social Sec No., PAN, Aadhar etc. */
	private long id;
	/** Name of the person. */
	private String name;
	/** Age of the person.*/
	private int age;
	/** Gender of the person. */
	private GenderEnum gender;
	
	public long getId() {
		return this.id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return this.age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public GenderEnum getGender() {
		return this.gender;
	}
	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}
}