package app.domain.model;

public class OrgRole {
    private String description;

    public OrgRole(String description) {
        checkDescription(description);
        this.description = description;
    }

    private void checkDescription(String description){
        if (description.length()>15)
            throw new IllegalArgumentException("Organization Role Description must have up to 15 chars.");
        if (!description.contains("[a-zA-Z]+"))
            throw new IllegalArgumentException("Organization Role Description can only have letters.");
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "OrgRole{" +
                "description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object otherObject) {
        if(this == otherObject)
            return true;

        if(otherObject == null || this.getClass() != otherObject.getClass())
            return false;

        OrgRole otherOrgRole = (OrgRole) otherObject;

        return this.description.equalsIgnoreCase(otherOrgRole.description);
    }
}
