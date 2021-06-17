package app.controller;

import app.ui.console.utils.TestFileUtils;
import app.domain.store.TestStore;
import app.mappers.dto.ClientDTO;
import app.mappers.dto.TestDTO;
import app.mappers.dto.TestFileDTO;
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

        List<ClientDTO> clientDTOList = consultTestByClient.getClientsDtoInOrder("tin");

    /*      for(ClientDTO client : clientDTOList){
            System.out.println(client);
            System.out.println();
        }*/

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