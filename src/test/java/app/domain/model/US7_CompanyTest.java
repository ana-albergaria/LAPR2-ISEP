package app.domain.model;

import app.mappers.dto.EmployeeDTO;
import app.mappers.dto.SpecialistDoctorDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class US7_CompanyTest {
    private Company company;
    private OrgRole r1;
    private OrgRole r2;

    @Before
    public void setUp() {
        company = new Company("Many Labs");
        r1 = new OrgRole("Specialist Doctor");
        r2 = new OrgRole("Medical Lab Technician");
    }

   /* @Test
    public void createSpecialistDoctor() {
        System.out.println("createEmployee (CompanyTest)");

        //Arrange
        SpecialistDoctor expObj = new SpecialistDoctor(r1,"12345",
                "Afonso","Lisboa","123456","afonso@gmail.com",
                "socCode12","344444444");

        SpecialistDoctorDTO empDTO = new SpecialistDoctorDTO("Specialist Doctor","12345",
                "Afonso","Lisboa","123456","afonso@gmail.com",
                "socCode12","344444444");

        //Act
        Employee obj = company.createSpecialistDoctor(empDTO);

        //Assert
        Assert.assertEquals(expObj, obj);
    }*/

    /*@Test
    public void createEmployee() {
        System.out.println("createEmployee (CompanyTest)");

        //Arrange
        Employee expObj = new Employee(r2,"12345",
                "Afonso","Lisboa","123456","afonso@gmail.com",
                "socCode12");

        EmployeeDTO empDTO = new EmployeeDTO("Medical Lab Technician","12345",
                "Afonso","Lisboa","123456","afonso@gmail.com",
                "socCode12");

        //Act
        Employee obj = company.createEmployee(empDTO);

        //Assert
        Assert.assertEquals(expObj, obj);
    }*/
}
