package app.domain.shared;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;

public interface ExternalAPI {
    public abstract Barcode getBarcode(String barcodeNumber) throws BarcodeException;
}
