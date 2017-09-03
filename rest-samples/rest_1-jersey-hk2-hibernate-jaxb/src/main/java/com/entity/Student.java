package com.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.entity.adapters.DateAdapter;

/**
 * 
 * @author Aritra
 */

@XmlRootElement(name = "student")	// JAXB
@XmlAccessorType(XmlAccessType.NONE)	// JAXB
@XmlType(propOrder = { "firstName", "lastName", "dob", "age", "gender", "address", "instituteName" })	// JAXB
@Entity	// JPA
@Table(name = "hbnt_STUDENT")	// JPA
// IMPORTANT : https://stackoverflow.com/questions/14446048/hibernate-table-not-mapped-error
// When you write HQL (or JPQL) queries, you use the names of the types, not the tables. So here we should use Student, not hbnt_STUDENT
// @NamedQuery(query = "Select s from hbnt_STUDENT s where s.id = :id", name = "find student by id") => wrong
// @NamedQuery(query = "Select s from Student s where s.id = :id", name = "find student by id") => correct
@NamedQueries({
	@NamedQuery(name = "Student.findByID",
			query = "SELECT s FROM Student s WHERE s.id = :id"),
	@NamedQuery(name = "Student.findByLastName",
			query = "SELECT s FROM Student s WHERE s.lastName = :lastName"), // same rule as above is applicable here also
	@NamedQuery(name = "Student.getAllStudents",
			query = "SELECT s FROM Student s")
	/*
	@NamedQuery(name = "Student.deleteByID",
			query = "DELETE FROM Student s WHERE s.id = :id")	// Transaction needed for update/delete, so em.find => then em.begin => update/delete => em.commit.
	*/
	})		// JPA Named Queries
public class Student implements Serializable{
	// Non-persistent fields must be marked as transient.
	/**
	 * Serial version ID for this class.
	 */
	@XmlTransient	// JAXB
	@Transient		// JPA
	private static final long serialVersionUID = 2718763948180888989L;

	@XmlAttribute	// To make xml entry as <student id = "XX">		// We are auto generating ID with the help of DB, so commenting the generation for XML. 
    // @XmlJavaTypeAdapter(type = int.class, value = IDAdapter.class)		// for autogenetaion of ID => https://stackoverflow.com/questions/24180499/how-to-auto-increment-id-in-xml-using-jaxb
	@Id 	// JPA
	// Why " GenerationType.IDENTITY " is used, because I am using MySQL.  https://stackoverflow.com/a/8955097/1679643
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// JPA
	private int id;
	
	@Column(name ="AGE")
	private int age;
	
	@XmlElement(name = "dob", required = true, nillable = false)	// JAXB
	@XmlJavaTypeAdapter(DateAdapter.class)		// JAXB
	@Column(name = "DOB")	// JPA
	private Date dob;
	
	@XmlElement(name = "gender", required = true, nillable = false)		// JAXB
	// default value => https://stackoverflow.com/questions/25274907/jpa-column-default-value-on-start
	// https://stackoverflow.com/questions/197045/setting-default-values-for-columns-in-jpa
	// ENUM => https://stackoverflow.com/a/14438905/1679643
	@Enumerated(EnumType.STRING)	// JPA
	private Gender gender = Gender.FEMALE;	// default value for column
	
	@Column(name = "FIRST_NAME", length = 30)
	private String firstName;

	@XmlElement(name = "last_name", required = true, nillable = false)		// JAXB
	// https://stackoverflow.com/a/7439544/1679643
	@Column(name = "LAST_NAME", length = 50, nullable = false)		// JPA
	private String lastName;

	@Column(name = "INSTITUTE")
	private String instituteName;
	
	@XmlElement(name = "address", required = true, nillable = false)	// JAXB // we are treating this as element here
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)		// JPA // https://stackoverflow.com/questions/19424179/jpa-saving-primary-and-foreign-key-together, https://stackoverflow.com/questions/17283431/data-was-not-saved-object-references-an-unsaved-transient-instance-save-the-t
	private Address address;
	
	// Google for more => boolean/true-false converter, pre and post JPA 2.1
	
	// Must have a default constructor for Hibernate/JPA and also for JAXB
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
			Address address) {
		this(age, dob, lastName, address);
		this.gender = gender;
		this.firstName = firstName;
		this.instituteName = instituteName;
	}

	// Getters an setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement(name = "age", required = true, nillable = false)
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@XmlElement(name = "first_name")	// JAXB
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@XmlElement(name = "institute_name")		// JAXB
	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public String toString(){
		
		LocalDate formattedDate = this.dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		formattedDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
		
		// https://dzone.com/articles/java-string-format-examples
		return (String.format("ID : %d, Name : %s %s, Age : %d, DOB : %s, Gender : %s Inst : %s, Address : %s", this.id,
				this.firstName, this.lastName, this.age, formattedDate, this.gender.getGender(), this.instituteName, this.address));
	}
	
}
