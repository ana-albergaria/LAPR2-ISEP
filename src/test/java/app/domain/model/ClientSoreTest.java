package app.domain.model;

import app.domain.store.ClientSore;
import app.domain.utils.Data;
import org.junit.Assert;
import org.junit.Test;

public class ClientSoreTest {

    @Test
    public void createsClientStore() {
        // Act
        ClientSore cs1 = new ClientSore(); // StudentList empty
        //Assert
        Client [] result = cs1.toArray();
        Assert.assertEquals(0, result.length); // check array
    }



    @Test
    public void createClientStoreWithSomeElements() {

        Data d1 = new Data (2002,1,5);

        ClientSore cs1 = new ClientSore();

        Client cl1 = cs1.registerClient("1234567890123457","1234567890",d1,"Male","1234567891","alex1@gmail.com","Alex", "12345678901");
        cs1.saveClient(cl1);

        Client cl2 = cs1.registerClient("1234567890123458","1234567891",d1,"Female","1234567892","alex2@gmail.com","Alex", "12345678902");
        cs1.saveClient(cl2);

        Client cl3 =cs1.registerClient("1234567890123459","1234567892",d1,"Male","1234567893","alex3@gmail.com","Alex", "12345678903");
        cs1.saveClient(cl3);

        Client [] result = cs1.toArray();
        Assert.assertEquals(3, result.length);



    }

    /*@Test
    public void createClientStoreWithSomeElementsButTwoAreTheSame() {

        Data d1 = new Data (2002,1,5);

        ClientSore cs1 = new ClientSore();

        Client cl1 = cs1.registerClient("1234567890123457","1234567890",d1,"Male","1234567891","alex1@gmail.com","Alex");
        cs1.saveClient(cl1);

        Client cl2 = cs1.registerClient("1234567890123457","1234567891",d1,"Female","1234567892","alex2@gmail.com","Alex");
        cs1.saveClient(cl2);

        Client cl3 =cs1.registerClient("1234567890123459","1234567892",d1,"Male","1234567893","alex3@gmail.com","Alex");
        boolean c = cs1.saveClient(cl3);


        Client [] result = cs1.toArray();
        Assert.assertEquals(2, result.length);

    }
*/


    @Test
    public void validateClient() {
    }

    @Test
    public void saveClient() {
    }

    @Test
    public void getClient() {
    }
}