package src.main.java;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        PatientList patients = new PatientList();

        Name name1 = new Name("John", "Smith");
        Name name2 = new Name("Jane", "Doe");
        Name name3 = new Name("Bob", "Adams");

        Date dob1 = new Date(100, 0, 1);
        Date dob2 = new Date(101, 5, 15);
        Date dob3 = new Date(99, 3, 20);

        Patient patient1 =
                new Patient(new PatientIdentity(name1, dob1));

        Patient patient2 =
                new Patient(new PatientIdentity(name2, dob2));

        Patient patient3 =
                new Patient(new PatientIdentity(name3, dob3));

        patients.add(patient1);
        patients.add(patient2);
        patients.add(patient3);

        System.out.println("Number of patients: "
                + patients.size());

        System.out.println("\nPatients:");

        PatientList.Iterator iterator = patients.new Iterator();

        Patient patient;

        while ((patient = iterator.next()) != null) {
            System.out.println(patient.toCSV());
        }

        //Test save
        System.out.println("\nSaving patients...");

        boolean saved = patients.saveToFile("patients.csv");

        System.out.println("Save successful: " + saved);

        //Test import.
        PatientList importedPatients = new PatientList();

        boolean imported =
                importedPatients.importFromFile("patients.csv");

        System.out.println("Import successful: " + imported);

        System.out.println("\nImported patients:");

        PatientList.Iterator importedIterator =
                importedPatients.new Iterator();

        while ((patient = importedIterator.next()) != null) {
            System.out.println(patient.toCSV());
        }

    
        //Test find.
        PatientIdentity searchIdentity =
                new PatientIdentity(
                        new Name("Jane", "Doe"),
                        new Date(101, 5, 15));

        Patient found =
                importedPatients.find(searchIdentity);

        System.out.println("\nFound patient:");

        if (found != null) {
            System.out.println(found.toCSV());
        } else {
            System.out.println("Patient not found.");
        }
    }
}