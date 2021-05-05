package app.domain.model;

import org.apache.commons.lang3.StringUtils;

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
        /*checkRoleRules(role);*/
        checkEmployeeIDRules(employeeID);
        checkNameRules(name);
        checkAdressRules(address);
        checkPhoneNumberRules(phoneNumber);
        checkEmailRules(email);
        checkSocCodeRules(socCode);
        this.role = role;
        this.employeeID = employeeID;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.socCode = socCode;
    }

    /*private void checkRoleRules(OrgRole role){
        if (StringUtils.isBlank(role))
            throw new IllegalArgumentException("Organization Role cannot be blank.");
        if (role.length()>15)
            throw new IllegalArgumentException("Organization Role must have up to 15 chars.");
    }*/

    private void checkEmployeeIDRules(String employeeID){

    }

    private void checkNameRules(String name){

    }

    private void checkAdressRules(String address){

    }

    private void checkPhoneNumberRules(String phoneNumber){

    }

    private void checkEmailRules(String email){

    }

    private void checkSocCodeRules(String socCode){

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
