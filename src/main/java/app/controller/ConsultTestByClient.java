package app.controller;

import app.domain.interfaces.RegressionModel;
import app.domain.interfaces.SortAlgorithm;
import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.sort.comparators.alphabeticalNameClient;
import app.domain.sort.comparators.ascendTinClient;
import app.domain.store.ClientStore;
import app.domain.store.TestStore;
import app.mappers.ClientsMapper;
import app.mappers.TestMapper;
import app.mappers.dto.ClientDTO;
import app.mappers.dto.TestDTO;

import java.util.Comparator;
import java.util.List;

public class ConsultTestByClient {
    /**
     * The company associated to the Controller.
     */
    private Company company;

    /**
     * Builds a Record Samples Controller's instance receiving the company.
     *
     * @param company company associated to the Controller.
     */
    public ConsultTestByClient(Company company) {
        this.company = company;
    }

    public ConsultTestByClient(){
        this(App.getInstance().getCompany());
    }



    public List<ClientDTO> getClientsDtoInOrder(String compareBy) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        List<Client> clients = getClients();
        SortAlgorithm sortAlgorithm = this.company.getSortAlgorithm();
        if(compareBy.equals("tin")){
            sortAlgorithm.sortClientsList(clients, new ascendTinClient());
        }else{
            sortAlgorithm.sortClientsList(clients, new alphabeticalNameClient());
        }
        return getClientsDto(clients);
    }

    public List<TestDTO> getTestsOfClientDto(String clientTin){
        TestMapper testMapper = new TestMapper();
        return testMapper.toDTO(getTestsOfClient(clientTin));
    }

    private List<Test> getTestsOfClient(String clientTin){
        TestStore testStore =  this.company.getTestStore();
        return testStore.getTestsByClientTin(clientTin);
    }

    public List<ClientDTO> getClientsDto(List<Client> clients){
        ClientsMapper clientsMapper = new ClientsMapper();
        return clientsMapper.toDTO(clients);
    }

    private List<Client> getClients(){
        ClientStore clientStore = this.company.getClientStore();
        return clientStore.getClients();
    }


}