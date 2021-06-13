package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.store.TestStore;
import app.mappers.TestMapper;
import app.mappers.dto.TestDTO;

import java.util.*;

/**
 * @author Marta Ribeiro 1201592
 */
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
     * @return list of client tests with results if withResults is true
     * otherwise return list of client tests without results
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
        List<Test> orderedTests = orderTests(desiredList);
        TestMapper mapper = new TestMapper();
        return mapper.toDTO(orderedTests);
    }

    public List<Test> orderTests(List<Test> clientWantedTests){
        List<Test> orderedTests = new ArrayList<>();
        orderedTests.add(clientWantedTests.get(0));
        int previousIndex = -1;
        int followingIndex = -1;
        for (Test test : clientWantedTests){
            for (int i = 0; i < orderedTests.size(); i++) {
                if (test.getDateOfTestRegistration().after(orderedTests.get(i).getDateOfTestRegistration())){
                    previousIndex=i;
                }
                if (test.getDateOfTestRegistration().before(orderedTests.get(i).getDateOfTestRegistration())){
                    followingIndex=i;
                }
            }
            if (previousIndex!=-1){
                orderedTests.add(previousIndex+1,test);
            } else if (previousIndex==-1 && followingIndex!=-1){
                orderedTests.add(followingIndex-1,test);
            }
            previousIndex=-1;
            followingIndex=-1;
        }
        return orderedTests;
    }

}
