package app.domain.shared;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;

public class BarbecueAdapter implements ExternalAPI {
    public Barcode getBarcode(String barcodeNumber) throws BarcodeException {
        return BarcodeFactory.createUPCA(barcodeNumber);
    }
}
