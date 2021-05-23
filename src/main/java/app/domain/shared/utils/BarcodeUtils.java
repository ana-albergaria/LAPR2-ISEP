package app.domain.shared.utils;

import app.domain.model.MyBarcode;

public class BarcodeUtils {
    public static String generateBarcodeNumber() {
        int totalBarcodes = MyBarcode.getTotalBarcodes();
        String s = String.format("%011d", totalBarcodes);

        if(s.length() > 11)
            throw new IllegalArgumentException("Impossible to generate more Barcodes with a unique barcode number.");

        return s;
    }

}
