package app.domain.model;


public class MyBarcode {
    private Object barcode;
    private static int totalBarcodes = 1;

    public MyBarcode(Object barcode) {
        this.barcode = barcode;
        totalBarcodes++;
    }

    public Object getBarcode() {
        return barcode;
    }

    public static int getTotalBarcodes() {
        return totalBarcodes;
    }
}

