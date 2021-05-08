package app.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Clinical Analysis Laboratory through:
 * a Laboratory ID, a name, an address, a phone number, a TIN number and the type of tests
 *
 * @author Ana Albergaria 1201518
 */

public class ClinicalAnalysisLaboratory extends Laboratory {
    /**
     * The laboratory ID of the Clinical Analysis Laboratory.
     */
    private String laboratoryID;

    /**
     * The type of tests the Clinical Analysis Laboratory operates.
     */
    private List<TestType> selectedTT; //ClinicalAnalysisLaboratory operates * TestType

    /**
     * Builds a Clinical Analysis Laboratory's instance receiving:
     * the Laboratory ID, the name, the address, the phone number, the TIN number and
     * the type of tests
     *
     * @param laboratoryID the laboratory ID of the Clinical Analysis Laboratory
     * @param name         the name of the Clinical Analysis Laboratory
     * @param address      the address of the Clinical Analysis Laboratory
     * @param phoneNumber  the phone number of the Clinical Analysis Laboratory
     * @param numTIN       the TIN number of the Clinical Analysis Laboratory
     * @param selectedTT   the type of tests the Clinical Analysis Laboratory operates
     */
    public ClinicalAnalysisLaboratory(String laboratoryID,
                                      String name,
                                      String address,
                                      String phoneNumber,
                                      String numTIN,
                                      List<TestType> selectedTT) {
        super(name, address, phoneNumber, numTIN);
        checkLaboratoryIDRules(laboratoryID);
        checkSelectedTestTypesRules(selectedTT);
        this.laboratoryID = laboratoryID;
        this.selectedTT = new ArrayList<>(selectedTT);
    }

    /**
     * Returns the laboratory ID of the Clinical Analysis Laboratory
     *
     * @return laboratory ID of the Clinical Analysis Laboratory
     */
    public String getLaboratoryID() {
        return laboratoryID;
    }

    /**
     * Returns the test types of the Clinical Analysis Laboratory
     *
     * @return test types of the Clinical Analysis Laboratory
     */
    public List<TestType> getSelectedTT() {
        return new ArrayList<>(selectedTT);
    }

    /**
     * It returns the textual description of the Clinical Analysis Laboratory instance.
     *
     * @return characteristics of the Clinical Analysis Laboratory
     */
    @Override
    public String toString() {
        List<TestType> copy = new ArrayList<>(selectedTT);

        StringBuilder s = new StringBuilder();
        for (TestType tt : copy) {
            s.append(tt);
            s.append("\n");
        }
            return String.format("%sLaboratory ID: %s%nTest Types: %n%s",
                    super.toString(), laboratoryID, s);
        }

        /**
         * Returns true if the laboratory ID received in the parameter respects
         * all the rules.
         * It returns false as soon as one of these conditions are not verified
         * by the laboratory ID provided:
         * - It is blank
         * - It has a length different than 5
         * - It doesn't contain only alphanumeric characters.
         *
         * @param laboratoryID the laboratory ID of the Clinical Analysis Laboratory
         *
         * @return true if the laboratory ID respects all the rules,
         *         otherwise returns false
         */
        private void checkLaboratoryIDRules (String laboratoryID){
            if (StringUtils.isBlank(laboratoryID))
                throw new IllegalArgumentException("Laboratory ID cannot be blank.");
            if ((laboratoryID.length() != 5))
                throw new IllegalArgumentException("Laboratory ID cannot have more or less than five alphanumeric characters.");
            if (!StringUtils.isAlphanumeric(laboratoryID))
                throw new IllegalArgumentException("Laboratory ID must only have alphanumeric characters.");
        }

        /**
         * Returns true if the list of the Test Types received in the parameter respects
         * all the rules.
         * It returns false as soon as one of these conditions are not verified
         * by the List of Test Types provided:
         * - It is null or it is empty
         *
         * @param selectedTT the type of tests the Clinical Analysis Laboratory operates
         *
         *
         * @return true if the list of Test Types respects all the rules,
         *         otherwise returns false
         */
        private void checkSelectedTestTypesRules (List < TestType > selectedTT) {
            if (selectedTT == null || selectedTT.isEmpty())
                throw new IllegalArgumentException("The list containing the Types of Test cannot be blank.");
        }

        /**
         * Compares the Clinical Analysis Laboratory with the received object.
         *
         * @param otherObject the object to be compared with the Clinical Analysis Laboratory
         * @return true if the received object represents other Clinical Analysis Laboratory
         * equivalent to the Clinical Analysis Laboratory. Otherwise, returns false.
         */
        @Override
        public boolean equals (Object otherObject){
            if (!super.equals(otherObject))
                return false;

            ClinicalAnalysisLaboratory instance = (ClinicalAnalysisLaboratory) otherObject;

            return this.laboratoryID.equalsIgnoreCase(instance.laboratoryID) &&
                    this.selectedTT.equals(instance.selectedTT);
        }


    }
