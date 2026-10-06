package src.main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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

        while (position < size
                && patients[position].getIdentity()
                .isLessThan(patient.getIdentity())) {
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

            PatientIdentity middleIdentity =
                    patients[middle].getIdentity();

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

    
    public boolean saveToFile(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);

            Iterator iterator = new Iterator();
            Patient patient;

            while ((patient = iterator.next()) != null) {
                writer.write(patient.toCSV());
                writer.write("\n");
            }

            writer.close();
            return true;

        } catch (IOException e) {
            return false;
        }
    }

    
    public boolean importFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                Patient patient = Patient.makePatient(line);

                if (patient != null) {
                    if (size < MAX_PATIENTS) {
                        patients[size] = patient;
                        size++;
                    }
                }
            }

            scanner.close();

            
            mergeSort(patients, 0, size - 1);

            return true;

        } catch (IOException e) {
            return false;
        }
    }

    
    private void mergeSort(Patient[] array, int low, int high) {
        if (low >= high) {
            return;
        }

        int middle = (low + high) / 2;

        mergeSort(array, low, middle);
        mergeSort(array, middle + 1, high);

        merge(array, low, middle, high);
    }

    //Merge two already-sorted portions of the array.
    private void merge(
            Patient[] array,
            int low,
            int middle,
            int high) {

        int leftSize = middle - low + 1;
        int rightSize = high - middle;

        Patient[] left = new Patient[leftSize];
        Patient[] right = new Patient[rightSize];

        for (int i = 0; i < leftSize; i++) {
            left[i] = array[low + i];
        }

        for (int i = 0; i < rightSize; i++) {
            right[i] = array[middle + 1 + i];
        }

        int leftIndex = 0;
        int rightIndex = 0;
        int arrayIndex = low;

        while (leftIndex < leftSize
                && rightIndex < rightSize) {

            if (left[leftIndex].getIdentity()
                    .isLessThan(right[rightIndex].getIdentity())) {

                array[arrayIndex] = left[leftIndex];
                leftIndex++;

            } else {

                array[arrayIndex] = right[rightIndex];
                rightIndex++;
            }

            arrayIndex++;
        }

        while (leftIndex < leftSize) {
            array[arrayIndex] = left[leftIndex];
            leftIndex++;
            arrayIndex++;
        }

        while (rightIndex < rightSize) {
            array[arrayIndex] = right[rightIndex];
            rightIndex++;
            arrayIndex++;
        }
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