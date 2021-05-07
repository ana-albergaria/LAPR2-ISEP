package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.shared.utils.PasswordUtils;

import java.util.Date;

public class RegisterClientController {

    private Company company;
    private Client cl;
    private String generatedPassword;

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

    public boolean RegisterClient(String clientsCitizenCardNumber, String nhsNumber, Date birthDate,
                                  String tinNumber, String email, String name, String phoneNumber) {
        this.cl = this.company.getClientStore().registerClient(clientsCitizenCardNumber, nhsNumber, birthDate,
                tinNumber, email, name, phoneNumber);
        return this.company.getClientStore().validateClient(cl);
    }

    public boolean saveClient() {
        return this.company.getClientStore().saveClient(cl);
    }

    public boolean makeClientAnUserAndSendPassword() {
        if(makeClientAnUser())
            return PasswordUtils.writePassword(generatedPassword, cl.getEmail());
        return false;
    }

    public boolean makeClientAnUser (){
        this.generatedPassword = PasswordUtils.generateRandomPassword();
        if(this.generatedPassword != null)
            return this.company.getAuthFacade().addUser(cl.getName(), cl.getEmail(), generatedPassword);
        return false;
    }

    public Client getClient() {
        return cl;
    }


}
