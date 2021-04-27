package app.domain.model;

public class TestType {
    private String designation;

    public TestType(String designation) {
        this.designation = designation;
    }

    //US8 - ANA
    public String getDesignation() {
        return designation;
    }
}
