package app.domain.thirdparty.adapters;

import app.domain.model.MyBarcode;
import app.domain.shared.utils.BarcodeUtils;
import app.domain.thirdparty.interfaces.ExternalAPI;
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
        return new MyBarcode(barcode, barcodeNumber);
    }

    @Override
    public void saveImageBarcode(MyBarcode myBarcode, String code) throws IOException, OutputException {
        File imageFolderPath = BarcodeUtils.imageFolderPath(code);
        Barcode barcode = (Barcode) myBarcode.getBarcode();
        barcode.setPreferredBarHeight(100);
        File imgFile = new File(imageFolderPath + "/barcode" + barcode.getData() + ".jpeg");
        BarcodeImageHandler.saveJPEG(barcode, imgFile);
    }

}


