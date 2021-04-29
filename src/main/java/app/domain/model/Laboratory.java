package app.domain.model;

import java.util.List;

public class Laboratory {
    private String name;
    private String address;
    private int phoneNumber;
    private int numTIN;
    private LaboratoryStore storeLab; //Company uses LaboratoryStore

    public Laboratory(String name, String address, int phoneNumber, int numTIN) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.numTIN = numTIN;
    }

    public LaboratoryStore getLaboratoryStore() {
        return storeLab;
    }




}
