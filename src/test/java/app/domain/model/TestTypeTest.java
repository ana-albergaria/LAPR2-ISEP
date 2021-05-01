package app.domain.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestTypeTest {

    private List<ParameterCategory> pcList;
    private ParameterCategory p1;
    private ParameterCategory p2;

    @Before
    public void setUp() {
        pcList = new ArrayList<>();
        p1 = new ParameterCategory("CODE1","Description");
        p2 = new ParameterCategory("CODE2","Description");
        pcList.add(p1);
        pcList.add(p2);
    }


    //Test 1: Check that it is not possible to create an instance of the ClinicalAnalysisLaboratory
    // class with null values.
    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        System.out.println("ensureNullIsNotAllowed");

        TestType instance = new TestType(null, null, null, null);
    }

    //Test 2: Check that it is not possible to create an instance of the TestType class with code being an empty String.
    @Test(expected = IllegalArgumentException.class)
    public void ensureTestTypeCodeIsNotEmpty() {
        System.out.println("ensureTestTypeCodeIsNotEmpty");

        TestType instance = new TestType("", "blood analysis", "needle", pcList);
    }

    //Test 3: Check that it is not possible to create an instance of the TestType class with code holding more than 5 characters.
    @Test(expected = IllegalArgumentException.class)
    public void ensureTestTypeCodeIsNotMoreThanFiveCharacteres() {
        System.out.println("ensureTestTypeCodeHasNotMoreThanFiveCharacteres");

        TestType instance = new TestType("AAA123", "blood analysis", "needle", pcList);
    }

    //Test 4: Check that it is not possible to create an instance of the TestType class with code holding less than 5 characters.
    @Test(expected = IllegalArgumentException.class)
    public void ensureTestTypeCodeisNotLessThanFiveCharacteres() {
        System.out.println("ensureTestTypeCodeisLessThanFiveCharacteres");

        TestType instance = new TestType("AA23", "blood analysis", "needle", pcList);
    }

    //Test 5: Check that it is not possible to create an instance of the TestType class with code holding not alphanumeric characters.
    @Test(expected = IllegalArgumentException.class)
    public void ensureTestTypeCodeIsAlphanumeric() {
        System.out.println("ensureTestTypeCodeIsAlphanumeric");

        TestType instance = new TestType("AA23@", "blood analysis", "needle", pcList);
    }


}