package app.controller;

import app.domain.model.Company;
import app.domain.model.Sample;
import app.domain.model.Test;
import app.domain.store.SampleStore;
import app.domain.store.TestStore;


import java.util.List;

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

    public boolean createSample() {
        SampleStore sampleStore = this.company.getSampleStore();

        this.sample = sampleStore.createSample();
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

}
