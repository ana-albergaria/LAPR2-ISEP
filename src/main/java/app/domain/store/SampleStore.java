package app.domain.store;

import app.domain.model.Sample;
import app.domain.model.TestType;

import java.util.ArrayList;
import java.util.List;

public class SampleStore {
    private List<Sample> samplesList = new ArrayList<>();

    public Sample createSample() {
        return new Sample();
    }

    public boolean validateSample(Sample sample) {
        if (sample == null)
            return false;
        return !this.samplesList.contains(sample);
    }
}
