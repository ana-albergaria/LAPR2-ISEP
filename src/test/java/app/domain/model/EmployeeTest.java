package app.domain.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeTest {
    private Company company;
    private OrgRole r1;
    private OrgRole r2;

    @Before
    public void setUp() {
        company = new Company("Many Labs");
        r1 = new OrgRole("Spec Doctor");
        r2 = new OrgRole("Med Lab Tech");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAC8NameNotNull() {
        Employee instance = new Employee(r1, null, "Morada",
                "1234567890","joana@gmail.com","1234");
    }
    @Test(expected = IllegalArgumentException.class)
    public void ensureAC8NameNotEmpty() {
        Employee instance = new Employee(r1, "", "Morada",
                "1234567890","joana@gmail.com","1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAC8NameNotWhiteSpace() {
        Employee instance = new Employee(r1, " ", "Morada",
                "1234567890","joana@gmail.com","1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAC8NameHasTheRightLength() {
        Employee instance = new Employee(r1, "abcdefghijklmnopqrstvuxzasdfghjklqejfjgkgnkfgnfkngkfngknkfn",
                "Morada", "1234567890","joana@gmail.com","1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAC8NameOnlyHasLetters() {
        Employee instance = new Employee(r1, "Laboratório1",
                "Morada", "1234567890","joana@gmail.com","1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAC9PhoneNumberNotNull() {
        Employee instance = new Employee(r1, "laboratório", "Morada",
                null,"joana@gmail.com","1234");
    }
    @Test(expected = IllegalArgumentException.class)
    public void ensureAC9PhoneNumberNotEmpty() {
        Employee instance = new Employee(r1, "laboratório", "Morada",
                "","joana@gmail.com","1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAC9PhoneNumberNotWhiteSpace() {
        Employee instance = new Employee(r1, "laboratôrio", "Morada",
                " ","joana@gmail.com","1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAC9PhoneNumberHasTheRightLength() {
        Employee instance = new Employee(r1, "laboratôrio", "Morada",
                "123","joana@gmail.com","1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAC9PhoneNumberHasOnlyDigits() {
        Employee instance = new Employee(r1, "laboratôrio", "Morada",
                "12a3456789","joana@gmail.com","1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAC10EmailNotNull() {
        Employee instance = new Employee(r1, "lab", "Morada",
                "1234567890",null,"1234");
    }
    @Test(expected = IllegalArgumentException.class)
    public void ensureAC10EmailNotEmpty() {
        Employee instance = new Employee(r1, "lab", "Morada",
                "1234567890","","1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAC10EmailNotWhiteSpace() {
        Employee instance = new Employee(r1, "lab", "Morada",
                "1234567890"," ","1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAC10EmailHasTheRightFormat() {
        Employee instance = new Employee(r1, "lab", "Morada",
                "1234567890","analamas","1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureSocCodeNotNull() {
        Employee instance = new Employee(r1, "lab", "Morada",
                "1234567890","joana@gmail.com",null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void ensureSocCodeNotEmpty() {
        Employee instance = new Employee(r1, "lab", "Morada",
                "1234567890","joana@gmail.com","");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureSocCodeNotWhiteSpace() {
        Employee instance = new Employee(r1, "lab", "Morada",
                "1234567890","joana@gmail.com"," ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureSocCodeWithRightLength() {
        Employee instance = new Employee(r1, "lab", "Morada",
                "1234567890","joana@gmail.com","123457");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureSocCodeOnlyHasDigits() {
        Employee instance = new Employee(r1, "lab", "Morada",
                "1234567890","joana@gmail.com","123a");
    }

}