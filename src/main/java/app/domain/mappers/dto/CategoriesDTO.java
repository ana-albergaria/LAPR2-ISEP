package app.domain.mappers.dto;

public class CategoriesDTO {

    private String name;
    private String code;

    public CategoriesDTO(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
