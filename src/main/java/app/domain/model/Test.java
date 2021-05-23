package app.domain.model;

import java.util.List;

public class Test {

    private String code;
    private List<Sample> samples;

    public String getCode(){
        return code;
    }

    public List<Sample> getSamples() {
        return samples;
    }

    //Como mudar o estado do teste? Responsabilidade do test? (if samples.size() == x)
    public boolean addSample(Sample sample) {
        return this.samples.add(sample);
    }
}
