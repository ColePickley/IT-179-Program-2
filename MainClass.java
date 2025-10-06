/*
 * Created on: September 9, 2025
 * 
 * ULID: ctpickl
 * Class: IT 179
 */
package ilstu.edu;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * Runs and controls the flow of the program
 * 
 * @author Cole Pickley
 */
public class MainClass {
	private static ArrayList<Patient> patients = new ArrayList<>();
	private static int idCounter = 0;
	private static boolean programRunning = true;
	private static int scanIn = 0;
	private static Scanner scan = new Scanner(System.in);
	
	/**
	 * Runs the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		while (programRunning) {
			printStartScreen();
			while (!scan.hasNextInt()) {
				scan.nextLine();
				System.out.println("Invalid Input");
				printStartScreen();
			}
			scanIn = scan.nextInt();
			scan.nextLine();
			switch (scanIn) {
				case 1:
					admitPatient();
					break;
				case 2:
					printPatientInfo();
					break;
				case 3:
					submitPRCTest();
					break;
				case 4:
					doRounds();
					break;
				case 5:
					dischargePatient();
					break;
				case 6:
					System.out.println();
					System.out.println("Have a nice day!");
					programRunning = false;
					break;
				default: System.out.println("Invalid Input");
			}
			scanIn = 0;
		}
	}
	
	/**
	 * Prints the start screen
	 */
	private static void printStartScreen() {
		System.out.println();
		System.out.println("Type a number correspeonding to one of the following options:");
		System.out.println("1. Admit a patient");
		System.out.println("2. Print patient information");
		System.out.println("3. Submit a PCR test result");
		System.out.println("4. Do rounds");
		System.out.println("5. Discharge patient");
		System.out.println("6. Exit");
		System.out.println();
	}
	
	/**
	 * Creates a new Patient object and stores it in the patients ArrayList
	 */
	private static void admitPatient() {
		String fName, lName, pcrIn;
		String mainSymptom = "";
		int age;
		boolean pcr = false;
		boolean inputValid = false;
		double temperature = -1;
		System.out.println();
		System.out.println("Enter patient name (<first> <last>):");
		fName = scan.next();
		lName = scan.next();
		scan.nextLine();
		System.out.println("Enter patient age:");
		while (!scan.hasNextInt()) {
			scan.nextLine();
			System.out.println("Invalid Input");
			System.out.println("Enter patient age:");
		}
		age = scan.nextInt();
		scan.nextLine();
		while (!inputValid) {
			System.out.println("Enter PCR test result (type 'p' for possitive or 'n' for negative):");
			pcrIn = scan.next();
			scan.nextLine();
			if (pcrIn.equals("p")) {
				pcr = true;
				System.out.println("Enter patient temperature:");
				while (!scan.hasNextDouble()) {
					scan.nextLine();
					System.out.println("Invalid Input");
					System.out.println("Enter patient temperature:");
				}
				temperature = scan.nextDouble();
				scan.nextLine();
				inputValid = true;
			}
			else if (pcrIn.equals("n")) {
				System.out.println("Enter main symptom:");
				System.out.println("(System processes treatment for coughing, runny nose, stuffy nose, and hyperglycemia)");
				mainSymptom = scan.next() + scan.nextLine();
				inputValid = true;
			}
			else
				System.out.println("Invalid Input");
		}
		if (pcr)
			patients.add(new Covid19Patient(idCounter, fName, lName, age, temperature));
		else
			patients.add(new RegularPatient(idCounter, fName, lName, age, mainSymptom));
		idCounter++;
	}
	
	/**
	 * Prints the info for all patients within the patients ArrayList
	 */
	private static void printPatientInfo() {
		if (patients.size() == 0) {
			System.out.println();
			System.out.println("Database empty");
		}
		else {
			for (int i = 0; i < patients.size(); i++) {
				System.out.println();
				System.out.println(patients.get(i));
			}
		}
	}
	
	/**
	 * Allows the user to submit a PRC test result for a specific patient
	 */
	private static void submitPRCTest() {
		boolean inputValid = false;
		int id = -1;
		String pcrIn;
		Patient p = null;
		System.out.println();
		if (patients.size() == 0)
			System.out.println("Datebase empty");
		else {
			System.out.println("Enter patient ID:");
			while (!inputValid) {
				while (!scan.hasNextInt()) {
					scan.nextLine();
					System.out.println("Invalid Input");
					System.out.println("Enter patient ID:");
				}
				id = scan.nextInt();
				p = getPatient(id);
				scan.nextLine();
				if (p == null) {
					System.out.println("Patient does not exist");
					System.out.println("Enter patient ID:");
				}
				else
					inputValid = true;
			}
			inputValid = false;
			while (!inputValid) {
				System.out.println("Enter PCR test result (type 'p' for possitive or 'n' for negative):");
				pcrIn = scan.next();
				scan.nextLine();
				if (pcrIn.equals("p")) {
					if (p instanceof RegularPatient) {
						System.out.println("Enter patient temperature:");
						while (!scan.hasNextDouble()) {
							scan.nextLine();
							System.out.println("Invalid Input");
							System.out.println("Enter patient temperature:");
						}
						double temperature = scan.nextDouble();
						scan.nextLine();
						patients.add(new Covid19Patient(p.getId(), p.getfName(), p.getlName(), p.getAge(), temperature));
						patients.remove(p);
						getPatient(id).setPcr(true);
						System.out.println("Test result submitted");
						inputValid = true;
					}
					else {
						System.out.println("New test result matches previous test");
						inputValid = true;
					}
				}
				else if (pcrIn.equals("n")) {
					if (p instanceof Covid19Patient) {
						patients.remove(p);
						System.out.println("Test result submitted and patient discharged");
						inputValid = true;
					}
					else {
						System.out.println("New test result matches previous test");
						inputValid = true;
					}
				}
				else
					System.out.println("Invalid Input");
			}
		}
	}
	
	/**
	 * Prompts the user to enter the temperatures for all the Covid19Patient objects in the patients ArrayList
	 * Calls the treat() method for each patient object
	 */
	private static void doRounds() {
		System.out.println();
		if (patients.size() == 0)
			System.out.println("Datebase empty");
		else {
			System.out.println("Enter the temperature of each patient as thier names appear:");
			for (int i = 0; i < patients.size(); i++) {
				if (patients.get(i) instanceof Covid19Patient) {
					Covid19Patient p = (Covid19Patient) patients.get(i);
					System.out.println("Name: " + p.getfName() + " " + p.getlName());
					while (!scan.hasNextDouble()) {
						scan.nextLine();
						System.out.println("Invalid Input");
					}
					p.setTemperature(scan.nextDouble());
					System.out.println("Patient ID: " + p.getId());
					System.out.println("Recommended treatment: " + p.treat());
				}
			}
			System.out.println("All COVID-19 patients treated");
		}
	}
	
	/**
	 * Prompts the user to choose a patient from within the patients ArrayList to remove
	 * Only removes the patient if their PRC test result is negative
	 */
	private static void dischargePatient() {
		int id;
		System.out.println();
		if (patients.size() == 0)
			System.out.println("Database empty");
		else {
			System.out.println("Enter patient ID:");
			while (!scan.hasNextInt()) {
				scan.nextLine();
				System.out.println("Invalid Input");
				System.out.println("Enter patient ID:");
			}
			id = scan.nextInt();
			Patient p = getPatient(id);
			if (p == null)
				System.out.println("Patient does not exist");
			else if (p.getPcr()) {
				System.out.println("Patient cannot be discharged due to possitive PCR test result");
			}
			else {
				patients.remove(p);
				System.out.println("Patient discharged");
			}
		}
	}
	
	/**
	 * Returns a Patient object from the patients ArrayList corresponding to the ID entered
	 * Returns null if no patient is found
	 * 
	 * @param id
	 * @return Patient
	 */
	private static Patient getPatient(int id) {
		for (int i = 0; i < patients.size(); i++) {
			if (patients.get(i).getId() == id) {
				return patients.get(i);
			}
		}
		return null;
	}
}
