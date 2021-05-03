package app.domain.model;

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

    @Before
    public void setUp() {
        pcList = new ArrayList<>();
        p1 = new ParameterCategory("CODE1","Description");
        p2 = new ParameterCategory("CODE2","Description");
        pcList.add(p1);
        pcList.add(p2);
        t1 = new TestType("CODE3","Description","swab", pcList);
        t2 = new TestType("CODE4","Description","swab", pcList);
        selectedTT = new ArrayList<>();
        selectedTT.add(t1);
        selectedTT.add(t2);
        company = new Company("Many Labs");
    }


    @Test
    public void createClinicalAnalysisLaboratory() {
        System.out.println("createClinicalAnalysisLaboratory (CompanyTest)");

        //Arrange
        ClinicalAnalysisLaboratory expObj = new ClinicalAnalysisLaboratory("CAL12",
                "CAL","Lisboa","91841378811","1234567890", selectedTT);

        //Act
        ClinicalAnalysisLaboratory obj = company.createClinicalAnalysisLaboratory("CAL12",
                "CAL","Lisboa","91841378811","1234567890", selectedTT);

        //Assert
        Assert.assertEquals(expObj, obj);
    }

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

    @Test
    public void ensureNullClinicalAnalysisLaboratoryIsNotSaved() {
        System.out.println("ensureNullClinicalAnalysisLaboratoryIsNotSaved (CompanyTest)");

        //Act
        boolean result = company.saveClinicalAnalysisLaboratory(null);

        //Assert
        Assert.assertFalse(result);
    }
}