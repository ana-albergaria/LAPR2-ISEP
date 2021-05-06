package app.domain.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        Client client = new Client("", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClient150yearsOld() throws ParseException {
        Date d2 = new SimpleDateFormat("dd/MM/yyyy").parse("02/02/1870");
        Client client = new Client("", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithCitizenCardNumberWith17Digits() throws ParseException {

        Date d1 = null;
        try {
            d1 = new SimpleDateFormat("dd/MM/yyyy").parse("08/08/2001");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ;
        Client client = new Client("11111111111111111", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithCitizenCardNumberWith15Digits() throws ParseException {

        Client client = new Client("111111111111111", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithCitizenCardNumberIsFullOfSpaces() {

        Client client = new Client(" ", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithNhsNumberEmpty() {
        Client client = new Client("1234567890123456", "", d1, "Male", "1111111111", "alex@gmail.com", "Alex");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithNhsNumberNumberWith11Digits() {

        Client client = new Client("1234567890123456", "12345678901", d1, "Male", "1234567890", "alex@gmail.com", "Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithNhsNumberNumberWith9Digits() {

        Client client = new Client("1234567890123456", "123456789", d1, "Male", "1234567890", "alex@gmail.com", "Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithNhsNumberFullOfSpaces() {

        Client client = new Client("1234567890123456", "  ", d1, "Male", "1234567890", "alex@gmail.com", "Alex");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithSexEmpty() {
        Client client = new Client("1234567890123456", "1234567890", d1, "", "1111111111", "alex@gmail.com", "Alex");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithSexOtherThanFemaleOrMaleJustDigits() {

        Client client = new Client("1234567890123456", "1234567890", d1, "123124", "1111111111", "alex@gmail.com", "Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithSexOtherThanFemaleOrMaleJustLeters() {

        Client client = new Client("1234567890123456", "1234567890", d1, "awodkwq", "1111111111", "alex@gmail.com", "Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createNhsNumberIsFullOfSpaces() {

        Client client = new Client("1234567890123456", "1234567890", d1, "   ", "1111111111", "alex@gmail.com", "Alex");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithTinNumberEmpty() {
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "", "alex@gmail.com", "Alex");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithTinNumberWith11Digits() {

        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "12345678901", "alex@gmail.com", "Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithTinNumberWith9Digits() {

        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "123456789", "alex@gmail.com", "Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithTinNumberFullOfSpaces() {

        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "   ", "alex@gmail.com", "Alex");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithEmailEmpty() {
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "", "Alex");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithEmailWrong() {

        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alexgmail.com", "Alex");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithEmailFullOfSpaces() {

        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "  ", "Alex");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithEmptyName() {
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithNameWrong() {

        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithNameFullOfSpaces() {

        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "  ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithPhoneNumberEmpty() throws ParseException {
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse("08/08/2001");
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithPhoneNumberWith12Digits() {

        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "123456789012");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithPhoneNumberWith10Digits() {

        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "1234567890");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithPhoneNumberFullOfSpaces() {

        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", " ");
    }


    @Test
    public void equalsTrue() {
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

        // Arrange
        Client c1 = new Client("1234567890123457", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678901");
        // Act

        Assert.assertTrue(c1.equals(c1));
    }


    @Test
    public void equalsFalseDueToNull() throws ParseException {
        // Arrange
        Client c1 = new Client("1234567890123457", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678901");
        // Act
        boolean result = c1.equals(null);
        Assert.assertFalse(result);
    }


}