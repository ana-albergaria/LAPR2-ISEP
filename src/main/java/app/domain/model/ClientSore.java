package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class ClientSore {

    private ArrayList<Client> clientList = new ArrayList<>();


    public Client registerClient(String clientsCitizenCardNumber, String nhsNumber, String birthDate, String sex,
                                 String tinNumber, String email, String name, String phoneNumber) {
        return new Client(clientsCitizenCardNumber, nhsNumber, birthDate, sex,
                tinNumber, email, name, phoneNumber);
    }

    public Client registerClient(String clientsCitizenCardNumber, String nhsNumber, String birthDate, String sex,
                                 String tinNumber, String email, String name) {
        return new Client(clientsCitizenCardNumber, nhsNumber, birthDate, sex,
                tinNumber, email, name);
    }

    public boolean validateClient(Client cl) {
        if (cl == null)
            return false;

        return !this.clientList.contains(cl);
    }

    public boolean saveClient(Client cl) {
        if (!validateClient(cl))
            return false;

        return this.clientList.add(cl);
    }

    public List<Client> getClient() {

        return clientList;
    }
}
