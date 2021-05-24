package app.domain.model;

import app.controller.RecordSamplesController;

import net.sourceforge.barbecue.BarcodeException;

import net.sourceforge.barbecue.output.OutputException;

import java.io.IOException;

public class Main {

    private static RecordSamplesController ctrl = new RecordSamplesController();

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, BarcodeException, IOException, OutputException {

        boolean success = ctrl.createSample();

        ctrl.saveImageBarcode();

        boolean success1 = ctrl.createSample();

        ctrl.saveImageBarcode();




/*

        JFrame frame = new JFrame();
        frame.getContentPane().add(barcode);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setLocation(500, 500);
        frame.setVisible(true);

 */

        /*
        BufferedImage img = BarcodeImageHandler.getImage(barcode);
        barcode.print(img.createGraphics());

         */









        /*
        Object barcode = myBarcode.getBarcode();

        File imgFile = new File("barcode.jpeg");
        


        BarcodeImageHandler.saveJPEG((Barcode) barcode, imgFile);

         */




    }
}
