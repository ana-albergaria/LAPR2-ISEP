package app.domain.store;

import app.domain.model.MyBarcode;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SampleStoreTest {
    private Object barcode;
    private MyBarcode myBarcode;

    @Before
    public void setUp() throws BarcodeException {
        barcode = (Barcode) BarcodeFactory.createUPCA("12345678901");
        myBarcode = new MyBarcode(barcode, barcode.getData());
    }

    @Test
    public void createSample() {
        SampleStore sampleStore = new SampleStore();
        sampleStore.createSample()

    }
}