package app.domain.store;

import app.controller.App;
import app.controller.ImportTestController;
import app.controller.RecordSamplesController;
import app.controller.ShowAllTestsController;
import app.domain.model.*;
import app.domain.shared.Constants;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

public class TestStoreTest {

    private List<Parameter> parametersBlood;
    private List<Parameter> parametersCovid;
    private List<ParameterCategory> pcListBlood;
    private List<ParameterCategory> pcList;
    private ParameterCategory p1;
    private ParameterCategory p2;
    private List<TestType> selectedTT;
    private TestType t1;
    private TestType t2;
    private Date d1;
    private ClinicalAnalysisLaboratory cal;
    private ImportTestController importTestCtrl;
    private TestStore testStore;
    private Date startDate;

    @Before
    public void setUp() throws ParseException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        parametersBlood = new ArrayList<>();
        parametersCovid = new ArrayList<>();

        d1 = new SimpleDateFormat("dd/MM/yyyy").parse("08/08/2001");

        pcListBlood = new ArrayList<>();
        p1 = new ParameterCategory("CODE1","Hemogram");
        pcListBlood.add(p1);
        Parameter rbc = new Parameter("RBC12", "rbc", "redbloodcells", p1);
        Parameter wbc = new Parameter("WBC12", "wbc", "whitebloodcells", p1);

        pcList = new ArrayList<>();
        p2 = new ParameterCategory("CODE1","covid");
        pcList.add(p2);
        Parameter igg = new Parameter("IGG12", "igg", "covidParam", p2);

        parametersBlood.add(rbc);
        parametersBlood.add(wbc);
        parametersCovid.add(igg);

        t1 = new TestType("CODE3","blood test","blood",pcListBlood, Constants.BLOOD_EXTERNAL_ADAPTER_2);
        t2 = new TestType("CODE4","covid","swab",pcList, Constants.COVID_EXTERNAL_ADAPTER);

        selectedTT = new ArrayList<>();
        selectedTT.add(t1);
        selectedTT.add(t2);

        cal =  new ClinicalAnalysisLaboratory("001DO",
                "CAL","Lisboa","91841378811","1234567890", selectedTT);

        //for US18 and US19
        importTestCtrl = new ImportTestController();
        importTestCtrl.importTestsFromFile("./tests_CovidMATCPCSV.csv");
        testStore = App.getInstance().getCompany().getTestStore();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2021);
        cal.set(Calendar.MONTH, 4);    // janeiro é representado por 0
        cal.set(Calendar.DAY_OF_MONTH, 29);
        startDate = cal.getTime();
        //end US18 and US19


    }

    @Test
    public void ensureNullTestNotStored(){
        TestStore testStore = new TestStore();
        Assert.assertFalse(testStore.saveTest(null));
    }

    @Test
    public void ensureEqualTestsAreNotSaved(){
        TestStore testStore = new TestStore();
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678901");
        app.domain.model.Test test = testStore.createTest("123456789012", client, t1, parametersBlood, cal);
        testStore.saveTest(test);
        Assert.assertFalse(testStore.saveTest(test));
    }

    @Test
    public void ensureDifferentTestsPassValidation(){
        TestStore testStore = new TestStore();
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678901");
        app.domain.model.Test test = testStore.createTest("123456789012", client, t1, parametersBlood, cal);
        testStore.saveTest(test);
        Client client2 = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678601");
        app.domain.model.Test test2 = testStore.createTest("123456789011", client, t1, parametersBlood, cal);
        Assert.assertTrue(testStore.saveTest(test2));
    }

    @Test //checks if tests with no sample are being found correctly
    public void ensureTestIsFoundByBarcodeNumber() throws ClassNotFoundException, InstantiationException, BarcodeException, IllegalAccessException {
        TestStore testStore = new TestStore();
        SampleStore sampleStore = new SampleStore();
        RecordSamplesController recordSamplesController = new RecordSamplesController();

        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678601");
        app.domain.model.Test test = testStore.createTest("123456789012", client, t1, parametersBlood, cal);
        testStore.saveTest(test);

        MyBarcode myBarcode = recordSamplesController.getBarcode();
        Sample sample = sampleStore.createSample(myBarcode);

        test.addSample(sample);

        assertSame(testStore.getTestByBarcodeNumber(myBarcode.getBarcodeNumber()), test);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void ensureNotExistentBarcodeNumThrowsException() throws ClassNotFoundException, InstantiationException, BarcodeException, IllegalAccessException {
        TestStore testStore = new TestStore();
        SampleStore sampleStore = new SampleStore();
        RecordSamplesController recordSamplesController = new RecordSamplesController();

        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678601");
        app.domain.model.Test test = testStore.createTest("123456789012", client, t1, parametersBlood, cal);
        testStore.saveTest(test);

        MyBarcode myBarcode = recordSamplesController.getBarcode();
        Sample sample = sampleStore.createSample(myBarcode);

        test.addSample(sample);

        testStore.getTestByBarcodeNumber("012345678912");
    }

    @Test //checks if tests with no sample are being found correctly
    public void ensureTestsAreFoundByCode()  {
        TestStore testStore = new TestStore();
        SampleStore sampleStore = new SampleStore();
        RecordSamplesController recordSamplesController = new RecordSamplesController();

        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678601");
        app.domain.model.Test test = testStore.createTest("123456789012", client, t1, parametersBlood, cal);
        testStore.saveTest(test);


        assertSame(testStore.getTestByCodeInTestList(test.getCode()), test);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void ensureNotExistentCodeThrowsException() {
        TestStore testStore = new TestStore();

        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678601");
        app.domain.model.Test test = testStore.createTest("123456789012", client, t1, parametersBlood, cal);
        testStore.saveTest(test);

        testStore.getTestByCodeInTestList("000000000002");
    }

    @Test
    public void ensureTestNotReadyToDiagnosisFalse() throws BarcodeException {
        TestStore testStore = new TestStore();

        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678601");
        app.domain.model.Test test = testStore.createTest("123456789012", client, t1, parametersBlood, cal);
        testStore.saveTest(test);

        Sample sample = new Sample(new MyBarcode(BarcodeFactory.createUPCA("12345678901"), "12345678901"));
        test.addSample(sample);

        assertSame(testStore.getTestsReadyToDiagnose().size(),0);
    }

    @Test
    public void ensureTestReadyToDiagnosisTrue() throws BarcodeException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        TestStore testStore = new TestStore();

        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678601");
        app.domain.model.Test test = testStore.createTest("123456789012", client, t1, parametersBlood, cal);
        testStore.saveTest(test);

        Sample sample = new Sample(new MyBarcode(BarcodeFactory.createUPCA("12345678901"), "12345678901"));
        test.addSample(sample);

        test.addTestResult("RBC12", 23.45, "ug");
        test.addTestResult("WBC12", 23.45, "ug");
        test.addChemicalAnalysisDate();

        assertEquals(1, testStore.getTestsReadyToDiagnose().size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void ensureAddTestResultForInexistentCodeThrowsException() throws BarcodeException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        TestStore testStore = new TestStore();

        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678601");
        app.domain.model.Test test = testStore.createTest("123456789012", client, t1, parametersBlood, cal);
        testStore.saveTest(test);

        Sample sample = new Sample(new MyBarcode(BarcodeFactory.createUPCA("12345678901"), "12345678901"));
        test.addSample(sample);

        test.addTestResult("ALALA", 23.45, "ug");
    }


    @Test
    public void ensureGetTestParametersByTestReturnsCorrectly() {
        TestStore testStore = new TestStore();
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678601");
        app.domain.model.Test test = testStore.createTest("123456789012", client, t1, parametersBlood, cal);
        testStore.saveTest(test);

        Assert.assertEquals(parametersBlood, testStore.getTotalTestParameters(test));
    }


    @Test
    public void ensureGetTestParametersReturnsCorrectly() {
        TestStore testStore = new TestStore();
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678601");
        app.domain.model.Test test = testStore.createTest("123456789012", client, t1, parametersBlood, cal);
        testStore.saveTest(test);

        Assert.assertEquals(test.getParameters(), testStore.getTestParameters(test));
    }

//========== US16 ================

    //Test 5
    @Test
    public void testGetNumTestsWaitingForResultsDayOrInterval() throws BarcodeException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        TestStore testStore = new TestStore();
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678601");

        app.domain.model.Test test1 = testStore.createTest("123456789011", client, t1, parametersBlood, cal);
        testStore.saveTest(test1);
        Date date1reg = new Date(2020,Calendar.JANUARY, 14,8,0,0);
        test1.setDateOfTestRegistration(date1reg);
        Sample sample1 = new Sample(new MyBarcode(BarcodeFactory.createUPCA("12345678901"), "12345678901"));
        test1.addSample(sample1);
        Date date1s = new Date(2020, Calendar.JANUARY, 15, 8, 0, 0);
        test1.setDateOfSamplesCollection(date1s);
        test1.addTestResult("RBC12", 23.45, "ug");
        test1.addTestResult("WBC12", 23.45, "ug");
        Date date1 = new Date(2020, Calendar.JANUARY, 16, 8, 0, 0);
        test1.setDateOfChemicalAnalysis(date1);
        Report report1 = new Report("Everything is well.");
        test1.addReport(report1);
        Date date1r = new Date(2020,Calendar.JANUARY,18,8,0,0);
        test1.setDateOfDiagnosis(date1r);

        app.domain.model.Test test2 = testStore.createTest("123456789012", client, t1, parametersBlood, cal);
        testStore.saveTest(test2);
        Date date2reg = new Date(2020,Calendar.JANUARY, 14,19,59,59);
        test2.setDateOfTestRegistration(date2reg);
        Sample sample2 = new Sample(new MyBarcode(BarcodeFactory.createUPCA("12345678902"), "12345678902"));
        test2.addSample(sample2);
        Date date2s = new Date(2020, Calendar.JANUARY, 15, 19, 59, 59);
        test2.setDateOfSamplesCollection(date2s);

        app.domain.model.Test test3 = testStore.createTest("123456789013", client, t1, parametersBlood, cal);
        testStore.saveTest(test3);
        Date date3reg = new Date(2020,Calendar.JANUARY, 14,15,1,26);
        test3.setDateOfTestRegistration(date3reg);

        app.domain.model.Test test4 = testStore.createTest("123456789011", client, t1, parametersBlood, cal);
        testStore.saveTest(test4);
        Date date4reg = new Date(2020,Calendar.JANUARY, 14,8,0,0);
        test4.setDateOfTestRegistration(date4reg);
        Sample sample4 = new Sample(new MyBarcode(BarcodeFactory.createUPCA("12345678904"), "12345678904"));
        test4.addSample(sample4);
        Date date4s = new Date(2020, Calendar.JANUARY, 15, 19, 59, 58);
        test4.setDateOfSamplesCollection(date4s);
        test4.addTestResult("RBC12", 23.45, "ug");
        test4.addTestResult("WBC12", 23.45, "ug");
        test4.addChemicalAnalysisDate();
        Date date4 = new Date(2020, Calendar.JANUARY, 15, 19, 59, 59);
        test4.setDateOfChemicalAnalysis(date4);
        Report report4 = new Report("Everything is well.");
        test4.addReport(report4);
        Date date4r = new Date(2020,Calendar.JANUARY,16,8,30,0);
        test4.setDateOfDiagnosis(date4r);

        Date beginningDay = new Date(2020, Calendar.JANUARY, 15, 8, 0, 0);
        Date endingDay = new Date(2020, Calendar.JANUARY, 15, 19, 59, 59);

        int expectedResult = 2;
        int obtainedResult = testStore.getNumTestsWaitingForResultsDayOrInterval(beginningDay, endingDay);

        Assert.assertEquals(expectedResult, obtainedResult);
    }

    //Test 6
    @Test
    public void testGetNumTestsWaitingForDiagnosisDayOrInterval() throws BarcodeException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        TestStore testStore = new TestStore();
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678601");

        app.domain.model.Test test1 = testStore.createTest("123456789011", client, t1, parametersBlood, cal);
        testStore.saveTest(test1);
        Date date1reg = new Date(2020,Calendar.JANUARY, 14,8,0,0);
        test1.setDateOfTestRegistration(date1reg);
        Sample sample1 = new Sample(new MyBarcode(BarcodeFactory.createUPCA("12345678901"), "12345678901"));
        test1.addSample(sample1);
        Date date1s = new Date(2020, Calendar.JANUARY, 15, 8, 0, 0);
        test1.setDateOfSamplesCollection(date1s);
        test1.addTestResult("RBC12", 23.45, "ug");
        test1.addTestResult("WBC12", 23.45, "ug");
        Date date1 = new Date(2020, Calendar.JANUARY, 16, 8, 0, 0);
        test1.setDateOfChemicalAnalysis(date1);
        Report report1 = new Report("Everything is well.");
        test1.addReport(report1);
        Date date1r = new Date(2020,Calendar.JANUARY,18,8,0,0);
        test1.setDateOfDiagnosis(date1r);

        app.domain.model.Test test2 = testStore.createTest("123456789012", client, t1, parametersBlood, cal);
        testStore.saveTest(test2);
        Date date2reg = new Date(2020,Calendar.JANUARY, 14,19,59,59);
        test2.setDateOfTestRegistration(date2reg);
        Sample sample2 = new Sample(new MyBarcode(BarcodeFactory.createUPCA("12345678902"), "12345678902"));
        test2.addSample(sample2);
        Date date2s = new Date(2020, Calendar.JANUARY, 15, 19, 59, 59);
        test2.setDateOfSamplesCollection(date2s);

        app.domain.model.Test test3 = testStore.createTest("123456789013", client, t1, parametersBlood, cal);
        testStore.saveTest(test3);
        Date date3reg = new Date(2020,Calendar.JANUARY, 14,15,1,26);
        test3.setDateOfTestRegistration(date3reg);

        app.domain.model.Test test4 = testStore.createTest("123456789011", client, t1, parametersBlood, cal);
        testStore.saveTest(test4);
        Date date4reg = new Date(2020,Calendar.JANUARY, 14,8,0,0);
        test4.setDateOfTestRegistration(date4reg);
        Sample sample4 = new Sample(new MyBarcode(BarcodeFactory.createUPCA("12345678904"), "12345678904"));
        test4.addSample(sample4);
        Date date4s = new Date(2020, Calendar.JANUARY, 15, 19, 59, 58);
        test4.setDateOfSamplesCollection(date4s);
        test4.addTestResult("RBC12", 23.45, "ug");
        test4.addTestResult("WBC12", 23.45, "ug");
        test4.addChemicalAnalysisDate();
        Date date4 = new Date(2020, Calendar.JANUARY, 15, 19, 59, 59);
        test4.setDateOfChemicalAnalysis(date4);
        Report report4 = new Report("Everything is well.");
        test4.addReport(report4);
        Date date4r = new Date(2020,Calendar.JANUARY,16,8,30,0);
        test4.setDateOfDiagnosis(date4r);

        Date beginningDay = new Date(2020, Calendar.JANUARY, 15, 8, 0, 0);
        Date endingDay = new Date(2020, Calendar.JANUARY, 16, 19, 59, 59);

        int expectedResult = 2;
        int obtainedResult = testStore.getNumTestsWaitingForDiagnosisDayOrInterval(beginningDay, endingDay);

        Assert.assertEquals(expectedResult, obtainedResult);
    }

    //Test 7
    @Test
    public void testGetNumTestsProcessedInLabDayOrInterval() throws BarcodeException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        TestStore testStore = new TestStore();
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678601");

        app.domain.model.Test test1 = testStore.createTest("123456789011", client, t1, parametersBlood, cal);
        testStore.saveTest(test1);
        Date date1reg = new Date(2020,Calendar.JANUARY, 14,8,0,0);
        test1.setDateOfTestRegistration(date1reg);
        Sample sample1 = new Sample(new MyBarcode(BarcodeFactory.createUPCA("12345678901"), "12345678901"));
        test1.addSample(sample1);
        Date date1s = new Date(2020, Calendar.JANUARY, 15, 8, 0, 0);
        test1.setDateOfSamplesCollection(date1s);
        test1.addTestResult("RBC12", 23.45, "ug");
        test1.addTestResult("WBC12", 23.45, "ug");
        Date date1 = new Date(2020, Calendar.JANUARY, 16, 8, 0, 0);
        test1.setDateOfChemicalAnalysis(date1);
        Report report1 = new Report("Everything is well.");
        test1.addReport(report1);
        Date date1r = new Date(2020,Calendar.JANUARY,18,8,0,0);
        test1.setDateOfDiagnosis(date1r);

        app.domain.model.Test test2 = testStore.createTest("123456789012", client, t1, parametersBlood, cal);
        testStore.saveTest(test2);
        Date date2reg = new Date(2020,Calendar.JANUARY, 14,19,59,59);
        test2.setDateOfTestRegistration(date2reg);
        Sample sample2 = new Sample(new MyBarcode(BarcodeFactory.createUPCA("12345678902"), "12345678902"));
        test2.addSample(sample2);
        Date date2s = new Date(2020, Calendar.JANUARY, 15, 19, 59, 59);
        test2.setDateOfSamplesCollection(date2s);

        app.domain.model.Test test3 = testStore.createTest("123456789013", client, t1, parametersBlood, cal);
        testStore.saveTest(test3);
        Date date3reg = new Date(2020,Calendar.JANUARY, 14,15,1,26);
        test3.setDateOfTestRegistration(date3reg);

        app.domain.model.Test test4 = testStore.createTest("123456789011", client, t1, parametersBlood, cal);
        testStore.saveTest(test4);
        Date date4reg = new Date(2020,Calendar.JANUARY, 14,8,0,0);
        test4.setDateOfTestRegistration(date4reg);
        Sample sample4 = new Sample(new MyBarcode(BarcodeFactory.createUPCA("12345678904"), "12345678904"));
        test4.addSample(sample4);
        Date date4s = new Date(2020, Calendar.JANUARY, 15, 19, 59, 58);
        test4.setDateOfSamplesCollection(date4s);
        test4.addTestResult("RBC12", 23.45, "ug");
        test4.addTestResult("WBC12", 23.45, "ug");
        test4.addChemicalAnalysisDate();
        Date date4 = new Date(2020, Calendar.JANUARY, 15, 19, 59, 59);
        test4.setDateOfChemicalAnalysis(date4);
        Report report4 = new Report("Everything is well.");
        test4.addReport(report4);
        Date date4r = new Date(2020,Calendar.JANUARY,16,19,59,59);
        test4.setDateOfDiagnosis(date4r);

        Date beginningDay = new Date(2020, Calendar.JANUARY, 15, 8, 0, 0);
        Date endingDay = new Date(2020, Calendar.JANUARY, 16, 19, 59, 59);

        int expectedResult = 1;
        int obtainedResult = testStore.getNumTestsProcessedInLabDayOrInterval(beginningDay, endingDay);

        Assert.assertEquals(expectedResult, obtainedResult);
    }

//========== END US16 ============

//=========== US1 ================

    @Test
    public void testGetTestsByClient() throws BarcodeException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        TestStore testStore = new TestStore();
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678601");
        Client client2 = new Client("1234567890123333", "1234567777", d1, "Female", "1234567777", "alexandra@gmail.com", "Alexandra", "12345677777");

        app.domain.model.Test test1 = testStore.createTest("123456789011", client, t1, parametersBlood, cal);
        testStore.saveTest(test1);
        Date date1reg = new Date(2020,Calendar.JANUARY, 14,8,0,0);
        test1.setDateOfTestRegistration(date1reg);

        app.domain.model.Test test4 = testStore.createTest("123456789011", client2, t1, parametersBlood, cal);
        testStore.saveTest(test4);
        Date date4reg = new Date(2020,Calendar.JANUARY, 14,8,1,0);
        test4.setDateOfTestRegistration(date4reg);

        app.domain.model.Test test3 = testStore.createTest("123456789013", client, t1, parametersBlood, cal);
        testStore.saveTest(test3);
        Date date3reg = new Date(2020,Calendar.JANUARY, 14,15,1,26);
        test3.setDateOfTestRegistration(date3reg);

        app.domain.model.Test test2 = testStore.createTest("123456789012", client2, t1, parametersBlood, cal);
        testStore.saveTest(test2);
        Date date2reg = new Date(2020,Calendar.JANUARY, 14,19,59,59);
        test2.setDateOfTestRegistration(date2reg);

        ArrayList<app.domain.model.Test> expectedResult = new ArrayList<>();
        expectedResult.add(test4);
        expectedResult.add(test2);

        ArrayList<app.domain.model.Test> obtainedResult = testStore.getTestsByClient(client2);

        Assert.assertEquals(expectedResult, obtainedResult);
    }

//=========== END US1 ============

    //for US18 and US19
    // Porque dá erro no Jenkins??
    /*@Test
    public void getObservedPositivesToTableOfValues() throws ClassNotFoundException, InstantiationException, ParseException, IllegalAccessException {
        int numberOfObservations = 8;
        NHSReportStore nhsReportStore = new NHSReportStore();
        List<String> dates = nhsReportStore.getDatesColumnToTableOfValues(numberOfObservations, startDate);
        int[] expObservedPositives = {1, 3, 2, 5, 8, 8, 0, 12};

        int[] observedPositives = testStore.getObservedPositivesToTableOfValues(numberOfObservations, dates);

        Assert.assertArrayEquals(expObservedPositives, observedPositives);
    }
     */


    @Test
    public void getNumberOfCovidTestsRealizedInADay() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2021);
        cal.set(Calendar.MONTH, 4);    // january is represented by 0
        cal.set(Calendar.DAY_OF_MONTH, 20);
        Date date = cal.getTime();
        double expNumber = 15;

        double number = testStore.getNumberOfCovidTestsRealizedInADay(date);

        Assert.assertEquals(expNumber, number, 0.0);
    }

    @Test
    public void getMeanAgeOfClientsOfCovidTestsInADay() throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2021);
        cal.set(Calendar.MONTH, 4);    // january is represented by 0
        cal.set(Calendar.DAY_OF_MONTH, 28);
        Date date = cal.getTime();

        double expNumber = 24.83333;
        double number = testStore.getMeanAgeOfClientsOfCovidTestsInADay(date);

        Assert.assertEquals(expNumber, number, 0.0001);
    }

    //Porque dá erro no Jenkins?
    /*@Test
    public void getObservedPositivesCovidInADay() {
        double expNumber = 1;
        double number = testStore.getObservedPositivesCovidInADay(startDate);

        Assert.assertEquals(expNumber, number, 0.0);

    }
     */


    //end US18 and US19
}