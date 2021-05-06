package app.domain.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParameterCategoryTest {
    Company company;

    @Before
    public void setUp(){
        company = new Company("many labs");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullArgsNotAllowed() {
        System.out.println("ensureNullArgsNotAllowed");
        ParameterCategory pc = new ParameterCategory(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCodeIsNotEmpty() {
        System.out.println("ensureCodeIsNotEmpty");

        ParameterCategory pc = new ParameterCategory("", "TESTE");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNameIsNotEmpty() {
        System.out.println("ensureNameIsNotEmpty");

        ParameterCategory pc = new ParameterCategory("CODE", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCodeIsNotMoreThan5Chars() {
        System.out.println("ensureCodeIsNotMoreThan5Chars");

        ParameterCategory pc = new ParameterCategory("MORETHEN5", "TESTE");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCodeIsNotLessThan5Chars() {
        System.out.println("ensureCodeIsNotLessThan5Chars");

        ParameterCategory pc = new ParameterCategory("LESS", "TESTE");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCodeIsAlphanumeric() {
        System.out.println("ensureCodeIsAlphanumeric");

        ParameterCategory pc = new ParameterCategory("@!$%#", "TESTE");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNameIsNotMoreThan10Chars() {
        System.out.println("ensureNameIsNotMoreThan10Chars");

        ParameterCategory pc = new ParameterCategory("CODE1", "MORTETHANTEN");
    }

    @Test
    public void ensureNameCanBe10char() {
        System.out.println("ensureNameCanBe10char");

        ParameterCategory pc = new ParameterCategory("CODE1", "EQUALSTEN");
    }

}