public class PatientList {
    private static final int MAX_PATIENTS = 1000;

    private Patient[] patients;
    private int size;

    public PatientList() {
        patients = new Patient[MAX_PATIENTS];
        size = 0;
    }

    public boolean add(Patient patient) {
        if (size >= MAX_PATIENTS || patient == null) {
            return false;
        }

        int position = 0;

        while (position < size && patients[position].getIdentity().isLessThan(patient.getIdentity())) {
            position++;
        }

        for (int i = size; i > position; i--) {
            patients[i] = patients[i - 1];
        }

        patients[position] = patient;
        size++;

        return true;
    }

    public Patient find(PatientIdentity identity) {
        if (identity == null) {
            return null;
        }

        int low = 0;
        int high = size - 1;

        while (low <= high) {
            int middle = (low + high) / 2;
            PatientIdentity middleIdentity = patients[middle].getIdentity();

            if (middleIdentity.match(identity)) {
                return patients[middle];
            }

            if (middleIdentity.isLessThan(identity)) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }

        return null;
    }

    public int size() {
        return size;
    }

    public class Iterator {
        private int current;

        public Iterator() {
            current = 0;
        }

        public Patient next() {
            if (current >= size) {
                return null;
            }

            return patients[current++];
        }
    }
}
