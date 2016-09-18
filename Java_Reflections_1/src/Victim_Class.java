
public class Victim_Class {

	public int publicFieldInt_1;
	int defaultFieldInt_1;
	protected int protectedFieldInt_1;
	private int privateFieldInt_1;

	public String publicFieldString_1;
	String defaultFieldString_1;
	protected String protectedFieldString_1;
	private String privateFieldString_1;

	public Victim_Class() {
		String stringVal = "Default Constructor";
		this.publicFieldInt_1 = -1;
		this.defaultFieldInt_1 = -1;
		this.protectedFieldInt_1 = -1;
		this.privateFieldInt_1 = -1;
		this.publicFieldString_1 = stringVal;
		this.defaultFieldString_1 = stringVal;
		this.protectedFieldString_1 = stringVal;
		this.privateFieldString_1 = stringVal;
	}

	public Victim_Class(int publicFieldInt_1, int defaultFieldInt_1, int protectedFieldInt_1, int privateFieldInt_1) {
		super();
		this.publicFieldInt_1 = publicFieldInt_1;
		this.defaultFieldInt_1 = defaultFieldInt_1;
		this.protectedFieldInt_1 = protectedFieldInt_1;
		this.privateFieldInt_1 = privateFieldInt_1;
	}

	public Victim_Class(String publicFieldString_1, String defaultFieldString_1, String protectedFieldString_1,
			String privateFieldString_1) {
		super();
		this.publicFieldString_1 = publicFieldString_1;
		this.defaultFieldString_1 = defaultFieldString_1;
		this.protectedFieldString_1 = protectedFieldString_1;
		this.privateFieldString_1 = privateFieldString_1;
	}

	public Victim_Class(int publicFieldInt_1, int defaultFieldInt_1, int protectedFieldInt_1, int privateFieldInt_1,
			String publicFieldString_1, String defaultFieldString_1, String protectedFieldString_1,
			String privateFieldString_1) {
		super();
		this.publicFieldInt_1 = publicFieldInt_1;
		this.defaultFieldInt_1 = defaultFieldInt_1;
		this.protectedFieldInt_1 = protectedFieldInt_1;
		this.privateFieldInt_1 = privateFieldInt_1;
		this.publicFieldString_1 = publicFieldString_1;
		this.defaultFieldString_1 = defaultFieldString_1;
		this.protectedFieldString_1 = protectedFieldString_1;
		this.privateFieldString_1 = privateFieldString_1;
	}

	public int getPublicFieldInt_1() {
		return this.publicFieldInt_1;
	}

	public void setPublicFieldInt_1(int publicFieldInt_1) {
		this.publicFieldInt_1 = publicFieldInt_1;
	}

	public int getDefaultFieldInt_1() {
		return this.defaultFieldInt_1;
	}

	public void setDefaultFieldInt_1(int defaultFieldInt_1) {
		this.defaultFieldInt_1 = defaultFieldInt_1;
	}

	public int getProtectedFieldInt_1() {
		return this.protectedFieldInt_1;
	}

	public void setProtectedFieldInt_1(int protectedFieldInt_1) {
		this.protectedFieldInt_1 = protectedFieldInt_1;
	}

	public int getPrivateFieldInt_1() {
		return this.privateFieldInt_1;
	}

	public void setPrivateFieldInt_1(int privateFieldInt_1) {
		this.privateFieldInt_1 = privateFieldInt_1;
	}

	public String getPublicFieldString_1() {
		return this.publicFieldString_1;
	}

	public void setPublicFieldString_1(String publicFieldString_1) {
		this.publicFieldString_1 = publicFieldString_1;
	}

	public String getDefaultFieldString_1() {
		return this.defaultFieldString_1;
	}

	public void setDefaultFieldString_1(String defaultFieldString_1) {
		this.defaultFieldString_1 = defaultFieldString_1;
	}

	public String getProtectedFieldString_1() {
		return this.protectedFieldString_1;
	}

	public void setProtectedFieldString_1(String protectedFieldString_1) {
		this.protectedFieldString_1 = protectedFieldString_1;
	}

	public String getPrivateFieldString_1() {
		return this.privateFieldString_1;
	}

	public void setPrivateFieldString_1(String privateFieldString_1) {
		this.privateFieldString_1 = privateFieldString_1;
	}

}