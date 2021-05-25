package app.domain.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Represents a Sample through:
 * a MyBarcode (containing the barcode number and the barcode generated by the API) and a collecting Date.
 *
 * @author Ana Albergaria
 */


public class Sample {
    /**
     * The MyBarcode of the Sample.
     */
    private MyBarcode myBarcode;
    /**
     * The collecting date of the Sample.
     */
    private Date collectingDate;

    /**
     * 
     * @param myBarcode
     */
    public Sample(MyBarcode myBarcode) {
        this.myBarcode = myBarcode;
        this.collectingDate = Calendar.getInstance().getTime();
    }

    public MyBarcode getMyBarcode() {
        return myBarcode;
    }

    public Date getCollectingDate() {
        return collectingDate;
    }


}
