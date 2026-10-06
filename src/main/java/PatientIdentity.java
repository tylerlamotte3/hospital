package src.main.java;

import java.util.Date;

public class PatientIdentity {
    private Name name;
    private Date dateOfBirth;

    public PatientIdentity(Name name, Date dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public Name getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public boolean match(PatientIdentity other) {
        return name.match(other.name)
                && dateOfBirth.equals(other.dateOfBirth);
    }

    public boolean isLessThan(PatientIdentity other) {
        if (name.isLessThan(other.name)) {
            return true;
        }

        if (name.match(other.name)) {
            return dateOfBirth.compareTo(other.dateOfBirth) < 0;
        }

        return false;
    }

    public String patientToString() {
        return name.nameToString() + ", " + dateOfBirth.toString();
    }

    @Override
    public String toString() {
        return "name: " + name.toString()
                + " dob: " + dateOfBirth.toString();
    }
}