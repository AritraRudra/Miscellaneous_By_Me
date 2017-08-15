package com.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "address")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = { "id", "street", "city", "postcode", "country"})
public class Address {
	// Non-persistent fields must be marked as transient.
		/**
		 * Serial version ID for this class.
		 */
		@XmlTransient
		private static final long serialVersionUID = 2718763948230898989L;

		//@Id
		private Integer id;
		//@Column(name = "Street", length = 30, nullable = true)
		private String street;
		//@Column(name = "City", length = 50, nullable = true)
		private String city;
		//@Column(name = "Country", length = 30, nullable = false)
		private String country;
		//@Column(name = "Zip_Code")
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
	@XmlElement(name = "street", required = true, nillable = false)
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
	@XmlElement(name = "country", required = true, nillable = false)
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
	@XmlElement(name = "zip_code", nillable = false)	// no blank space e.g., "zip code"
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
