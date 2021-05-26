package app.domain.shared;

import app.domain.model.MyReferenceValue;
import app.domain.model.Parameter;

public interface ExternalModule {
    public abstract MyReferenceValue getReferenceValue(Parameter parameter);
}
