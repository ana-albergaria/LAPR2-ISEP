package app.domain.model;

import app.controller.App;
import auth.AuthFacade;
import auth.domain.model.User;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String employeeID;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String socCode;
    private OrgRole role;
    private User user;

    /**
     * List of existing employees.
     */
    private List<Employee> empList = new ArrayList<>();

    public Employee(OrgRole role,
                    String employeeID,
                    String name,
                    String address,
                    String phoneNumber,
                    String email,
                    String socCode) {
        checkRoleRules(role);
        checkNameRules(name);
        checkAddressRules(address);
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

    /**
     * Generates an employee ID based on the employee name
     * and the number of employees in the company.
     *
     * @param name the employee name.
     *
     * @return generated employee ID.
     */
    private String generateEmployeeID(String name){ //+nr de employees criados
        String employeeID = "";
        String[] nameArray = name.split(" ");
        for (int i = 0; i < nameArray.length; i++) {
            String word = nameArray[i];
            employeeID = employeeID + word.charAt(0);
        }
        int num = this.empList.size() + 1;
        String str = String.format("%05d", num);
        employeeID = employeeID + str;
        return employeeID;
    }

    private void checkRoleRules(OrgRole role){
        if (StringUtils.isBlank(role.getDescription()))
            throw new IllegalArgumentException("Organization Role cannot be blank.");
    }

    private void checkNameRules(String name){
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be blank.");
        if (name.length()>35)
            throw new IllegalArgumentException("Name must have up to 35 chars.");
    }

    private void checkAddressRules(String address){
        if (StringUtils.isBlank(address))
            throw new IllegalArgumentException("Address cannot be blank.");
        if (address.length()>30)
            throw new IllegalArgumentException("Address must have up to 30 chars.");
    }

    private void checkPhoneNumberRules(String phoneNumber){
        if (StringUtils.isBlank(phoneNumber))
            throw new IllegalArgumentException("Phone Number cannot be blank.");
        if (phoneNumber.length()!=10)
            throw new IllegalArgumentException("Phone Number must have 10 digits.");
        if (!phoneNumber.matches("[0-9]+"))
            throw new IllegalArgumentException("Phone Number must only have numbers.");
    }

    private void checkEmailRules(String email){
        if (StringUtils.isBlank(email))
            throw new IllegalArgumentException("Email cannot be blank.");
        String[] emailArray = email.split("@");
        String word = emailArray[1];
        if (!email.contains("@") || email.indexOf("@")==email.lastIndexOf("@") || !word.contains("."))
            throw new IllegalArgumentException("Email address doesn't exist.");
    }

    //SEGUNDO A INTERNET ACHO QUE SÃO 6 DIGITS, MAS NAS PERGUNTAS ALGUÉM DISSE 4 E O CLIENTE NÃO CORRIGIU
    private void checkSocCodeRules(String socCode){
        if (StringUtils.isBlank(socCode))
            throw new IllegalArgumentException("SOC Code cannot be blank.");
        if (socCode.length()!=4)
            throw new IllegalArgumentException("SOC Code must have 4 digits.");
        if (!phoneNumber.matches("[0-9]+"))
            throw new IllegalArgumentException("SOC Code must only have numbers.");
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
