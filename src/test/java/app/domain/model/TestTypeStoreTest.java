package app.domain.model;

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

    @Test
    public void createTestType() {
        TestType expected = new TestType("AAA23", "blood analysis", "needle", pcList);
        TestType actual = company.getTestTypeStore().createTestType("AAA23", "blood analysis", "needle", pcList);
        System.out.println(actual);
        Assert.assertEquals(expected,actual);
    }

 /*
    @Test
    public void getTestTypes() {
    }

    @Test
    public void validateTestType() {
    }

    @Test
    public void saveTestType() {
    }

    @Test
    public void getTestTypesByCode() {
    }

    @Test
    public void getSingleTestTypeByCode() {
    }

  */
}