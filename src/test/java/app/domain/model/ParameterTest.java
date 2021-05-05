package app.domain.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParameterTest {

    private ParameterCategory p1;

    @Before
    public void setUp() {
        p1 = new ParameterCategory("HEM01","Hemogram");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createParameterWithParameterCodeEmpty() {
        Parameter parameter = new Parameter("", "RBC", "Red Blood Cells", p1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createParameterWithParameterCodeWith4Chars() {
        Parameter parameter = new Parameter("RBC1", "RBC", "Red Blood Cells", p1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createParameterWithParameterCodeWith10Chars() {
        Parameter parameter = new Parameter("RBCCOUNT01", "RBC", "Red Blood Cells", p1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createParameterWithParameterCodeFullOfSpaces() {
        Parameter parameter = new Parameter("     ", "RBC", "Red Blood Cells", p1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createParameterWithShortNameEmpty() {
        Parameter parameter = new Parameter("RBC01", "", "Red Blood Cells", p1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createParameterWithShortNameWith9Chars() {
        Parameter parameter = new Parameter("RBC01", "RBC Count", "Red Blood Cells", p1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createParameterWithShortNameFullOfSpaces() {
        Parameter parameter = new Parameter("RBC01", "   ", "Red Blood Cells", p1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createParameterWithDescriptionEmpty() {
        Parameter parameter = new Parameter("RBC01", "RBC", "", p1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createParameterWithDescriptionWith61Chars() {
        Parameter parameter = new Parameter("RBC01", "RBC",
                "This parameter corresponds to the counting of Red Blood Cells", p1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createParameterWithDescriptionFullOfSpaces() {
        Parameter parameter = new Parameter("RBC01", "RBC", "               ", p1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createParameterWithParameterCategoryNull() {
        Parameter parameter = new Parameter("RBC01", "RBC", "Red Blood Cells", null);
    }

    /*@Test
    public void equalsTrue() {
        Parameter parameter1 = new Parameter("RBC01", "RBC", "Red Blood Cells", p1);
        Parameter parameter2 = new Parameter("RBC01", "RBC", "Red Blood Cells", p1);
        boolean result = parameter1.equals(parameter2);
        Assert.assertTrue(result);
    }

    @Test
    public void equalsFalse() {
        Parameter parameter1 = new Parameter("RBC01", "RBC", "Red Blood Cells", p1);
        Parameter parameter2 = new Parameter("WBC01", "WBC", "White Blood Cells", p1);
        boolean result = parameter1.equals(parameter2);
        Assert.assertFalse(result);
    }

    @Test
    public void equalsTrueToItself() {
        Parameter parameter1 = new Parameter("RBC01", "RBC", "Red Blood Cells", p1);
        boolean result = parameter1.equals(parameter1);
        Assert.assertTrue(result);
    }

    @Test
    public void equalsFalseDueToNull() {
        Parameter parameter1 = new Parameter("RBC01", "RBC", "Red Blood Cells", p1);
        boolean result = parameter1.equals(null);
        Assert.assertFalse(result);
    }*/

    //equalsTrueWithXDifferent

}