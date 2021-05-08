package app.domain.store;

import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParameterCategoryStoreTest {
    private Company company;
    private ParameterCategoryStore parameterCategoryStore;

    @Before
    public void setUp() {
        company = new Company("Many Labs");
        parameterCategoryStore = company.getParameterCategoryStore();
    }


    @Test
    public void createParameterCategory() {
        ParameterCategory expObject = new ParameterCategory("code1","hemogram");
        ParameterCategory obj = parameterCategoryStore.createParameterCategory("code1", "hemogram");

        Assert.assertEquals(expObject, obj);

    }

    @Test
    public void ensureDifferentParameterCategoriesAreSaved() {
        ParameterCategory pc1 = new ParameterCategory("code1", "hemogram");
        parameterCategoryStore.saveParameterCategory(pc1);
        ParameterCategory pc2 = new ParameterCategory("code2","categorie2");

        boolean result = parameterCategoryStore.saveParameterCategory(pc2);

        Assert.assertTrue(result);
    }

    @Test
    public void ensureParameterCategoryIsNotSavedExistingAlreadyTheSameObject() {
        ParameterCategory pc1 = new ParameterCategory("code1", "hemogram");
        parameterCategoryStore.saveParameterCategory(pc1);

        boolean result = parameterCategoryStore.saveParameterCategory(pc1);

        Assert.assertFalse(result);
    }

    @Test
    public void ensureParameterCategoryIsNotSavedExistingEqualObject() {
        ParameterCategory pc1 = new ParameterCategory("code1", "hemogram");
        parameterCategoryStore.saveParameterCategory(pc1);
        ParameterCategory pc2 = new ParameterCategory("code1", "hemogram");

        boolean result = parameterCategoryStore.saveParameterCategory(pc2);

        Assert.assertFalse(result);
    }

    @Test
    public void ensureNullParameterCategoryIsNotSaved() {

        boolean result = parameterCategoryStore.saveParameterCategory(null);

        Assert.assertFalse(result);
    }

}