package app.domain.model;

import org.apache.commons.lang3.StringUtils;

public class Laboratory {
    private String name;
    private String address;
    private int phoneNumber;
    private int numTIN;
    private LaboratoryStore storeLab; //Laboratory uses LaboratoryStore

    public Laboratory(String name, String address, int phoneNumber, int numTIN) {
        checkNameRules(name);
        checkAddressRules(address);
        checkPhoneNumberRules(phoneNumber);
        checkTINNumberRules(numTIN);
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.numTIN = numTIN;
    }

    public LaboratoryStore getLaboratoryStore() {
        return storeLab;
    }

    public void checkNameRules(String name) {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be blank.");
        if(StringUtils.isAlphanumeric(name))
            throw new IllegalArgumentException("Name must only contain letters.");
        if (name.length() > 20)
            throw new IllegalArgumentException("Name cannot have more than 20 characters.");
    }

    //INCOMPLETO?
    public void checkAddressRules(String address) {
        if (StringUtils.isBlank(address))
            throw new IllegalArgumentException("Address cannot be blank.");
        if (address.length() > 30)
            throw new IllegalArgumentException("Address cannot have more than 30 characters.");
    }

    //INCOMPLETO!
    public void checkPhoneNumberRules(int phoneNumber) {
        if (phoneNumber != 11)
            throw new IllegalArgumentException("Phone Number must contain exactly 11 digits.");
    }
    //INCOMPLETO!
    public void checkTINNumberRules(int numTIN) {
        if (numTIN != 10)
            throw new IllegalArgumentException("TIN Number must contain exactly 10 digits.");
    }

    //FALTA FAZER VALIDAÇÃO DOS ACCEPTING CRITERIA LOCALMENTE + TOSTRING





}
