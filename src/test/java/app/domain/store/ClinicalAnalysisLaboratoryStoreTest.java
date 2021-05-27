package app.domain.store;

import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import app.domain.shared.Constants;
import app.domain.store.ClinicalAnalysisLaboratoryStore;
import app.mappers.dto.ClinicalAnalysisLaboratoryDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ClinicalAnalysisLaboratoryStoreTest {
    private List<ParameterCategory> pcList;
    private ParameterCategory p1;
    private ParameterCategory p2;
    private List<TestType> selectedTT;
    private TestType t1;
    private TestType t2;
    private Company company;
    private ClinicalAnalysisLaboratoryDTO c1Dto;
    private ClinicalAnalysisLaboratoryDTO c2Dto;
    private ClinicalAnalysisLaboratoryDTO c3Dto;
    private ClinicalAnalysisLaboratory c1;
    private ClinicalAnalysisLaboratory c2;
    private ClinicalAnalysisLaboratory c3;
    private List<String> testTypeCodes;
    private ClinicalAnalysisLaboratoryStore calStore;

    @Before
    public void setUp() {
        pcList = new ArrayList<>();
        p1 = new ParameterCategory("CODE1","Name");
        p2 = new ParameterCategory("CODE2","Name");
        pcList.add(p1);
        pcList.add(p2);
        company = new Company("Many Labs");
        t1 = company.getTestTypeStore().createTestType("CODE3","Description","swab", pcList,"");
        t2 = company.getTestTypeStore().createTestType("CODE4","Description","swab", pcList,"");
        t1 = company.getTestTypeStore().createTestType("CODE3","Description","swab", pcList, Constants.BLOOD_EXTERNAL_ADAPTER_2);
        t2 = company.getTestTypeStore().createTestType("CODE4","Description","swab", pcList, Constants.BLOOD_EXTERNAL_ADAPTER_2);
        company.getTestTypeStore().saveTestType(t1);
        company.getTestTypeStore().saveTestType(t2);
        selectedTT = new ArrayList<>();
        selectedTT.add(t1);
        selectedTT.add(t2);
        testTypeCodes = new ArrayList<>();
        testTypeCodes.add("CODE3");
        testTypeCodes.add("CODE4");


        c1Dto = new ClinicalAnalysisLaboratoryDTO("CAL12",
                "CAL","Lisboa","91841378811","1234567890", testTypeCodes);
        c2Dto = new ClinicalAnalysisLaboratoryDTO("LAB23",
                "Laboratorio","Porto","91899998811","1239999890", testTypeCodes);
        c3Dto = new ClinicalAnalysisLaboratoryDTO("SON55",
                "SYNLAB","Guarda","00899998811","0039999890", testTypeCodes);
        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();
        c1 = calStore.createClinicalAnalysisLaboratory(c1Dto, selectedTT);
        c2 = calStore.createClinicalAnalysisLaboratory(c2Dto, selectedTT);
        c3 = calStore.createClinicalAnalysisLaboratory(c3Dto, selectedTT);
    }

    //for US8
    @Test
    public void createClinicalAnalysisLaboratory() {
        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();
        //Arrange
        ClinicalAnalysisLaboratory expObj = new ClinicalAnalysisLaboratory("CAL12",
                "CAL","Lisboa","91841378811","1234567890", selectedTT);
        ClinicalAnalysisLaboratoryDTO calDto = new ClinicalAnalysisLaboratoryDTO("CAL12",
                "CAL","Lisboa","91841378811","1234567890", testTypeCodes);

        //Act
        ClinicalAnalysisLaboratory obj = calStore.createClinicalAnalysisLaboratory(calDto, selectedTT);

        //Assert
        Assert.assertEquals(expObj, obj);


    }

    @Test
    public void ensureDifferentClinicalAnalysisLaboratoryIsSaved() {
        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();
        //Arrange
        ClinicalAnalysisLaboratoryDTO c1Dto = new ClinicalAnalysisLaboratoryDTO("CAL12",
                "CAL","Lisboa","91841378811","1234567890", testTypeCodes);
        ClinicalAnalysisLaboratory c1 = calStore.createClinicalAnalysisLaboratory(c1Dto, selectedTT);
        calStore.saveClinicalAnalysisLaboratory(c1);

        //Act
        ClinicalAnalysisLaboratoryDTO c2Dto = new ClinicalAnalysisLaboratoryDTO("LAB23",
                "Laboratorio","Outeiro","91841378810","1234467890", testTypeCodes);
        ClinicalAnalysisLaboratory c2 = calStore.createClinicalAnalysisLaboratory(c2Dto, selectedTT);
        boolean result = calStore.saveClinicalAnalysisLaboratory(c2);

        //Assert
        Assert.assertTrue(result);
    }

    //TESTES DE VALIDAÇÃO
    @Test(expected = IllegalArgumentException.class)
    public void ensureClinicalAnalysisLaboratoryIsNotSavedExistingAlreadyTheSameObject() {
        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();

        //Arrange
        ClinicalAnalysisLaboratoryDTO c1Dto = new ClinicalAnalysisLaboratoryDTO("CAL12",
                "CAL","Lisboa","91841378811","1234567890", testTypeCodes);
        ClinicalAnalysisLaboratory c1 = calStore.createClinicalAnalysisLaboratory(c1Dto, selectedTT);
        calStore.saveClinicalAnalysisLaboratory(c1);

        //Act
        boolean result = calStore.saveClinicalAnalysisLaboratory(c1);

        //Assert
        Assert.assertFalse(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureClinicalAnalysisLaboratoryIsNotSavedExistingEqualObject() {
        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();

        //Arrange
        ClinicalAnalysisLaboratoryDTO c1Dto = new ClinicalAnalysisLaboratoryDTO("CAL12",
                "CAL","Lisboa","91841378811","1234567890", testTypeCodes);
        ClinicalAnalysisLaboratory c1 = calStore.createClinicalAnalysisLaboratory(c1Dto, selectedTT);
        ClinicalAnalysisLaboratoryDTO c2Dto = new ClinicalAnalysisLaboratoryDTO("CAL12",
                "CAL","Lisboa","91841378811","1234567890", testTypeCodes);
        ClinicalAnalysisLaboratory c2 = calStore.createClinicalAnalysisLaboratory(c2Dto, selectedTT);
        calStore.saveClinicalAnalysisLaboratory(c1);

        //Act
        boolean result = calStore.saveClinicalAnalysisLaboratory(c2);

        //Assert
        Assert.assertFalse(result);
    }
    //FIM TESTES VALIDAÇÃO

    @Test
    public void ensureNullClinicalAnalysisLaboratoryIsNotSaved() {
        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();

        //Act
        boolean result = calStore.saveClinicalAnalysisLaboratory(null);

        //Assert
        Assert.assertFalse(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNoCalWithDuplicatedLaboratoryIDIsNotSaved() {
        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();

        calStore.saveClinicalAnalysisLaboratory(c1);
        calStore.saveClinicalAnalysisLaboratory(c2);
        calStore.saveClinicalAnalysisLaboratory(c3);

        ClinicalAnalysisLaboratoryDTO c0Dto = new ClinicalAnalysisLaboratoryDTO("LAB23",
                "BMAC","Bragança","97777378811","1777767890", testTypeCodes);
        ClinicalAnalysisLaboratory c0 = calStore.createClinicalAnalysisLaboratory(c0Dto, selectedTT);

        boolean result = calStore.saveClinicalAnalysisLaboratory(c0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNoCalWithDuplicatedAddressIsNotSaved() {
        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();

        calStore.saveClinicalAnalysisLaboratory(c1);
        calStore.saveClinicalAnalysisLaboratory(c2);
        calStore.saveClinicalAnalysisLaboratory(c3);

        ClinicalAnalysisLaboratoryDTO c0Dto = new ClinicalAnalysisLaboratoryDTO("MEL23",
                "BMAC","guarda","97777378811","1777767890", testTypeCodes);
        ClinicalAnalysisLaboratory c0 = calStore.createClinicalAnalysisLaboratory(c0Dto, selectedTT);

        boolean result = calStore.saveClinicalAnalysisLaboratory(c0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNoCalWithDuplicatedPhoneNumberIsNotSaved() {
        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();

        calStore.saveClinicalAnalysisLaboratory(c1);
        calStore.saveClinicalAnalysisLaboratory(c2);
        calStore.saveClinicalAnalysisLaboratory(c3);

        ClinicalAnalysisLaboratoryDTO c0Dto = new ClinicalAnalysisLaboratoryDTO("MEL23",
                "BMAC","Bragança","91899998811","1777767890", testTypeCodes);
        ClinicalAnalysisLaboratory c0 = calStore.createClinicalAnalysisLaboratory(c0Dto, selectedTT);

        boolean result = calStore.saveClinicalAnalysisLaboratory(c0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNoCalWithDuplicatedTINNumberIsNotSaved() {
        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();

        calStore.saveClinicalAnalysisLaboratory(c1);
        calStore.saveClinicalAnalysisLaboratory(c2);
        calStore.saveClinicalAnalysisLaboratory(c3);

        ClinicalAnalysisLaboratoryDTO c0Dto = new ClinicalAnalysisLaboratoryDTO("MEL23",
                "BMAC","Bragança","97777378811","1234567890", testTypeCodes);
        ClinicalAnalysisLaboratory c0 = calStore.createClinicalAnalysisLaboratory(c0Dto, selectedTT);

        boolean result = calStore.saveClinicalAnalysisLaboratory(c0);
    }



}
