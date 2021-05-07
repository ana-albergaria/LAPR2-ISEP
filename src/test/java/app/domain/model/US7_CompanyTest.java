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
        r1 = new OrgRole("Spec Doctor");
        r2 = new OrgRole("Med Lab Tech");
    }

   @Test
    public void createSpecialistDoctor() {

        //Arrange
        SpecialistDoctor expObj = new SpecialistDoctor(r1,
                "Afonso","Lisboa","1234567890","afonso@gmail.com",
                "1234","123456");

        SpecialistDoctorDTO empDTO = new SpecialistDoctorDTO("Spec Doctor",
                "Afonso","Lisboa","1234567890","afonso@gmail.com",
                "1234","123456");

        //Act
        Employee obj = company.createSpecialistDoctor(empDTO);

        //Assert
        Assert.assertEquals(expObj, obj);
    }

    @Test
    public void createEmployee() {

        //Arrange
        Employee expObj = new Employee(r2,
                "Afonso","Lisboa","1234567890","afonso@gmail.com",
                "1234");

        EmployeeDTO empDTO = new EmployeeDTO("Med Lab Tech",
                "Afonso","Lisboa","1234567890","afonso@gmail.com",
                "1234");

        //Act
        Employee obj = company.createEmployee(empDTO);

        //Assert
        Assert.assertEquals(expObj, obj);
    }
}
