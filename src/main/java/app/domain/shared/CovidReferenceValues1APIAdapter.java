package app.domain.shared;

import app.domain.model.MyReferenceValue;
import app.domain.model.Parameter;
import com.example3.CovidReferenceValues1API;

public class CovidReferenceValues1APIAdapter implements ExternalModule {
    public MyReferenceValue getReferenceValue(Parameter parameter) {
        CovidReferenceValues1API crv1api = new CovidReferenceValues1API();
        Double minRefValue = crv1api.getMinReferenceValue(parameter.getPrmCode(), 12345);
        Double maxRefValue = crv1api.getMaxReferenceValue(parameter.getPrmCode(), 12345);
        return new MyReferenceValue(minRefValue, maxRefValue);
    }
}
