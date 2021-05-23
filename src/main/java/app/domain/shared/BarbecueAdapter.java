package app.domain.shared;

import app.domain.model.MyBarcode;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

import java.io.File;
import java.io.IOException;

public class BarbecueAdapter implements ExternalAPI {

    @Override
    public MyBarcode getBarcode(String barcodeNumber) throws BarcodeException {
        Barcode barcode = BarcodeFactory.createUPCA(barcodeNumber);
        return new MyBarcode(barcode);
    }

    @Override
    public void saveImageBarcode(MyBarcode myBarcode) throws IOException, OutputException {
        Barcode barcode = (Barcode) myBarcode.getBarcode();
        barcode.setPreferredBarHeight(100);
        File imgFile = new File("./Samples/barcode" + barcode.getData() + ".jpeg");
        BarcodeImageHandler.saveJPEG(barcode, imgFile);
    }

}
