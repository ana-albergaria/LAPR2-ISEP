package app.domain.store;

import app.domain.model.Client;
import app.domain.utils.Data;

import java.util.ArrayList;
import java.util.List;

public class ClientSore {

    private ArrayList<Client> clientList = new ArrayList<>();


    public app.domain.model.Client registerClient(String clientsCitizenCardNumber, String nhsNumber, Data birthDate, String sex,
                                                  String tinNumber, String email, String name, String phoneNumber) {
        return new app.domain.model.Client(clientsCitizenCardNumber, nhsNumber, birthDate, sex,
                tinNumber, email, name, phoneNumber);
    }

    public app.domain.model.Client registerClient(String clientsCitizenCardNumber, String nhsNumber, Data birthDate, String sex,
                                                  String tinNumber, String email, String name) {
        return new app.domain.model.Client(clientsCitizenCardNumber, nhsNumber, birthDate, sex,
                tinNumber, email, name);
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
