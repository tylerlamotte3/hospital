package src.main.java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Patient {
    private PatientIdentity identity;

    public Patient(PatientIdentity identity) {
        this.identity = identity;
    }

    public PatientIdentity getIdentity() {
        return identity;
    }

    public String toCSV() {
        return identity.getName().fullname() + ", "
                + new SimpleDateFormat("MM-dd-yyyy").format(identity.getDateOfBirth());
    }

    public static Patient makePatient(String line) {
        try {
            Scanner scanner = new Scanner(line);
            scanner.useDelimiter(",");

            if (!scanner.hasNext()) {
                scanner.close();
                return null;
            }

            String lastName = scanner.next().trim();

            if (!scanner.hasNext()) {
                scanner.close();
                return null;
            }

            String firstName = scanner.next().trim();

            if (!scanner.hasNext()) {
                scanner.close();
                return null;
            }

            String dateString = scanner.next().trim();

            scanner.close();

            if (lastName.isEmpty() || firstName.isEmpty() || dateString.isEmpty()) {
                return null;
            }

            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
            formatter.setLenient(false);

            Date dateOfBirth = formatter.parse(dateString);

            Name name = new Name(firstName, lastName);
            PatientIdentity identity = new PatientIdentity(name, dateOfBirth);

            return new Patient(identity);

        } catch (ParseException | RuntimeException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return identity.toString();
    }
}