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
     * The myBarcode of the Sample.
     */
    private MyBarcode myBarcode;
    /**
     * The collecting date of the Sample.
     */
    private Date collectingDate;

    /**
     * Builds a Sample's instance receiving:
     * the MyBarcode
     *
     * @param myBarcode the myBarcode of the Sample
     */
    public Sample(MyBarcode myBarcode) {
        this.myBarcode = myBarcode;
        this.collectingDate = Calendar.getInstance().getTime();
    }

    /**
     * Returns the myBarcode of the Sample
     *
     * @return myBarcode of the Sample
     */
    public MyBarcode getMyBarcode() {
        return myBarcode;
    }

    /**
     * Returns the collecting date of the Sample
     *
     * @return collecting date of the Sample
     */
    public Date getCollectingDate() {
        return collectingDate;
    }


}