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
        t1 = new TestType("CODE3","Description","swab",pcList);
        t2 = new TestType("CODE4","Description","swab",pcList);
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

    /*
    @Test
    public void validateClinicalAnalysisLaboratory() {
    }

    @Test
    public void saveClinicalAnalysisLaboratory() {
    }
    
     */
}