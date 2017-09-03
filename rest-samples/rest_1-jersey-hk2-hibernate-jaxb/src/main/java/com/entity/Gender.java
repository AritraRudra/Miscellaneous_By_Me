package com.entity;

import java.util.EnumSet;

import javax.xml.bind.annotation.XmlEnumValue;

import com.exceptions.UnsupportedGenderException;

/**
 * @author Aritra
 */

public enum Gender{
	// https://stackoverflow.com/a/19538804/1679643
	@XmlEnumValue("MALE")
	MALE("MALE"),
	@XmlEnumValue("FEMALE")
	FEMALE("FEMALE");
	//MALE("MALE"), FEMALE("FEMALE");

	/** Gender of the person */
	private String gender;

	private Gender(final String gender) {
		this.gender = gender;
	}
	
	public String getGender() {
		return this.gender;
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

	public static Gender getGenderByValue(final String strToCheck) throws UnsupportedGenderException{
		for (final Gender gender : EnumSet.allOf(Gender.class)) {
            if (gender.toString().equals(strToCheck)) {
                return gender;
            }
        }
        throw new UnsupportedGenderException("Unsupported Gender type : "+ strToCheck);
	}
}