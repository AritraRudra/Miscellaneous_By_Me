package com.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <code>
 * create table hbnt_ADDRESS (
       id integer not null auto_increment,
        City varchar(50),
        Country varchar(30) not null,
        Zip_Code bigint,
        Street varchar(30),
        primary key (id)
    )
    </code>
 * @author Aritra
 */

@Entity
@Table(name = "hbnt_ADDRESS")
public class Address  implements Serializable{
	// Non-persistent fields must be marked as transient.
	/**
	 * Serial version ID for this class.
	 */
	@Transient
	private static final long serialVersionUID = 2718763948230898989L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	// https://stackoverflow.com/a/1914486/1679643
	@Column(name = "Street", length = 30, nullable = true)
	private String street;
	@Column(name = "City", length = 50, nullable = true)
	private String city;
	@Column(name = "Country", length = 30, nullable = false)
	private String country;

	// nullable not applicable for primitive types
	// https://stackoverflow.com/questions/3225103/hibernate-jpa-nullable-values-objects
	// https://stackoverflow.com/questions/14893937/primitive-type-in-jpa-mapping-what-if-the-database-column-may-be-null
	//@Column(name = "Zip Code")	// column name can not have empty spaces
	@Column(name = "Zip_Code")
	private long postcode;
	
	
	public Address(){
		
	}
	
	public Address(String street, String city, String country, long postcode) {
		super();
		this.street = street;
		this.city = city;
		this.country = country;
		this.postcode = postcode;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the postcode
	 */
	public long getPostcode() {
		return postcode;
	}

	/**
	 * @param postcode
	 *            the postcode to set
	 */
	public void setPostcode(final long postcode) {
		this.postcode = postcode;
	}

	@Override
	public String toString(){
		return (String.format("Street : %s, City : %s, Zip code : %s, Country : %s", this.street, this.city,
				this.postcode, this.country));
	}
}
