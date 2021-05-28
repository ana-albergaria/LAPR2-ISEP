package app.domain.adapters;

import app.domain.model.MyReferenceValue;
import app.domain.model.Parameter;
import app.domain.interfaces.ExternalModule;
import com.example1.ExternalModule3API;

public class ExternalModule3APIAdapter2 implements ExternalModule {

    public MyReferenceValue getReferenceValue(Parameter parameter) {
        ExternalModule3API em3api = new ExternalModule3API();
        Double minRefValue = em3api.getMinReferenceValue(parameter.getPrmCode(), 12345);
        Double maxRefValue = em3api.getMaxReferenceValue(parameter.getPrmCode(), 12345);

        return new MyReferenceValue(minRefValue, maxRefValue);
    }



}
