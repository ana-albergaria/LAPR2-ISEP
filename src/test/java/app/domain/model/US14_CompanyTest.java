package app.domain.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class US14_CompanyTest {

    private Company company;

    @Before
    public void setUp() {
        company = new Company("Many Labs");
    }

    @Test
    public void createReport(){
        String reportText = "Everything is good.";

        Report reportExpected = new Report(reportText);

        Report reportActual = company.createReport(reportText);

        Assert.assertEquals(reportExpected, reportActual);
    }

}