package app.domain.mappers;

import app.domain.mappers.dto.CategoriesDTO;
import app.domain.model.ParameterCategory;

import java.util.ArrayList;
import java.util.List;

public class CategoriesMapper {

    public CategoriesDTO toDTO(ParameterCategory category)
    {
        return new CategoriesDTO(category.getName(),category.getCode());
    }

    public List<CategoriesDTO> toDTO(List<ParameterCategory> categories)
    {
        List<CategoriesDTO> categoriesDTO = new ArrayList<>();
        for(ParameterCategory category : categories)
        {
            categoriesDTO.add(this.toDTO(category));
        }
        return categoriesDTO;
    }
}
