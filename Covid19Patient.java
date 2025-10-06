/*
 * Created on: September 9, 2025
 * 
 * ULID: ctpickl
 * Class: IT 179
 */
package ilstu.edu;

/*
 * Extends Patient class
 * Contains methods and variables pertaining exclusively to COVID-19 patients
 * 
 * @author Cole Pickley
 */
public class Covid19Patient extends Patient{
	private double temperature;
	
	/**
	 * Constructor for Covid19Patient class
	 * 
	 * Calls the super constructor
	 * Sets the temperature and pcr variables
	 * 
	 * @param id
	 * @param fName
	 * @param lName
	 * @param age
	 * @param temperature
	 */
	public Covid19Patient(int id, String fName, String lName, int age, double temperature) {
		super(id, fName, lName, age);
		this.temperature = temperature;
		super.setPcr(true);
	}
	
	/**
	 * Getter for temperature variable
	 * 
	 * @return double
	 */
	public double getTemperature() {
		return temperature;
	}
	
	/**
	 * Setter for temperature variable
	 * 
	 * @param temperature
	 */
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
	/**
	 * Returns suggested treatment for patient
	 * 
	 * @return String
	 */
	@Override
	public String treat() {
		if (super.getAge() > 70 && temperature > 39.75) {
			if (temperature > 41)
				return "Paxlovid and Dexamethasone";
			return "Paxlovid";
		}
		else if (temperature > 41)
			return "Fluids, Acetaminophen, and Dexamethasone";
		return "Fluids and Acetaminophen";
	}
	
	/**
	 * Returns a String containing the Patient's information
	 * Calls super.toString()
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return super.toString() + 
				"\nTemperature: " + temperature + 
				"\nTreatment: " + this.treat();
	}
}
