package app.domain.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpecialistDoctorTest {
    private Company company;
    private OrgRole r1;

    @Before
    public void setUp(){
        company = new Company("Many Labs");
        r1 = new OrgRole("Spec Doctor");
    }

    //+testes para os atributos

    @Test
    public void equalsTrue(){
        SpecialistDoctor sd1 = new SpecialistDoctor(r1, "João Matos", "Morada",
                "1234567890","joao@gmail.com","1234", "123456");
        SpecialistDoctor sd2 = new SpecialistDoctor(r1, "Joana Matos", "Morada",
                "1234567891","joana@gmail.com","1234", "123457");
        boolean result = sd1.equals(sd2);
        Assert.assertTrue(result);
    }

    @Test
    public void equalsFalse(){
        SpecialistDoctor sd1 = new SpecialistDoctor(r1, "João Matos", "Morada",
                "1234567890","joao@gmail.com","1234", "123456");
        SpecialistDoctor sd2 = new SpecialistDoctor(r1, "Joana Matos", "Morada",
                "1234567891","joana@gmail.com","1234", "123457");
        boolean result = sd1.equals(sd2);
        Assert.assertFalse(result);
    }

    @Test
    public void equalsTrueToItself(){
        SpecialistDoctor sd1 = new SpecialistDoctor(r1, "João Matos", "Morada",
                "1234567890","joao@gmail.com","1234", "123456");
        boolean result = sd1.equals(sd1);
        Assert.assertTrue(result);
    }

    @Test
    public void equalsFalseDueToNull(){
        SpecialistDoctor sd1 = new SpecialistDoctor(r1, "João Matos", "Morada",
                "1234567890","joao@gmail.com","1234", "123456");
        boolean result = sd1.equals(null);
        Assert.assertFalse(result);
    }

}