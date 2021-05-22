package app.domain.model;

import app.controller.RecordSamplesController;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;

import java.io.IOException;

public class Main {

    private static RecordSamplesController ctrl = new RecordSamplesController();

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, BarcodeException, IOException, OutputException {

        boolean success = ctrl.createSample();

        MyBarcode myBarcode = ctrl.getBarcode();

        ctrl.saveImageBarcode(myBarcode);

        String number = String.format("%12d", 1);
        System.out.println(number);

        /*
        Object barcode = myBarcode.getBarcode();

        File imgFile = new File("barcode.jpeg");
        


        BarcodeImageHandler.saveJPEG((Barcode) barcode, imgFile);

         */




    }
}
