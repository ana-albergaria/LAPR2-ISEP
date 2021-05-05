package app.mappers.dto;

/**
 * @author Ana Albergaria 1201518
 */

public class LaboratoryDTO {
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
    public LaboratoryDTO(String name, String address, String phoneNumber, String numTIN) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.numTIN = numTIN;
    }

    /**
     * Returns the name of the Laboratory
     *
     * @return name of the Laboratory
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the address of the Laboratory
     *
     * @return address of the Laboratory
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the phone number of the Laboratory
     *
     * @return phone number of the Laboratory
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns the TIN number of the Laboratory
     *
     * @return TIN number of the Laboratory
     */
    public String getNumTIN() {
        return numTIN;
    }
}
