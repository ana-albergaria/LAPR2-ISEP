package app.domain.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Sample {
    private MyBarcode myBarcode;
    private Date collectingDate;

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
