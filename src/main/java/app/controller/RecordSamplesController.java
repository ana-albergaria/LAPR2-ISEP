package app.controller;

import app.domain.model.Company;
import app.domain.model.MyBarcode;
import app.domain.model.Sample;
import app.domain.model.Test;
import app.domain.shared.ExternalAPI;
import app.domain.shared.utils.BarcodeUtils;
import app.domain.store.SampleStore;
import app.domain.store.TestStore;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;


import java.io.IOException;

public class RecordSamplesController {

    private Company company;
    private Sample sample;


    public RecordSamplesController() {
        this(App.getInstance().getCompany());
    }

    public RecordSamplesController(Company company) {
        this.company = company;
        this.sample = null;
    }

    public boolean createSample() throws ClassNotFoundException, InstantiationException, BarcodeException, IllegalAccessException {
        SampleStore sampleStore = this.company.getSampleStore();
        MyBarcode myBarcode = getBarcode();
        this.sample = sampleStore.createSample(myBarcode);
        return sampleStore.validateSample(sample);
    }

    public boolean addSample(String code) {
        TestStore testStore = this.company.getTestStore();
        Test selectedTest = testStore.getTestByCode(code);
        return selectedTest.addSample(sample);
    }

    /*
    public List<TestDTO> getTestsNoSamples() {
        TestStore testStore = this.company.getTestStore();
        List<Test> listTestsNoSamples = testStore.getTestsWithNoSamples();

        TestMapper mapper = new TestMapper();
        return mapper.toDTO(listTestsNoSamples);
    }
     */

    public ExternalAPI getExternalAPI() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        //no parâmetro do Class.forName, vai ser colocada a String retirada da leitura do ficheiro de configuração
        String className = App.getInstance().getBarcodeClassNameConfig();

        Class<?> oClass = Class.forName(className);

        return (ExternalAPI) oClass.newInstance();
    }


    public MyBarcode getBarcode() throws IllegalAccessException, ClassNotFoundException, InstantiationException, BarcodeException {
        ExternalAPI api = getExternalAPI();

        String barcodeNumber = BarcodeUtils.generateBarcodeNumber();

        return api.getBarcode(barcodeNumber);
    }

    public void saveImageBarcode() throws IllegalAccessException, ClassNotFoundException, InstantiationException, IOException, OutputException {
        ExternalAPI api = getExternalAPI();

        MyBarcode myBarcode = this.sample.getMyBarcode();

        api.saveImageBarcode(myBarcode);
    }
}
