package app.domain.model;

import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SampleTest {
    private Object barcode;
    private MyBarcode myBarcode;

    @Before
    public void setUp() throws BarcodeException {
        barcode = BarcodeFactory.createUPCA("12345678901");
        myBarcode = new MyBarcode(barcode, "12345678901");
    }

    @Test
    public void getCollectingDate() {
        Sample obj = new Sample(myBarcode);
        String expCollectingDate = null;
        //the Collecting Date of the Sample starts null and only will be added after all the samples are created.

        Assert.assertEquals(expCollectingDate, obj.getCollectingDate());

    }




}