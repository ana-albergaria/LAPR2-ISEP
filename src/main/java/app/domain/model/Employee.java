package app.domain.model;

public class Employee {
    private String employeeID;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String socCode;
    private OrgRole role;

    public Employee(OrgRole role,
                    String employeeID,
                    String name,
                    String address,
                    String phoneNumber,
                    String email,
                    String socCode) {
        this.role = role;
        this.employeeID = employeeID;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.socCode = socCode;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getSocCode() {
        return socCode;
    }

    public OrgRole getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID='" + employeeID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", socCode='" + socCode + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object otherObject) {
        if(this == otherObject)
            return true;

        if(otherObject == null || this.getClass() != otherObject.getClass())
            return false;

        Employee otherEmployee = (Employee) otherObject;

        return this.employeeID.equalsIgnoreCase(otherEmployee.employeeID) &&
                this.name.equalsIgnoreCase(otherEmployee.name) &&
                this.address.equalsIgnoreCase(otherEmployee.address) &&
                this.phoneNumber.equalsIgnoreCase(otherEmployee.phoneNumber) &&
                this.email.equalsIgnoreCase(otherEmployee.email) &&
                this.socCode.equalsIgnoreCase(otherEmployee.socCode) &&
                this.role.equals(otherEmployee.role);
    }
}
