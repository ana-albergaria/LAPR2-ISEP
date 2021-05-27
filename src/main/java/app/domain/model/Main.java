package app.domain.model;

import app.controller.RecordSamplesController;

import com.example1.ExternalModule3API;
import com.example2.ExternalModule2API;
import com.example3.CovidReferenceValues1API;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;

import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    private static RecordSamplesController ctrl = new RecordSamplesController();

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, BarcodeException, IOException, OutputException {


        boolean success = ctrl.createSample();

        String code = "12345678901";

        ctrl.saveImageBarcode(code);

        boolean success1 = ctrl.createSample();

        String code2 = "09876543212";

        ctrl.saveImageBarcode(code2);

        boolean success2 = ctrl.createSample();

        ctrl.saveImageBarcode(code2);



        ParameterCategory pc1 = new ParameterCategory("code1","name");
        Parameter p1 = new Parameter("HB000","name","descrip",pc1);

        ExternalModule2API em2api = new ExternalModule2API();
        System.out.println(em2api.getReferenceFor(p1.getPrmCode()));

        ExternalModule3API em3api = new ExternalModule3API();
        Double minRefValue = em3api.getMinReferenceValue(p1.getPrmCode(), 12345);
        Double maxRefValue = em3api.getMaxReferenceValue(p1.getPrmCode(), 12345);

        System.out.println(minRefValue + "          " + maxRefValue);

        Parameter p2 = new Parameter("IgGAN","name","descrip",pc1);

        CovidReferenceValues1API crv1api = new CovidReferenceValues1API();
        Double min = crv1api.getMinReferenceValue(p2.getPrmCode(), 12345);
        Double max = crv1api.getMaxReferenceValue(p2.getPrmCode(), 12345);

        System.out.println(min + "          " + max);



        Barcode barcode = BarcodeFactory.createUPCA("01234567890");

        MyBarcode myBarcode = new MyBarcode(barcode,"01234567890");
        Sample sample = new Sample(myBarcode);
        Barcode barcode1 = (Barcode) myBarcode.getBarcode();

        barcode.setPreferredBarHeight(100);

        File imgFile = new File("barcode.jpeg");

        BarcodeImageHandler.saveJPEG(barcode1, imgFile);

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


