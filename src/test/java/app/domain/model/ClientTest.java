package app.domain.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientTest {
    public Date d1;

    @Before
    public void setUp() throws ParseException {
        d1 = new SimpleDateFormat("dd/MM/yyyy").parse("08/08/2001");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithCitizenCardNumberEmpty() throws ParseException {
        System.out.println("createClientWithCitizenCardNumberEmpty");
        Client client = new Client("", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithCitizenCardNumberWith17Digits() throws ParseException {
        System.out.println("createClientWithCitizenCardNumberWith17Digits");
        Client client = new Client("11111111111111111", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithCitizenCardNumberWith15Digits() throws ParseException {
        System.out.println("createClientWithCitizenCardNumberWith15Digits");
        Client client = new Client("111111111111111", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithCitizenCardNumberIsFullOfSpaces() {
        System.out.println("createClientWithCitizenCardNumberIsFullOfSpaces");
        Client client = new Client(" ", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void citizenCardWithLetters() {
        System.out.println("citizenCardWithLetters");
        Client client = new Client("A123456789101112", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithNhsNumberEmpty() {
        System.out.println("createClientWithNhsNumberEmpty");
        Client client = new Client("1234567890123456", "", d1, "Male", "1111111111", "alex@gmail.com", "Alex");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithNhsNumberNumberWith11Digits() {
        System.out.println("createClientWithNhsNumberNumberWith11Digits");
        Client client = new Client("1234567890123456", "12345678901", d1, "Male", "1234567890", "alex@gmail.com", "Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithNhsNumberNumberWith9Digits() {
        System.out.println("createClientWithNhsNumberNumberWith9Digits");
        Client client = new Client("1234567890123456", "123456789", d1, "Male", "1234567890", "alex@gmail.com", "Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithNhsNumberFullOfSpaces() {
        System.out.println("createClientWithNhsNumberFullOfSpaces");
        Client client = new Client("1234567890123456", "  ", d1, "Male", "1234567890", "alex@gmail.com", "Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void clientWithNhsNumberWithLetters() {
        System.out.println("clientWithNhsNumberWithLetters");
        Client client = new Client("1234567890123456", "A123456789", d1, "Male", "1234567890", "alex@gmail.com", "Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithSexEmpty() {
        System.out.println("createClientWithSexEmpty");
        Client client = new Client("1234567890123456", "1234567890", d1, "", "1111111111", "alex@gmail.com", "Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithSexWithJustDigits() {
        System.out.println("createClientWithSexWithJustDigits");
        Client client = new Client("1234567890123456", "1234567890", d1, "123124", "1111111111", "alex@gmail.com", "Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithSexOtherThanFemaleOrMaleJustLeters() {
        System.out.println("createClientWithSexOtherThanFemaleOrMaleJustLeters");
        Client client = new Client("1234567890123456", "1234567890", d1, "awodkwq", "1111111111", "alex@gmail.com", "Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createSexFullOfSpaces() {
        System.out.println("createSexFullOfSpaces");
        Client client = new Client("1234567890123456", "1234567890", d1, "   ", "1111111111", "alex@gmail.com", "Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithTinNumberEmpty() {
        System.out.println("createClientWithTinNumberEmpty");
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "", "alex@gmail.com", "Alex");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithTinNumberWith11Digits() {
        System.out.println("createClientWithTinNumberWith11Digits");
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "12345678901", "alex@gmail.com", "Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithTinNumberWith9Digits() {
        System.out.println("createClientWithTinNumberWith9Digits");
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "123456789", "alex@gmail.com", "Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithTinNumberWithLetters() {
        System.out.println("createClientWithTinNumberWithLetters");
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "AA12345678", "alex@gmail.com", "Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithTinNumberFullOfSpaces() {
        System.out.println("createSexFullOfSpaces");
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "   ", "alex@gmail.com", "Alex");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithEmailEmpty() {
        System.out.println("createSexFullOfSpaces");
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "", "Alex");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithEmailWrong() {
        System.out.println("createClientWithEmailWrong");
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alexgmail.com", "Alex");

    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithEmailFullOfSpaces() {
        System.out.println("createSexFullOfSpaces");
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "  ", "Alex");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithEmptyName() {
        System.out.println("createSexFullOfSpaces");
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithNameWrong() {
        System.out.println("createSexFullOfSpaces");
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithNameFullOfSpaces() {
        System.out.println("createSexFullOfSpaces");
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "  ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithPhoneNumberEmpty() throws ParseException {
        System.out.println("createSexFullOfSpaces");
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithPhoneNumberWith12Digits() {
        System.out.println("createSexFullOfSpaces");
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "123456789012");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithPhoneNumberWith10Digits() {
        System.out.println("createSexFullOfSpaces");
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "1234567890");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithPhoneNumberFullOfSpaces() {
        System.out.println("createSexFullOfSpaces");
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", " ");
    }

    /*@Test(expected = IllegalArgumentException.class)
    public void createClient150yearsOld() {
        System.out.println("createClient150yearsOld");
        try{
            Date d2 = new SimpleDateFormat("dd/MM/yyyy").parse("02/04/1873");
            Client client = new Client("1234567891222222", "1234567890", d2, "Male", "1234567890", "alex@gmail.com", "Alex");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }*/


    @Test
    public void equalsTrue() {
        System.out.println("createSexFullOfSpaces");
        // Arrange
        Client c1 = new Client("1234567890123457", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678901");

        Client c2 = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678901");
        // Act
        boolean result = c1.equals(c2);
        // Assert
        Assert.assertTrue(result);
    }

    @Test
    public void equalsFalse() {
        System.out.println("createSexFullOfSpaces");
        // Arrange
        Client c1 = new Client("1234567890123457", "1234567891", d1, "Male", "1234567891", "alex2@gmail.com", "Alex", "12345678901");

        Client c2 = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex Dias", "12345678902");
        // Act
        boolean result = c1.equals(c2);
        // Assert
        Assert.assertFalse(result);
    }


    @Test
    public void equalsTrueWithJustCitizenCardDifferent() {
        System.out.println("createSexFullOfSpaces");
        // Arrange
        Client c1 = new Client("1234567890123457", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678901");

        Client c2 = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678901");
        // Act
        boolean result = c1.equals(c2);
        // Assert
        Assert.assertTrue(result);
    }

    @Test
    public void equalsTrueToItself() {
        System.out.println("createSexFullOfSpaces");
        // Arrange
        Client c1 = new Client("1234567890123457", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678901");
        // Act

        Assert.assertTrue(c1.equals(c1));
    }


    @Test
    public void equalsFalseDueToNull() throws ParseException {
        System.out.println("createSexFullOfSpaces");
        // Arrange
        Client c1 = new Client("1234567890123457", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678901");
        // Act
        boolean result = c1.equals(null);
        Assert.assertFalse(result);
    }


}