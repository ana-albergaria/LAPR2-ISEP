package app.domain.shared.utils;

import app.domain.model.MyBarcode;

import java.io.File;

public class BarcodeUtils {
    public static String generateBarcodeNumber() {
        int totalBarcodes = MyBarcode.getTotalBarcodes();
        String s = String.format("%011d", totalBarcodes);

        if(s.length() > 11)
            throw new IllegalArgumentException("Impossible to generate more Barcodes with a unique barcode number.");

        return s;
    }

    public static File imageFolderPath(String code) {
        File path = new File("./Samples/Test-Code" + code);
        if(!path.exists())
            path.mkdir();

        return path;
    }

}
