package app.domain.shared.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtils {
    public static void main(String[] args) {
        System.out.println(formatDate(currentDateAndTime()));

    }
    /*
    public static String currentDateAndTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        return sdf.format(date);
    }
     */

    public static Date currentDateAndTime() {
        Calendar today = Calendar.getInstance();
        today.get(Calendar.YEAR);
        today.get(Calendar.MONTH); // janeiro é representado por 0
        today.get(Calendar.DAY_OF_MONTH);
        today.get(Calendar.HOUR_OF_DAY);
        today.get(Calendar.MINUTE);

        return today.getTime();
    }

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(date);
    }
}
