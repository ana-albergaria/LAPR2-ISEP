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

    //Test 6: Check that it is not possible to create an instance of the TestType class with description holding more than 15 characters.
    @Test(expected = IllegalArgumentException.class)
    public void ensureTestTypeDescriptionLengthSmallerThan15Char() {
        System.out.println("ensureTestTypeDescriptionLengthSmallerThan15Char");

        TestType instance = new TestType("AA232", "1234567891234567", "needle", pcList);
    }
    //Test 6(mutation): Check that it is possible to create an instance of the TestType class with description holding 15 characters.
    @Test
    public void ensureTestTypeDescriptionLengthSmallerOrEqualThan15Char() {
        System.out.println("ensureTestTypeDescriptionLengthSmallerOrEqualThan15Char");

        TestType instance = new TestType("AA232", "123456789123456", "needle", pcList);
    }

    //Test 7: Check that it is not possible to create an instance of the TestType class with description being an empty String.
    @Test(expected = IllegalArgumentException.class)
    public void ensureTestTypeDescriptionIsNotEmpty() {
        System.out.println("ensureTestTypeDescriptionIsNotEmpty");

        TestType instance = new TestType("AA232", "", "needle", pcList);
    }

    //Test 8: Check that it is not possible to create an instance of the TestType class with collecting method holding more than 20 characters.
    @Test(expected = IllegalArgumentException.class)
    public void ensureTestTypeCollectingMethodLengthSmallerThan20Char() {
        System.out.println("ensureTestTypeCollectingMethodLengthSmallerThan20Char");

        TestType instance = new TestType("AA232", "blood analysis", "fourfourfourfourfour1", pcList);
    }
    //Test 8(mutation): Check that it is not possible to create an instance of the TestType class with collecting method holding 20 characters.
    @Test
    public void ensureTestTypeCollectingMethodLengthSmallerOrEqualThan20Char() {
        System.out.println("ensureTestTypeCollectingMethodLengthSmallerThan20Char");

        TestType instance = new TestType("AA232", "blood analysis", "fourfourfourfourfour", pcList);
    }

    //Test 9: Check that it is not possible to create an instance of the TestType class with collecting method being an empty String.
    @Test(expected = IllegalArgumentException.class)
    public void ensureTestTypeCollectingMethodIsNotEmpty() {
        System.out.println("ensureTestTypeDescriptionIsNotEmpty");

        TestType instance = new TestType("AA232", "blood analysis", "", pcList);
    }

}