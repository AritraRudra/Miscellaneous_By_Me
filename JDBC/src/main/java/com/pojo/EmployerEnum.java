package com.pojo;

public enum EmployerEnum {
	SELF_EMPLOYED("SELF_EMPLOYED"),
	OTHER("OTHER"),
	EMPLOYER_A("EMPLOYER_A"),
	EMPLOYER_B("EMPLOYER_B"),
	EMPLOYER_C("EMPLOYER_C");

	/** Name of the employer. */
	private String employerName;

	private EmployerEnum(final String employerName) {
		this.employerName = employerName;
	}

	/*
	 * (non-Javadoc) 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.employerName;
	}
}