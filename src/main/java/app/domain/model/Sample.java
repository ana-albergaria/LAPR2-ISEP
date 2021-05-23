package app.domain.model;

import java.util.Date;

public class Sample {
    private MyBarcode barcode;
    private Date collectingDate;

    public Sample(MyBarcode barcode) {
        this.barcode = barcode;
        this.collectingDate = new Date();
    }

    public MyBarcode getMyBarcode() {
        return barcode;
    }

    public Date getCollectingDate() {
        return collectingDate;
    }
}
