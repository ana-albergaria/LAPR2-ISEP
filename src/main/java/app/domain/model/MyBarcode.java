package app.domain.model;


public class MyBarcode {
    private Object barcode;
    private String barcodeNumber;
    private static int totalBarcodes = 1;

    public MyBarcode(Object barcode, String barcodeNumber) {
        this.barcode = barcode;
        this.barcodeNumber = barcodeNumber;
        totalBarcodes++;
    }

    public Object getBarcode() {
        return barcode;
    }

    public String getBarcodeNumber() {
        return barcodeNumber;
    }

    public static int getTotalBarcodes() {
        return totalBarcodes;
    }
}

