package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClientTest {

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithCitizenCardNumberEmpty() {
        Data d1 = new Data (2002,1,5);
        Client client = new Client("","1234567890",d1,"Male","1234567890","alex@gmail.com","Alex");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithCitizenCardNumberWith17Digits() {

        Data d1 = new Data (2002,1,5);
        Client client = new Client("11111111111111111","1234567890",d1,"Male","1234567890","alex@gmail.com","Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithCitizenCardNumberWith15Digits() {

        Data d1 = new Data (2002,1,5);
        Client client = new Client("111111111111111","1234567890",d1,"Male","1234567890","alex@gmail.com","Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithCitizenCardNumberIsFullOfSpaces() {

        Data d1 = new Data (2002,1,5);
        Client client = new Client(" ","1234567890",d1,"Male","1234567890","alex@gmail.com","Alex");
    }





    @Test(expected = IllegalArgumentException.class)
    public void createClientWithNhsNumberEmpty() {
        Data d1 = new Data (2002,1,5);
        Client client = new Client("1234567890123456","",d1,"Male","1111111111","alex@gmail.com","Alex");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithNhsNumberNumberWith11Digits() {

        Data d1 = new Data (2002,1,5);
        Client client = new Client("1234567890123456","12345678901",d1,"Male","1234567890","alex@gmail.com","Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithNhsNumberNumberWith9Digits() {

        Data d1 = new Data (2002,1,5);
        Client client = new Client("1234567890123456","123456789",d1,"Male","1234567890","alex@gmail.com","Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithNhsNumberFullOfSpaces() {

        Data d1 = new Data (2002,1,5);
        Client client = new Client("1234567890123456","  ",d1,"Male","1234567890","alex@gmail.com","Alex");
    }





    @Test(expected = IllegalArgumentException.class)
    public void createClientWithSexEmpty() {
        Data d1 = new Data (2002,1,5);
        Client client = new Client("1234567890123456","1234567890",d1,"","1111111111","alex@gmail.com","Alex");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithSexOtherThanFemaleOrMaleJustDigits() {

        Data d1 = new Data (2002,1,5);
        Client client = new Client("1234567890123456","1234567890",d1,"123124","1111111111","alex@gmail.com","Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithSexOtherThanFemaleOrMaleJustLeters() {

        Data d1 = new Data (2002,1,5);
        Client client = new Client("1234567890123456","1234567890",d1,"awodkwq","1111111111","alex@gmail.com","Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createNhsNumberIsFullOfSpaces() {

        Data d1 = new Data (2002,1,5);
        Client client = new Client("1234567890123456","1234567890",d1,"   ","1111111111","alex@gmail.com","Alex");
    }






    @Test(expected = IllegalArgumentException.class)
    public void createClientWithTinNumberEmpty() {
        Data d1 = new Data (2002,1,5);
        Client client = new Client("1234567890123456","1234567890",d1,"Male","","alex@gmail.com","Alex");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithTinNumberWith11Digits() {

        Data d1 = new Data (2002,1,5);
        Client client = new Client("1234567890123456","1234567890",d1,"Male","12345678901","alex@gmail.com","Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithTinNumberWith9Digits() {

        Data d1 = new Data (2002,1,5);
        Client client = new Client("1234567890123456","1234567890",d1,"Male","123456789","alex@gmail.com","Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithTinNumberFullOfSpaces() {

        Data d1 = new Data (2002,1,5);
        Client client = new Client("1234567890123456","1234567890",d1,"Male","   ","alex@gmail.com","Alex");
    }





    @Test(expected = IllegalArgumentException.class)
    public void createClientWithEmailEmpty() {
        Data d1 = new Data (2002,1,5);
        Client client = new Client("1234567890123456","1234567890",d1,"Male","1234567890","","Alex");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithEmailWrong() {

        Data d1 = new Data (2002,1,5);
        Client client = new Client("1234567890123456","1234567890",d1,"Male","1234567890","alexgmail.com","Alex");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithEmailFullOfSpaces() {

        Data d1 = new Data (2002,1,5);
        Client client = new Client("1234567890123456","1234567890",d1,"Male","1234567890","  ","Alex");
    }






    @Test(expected = IllegalArgumentException.class)
    public void createClientWithEmptyName() {
        Data d1 = new Data (2002,1,5);
        Client client = new Client("1234567890123456","1234567890",d1,"Male","1234567890","alex@gmail.com","");
    }



    @Test(expected = IllegalArgumentException.class)
    public void createClientWithNameWrong() {

        Data d1 = new Data (2002,1,5);
        Client client = new Client("1234567890123456","1234567890",d1,"Male","1234567890","alex@gmail.com","");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithNameFullOfSpaces() {

        Data d1 = new Data (2002,1,5);
        Client client = new Client("1234567890123456","1234567890",d1,"Male","1234567890","alex@gmail.com","  ");
    }








    @Test(expected = IllegalArgumentException.class)
    public void createClientWithPhoneNumberEmpty() {
        Data d1 = new Data (2002,1,5);
        Client client = new Client("1234567890123456","1234567890",d1,"Male","1234567890","alex@gmail.com","Alex", "");
    }


    @Test(expected = IllegalArgumentException.class)
    public void createClientWithPhoneNumberWith12Digits() {

        Data d1 = new Data (2002,1,5);
        Client client = new Client("1234567890123456","1234567890",d1,"Male","1234567890","alex@gmail.com","Alex", "123456789012");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithPhoneNumberWith10Digits() {

        Data d1 = new Data (2002,1,5);
        Client client = new Client("1234567890123456","1234567890",d1,"Male","1234567890","alex@gmail.com","Alex", "1234567890");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientWithPhoneNumberFullOfSpaces() {

        Data d1 = new Data (2002,1,5);
        Client client = new Client("1234567890123456","1234567890",d1,"Male","1234567890","alex@gmail.com","Alex", " ");
    }





    @Test
    public void equalsTrue() {
        // Arrange
        Data d1 = new Data (2002,1,5);
        Client c1 = new Client("1234567890123457","1234567890",d1,"Male","1234567890","alex@gmail.com","Alex", "12345678901");

        Client c2 = new Client("1234567890123456","1234567890",d1,"Male","1234567890","alex@gmail.com","Alex", "12345678901");
        // Act
        boolean result = c1.equals(c2);
        // Assert
        Assert.assertTrue(result);
    }

    @Test
    public void equalsFalse() {
        // Arrange
        Data d1 = new Data (2002,1,5);
        Client c1 = new Client("1234567890123457","1234567891",d1,"Male","1234567891","alex2@gmail.com","Alex", "12345678901");

        Client c2 = new Client("1234567890123456","1234567890",d1,"Male","1234567890","alex@gmail.com","Alex Dias", "12345678902");
        // Act
        boolean result = c1.equals(c2);
        // Assert
        Assert.assertFalse(result);
    }


    @Test
    public void equalsTrueWithJustCitizenCardDifferent() {
        // Arrange
        Data d1 = new Data (2002,1,5);
        Client c1 = new Client("1234567890123457","1234567890",d1,"Male","1234567890","alex@gmail.com","Alex", "12345678901");

        Client c2 = new Client("1234567890123456","1234567890",d1,"Male","1234567890","alex@gmail.com","Alex", "12345678901");
        // Act
        boolean result = c1.equals(c2);
        // Assert
        Assert.assertTrue(result);
    }

    @Test
    public void equalsTrueToItself() {

        // Arrange
        Data d1 = new Data (2002,1,5);
        Client c1 = new Client("1234567890123457","1234567890",d1,"Male","1234567890","alex@gmail.com","Alex", "12345678901");
        // Act

        Assert.assertTrue(c1.equals(c1));
    }


    @Test
    public void equalsFalseDueToNull() {
        // Arrange
        Data d1 = new Data (2002,1,5);
        Client c1 = new Client("1234567890123457","1234567890",d1,"Male","1234567890","alex@gmail.com","Alex", "12345678901");
        // Act
        boolean result = c1.equals(null);
        Assert.assertFalse(result);
    }






















}