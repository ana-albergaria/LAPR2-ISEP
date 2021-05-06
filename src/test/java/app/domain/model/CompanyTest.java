package app.domain.model;

import app.mappers.dto.ClinicalAnalysisLaboratoryDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CompanyTest {
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

    @Before
    public void setUp() {
        pcList = new ArrayList<>();
        p1 = new ParameterCategory("CODE1","Name");
        p2 = new ParameterCategory("CODE2","Name");
        pcList.add(p1);
        pcList.add(p2);
        company = new Company("Many Labs");
        t1 = company.getTestTypeStore().createTestType("CODE3","Description","swab", pcList);
        t2 = company.getTestTypeStore().createTestType("CODE4","Description","swab", pcList);
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
        c1 = company.createClinicalAnalysisLaboratory(c1Dto);
        c2 = company.createClinicalAnalysisLaboratory(c2Dto);
        c3 = company.createClinicalAnalysisLaboratory(c3Dto);
    }

    //for US8
    @Test
    public void createClinicalAnalysisLaboratory() {
        System.out.println("createClinicalAnalysisLaboratory (CompanyTest)");

        //Arrange
        ClinicalAnalysisLaboratory expObj = new ClinicalAnalysisLaboratory("CAL12",
                "CAL","Lisboa","91841378811","1234567890", selectedTT);
        ClinicalAnalysisLaboratoryDTO calDto = new ClinicalAnalysisLaboratoryDTO("CAL12",
                "CAL","Lisboa","91841378811","1234567890", testTypeCodes);

        //Act
        ClinicalAnalysisLaboratory obj = company.createClinicalAnalysisLaboratory(calDto);

        //Assert
        Assert.assertEquals(expObj, obj);


    }

    @Test
    public void ensureDifferentClinicalAnalysisLaboratoryIsSaved() {
        System.out.println("ensureDifferentClinicalAnalysisLaboratoryIsSaved (CompanyTest)");

        //Arrange
        ClinicalAnalysisLaboratoryDTO c1Dto = new ClinicalAnalysisLaboratoryDTO("CAL12",
                "CAL","Lisboa","91841378811","1234567890", testTypeCodes);
        ClinicalAnalysisLaboratory c1 = company.createClinicalAnalysisLaboratory(c1Dto);
        company.saveClinicalAnalysisLaboratory(c1);

        //Act
        ClinicalAnalysisLaboratoryDTO c2Dto = new ClinicalAnalysisLaboratoryDTO("LAB23",
                "Laboratorio","Outeiro","91841378810","1234467890", testTypeCodes);
        ClinicalAnalysisLaboratory c2 = company.createClinicalAnalysisLaboratory(c2Dto);
        boolean result = company.saveClinicalAnalysisLaboratory(c2);

        //Assert
        Assert.assertTrue(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureClinicalAnalysisLaboratoryIsNotSavedExistingAlreadyTheSameObject() {
        System.out.println("ensureClinicalAnalysisLaboratoryIsNotSavedExistingAlreadyTheSameObject (CompanyTest)");

        //Arrange
        ClinicalAnalysisLaboratoryDTO c1Dto = new ClinicalAnalysisLaboratoryDTO("CAL12",
                "CAL","Lisboa","91841378811","1234567890", testTypeCodes);
        ClinicalAnalysisLaboratory c1 = company.createClinicalAnalysisLaboratory(c1Dto);
        company.saveClinicalAnalysisLaboratory(c1);

        //Act
        boolean result = company.saveClinicalAnalysisLaboratory(c1);

        //Assert
        Assert.assertFalse(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureClinicalAnalysisLaboratoryIsNotSavedExistingEqualObject() {
        System.out.println("ensureClinicalAnalysisLaboratoryIsNotSavedExistingEqualObject (CompanyTest)");

        //Arrange
        ClinicalAnalysisLaboratoryDTO c1Dto = new ClinicalAnalysisLaboratoryDTO("CAL12",
                "CAL","Lisboa","91841378811","1234567890", testTypeCodes);
        ClinicalAnalysisLaboratory c1 = company.createClinicalAnalysisLaboratory(c1Dto);
        ClinicalAnalysisLaboratoryDTO c2Dto = new ClinicalAnalysisLaboratoryDTO("CAL12",
                "CAL","Lisboa","91841378811","1234567890", testTypeCodes);
        ClinicalAnalysisLaboratory c2 = company.createClinicalAnalysisLaboratory(c2Dto);
        company.saveClinicalAnalysisLaboratory(c1);

        //Act
        boolean result = company.saveClinicalAnalysisLaboratory(c2);

        //Assert
        Assert.assertFalse(result);
    }


    @Test(expected = IllegalArgumentException.class)
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

        ClinicalAnalysisLaboratoryDTO c0Dto = new ClinicalAnalysisLaboratoryDTO("LAB23",
                "BMAC","Bragança","97777378811","1777767890", testTypeCodes);
        ClinicalAnalysisLaboratory c0 = company.createClinicalAnalysisLaboratory(c0Dto);

        boolean result = company.saveClinicalAnalysisLaboratory(c0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNoCalWithDuplicatedAddressIsNotSaved() {
        System.out.println("ensureNoCalWithDuplicatedAddressIsNotSaved");

        company.saveClinicalAnalysisLaboratory(c1);
        company.saveClinicalAnalysisLaboratory(c2);
        company.saveClinicalAnalysisLaboratory(c3);

        ClinicalAnalysisLaboratoryDTO c0Dto = new ClinicalAnalysisLaboratoryDTO("MEL23",
                "BMAC","guarda","97777378811","1777767890", testTypeCodes);
        ClinicalAnalysisLaboratory c0 = company.createClinicalAnalysisLaboratory(c0Dto);

        boolean result = company.saveClinicalAnalysisLaboratory(c0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNoCalWithDuplicatedPhoneNumberIsNotSaved() {
        System.out.println("ensureNoCalWithDuplicatedPhoneNumberIsNotSaved");

        company.saveClinicalAnalysisLaboratory(c1);
        company.saveClinicalAnalysisLaboratory(c2);
        company.saveClinicalAnalysisLaboratory(c3);

        ClinicalAnalysisLaboratoryDTO c0Dto = new ClinicalAnalysisLaboratoryDTO("MEL23",
                "BMAC","Bragança","91899998811","1777767890", testTypeCodes);
        ClinicalAnalysisLaboratory c0 = company.createClinicalAnalysisLaboratory(c0Dto);

        boolean result = company.saveClinicalAnalysisLaboratory(c0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNoCalWithDuplicatedTINNumberIsNotSaved() {
        System.out.println("ensureNoCalWithDuplicatedTINNumberIsNotSaved");

        company.saveClinicalAnalysisLaboratory(c1);
        company.saveClinicalAnalysisLaboratory(c2);
        company.saveClinicalAnalysisLaboratory(c3);

        ClinicalAnalysisLaboratoryDTO c0Dto = new ClinicalAnalysisLaboratoryDTO("MEL23",
                "BMAC","Bragança","97777378811","1234567890", testTypeCodes);
        ClinicalAnalysisLaboratory c0 = company.createClinicalAnalysisLaboratory(c0Dto);

        boolean result = company.saveClinicalAnalysisLaboratory(c0);
    }

    @Test
    public void validateClinicalAnalysisLaboratory() {
        System.out.println("validateClinicalAnalysisLaboratory");

        ClinicalAnalysisLaboratoryDTO c0Dto = new ClinicalAnalysisLaboratoryDTO("MEL23",
                "BMAC","Bragança","97777378811","1234567890", testTypeCodes);
        ClinicalAnalysisLaboratory c0 = company.createClinicalAnalysisLaboratory(c0Dto);

        boolean result = company.validateClinicalAnalysisLaboratory(c0);
    }


}
