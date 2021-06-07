package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.shared.Constants;
import app.domain.shared.utils.TestFileUtils;
import app.domain.store.ClientStore;
import app.domain.store.TestStore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ImportTestController {

    /**
     * Company instance of the session
     */
    private final Company company;


    /**
     * Empty constructor for having the actual instance of the company when instantiated.
     */
    public ImportTestController() {
        this(App.getInstance().getCompany());
    }

    /**
     * Construtor recieving the company as an argument
     *
     * @param company instance of company to be used
     */
    public ImportTestController(Company company) {
        this.company = company;
    }

    public boolean importTestsFromFile(String filePath) throws ParseException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        List<String[]>  processedListData = TestFileUtils.getTestDataByFile(filePath);
        ClientStore clientStore = this.company.getClientStore();
        TestStore testStore = this.company.getTestStore();
        CreateTestController testController = new CreateTestController();

        for (String[] testData : processedListData){
            /*testController.setCurrentCal(testData[2]);*/
            if(!clientStore.existsClientByTin(testData[5])){
                createAndSaveClient(testData);
            }
            List<String> parameterCodes = TestFileUtils.getParameterCodes(testData);
            testController.createTest(testData[1], testData[5], testData[11], parameterCodes);
            if(!testController.saveTest()) throw new UnsupportedOperationException(
                    String.format("Failed creating test with %s NHS number", testData[1]));

            Test createdTest = testStore.getTestByNhsNumber(testData[1]);

            if(createdTest == null) return false;
            List<Double> parameterResults = TestFileUtils.getParameterResults(testData);
            for(int i=0;i<parameterCodes.size();i++){
                createdTest.addTestResult(parameterCodes.get(i), parameterResults.get(i), "");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            createdTest.setDateOfTestRegistration(sdf.parse(testData[21]));
            createdTest.setDateOfChemicalAnalysis(sdf.parse(testData[22]));
            createdTest.setDateOfTestRegistration(sdf.parse(testData[23]));
        }
        return true;
    }

    private void createAndSaveClient(String [] data) throws ParseException {
        RegisterClientController clientController = new RegisterClientController();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String citizenCardNum = String.format("%016d", Integer.parseInt(data[3]));
        String nhsNum = data[4];
        String tin = data[5];
        Date date = df.parse(data[6]);
        String phoneNum = data[7];
        String name = data[8];
        String email = data[9];
        clientController.registerClient(citizenCardNum, nhsNum, date, tin, email, name, phoneNum);
        System.out.println(clientController.saveClient());
    }

}
