package app.domain.model;

import app.domain.store.TestTypeStore;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestTypeStoreTest {
    private List<ParameterCategory> pcList;
    private ParameterCategory p1;
    private ParameterCategory p2;
    private Company company = new Company("many labs");
    @Before
    public void setUp() {
        pcList = new ArrayList<>();
        p1 = new ParameterCategory("CODE1","Description");
        p2 = new ParameterCategory("CODE2","Description");
        pcList.add(p1);
        pcList.add(p2);
    }

    //Test 10: Check if createTestType method returns TestType correctly
    @Test
    public void createTestType() {
        TestType expected = new TestType("AAA23", "blood analysis", "needle", pcList);
        TestType actual = company.getTestTypeStore().createTestType("AAA23", "blood analysis", "needle", pcList);
        Assert.assertEquals(expected,actual);
    }

    //Test 11: Check that it is not possible to save a repeated Test Type in the store
    @Test
    public void ensureTestTypeIsNotSavedRepeatedWithSameObject() {
        System.out.println("ensureTestTypeIsNotSavedRepeatedWithSameObject");
        TestType t1 = company.getTestTypeStore().createTestType("AAA23", "blood analysis", "needle", pcList);
        company.getTestTypeStore().saveTestType(t1);
        boolean actual = company.getTestTypeStore().saveTestType(t1);
        assertFalse(actual);
    }

    //Test 11(with different objects with same attributes): Check that it is not possible to save a repeated Test Type in the store
    @Test
    public void ensureTestTypeIsNotSavedRepeatedWithAlikeObject() {
        System.out.println("ensureTestTypeIsNotSavedRepeatedWithAlikeObject");
        TestType t1 = company.getTestTypeStore().createTestType("AAA23", "blood analysis", "needle", pcList);
        TestType t2 = company.getTestTypeStore().createTestType("AAA23", "blood analysis", "needle", pcList);
        company.getTestTypeStore().saveTestType(t1);
        boolean actual = company.getTestTypeStore().saveTestType(t2);
        assertFalse(actual);
    }

    //Test 12: Check that it is not possible to save a null Test Type in the store
    @Test
    public void ensureTestTypeIsNotSavedIfNull() {
        System.out.println("ensureTestTypeIsNotSavedIfNull");
        assertFalse(company.getTestTypeStore().saveTestType(null));
    }

    //Test 13: Check that it is not possible to get the test types by code with not assigned codes
    @Test(expected = UnsupportedOperationException.class)
    public void ensureGetWithInvalidTestTypeCodeThrowsException() {
        System.out.println("ensureGetWithInvalidTestTypeCodeThrowsException");
        TestTypeStore testTypeStore = company.getTestTypeStore();
        TestType t1 = testTypeStore.createTestType("AAAA1", "blood analysis", "needle", pcList);
        TestType t2 = testTypeStore.createTestType("AAAA2", "blood analysis", "needle", pcList);
        testTypeStore.saveTestType(t1);
        testTypeStore.saveTestType(t2);
        List<String> codeList = new ArrayList<String>();
        codeList.add("AAAA1");
        codeList.add("AAAAA");

        testTypeStore.getTestTypesByCode(codeList);
    }

   /* //Test 14: Check that it is not possible to save a null Test Type in the store
    @Test
    public void ensureTestTypeIsNotSavedIfNull() {
        System.out.println("ensureTestTypeIsNotSavedIfNull");
        assertFalse(company.getTestTypeStore().saveTestType(null));
    }*/
}