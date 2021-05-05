package app.domain.model;

public class OrgRole {
    private String description;

    public OrgRole(String description) {
        this.description = description;
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
