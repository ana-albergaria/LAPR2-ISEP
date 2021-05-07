package app.domain.store;

import app.domain.model.Client;
import app.domain.model.Employee;

import java.util.ArrayList;
import java.util.List;

import java.util.Date;

public class ClientStore {

    private ArrayList<Client> clientList = new ArrayList<>();


    public Client registerClient(String clientsCitizenCardNumber, String nhsNumber, Date birthDate, String sex,
                                                  String tinNumber, String email, String name, String phoneNumber) {
        return new Client(clientsCitizenCardNumber, nhsNumber, birthDate, sex,
                tinNumber, email, name, phoneNumber);
    }

    public Client registerClient(String clientsCitizenCardNumber, String nhsNumber, Date birthDate,
                                                  String tinNumber, String email, String name, String phoneNumber) {
        return new Client(clientsCitizenCardNumber, nhsNumber, birthDate,
                tinNumber, email, name, phoneNumber);
    }

    public boolean validateClient(app.domain.model.Client cl) {
        if (cl == null)
            return false;

        return !this.clientList.contains(cl);
    }

    public boolean saveClient(app.domain.model.Client cl) {
        if (!validateClient(cl))
            return false;
        return this.clientList.add(cl);
    }

    public Client[] toArray() {
        Client[] array = new Client[this.clientList.size()];
        return this.clientList.toArray(array);
    }

    public List<app.domain.model.Client> getClients() {
        return clientList;
    }

}
