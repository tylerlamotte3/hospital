package edu.frederick.cmsc230;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        // Create two names
        Name name1 = new Name("John", "Smith");
        Name name2 = new Name("Jane", "Doe");

        // Display the names
        System.out.println("Name 1: " + name1.fullname());
        System.out.println("Name 2: " + name2.fullname());

        // Create dates of birth
        Date dob1 = new Date(100, 0, 1);
        Date dob2 = new Date(101, 5, 15);

        // Create patient identities
        PatientIdentity identity1 = new PatientIdentity(name1, dob1);
        PatientIdentity identity2 = new PatientIdentity(name2, dob2);

        // Create patients
        Patient patient1 = new Patient(identity1);
        Patient patient2 = new Patient(identity2);

        // Display patient identities
        System.out.println("Patient 1: " + patient1.getIdentity());
        System.out.println("Patient 2: " + patient2.getIdentity());

        // Test matching
        System.out.println("Do the patients match? "
                + patient1.getIdentity().match(patient2.getIdentity()));

        // Test sorting
        System.out.println("Is Patient 1 less than Patient 2? "
                + patient1.getIdentity().isLessThan(patient2.getIdentity()));
    }
}