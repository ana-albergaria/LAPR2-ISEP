package app.controller;

import app.domain.model.Client;
import app.domain.sort.algorithms.BubbleSort;
import app.domain.sort.algorithms.InsertionSort;
import app.domain.sort.comparators.alphabeticalNameClient;
import app.domain.sort.comparators.ascendTinClient;
import app.ui.console.utils.TestFileUtils;
import app.domain.store.TestStore;
import app.mappers.dto.ClientDTO;
import app.mappers.dto.TestDTO;
import app.mappers.dto.TestFileDTO;
import jdk.jfr.Timespan;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsultTestByClientTest {

    @Before
    public void setUp() throws ClassNotFoundException, InstantiationException, ParseException, IllegalAccessException, BarcodeException, OutputException, IOException {
        ImportTestController importTestController = new ImportTestController();
        TestFileUtils testFileUtils = new TestFileUtils();
        importTestController = new ImportTestController();
        List<TestFileDTO> procedData = testFileUtils.getTestsDataToDto("tests_CovidMATCPCSV.csv");
        for (TestFileDTO testData : procedData) {
            importTestController.importTestFromFile(testData);
        }
        TestStore testStore = App.getInstance().getCompany().getTestStore();
        CreateTestController createTestController = new CreateTestController("001WA");
        List<String> params = new ArrayList<>();
        params.add("WBC00");
        params.add("RBC00");
        createTestController.createTest("123456789012", "2100000004", "Blood", params);
        createTestController.saveTest();
        App.getInstance().getCompany().getTestStore().getTestsByClient(App.getInstance().getCompany().getClientStore().getClientByTinNumber("2100000004")).get(1).setDateOfValidation(new Date());
    }
    
    @Test
    public void getClientsDtoInOrder() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        ConsultTestByClient consultTestByClient = new ConsultTestByClient();

        List<ClientDTO> clientDTOList = consultTestByClient.getClientsDtoInOrder("name");

          for(ClientDTO client : clientDTOList){
            System.out.println(client);
            System.out.println();
        }

    }

    @Test
    public void runtimeTestForInsertion(){
        List<Client> clients = App.getInstance().getCompany().getClientStore().getClients();
        InsertionSort insertionSort = new InsertionSort();
/*        long start2 = System.currentTimeMillis();*/
            insertionSort.sort(clients, new ascendTinClient());
/*        long end2 = System.currentTimeMillis();
        System.out.println("Elapsed Time in milli seconds: "+ (end2-start2));*/
    }

    @Test
    public void runtimeTestForBubble(){
        List<Client> clients = App.getInstance().getCompany().getClientStore().getClients();
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.bubbleSortArrayList(clients, new ascendTinClient());
    }

    @Test
    public void getTestsOfClientDto(){
        ConsultTestByClient consultTestByClient = new ConsultTestByClient();
        List<TestDTO> testDTOS = consultTestByClient.getValidatedTestsOfClient("2100000004");
        for (TestDTO testDTO : testDTOS){
            System.out.println(testDTO.toStringWithAllData());
        }
    }
}