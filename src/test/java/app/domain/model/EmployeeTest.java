package app.domain.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        Employee instance = new Employee(r1, "emp1",
                "Morada", "1234567890","joana@gmail.com","1234");
    }

    //MUTATION
    @Test
    public void ensureAC8NameWithLengthOf35() {

        Employee instance = new Employee(r1, "EmployeeComNomeTrintaECincoLetrasss",
                "Morada", "1234567890","joana@gmail.com","1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAC9PhoneNumberNotNull() {
        Employee instance = new Employee(r1, "emp", "Morada",
                null,"joana@gmail.com","1234");
    }
    @Test(expected = IllegalArgumentException.class)
    public void ensureAC9PhoneNumberNotEmpty() {
        Employee instance = new Employee(r1, "emp", "Morada",
                "","joana@gmail.com","1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAC9PhoneNumberNotWhiteSpace() {
        Employee instance = new Employee(r1, "emp", "Morada",
                " ","joana@gmail.com","1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAC9PhoneNumberHasTheRightLength() {
        Employee instance = new Employee(r1, "employee", "Morada",
                "123","joana@gmail.com","1234");
    }
    //MUTATION
    @Test
    public void ensurePhoneNumberHasLengthOf10() {
        Employee instance = new Employee(r1, "employee", "Morada",
                "1234567890","joana@gmail.com","1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAC9PhoneNumberHasOnlyDigits() {
        Employee instance = new Employee(r1, "employee", "Morada",
                "12a3456789","joana@gmail.com","1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAC10EmailNotNull() {
        Employee instance = new Employee(r1, "emp", "Morada",
                "1234567890",null,"1234");
    }
    @Test(expected = IllegalArgumentException.class)
    public void ensureAC10EmailNotEmpty() {
        Employee instance = new Employee(r1, "emp", "Morada",
                "1234567890","","1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAC10EmailNotWhiteSpace() {
        Employee instance = new Employee(r1, "emp", "Morada",
                "1234567890"," ","1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAC10EmailHasTheRightFormat() {
        Employee instance = new Employee(r1, "emp", "Morada",
                "1234567890","analamas","1234");
    }
    //MUTATION
    @Test
    public void checkEmailHasTheRightFormat() {
        Employee instance = new Employee(r1, "emp", "Morada",
                "1234567890","analamas@hotmail.com","1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureSocCodeNotNull() {
        Employee instance = new Employee(r1, "emp", "Morada",
                "1234567890","joana@gmail.com",null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void ensureSocCodeNotEmpty() {
        Employee instance = new Employee(r1, "emp", "Morada",
                "1234567890","joana@gmail.com","");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureSocCodeNotWhiteSpace() {
        Employee instance = new Employee(r1, "emp", "Morada",
                "1234567890","joana@gmail.com"," ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureSocCodeWithRightLength() {
        Employee instance = new Employee(r1, "emp", "Morada",
                "1234567890","joana@gmail.com","123457");
    }

    @Test
    public void ensureSocCodeWithLengthOf4() {
        Employee instance = new Employee(r1, "emp", "Morada",
                "1234567890","joana@gmail.com","1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureSocCodeOnlyHasDigits() {
        Employee instance = new Employee(r1, "emp", "Morada",
                "1234567890","joana@gmail.com","123a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAddressNotNull() {
        Employee instance = new Employee(r1, "employee", null,
                "1234567890","joana@gmail.com","1234");
    }
    @Test(expected = IllegalArgumentException.class)
    public void ensureAddressNotEmpty() {
        Employee instance = new Employee(r1, "employee", "",
                "1234567890","joana@gmail.com","1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAddressNotWhiteSpace() {
        Employee instance = new Employee(r1, "employee", " ",
                "1234567890","joana@gmail.com","1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAddressHasTheRightLength() {
        Employee instance = new Employee(r1, "employee", "Moradaasfsgdthfyjgukilihfeadgdhjklkhfsdfgvbjmjhmgfsddfhgjh",
                "1234567890","joana@gmail.com","1234");
    }

    //MUTATION
    @Test
    public void ensureAddressWithLengthOf30() {
        Employee instance = new Employee(r1, "employee", "qwertyuiopasdfghjkl zxcvbnmqwe",
                "1234567890","joana@gmail.com","1234");
    }



}