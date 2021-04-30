package app.domain.model;

import org.apache.commons.lang3.StringUtils;

public class Laboratory {
    private String name;
    private String address;
    private String phoneNumber;
    private String numTIN;
    private LaboratoryStore storeLab; //Laboratory uses LaboratoryStore

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
    public void checkPhoneNumberRules(String phoneNumber) {
        if (StringUtils.isBlank(phoneNumber))
            throw new IllegalArgumentException("Phone Number cannot be blank.");
        if ((!phoneNumber.chars().allMatch(Character::isDigit)))
            throw new IllegalArgumentException("Phone Number must only contain digits.");
        if (phoneNumber.length() != 11)
            throw new IllegalArgumentException("Phone Number must contain exactly 11 digits.");

    }
    //INCOMPLETO!
    public void checkTINNumberRules(String numTIN) {
        if (StringUtils.isBlank(numTIN))
            throw new IllegalArgumentException("TIN Number cannot be blank.");
        if ((!numTIN.chars().allMatch(Character::isDigit)))
            throw new IllegalArgumentException("TIN Number must only contain digits.");
        if (numTIN.length() != 10)
            throw new IllegalArgumentException("TIN Number must contain exactly 10 digits.");
    }

    //FALTA FAZER VALIDAÇÃO DOS ACCEPTING CRITERIA LOCALMENTE + TOSTRING





}
