package app.domain.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClinicalAnalysisLaboratoryTest {
    private List<ParameterCategory> pcList;
    private ParameterCategory p1;
    private ParameterCategory p2;
    private List<TestType> selectedTT;
    private TestType t1;
    private TestType t2;

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
    }

    //Test 1: Check that it is not possible to create an instance of the ClinicalAnalysisLaboratory
    // class with null values.
    @Test(expected = IllegalArgumentException.class)
        public void ensureNullIsNotAllowed() {
        System.out.println("ensureNullIsNotAllowed");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory(null,
                null,null,null,null,null);
    }


    //AC2 - The Laboratory ID must have five alphanumeric characters.
    //Test 2, 3, 4: Check that it is not possible to create an instance of the
    //ClinicalAnalysisLaboratory class with a blank (null, empty (""), or whitespace) laboratoryID.
    @Test(expected = IllegalArgumentException.class)
    public void ensureLaboratoryIDNotNull() {
        System.out.println("ensureAC2LaboratoryIDNotNull");

        //- Null laboratoryID
        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory(null,
                "CAL","Lisboa","91841378811","1234567890", selectedTT);

    }
    @Test(expected = IllegalArgumentException.class)
    public void ensureLaboratoryIDNotEmpty() {
        System.out.println("ensureAC2LaboratoryIDNotEmpty");

        //- Empty laboratoryID
        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("",
                "CAL","Lisboa","91841378811","1234567890", selectedTT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureLaboratoryIDNotWhiteSpace() {
        System.out.println("ensureAC2LaboratoryIDNotWhiteSpace");

        //- Whitespace laboratoryID
        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory(" ",
                "CAL","Lisboa","91841378811","1234567890", selectedTT);
    }

    //Test 5: Check that it is not possible to create an instance of the
    //ClinicalAnalysisLaboratory class with a laboratoryID length different than 5.
    @Test(expected = IllegalArgumentException.class)
    public void ensureLaboratoryIDWithRightLength() {
        System.out.println("ensureAC2LaboratoryIDWithRightLength");

        //- Whitespace laboratoryID
        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL123456",
                "CAL","Lisboa","91841378811","1234567890",selectedTT);
    }



    //Test 6: Check that it is not possible to create an instance of the
    //ClinicalAnalysisLaboratory class with a laboratoryID that
    //doesn't contain alphanumeric characters.
    @Test(expected = IllegalArgumentException.class)
    public void ensureLaboratoryIDIsAlphanumeric() {
        System.out.println("ensureAC2LaboratoryIDIsAlphanumeric");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("C.L1@",
                "CAL","Lisboa","91841378811","1234567890", selectedTT);
    }

    //AC3 - The name is a string with no more than 20 characters.
    //Test 7: Check that it is not possible to create an instance of the
    //ClinicalAnalysisLaboratory class with a with a blank
    // (null, empty (""), or whitespace) name.

    @Test(expected = IllegalArgumentException.class)
    public void ensureNameNotNull() {
        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                null,"Lisboa","91841378811","1234567890",selectedTT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNameNotEmpty() {
        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "","Lisboa","91841378811","1234567890",selectedTT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNameNotWhiteSpace() {
        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                " ","Lisboa","91841378811","1234567890",selectedTT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNameOnlyLetters() {
        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "Ana24","Lisboa","91841378811","1234567890",selectedTT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNameWithRightLength() {
        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "Clinical Laboratory ManyLabs ","Lisboa","91841378811","1234567890",selectedTT);
    }


}