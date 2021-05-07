package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;

import java.util.Date;

public class RegisterClientController {

    private Company company;
    private Client cl;

    public RegisterClientController() {
        this(App.getInstance().getCompany());
    }

    public RegisterClientController(Company application) {
        this.company = application;
        this.cl = null;
    }


    public boolean RegisterClient(String clientsCitizenCardNumber, String nhsNumber, Date birthDate, String sex,
                                  String tinNumber, String email, String name, String phoneNumber) {
        this.cl = this.company.getClientStore().registerClient(clientsCitizenCardNumber, nhsNumber, birthDate, sex,
                tinNumber, email, name, phoneNumber);
        return this.company.getClientStore().validateClient(cl);
    }

    public boolean RegisterClient(String clientsCitizenCardNumber, String nhsNumber, Date birthDate, String sex,
                                  String tinNumber, String email, String name) {
        this.cl = this.company.getClientStore().registerClient(clientsCitizenCardNumber, nhsNumber, birthDate, sex,
                tinNumber, email, name);
        return this.company.getClientStore().validateClient(cl);
    }

    public boolean saveClient() {

        return this.company.getClientStore().saveClient(cl);
    }

    public boolean makeClientAnUser() {
        return this.company.getAuthFacade().addUser(cl.getName(), cl.getEmail(), cl.getPsw());
    }


    public Client getClient() {
        return cl;
    }


}
