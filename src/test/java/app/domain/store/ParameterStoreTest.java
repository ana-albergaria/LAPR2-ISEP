package app.domain.store;

import app.domain.model.Company;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.store.ParameterStore;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParameterStoreTest {

    private ParameterCategory p1;
    private ParameterCategory p2;
    private Company company = new Company("Many Labs");
    private List<ParameterCategory> pcList;

    @Before
    public void setUp() {
        pcList = new ArrayList<>();
        p1 = new ParameterCategory("HEM01","Hemogram");
        p2 = new ParameterCategory("COD01","Name");
        pcList.add(p1);
        pcList.add(p2);
    }

    @Test
    public void createParameterStore() {
        System.out.println("ensureParameterStoreIsBeingCreatedCorrectly");
        ParameterStore ps1 = new ParameterStore();
        Parameter[] result = ps1.toArray();
        Assert.assertEquals(0, result.length);
    }

    @Test
    public void createParameterStoreWithSomeElements() {
        System.out.println("createParameterStoreWithSomeElements");
        ParameterStore ps1 = new ParameterStore();
        Parameter parameter1 = ps1.createParameter("RBC01", "RBC", "Red Blood Cells", p1);
        ps1.saveParameter(parameter1);
        Parameter parameter2 = ps1.createParameter("WBC01", "WBC", "White Blood Cells", p1);
        ps1.saveParameter(parameter2);
        Parameter parameter3 = ps1.createParameter("PLT01", "PLT", "Platelets", p1);
        ps1.saveParameter(parameter3);
        Parameter parameter4 = ps1.createParameter("PCOD1", "NAME", "Description", p2);
        ps1.saveParameter(parameter4);
        Parameter[] result = ps1.toArray();
        Assert.assertEquals(4, result.length);
    }

    @Test
    public void createParameter() {
        System.out.println("createParameter");
        Parameter expected = new Parameter("RBC01", "RBC", "Red Blood Cells", p1);
        ParameterStore parameterStore = company.getParameterStore();
        Parameter actual = parameterStore.createParameter("RBC01", "RBC", "Red Blood Cells", p1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void ensureParameterIsNotSavedRepeatedWithSameObject() {
        System.out.println("ensureParameterIsNotSavedRepeatedWithSameObject");
        Parameter parameter1 = company.getParameterStore().createParameter("RBC01", "RBC", "Red Blood Cells", p1);
        Parameter parameter2 = company.getParameterStore().createParameter("RBC01", "RBC", "Red Blood Cells", p1);
        ParameterStore parameterStore = company.getParameterStore();
        parameterStore.saveParameter(parameter1);
        boolean result = parameterStore.saveParameter(parameter2);
        assertFalse(result);
    }

    @Test
    public void ensureParameterIsNotSavedIfNull() {
        System.out.println("ensureParameterIsNotSavedIfNull");
        assertFalse(company.getTestTypeStore().saveTestType(null));
    }

    /*@Test
    public void validateParameter() {

    }

    @Test
    public void saveParameter() {

    }*/



}