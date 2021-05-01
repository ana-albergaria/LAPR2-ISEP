package app.domain.model;

import org.apache.commons.lang3.StringUtils;

/**
 * This class allows the construction of a class hierarchy
 * to represent different types of laboratories.
 * Specifies common characteristics to all the
 * hierarchy classes.
 *
 * @author Ana Albergaria 1201518
 */

public class Laboratory {
    /**
     * The name of the Laboratory.
     */
    private String name;

    /**
     * The address of the Laboratory.
     */
    private String address;

    /**
     * The phone number the Laboratory.
     */
    private String phoneNumber;

    /**
     * The TIN number of the Laboratory.
     */
    private String numTIN;

    /**
     * Builds a Laboratory's instance receiving:
     * the name, the address, the phone number, the TIN number
     *
     * @param name the name of the Laboratory
     * @param address the address of the Laboratory
     * @param phoneNumber the phone number of the Laboratory
     * @param numTIN the TIN number of the Laboratory
     */
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

    /**
     * It returns the textual description of the Laboratory instance.
     *
     * @return characteristics of the Laboratory
     */
    @Override
    public String toString() {
        return String.format("LABORATORY%nName: %s%n"
                + "Address: %s%nPhoneNumber: %s%nTIN Number: %s%n", name, address, phoneNumber, numTIN);
    }

    public void checkNameRules(String name) {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be blank.");
        if ((!name.matches("^[ A-Za-z]+$")))
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
        //if ((!phoneNumber.chars().allMatch(Character::isDigit)))
        if ((!phoneNumber.matches("[0-9]+")))
            throw new IllegalArgumentException("Phone Number must only contain digits.");
        if (phoneNumber.length() != 11)
            throw new IllegalArgumentException("Phone Number must contain exactly 11 digits.");

    }

    public void checkTINNumberRules(String numTIN) {
        if (StringUtils.isBlank(numTIN))
            throw new IllegalArgumentException("TIN Number cannot be blank.");
        //if ((!numTIN.chars().allMatch(Character::isDigit)))
        if ((!numTIN.matches("[0-9]+")))
            throw new IllegalArgumentException("TIN Number must only contain digits.");
        if (numTIN.length() != 10)
            throw new IllegalArgumentException("TIN Number must contain exactly 10 digits.");
    }

    @Override
    public boolean equals(Object otherObject) {
        if(this == otherObject)
            return true;

        if(otherObject == null || this.getClass() != otherObject.getClass())
            return false;

        Laboratory otherLaboratory = (Laboratory) otherObject;

        return this.name.equalsIgnoreCase(otherLaboratory.name) &&
                this.address.equalsIgnoreCase(otherLaboratory.address) &&
                this.phoneNumber.equalsIgnoreCase(otherLaboratory.phoneNumber) &&
                this.numTIN.equalsIgnoreCase(otherLaboratory.numTIN);
    }








}
