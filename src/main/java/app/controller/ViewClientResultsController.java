package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.store.TestStore;
import app.mappers.TestMapper;
import app.mappers.dto.TestDTO;

import java.util.ArrayList;
import java.util.List;

public class ViewClientResultsController {

    /**
     * The company associated to the Controller.
     */
    private Company company;

    /**
     * Builds an empty constructor for having the actual instance of the company when instantiated.
     */
    public ViewClientResultsController(){
        this(App.getInstance().getCompany());
    }

    /**
     * Builds a View Client Results Controller's instance receiving the company.
     *
     * @param company company associated to the Controller.
     */
    public ViewClientResultsController(Company company){
        this.company = company;
    }

    /**
     * Retrieves list of client tests with or list of client tests without results
     * @param client the client
     * @param withResults whether the list to be returned is the list of client tests with or without results
     * @return list of client tests with or list of client tests without results
     */
    public List<TestDTO> getClientTestsWithOrWithoutResults(Client client, boolean withResults){
        TestStore tstStore = this.company.getTestStore();
        List<Test> clientTests = tstStore.getTestsByClient(client);
        ArrayList<Test> desiredList = new ArrayList<>();
        for (Test test : clientTests){
            if (test.hasResults() && withResults){
                desiredList.add(test);
            } else if (!test.hasResults() && !withResults){
                desiredList.add(test);
            }
        }
        TestMapper mapper = new TestMapper();
        return mapper.toDTO(desiredList);
    }


}
