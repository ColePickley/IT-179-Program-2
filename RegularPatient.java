/*
 * Created on: September 9, 2025
 * 
 * ULID: ctpickl
 * Class: IT 179
 */
package ilstu.edu;

/*
 * Extends Patient class
 * Contains methods and variables pertaining exclusively to regular patients
 * 
 * @author Cole Pickley
 */
public class RegularPatient extends Patient {
	private String mainSymptom;
	
	/**
	 * Constructor for RegularPatient class
	 * 
	 * Calls the super constructor
	 * Sets the mainSymptom and pcr variables
	 * 
	 * @param id
	 * @param fName
	 * @param lName
	 * @param age
	 * @param temperature
	 */
	public RegularPatient(int id, String fName, String lName, int age, String mainSymptom) {
		super(id, fName, lName, age);
		this.mainSymptom = mainSymptom;
		super.setPcr(false);
	}
	
	/**
	 * Returns suggested treatment for patient
	 * 
	 * @return String
	 */
	@Override
	public String treat() {
		if (mainSymptom.toLowerCase().equals("coughing") || 
				mainSymptom.toLowerCase().equals("runny nose") || 
				mainSymptom.toLowerCase().equals("stuffy nose"))
			return "Amoxicillin";
		else if (mainSymptom.toLowerCase().equals("hyperglycemia"))
			return "Insulin";
		return "IV Fluids";
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
				"\nMain Symptom: " + mainSymptom + 
				"\nTreatment: " + this.treat();
	}
}
