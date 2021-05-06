package app.domain.model;

import app.controller.App;
import auth.AuthFacade;
import auth.domain.model.User;
import org.apache.commons.lang3.StringUtils;

public class Employee {
    private String employeeID;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String socCode;
    private OrgRole role;
    private User user;

    public Employee(OrgRole role,
                    String employeeID,
                    String name,
                    String address,
                    String phoneNumber,
                    String email,
                    String socCode) {
        checkRoleRules(role);
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

    //E SE COLOCAREM LETRAS?
    private void checkRoleRules(OrgRole role){
        if (StringUtils.isBlank(role.getDescription()))
            throw new IllegalArgumentException("Organization Role cannot be blank.");
        if (role.getDescription().length()>15)
            throw new IllegalArgumentException("Organization Role must have up to 15 chars.");
    }

    //QUAL O TAMANHO?
    private void checkEmployeeIDRules(String employeeID){
        if (StringUtils.isBlank(employeeID))
            throw new IllegalArgumentException("Employee ID cannot be blank.");
        /*if (employeeID.length()>x)
            throw new IllegalArgumentException("Employee ID must have x chars.");*/
    }

    //QUAL O TAMANHO?
    private void checkNameRules(String name){
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be blank.");
        /*if (name.length()>x)
            throw new IllegalArgumentException("Name must have x chars.");*/
    }

    //QUAL O TAMANHO?
    private void checkAdressRules(String address){
        if (StringUtils.isBlank(address))
            throw new IllegalArgumentException("Address cannot be blank.");
        /*if (address.length()>x)
            throw new IllegalArgumentException("Address must have x chars.");*/
    }

    //E SE COLOCAREM LETRAS?
    private void checkPhoneNumberRules(String phoneNumber){
        if (StringUtils.isBlank(phoneNumber))
            throw new IllegalArgumentException("Phone Number cannot be blank.");
        if (phoneNumber.length()!=10)
            throw new IllegalArgumentException("Phone Number must have 10 digits.");
    }

    private void checkEmailRules(String email){
        if (StringUtils.isBlank(email))
            throw new IllegalArgumentException("Email cannot be blank.");
    }

    //E SE COLOCAREM LETRAS?
    //SEGUNDO A INTERNET ACHO QUE SÃO 6 DIGITS, MAS NAS PERGUNTAS ALGUÉM DISSE 4 E O CLIENTE NÃO CORRIGIU
    private void checkSocCodeRules(String socCode){
        if (StringUtils.isBlank(socCode))
            throw new IllegalArgumentException("SOC Code cannot be blank.");
        if (socCode.length()!=4)
            throw new IllegalArgumentException("SOC Code must have 4 digits.");
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
