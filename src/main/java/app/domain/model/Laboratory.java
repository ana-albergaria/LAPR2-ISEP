package app.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Laboratory {
    private String name;
    private String address;
    private String phoneNumber;
    private String numTIN;
    //private List<Laboratory> labList;
    private List<ClinicalAnalysisLaboratory> calList;

    public Laboratory(String name, String address, String phoneNumber, String numTIN) {
        checkNameRules(name);
        checkAddressRules(address);
        checkPhoneNumberRules(phoneNumber);
        checkTINNumberRules(numTIN);
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.numTIN = numTIN;
    }

    @Override
    public String toString() {
        return String.format("LABORATORY%nName: %s%n"
                + "Address: %s%nPhoneNumber: %s%nTIN Number: %s%n", name, address, phoneNumber, numTIN);
    }

    public void checkNameRules(String name) {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be blank.");
        if ((!name.chars().allMatch(Character::isLetter)))
            throw new IllegalArgumentException("Name must only contain letters.");
        if (name.length() > 20)
            throw new IllegalArgumentException("Name cannot have more than 20 characters.");
    }


    public void checkAddressRules(String address) {
        if (StringUtils.isBlank(address))
            throw new IllegalArgumentException("Address cannot be blank.");
        if (address.length() > 30)
            throw new IllegalArgumentException("Address cannot have more than 30 characters.");
    }


    public void checkPhoneNumberRules(String phoneNumber) {
        if (StringUtils.isBlank(phoneNumber))
            throw new IllegalArgumentException("Phone Number cannot be blank.");
        if ((!phoneNumber.matches("[0-9]+")))
            throw new IllegalArgumentException("Phone Number must only contain digits.");
        if (phoneNumber.length() != 11)
            throw new IllegalArgumentException("Phone Number must contain exactly 11 digits.");

    }

    public void checkTINNumberRules(String numTIN) {
        if (StringUtils.isBlank(numTIN))
            throw new IllegalArgumentException("TIN Number cannot be blank.");
        if ((!numTIN.chars().allMatch(Character::isDigit)))
            throw new IllegalArgumentException("TIN Number must only contain digits.");
        if (numTIN.length() != 10)
            throw new IllegalArgumentException("TIN Number must contain exactly 10 digits.");
    }

    public ClinicalAnalysisLaboratory createClinicalAnalysisLaboratory(String laboratoryID,
                                                                       String name,
                                                                       String address,
                                                                       String phoneNumber,
                                                                       String numTIN,
                                                                       List<TestType> selectedTT) {
        return new ClinicalAnalysisLaboratory(laboratoryID, name, address,
                phoneNumber, numTIN, selectedTT);
    }

    public boolean validateClinicalAnalysisLaboratory(ClinicalAnalysisLaboratory cal){
        if (cal == null)
            return false;
        return ! this.calList.contains(cal);
    }

    public boolean saveClinicalAnalysisLaboratory(ClinicalAnalysisLaboratory cal){
        if (!validateClinicalAnalysisLaboratory(cal))
            return false;
        return this.calList.add(cal);
    }

    //SUPOSTAMENTE ESTÁ COMPLETO!
    //FALTA FAZER VALIDAÇÃO DOS ACCEPTING CRITERIA LOCALMENTE + TOSTRING





}
