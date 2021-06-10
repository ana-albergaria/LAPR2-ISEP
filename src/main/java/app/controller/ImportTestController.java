package app.controller;

import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.shared.utils.TestFileUtils;
import app.domain.store.ClientStore;
import app.domain.store.TestStore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
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
        List<String>  dataLabels = TestFileUtils.getDataLabels();
        ClientStore clientStore = this.company.getClientStore();
        TestStore testStore = this.company.getTestStore();
        CreateTestController testController = new CreateTestController();
        
        for (String[] testData : processedListData){
            /*testController.setCurrentCal(testData[2]);*/
            if(!clientStore.existsClientByTin(testData[dataLabels.indexOf("TIN")])){
                createAndSaveClient(testData, dataLabels);
            }
            List<String> parameterCodes = TestFileUtils.getParameterCodes(testData);
            testController.createTest(testData[dataLabels.indexOf("NHS_Code")],
                    testData[dataLabels.indexOf("TIN")],
                    testData[dataLabels.indexOf("TestType")],
                    parameterCodes);
            if(!testController.saveTest()) throw new UnsupportedOperationException(
                    String.format("Failed creating test with %s NHS number", testData[1]));

            Test createdTest = testStore.getTestByNhsNumber(testData[1]);
            if(createdTest == null) return false;
            addTestResults(testData,parameterCodes,createdTest);
            addTestDates(testData, createdTest, dataLabels);
        }
        return true;
    }


    private void addTestDates(String[] testData, Test createdTest, List<String> dataLabels) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        createdTest.setDateOfTestRegistration(sdf.parse(testData[dataLabels.indexOf("Test_Reg_DateHour")]));
        createdTest.setDateOfChemicalAnalysis(sdf.parse(testData[dataLabels.indexOf("Test_Chemical_DateHour")]));
        createdTest.setDateOfDiagnosis(sdf.parse(testData[dataLabels.indexOf("Test_Doctor_DateHour")]));
    }

    private void createAndSaveClient(String [] data, List<String> dataLabels) throws ParseException {
        RegisterClientController clientController = new RegisterClientController();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String citizenCardNum = String.format("%016d", Integer.parseInt(data[dataLabels.indexOf("CitizenCard_Number")]));
        String nhsNum = data[dataLabels.indexOf("NHS_Number")];
        String tin = data[dataLabels.indexOf("TIN")];
        Date date = df.parse(data[dataLabels.indexOf("BirthDay")]);
        String phoneNum = data[dataLabels.indexOf("PhoneNumber")];
        String name = data[dataLabels.indexOf("Name")];
        String email = data[dataLabels.indexOf("E-mail ")];
        clientController.registerClient(citizenCardNum, nhsNum, date, tin, email, name, phoneNum);
        clientController.saveClient();
    }

    private void addTestResults(String[] testData, List<String> parameterCodes, Test createdTest) throws IllegalAccessException, ClassNotFoundException, InstantiationException, ParseException {
        List<Double> parameterResults = TestFileUtils.getParameterResults(testData);
        for(int i=0;i<parameterCodes.size();i++){
            createdTest.addTestResult(parameterCodes.get(i), parameterResults.get(i), "");
        }
    }

}
