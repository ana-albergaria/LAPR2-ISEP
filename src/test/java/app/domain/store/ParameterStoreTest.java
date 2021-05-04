package app.domain.store;

import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParameterStoreTest {

    private ParameterCategory p1;
    private ParameterCategory p2;

    @Before
    public void setUp() {
        p1 = new ParameterCategory("HEM01","Hemogram");
        p2 = new ParameterCategory("COD01","Name");
    }

    @Test
    public void createsParameterStore() {
        ParameterStore ps1 = new ParameterStore();
        Parameter[] result = ps1.toArray();
        Assert.assertEquals(0, result.length);
    }

    @Test
    public void createParameterStoreWithSomeElements() {
        ParameterStore ps1 = new ParameterStore();
        Parameter parameter1 = ps1.createParameter("RBC01", "RBC", "Red Blood Cells", p1);
        ps1.saveParameter(parameter1);
        Parameter parameter2 = ps1.createParameter("WBC01", "WBC", "White Blood Cells", p1);
        ps1.saveParameter(parameter2);
        Parameter parameter3 = ps1.createParameter("PLT01", "PLT", "Platelets", p1);
        ps1.saveParameter(parameter3);
        Parameter parameter4 = ps1.createParameter("PCOD1", "NAME", "This is a description", p2);
        ps1.saveParameter(parameter4);
        Parameter[] result = ps1.toArray();
        Assert.assertEquals(4, result.length);
    }

    @Test
    public void validateParameter() {

    }

    @Test
    public void saveParameter() {
        
    }



}