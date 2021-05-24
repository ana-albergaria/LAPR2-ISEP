package app.domain.store;

import app.domain.model.MyBarcode;
import app.domain.model.Sample;

import java.util.ArrayList;
import java.util.List;

public class SampleStore {
    private List<Sample> samplesList = new ArrayList<>();

    public Sample createSample(MyBarcode barcode) {
        return new Sample(barcode);
    }

    public boolean validateSample(Sample sample) {
        if (sample == null)
            return false;
        //return !this.samplesList.contains(sample);
        return true;
    }
}
