package edu.frederick.cmsc230;

public class Patient {
    private PatientIdentity identity;

    public Patient(PatientIdentity identity) {
        this.identity = identity;
    }

    public PatientIdentity getIdentity() {
        return identity;
    }
}