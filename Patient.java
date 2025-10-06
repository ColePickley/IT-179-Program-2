/*
 * Created on: September 9, 2025
 * 
 * ULID: ctpickl
 * Class: IT 179
 */
package ilstu.edu;

/*
 * Acts as the parent class for both Covid19Patient and RegularPatient
 * 
 * @author Cole Pickley
 */
public abstract class Patient {
	private int id;
	private String fName;
	private String lName;
	private int age;
	private boolean pcr;
	
	/**
	 * Constructor for the Patient class
	 * 
	 * Sets the following local variables: id, fName, lName, and age
	 * 
	 * @param id
	 * @param fName
	 * @param lName
	 * @param age
	 */
	public Patient(int id, String fName, String lName, int age) {
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.age = age;
	}
	
	/**
	 * Getter for pcr variable
	 * 
	 * @return boolean
	 */
	public boolean getPcr() {
		return pcr;
	}
	
	/**
	 * Setter for pcr variable
	 * 
	 * @param pcr
	 */
	public void setPcr(boolean pcr) {
		this.pcr = pcr;
	}
	
	/**
	 * Getter for id variable
	 * 
	 * @return int
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Setter for id variable
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Getter for fName variable
	 * 
	 * @return String
	 */
	public String getfName() {
		return fName;
	}
	
	/**
	 * Setter for fName variable
	 * 
	 * @param fName
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}
	
	/**
	 * Getter for lName variable
	 * 
	 * @return String
	 */
	public String getlName() {
		return lName;
	}
	
	/**
	 * Setter for lName variable
	 * 
	 * @param lName
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}
	
	/**
	 * Getter for age variable
	 * 
	 * @return int
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Setter for the age variable
	 * 
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * Abstract method for treating Patient objects
	 * 
	 * @return String
	 */
	public abstract String treat();
	
	/**
	 * Returns a String containing the Patient's information
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		String output =  "ID: " + id + 
				"\nFull Name: " + fName + " " + lName + 
				"\nAge: " + age;
		if (pcr)
			output += "\nPCR Test Result: Positive";
		else
			output += "\nPCR Test Result: Negative";
		return output;
	}
}
