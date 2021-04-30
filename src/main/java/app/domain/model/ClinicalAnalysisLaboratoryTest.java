package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClinicalAnalysisLaboratoryTest {

    //Test 1: Check that it is not possible to create an instance of the ClinicalAnalysisLaboratory
    // class with null values.
    @Test(expected = IllegalArgumentException.class)
        public void ensureNullIsNotAllowed() {
        System.out.println("ensureNullIsNotAllowed");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory(null,
                null,null,null,null,null);
    }

    //AC2
    //Test 2: Check that it is not possible to create an instance of the
    //ClinicalAnalysisLaboratory class with a blank (null, empty (""), or whitespace) laboratoryID.
    @Test(expected = IllegalArgumentException.class)
    public void ensureLaboratoryIDNotNull() {
        System.out.println("ensureAC2LaboratoryIDNotNull");

        List<ParameterCategory> pcList1 = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("CODE1","Description","NHDID");
        ParameterCategory p2 = new ParameterCategory("CODE2","Description","NHDID");
        pcList1.add(p1);
        pcList1.add(p2);

        List<TestType> selectedTT = new ArrayList<>();
        TestType t1 = new TestType("CODE3","Description","swab",pcList1);
        TestType t2 = new TestType("CODE4","Description","swab",pcList1);
        selectedTT.add(t1);
        selectedTT.add(t2);

        //- Null laboratoryID
        ClinicalAnalysisLaboratory instance1 = new ClinicalAnalysisLaboratory(null,
                "CAL","Lisboa","91841378811","1234567890",selectedTT);
        //- Empty laboratoryID
        ClinicalAnalysisLaboratory instance2 = new ClinicalAnalysisLaboratory("",
                "CAL","Lisboa","91841378811","1234567890",selectedTT);
        //- Whitespace laboratoryID
        ClinicalAnalysisLaboratory instance3 = new ClinicalAnalysisLaboratory(" ",
                "CAL","Lisboa","91841378811","1234567890",selectedTT);
    }
    @Test(expected = IllegalArgumentException.class)
    public void ensureLaboratoryIDNotEmpty() {
        System.out.println("ensureAC2LaboratoryIDNotEmpty");

        List<ParameterCategory> pcList2 = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("CODE1","Description","NHDID");
        ParameterCategory p2 = new ParameterCategory("CODE2","Description","NHDID");
        pcList2.add(p1);
        pcList2.add(p2);

        List<TestType> selectedTT = new ArrayList<>();
        TestType t1 = new TestType("CODE3","Description","swab",pcList2);
        TestType t2 = new TestType("CODE4","Description","swab",pcList2);
        selectedTT.add(t1);
        selectedTT.add(t2);

        //- Empty laboratoryID
        ClinicalAnalysisLaboratory instance2 = new ClinicalAnalysisLaboratory("",
                "CAL","Lisboa","91841378811","1234567890",selectedTT);
        //- Whitespace laboratoryID
        ClinicalAnalysisLaboratory instance3 = new ClinicalAnalysisLaboratory(" ",
                "CAL","Lisboa","91841378811","1234567890",selectedTT);
    }
    @Test(expected = IllegalArgumentException.class)
    public void ensureLaboratoryIDNotWhiteSpace() {
        System.out.println("ensureAC2LaboratoryIDNotWhiteSpace");

        List<ParameterCategory> pcList3 = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("CODE1","Description","NHDID");
        ParameterCategory p2 = new ParameterCategory("CODE2","Description","NHDID");
        pcList3.add(p1);
        pcList3.add(p2);

        List<TestType> selectedTT = new ArrayList<>();
        TestType t1 = new TestType("CODE3","Description","swab",pcList3);
        TestType t2 = new TestType("CODE4","Description","swab",pcList3);
        selectedTT.add(t1);
        selectedTT.add(t2);

        //- Whitespace laboratoryID
        ClinicalAnalysisLaboratory instance3 = new ClinicalAnalysisLaboratory(" ",
                "CAL","Lisboa","91841378811","1234567890",selectedTT);
    }



}