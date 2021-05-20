package app.domain.model;

import app.domain.shared.ExternalAPI;
import net.sourceforge.barbecue.BarcodeException;

public class Sample {
    private Barcode barcode;


    public Sample() {
        //this.barcode = getBarcode();
    }

    public ExternalAPI getExternalAPI() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        //no parâmetro do Class.forName, vai ser colocada a String retirada da leitura do ficheiro de configuração
        Class<?> oClass = Class.forName("app.domain.shared.BarbecueAdapter");

        return (ExternalAPI) oClass.newInstance();
    }

    /*
    public Barcode getBarcode() throws IllegalAccessException, ClassNotFoundException, InstantiationException, BarcodeException {
        ExternalAPI api = getExternalAPI();

        String barcodeNumber = "";

        net.sourceforge.barbecue.Barcode barcode = api.getBarcode(barcodeNumber);

        return barcode;

    }

     */




}
