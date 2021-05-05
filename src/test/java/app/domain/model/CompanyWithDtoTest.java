package app.domain.model;

import app.mappers.dto.ClinicalAnalysisLaboratoryDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CompanyWithDtoTest {
    private List<ParameterCategory> pcList;
    private ParameterCategory p1;
    private ParameterCategory p2;
    private List<TestType> selectedTT;
    private TestType t1;
    private TestType t2;
    private Company company;
    private ClinicalAnalysisLaboratory c1;
    private ClinicalAnalysisLaboratory c2;
    private ClinicalAnalysisLaboratory c3;
    private List<String> testTypeCodes;

    @Before
    public void setUp() {
        pcList = new ArrayList<>();
        p1 = new ParameterCategory("CODE1","Name");
        p2 = new ParameterCategory("CODE2","Name");
        pcList.add(p1);
        pcList.add(p2);
        t1 = new TestType("CODE3","Description","swab", pcList);
        t2 = new TestType("CODE4","Description","swab", pcList);
        selectedTT = new ArrayList<>();
        selectedTT.add(t1);
        selectedTT.add(t2);
        company = new Company("Many Labs");
        testTypeCodes = new ArrayList<>();
        testTypeCodes.add("CODE3");
        testTypeCodes.add("CODE4");

        c1 = new ClinicalAnalysisLaboratory("CAL12",
                "CAL","Lisboa","91841378811","1234567890", selectedTT);
        c2 = new ClinicalAnalysisLaboratory("LAB23",
                "Laboratorio","Porto","91899998811","1239999890", selectedTT);
        c3 = new ClinicalAnalysisLaboratory("SON55",
                "SYNLAB","Guarda","00899998811","0039999890", selectedTT);
    }


    @Test
    public void createClinicalAnalysisLaboratory() {
        System.out.println("createClinicalAnalysisLaboratory (CompanyTest)");

        //Arrange
        TestType testType1 = company.getTestTypeStore().createTestType("CODE3","Description","swab", pcList);
        TestType testType2 = company.getTestTypeStore().createTestType("CODE4","Description","swab", pcList);
        company.getTestTypeStore().saveTestType(testType1);
        company.getTestTypeStore().saveTestType(testType2);

        ClinicalAnalysisLaboratory expObj = new ClinicalAnalysisLaboratory("CAL12",
                "CAL","Lisboa","91841378811","1234567890", selectedTT);
        ClinicalAnalysisLaboratoryDTO calDto = new ClinicalAnalysisLaboratoryDTO("CAL12",
                "CAL","Lisboa","91841378811","1234567890", testTypeCodes);

        //Act
        ClinicalAnalysisLaboratory obj = company.createClinicalAnalysisLaboratory(calDto);

        //Assert
        Assert.assertEquals(expObj, obj);
    }
/*
    @Test
    public void ensureDifferentClinicalAnalysisLaboratoryIsSaved() {
        System.out.println("ensureDifferentClinicalAnalysisLaboratoryIsSaved (CompanyTest)");

        //Arrange
        ClinicalAnalysisLaboratory c1 = company.createClinicalAnalysisLaboratory("CAL12",
                "CAL","Lisboa","91841378811","1234567890", selectedTT);
        company.saveClinicalAnalysisLaboratory(c1);

        //Act
        ClinicalAnalysisLaboratory c2 = company.createClinicalAnalysisLaboratory("LAB23",
                "Laboratorio","Outeiro","91841378810","1234467890", selectedTT);
        boolean result = company.saveClinicalAnalysisLaboratory(c2);

        //Assert
        Assert.assertTrue(result);
    }

 */


/* TESTES DE VALIDAÇÃO
    @Test
    public void ensureClinicalAnalysisLaboratoryIsNotSavedExistingAlreadyTheSameObject() {
        System.out.println("ensureClinicalAnalysisLaboratoryIsNotSavedExistingAlreadyTheSameObject (CompanyTest)");

        //Arrange
        ClinicalAnalysisLaboratory c1 = company.createClinicalAnalysisLaboratory("CAL12",
                "CAL","Lisboa","91841378811","1234567890", selectedTT);
        company.saveClinicalAnalysisLaboratory(c1);

        //Act
        boolean result = company.saveClinicalAnalysisLaboratory(c1);

        //Assert
        Assert.assertFalse(result);
    }

    @Test
    public void ensureClinicalAnalysisLaboratoryIsNotSavedExistingEqualObject() {
        System.out.println("ensureClinicalAnalysisLaboratoryIsNotSavedExistingEqualObject (CompanyTest)");

        //Arrange
        ClinicalAnalysisLaboratory c1 = company.createClinicalAnalysisLaboratory("CAL12",
                "CAL","Lisboa","91841378811","1234567890", selectedTT);
        ClinicalAnalysisLaboratory c2 = company.createClinicalAnalysisLaboratory("CAL12",
                "CAL","Lisboa","91841378811","1234567890", selectedTT);
        company.saveClinicalAnalysisLaboratory(c1);

        //Act
        boolean result = company.saveClinicalAnalysisLaboratory(c2);

        //Assert
        Assert.assertFalse(result);
    }
    FIM TESTES VALIDAÇÃO


    @Test
    public void ensureNullClinicalAnalysisLaboratoryIsNotSaved() {
        System.out.println("ensureNullClinicalAnalysisLaboratoryIsNotSaved (CompanyTest)");

        //Act
        boolean result = company.saveClinicalAnalysisLaboratory(null);

        //Assert
        Assert.assertFalse(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNoCalWithDuplicatedLaboratoryIDIsNotSaved() {
        System.out.println("ensureNoCalWithDuplicatedLaboratoryIDIsNotSaved");

        company.saveClinicalAnalysisLaboratory(c1);
        company.saveClinicalAnalysisLaboratory(c2);
        company.saveClinicalAnalysisLaboratory(c3);

        ClinicalAnalysisLaboratory c0 = company.createClinicalAnalysisLaboratory("LAB23",
                "BMAC","Bragança","97777378811","1777767890", selectedTT);

        boolean result = company.saveClinicalAnalysisLaboratory(c0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNoCalWithDuplicatedAddressIsNotSaved() {
        System.out.println("ensureNoCalWithDuplicatedAddressIsNotSaved");

        company.saveClinicalAnalysisLaboratory(c1);
        company.saveClinicalAnalysisLaboratory(c2);
        company.saveClinicalAnalysisLaboratory(c3);

        ClinicalAnalysisLaboratory c0 = company.createClinicalAnalysisLaboratory("MEL23",
                "BMAC","guarda","97777378811","1777767890", selectedTT);

        boolean result = company.saveClinicalAnalysisLaboratory(c0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNoCalWithDuplicatedPhoneNumberIsNotSaved() {
        System.out.println("ensureNoCalWithDuplicatedPhoneNumberIsNotSaved");

        company.saveClinicalAnalysisLaboratory(c1);
        company.saveClinicalAnalysisLaboratory(c2);
        company.saveClinicalAnalysisLaboratory(c3);

        ClinicalAnalysisLaboratory c0 = company.createClinicalAnalysisLaboratory("MEL23",
                "BMAC","Bragança","91899998811","1777767890", selectedTT);

        boolean result = company.saveClinicalAnalysisLaboratory(c0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNoCalWithDuplicatedTINNumberIsNotSaved() {
        System.out.println("ensureNoCalWithDuplicatedTINNumberIsNotSaved");

        company.saveClinicalAnalysisLaboratory(c1);
        company.saveClinicalAnalysisLaboratory(c2);
        company.saveClinicalAnalysisLaboratory(c3);

        ClinicalAnalysisLaboratory c0 = company.createClinicalAnalysisLaboratory("MEL23",
                "BMAC","Bragança","97777378811","1234567890", selectedTT);

        boolean result = company.saveClinicalAnalysisLaboratory(c0);
    }

 */



}
