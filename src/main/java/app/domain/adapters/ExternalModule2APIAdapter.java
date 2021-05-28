package app.domain.adapters;

import app.domain.model.MyReferenceValue;
import app.domain.model.Parameter;
import app.domain.interfaces.ExternalModule;
import com.example2.EMRefValue;
import com.example2.ExternalModule2API;


public class ExternalModule2APIAdapter implements ExternalModule {

    public MyReferenceValue getReferenceValue(Parameter parameter) {
        ExternalModule2API em2api = new ExternalModule2API();
        EMRefValue refValue = em2api.getReferenceFor(parameter.getPrmCode());
        return new MyReferenceValue(refValue.getMinValue(), refValue.getMaxValue());
    }




}
