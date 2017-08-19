package com.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.model.adapters.IDAdapter;
import com.model.adapters.DateAdapter;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.NONE)		// http://blog.bdoughan.com/2011/06/using-jaxbs-xmlaccessortype-to.html	=>to have xml entries of getters by default unless overridden by annotation, set this as PROPERTY.
// Define the order in which the fields are written, optional annotation
@XmlType(propOrder = { "firstName", "lastName", "dob", "age", "gender", "address", "instituteName" })
public class Student {
	/**
	 * Serial version ID for this class.
	 */
	@XmlTransient
	private static final long serialVersionUID = 2718763948180888989L;

	@XmlAttribute	// To make xml entry as <student id = "XX">		// https://stackoverflow.com/a/22471301/1679643 => int.class ???, it's from jdk 1.1 -> java.lang.Integer.TYPE javaDoc says "The Class instance representing the primitive type int."
    @XmlJavaTypeAdapter(type=int.class, value=IDAdapter.class)		// for autogenetaion of ID => https://stackoverflow.com/questions/24180499/how-to-auto-increment-id-in-xml-using-jaxb and for "int.class"!! => https://stackoverflow.com/a/19793982/1679643
	private int id = 0;
	@XmlElement(name = "age", required = true, nillable = false)		// 1 counts of IllegalAnnotationExceptions	Class has two properties of the same name "age" because @XmlAccessorType is PROPERTY 
	private int age;
	// https://stackoverflow.com/a/19273820/1679643
	@XmlElement(name = "dob", required = true, nillable = false)
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date dob;
	private Gender gender = Gender.FEMALE;	// default value for column
	@XmlElement(name = "first_name")
	private String firstName;

	//@Column(name = "LAST_NAME", length = 50, nullable = false)
	@XmlElement(name = "last_name", required = true, nillable = false)
	private String lastName;
	private String instituteName;
	
	@XmlTransient
	private String transientData;
	
	//@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)
	//@XmlElement(name = "address", required = true, nillable = false)	// we are treating this as element here
	private Address address;
	
	
	// getters
	public int getId() {
		return Integer.valueOf(this.id);
	}

	// https://stackoverflow.com/questions/33746/xml-attribute-vs-xml-element
	// https://stackoverflow.com/questions/1727468/xsd-difference-between-element-and-attribute
	// attributes will come inside <elm att1="a" att2="b">, elements are
	// <element_X>value_X</element_X>

	//@XmlElement(name = "age", required = true, nillable = false)
	public int getAge() {
		return age;
	}

	public Date getDob() {
		return dob;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@XmlElement(name = "gender", required = true, nillable = false)
	public Gender getGender() {
		return gender;
	}

	@XmlElement(name = "institute_name")
	public String getInstituteName() {
		return instituteName;
	}

	@XmlElement(name = "address", required = true, nillable = false)
	public Address getAddress() {
		return address;
	}
	
	
	// setters

	public void setAge(int age) {
		this.age = age;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getTransientData() {
		return transientData;
	}

	public void setTransientData(String transientData) {
		this.transientData = transientData;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Student(){
		
	}
	
	public Student(int age, Date dob, String lastName, Address address) {
		super();
		this.age = age;
		this.dob = dob;
		this.lastName = lastName;
		this.address = address;
	}
	
	public Student(int age, Date dob, Gender gender, String firstName, String lastName, String instituteName,
			String transientData, Address address) {
		this(age, dob, lastName, address);
		this.gender = gender;
		this.firstName = firstName;
		this.instituteName = instituteName;
		this.transientData = transientData;
	}
	
	@Override
	public String toString(){
		LocalDate localDob = this.dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		String formattedDob = localDob.format(dateTimeFormatter);
		// https://dzone.com/articles/java-string-format-examples
		return (String.format("ID : %d, Name : %s %s, Age : %d, DOB : %s, Inst : %s, Address : %s", this.id,
				this.firstName, this.lastName, this.age, formattedDob, this.instituteName, this.address.toString()));
	}
	
}
