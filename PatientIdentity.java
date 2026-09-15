import java.util.Date;

public class PatientIdentity {
    private Name name;
    private Date dateOfBirth;

    public PatientIdentity(Name name, Date dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public boolean match(PatientIdentity other) {
        return name.match(other.name)
                && dateOfBirth.equals(other.dateOfBirth);
    }

    public boolean isLessThan(PatientIdentity identity) {
        if (!name.match(identity.name)) {
            return name.isLessThan(identity.name);
        }

        return dateOfBirth.compareTo(identity.dateOfBirth) < 0;
    }

    
    public String patientToString() {
        return name.nameToString() + ", " + dateOfBirth.toString();
    }
}