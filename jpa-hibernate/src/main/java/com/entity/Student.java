package com.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
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

/**
 * <code>create table hbnt_STUDENT (
       id integer not null auto_increment,
        AGE integer,
        DOB datetime,
        FIRST_NAME varchar(30),
        gender varchar(255),
        INSTITUTE varchar(255),
        LAST_NAME varchar(50) not null,
        address_id integer,
        primary key (id)
    )
    </code><br>
    <code>
    alter table hbnt_STUDENT 
       add constraint FKjpmaxbesa8ui9m1rncvs29uap 
       foreign key (address_id) 
       references hbnt_ADDRESS (id)
       </code>
 * @author Aritra
 */

@Entity
@Table(name = "hbnt_STUDENT")
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
			query = "SELECT s FROM Student s"),
	@NamedQuery(name = "Student.getAllStudentsWithAgeMoreThen",
			query = "SELECT s FROM Student s WHERE s.age > :age"),
	@NamedQuery(name = "Student.getAllStudentsWithAgeLessThen",
			query = "SELECT s FROM Student s WHERE s.age < :age")
	})
public class Student implements Serializable{
	// Non-persistent fields must be marked as transient.
	/**
	 * Serial version ID for this class.
	 */
	@Transient
	private static final long serialVersionUID = 2718763948180888989L;

	@Id
	// Why " GenerationType.IDENTITY " is used, because I am using MySQL.  https://stackoverflow.com/a/8955097/1679643
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name ="AGE")
	private int age;
	
	@Column(name = "DOB")
	private Date dob;
	
	// default value => https://stackoverflow.com/questions/25274907/jpa-column-default-value-on-start
	// https://stackoverflow.com/questions/197045/setting-default-values-for-columns-in-jpa
	// ENUM => https://stackoverflow.com/a/14438905/1679643
	@Enumerated(EnumType.STRING)
	private Gender gender = Gender.FEMALE;	// default value for column
	
	@Column(name = "FIRST_NAME", length = 30)
	private String firstName;

	// https://stackoverflow.com/a/7439544/1679643
	@Column(name = "LAST_NAME", length = 50, nullable = false)
	private String lastName;

	@Column(name = "INSTITUTE")
	private String instituteName;
	
	@Transient
	private String transientData;
	
	// https://stackoverflow.com/questions/19424179/jpa-saving-primary-and-foreign-key-together
	// https://stackoverflow.com/questions/17283431/data-was-not-saved-object-references-an-unsaved-transient-instance-save-the-t
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Address address;
	
	// Google for more => boolean/true-false converter, pre and post JPA 2.1
	
	
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

	// Getters an setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getInstituteName() {
		return instituteName;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public String toString(){
		LocalDateTime localDob = this.dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
		String formattedDob = localDob.format(dateTimeFormatter);
		// https://dzone.com/articles/java-string-format-examples
		return (String.format("ID : %d, Name : %s %s, Age : %d, DOB : %s, Inst : %s, Address : %s", this.id,
				this.firstName, this.lastName, this.age, formattedDob, this.instituteName, this.address.toString()));
	}
	
}
