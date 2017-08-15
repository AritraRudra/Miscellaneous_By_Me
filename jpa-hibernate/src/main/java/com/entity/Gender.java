package com.entity;

/**
 * @author Aritra
 */

public enum Gender{
	MALE("MALE"), FEMALE("FEMALE");

	/** Gender of the person */
	private String gender;

	private Gender(final String gender) {
		this.gender = gender;
	}
	
	public String getGender() {
		return this.gender;
	}
	
	/**
	 * Get the gender corresponding to the supplied value.
	 * 
	 * @param value the value to get from.
	 * @return {@link Gender}.
	 */
	public static Gender fromValue(final String value) throws Exception{
		return valueOf(value);
	}
	 
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.gender;
	}
}