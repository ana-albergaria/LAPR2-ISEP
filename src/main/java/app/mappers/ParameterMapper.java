package app.mappers;

import app.domain.model.Parameter;
import app.mappers.dto.ParameterDTO;

import java.util.ArrayList;
import java.util.List;

public class ParameterMapper {

    public ParameterDTO toDTO(Parameter param) {
        return new ParameterDTO(param.getPrmCode(), param.getShortName(), param.getDescription(), param.getPc());
    }

    public List<ParameterDTO> toDTO(List<Parameter> params) {
        List<ParameterDTO> parameterDTOS = new ArrayList<>();
        for (Parameter param : params) {
            parameterDTOS.add(this.toDTO(param));
        }
        return parameterDTOS;
    }

}
