package com.pojo;

public class Person {
	/** Identifier for the person, can be Social Sec No., PAN, Aadhar etc. */
	protected long id;
	/** Name of the person. */
	protected String name;
	/** Age of the person.*/
	protected int age;
	/** Gender of the person. */
	protected GenderEnum gender;
	
	/**
	 * Use this constructor everywhere except when populating data from database.
	 * @param name
	 * @param age
	 * @param gender
	 */
	public Person(final String name, final int age, final GenderEnum gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	/**
	 * Use only when populating data from database.
	 * @param id
	 * @param name
	 * @param age
	 * @param gender
	 */
	public Person(final long id, final String name, final int age, final GenderEnum gender) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

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
	
	@Override
	public String toString(){
		return (this.id+" "+this.name+" "+this.age+" "+this.gender);
	}
}