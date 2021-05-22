package app.domain.model;



public class Sample {
    //private MyBarcode barcode;
    private String barcodeNumber;
    private static int totalSamples = 0;

    public Sample() {
        //this.barcode = barcode;
        this.barcodeNumber = generateBarcodeNumber();
        totalSamples++;
    }

    /*
    public MyBarcode getMyBarcode() {
        return barcode;
    }
     */

    public String getBarcodeNumber() {
        return barcodeNumber;
    }

    public String generateBarcodeNumber() {
        return String.format("%12d", totalSamples);
    }
}
