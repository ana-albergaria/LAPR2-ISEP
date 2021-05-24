package app.domain.shared;

import app.domain.model.MyBarcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;

import java.io.IOException;

public interface ExternalAPI {
    public abstract MyBarcode getBarcode(String barcodeNumber) throws BarcodeException;

    public abstract void saveImageBarcode(MyBarcode myBarcode, String code) throws IOException, OutputException;
}
