package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.OrgRole;
import app.domain.model.SpecialistDoctor;
import app.mappers.OrgRoleMapper;
import app.mappers.dto.EmployeeDTO;
import app.mappers.dto.OrgRoleDTO;
import app.mappers.dto.SpecialistDoctorDTO;
import app.domain.shared.Constants;
import auth.AuthFacade;
import auth.UserSession;
import auth.domain.model.UserRole;
import auth.domain.store.UserRoleStore;
import auth.mappers.dto.UserRoleDTO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class RegisterEmployeeController {
    private Company company;
    private Employee emp;
    private SpecialistDoctor sd;
    private Random rnd;
    private String generatedPassword;

    public RegisterEmployeeController() {
        this(App.getInstance().getCompany());
    }

    public RegisterEmployeeController(Company company) {
        this.company = company;
        rnd = new Random();
        this.emp = null;
        this.sd = null;
    }

    public boolean createEmployee(EmployeeDTO empDTO) {
        if (empDTO.getRoleDesignation().equalsIgnoreCase("Spec Doctor"))
            this.sd = this.company.createSpecialistDoctor((SpecialistDoctorDTO) empDTO);
        else
            this.emp = this.company.createEmployee(empDTO);

        return this.company.validateEmployee(emp);
    }

    private String generateRandomPassword(){
        StringBuilder salt = new StringBuilder();
        String saltChars = "abcdefghijklmnopkrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * saltChars.length());
            salt.append(saltChars.charAt(index));
        }
        return salt.toString();
    }

    public List<OrgRoleDTO> getRoles() {
        List<OrgRole> roles = this.company.getRoles();
        OrgRoleMapper mapper = new OrgRoleMapper();
        return mapper.toDTO(roles);

    }

    public boolean makeEmployeeAUser(){
        this.generatedPassword = generateRandomPassword();
        return this.company.makeEmployeeAUser(emp, generatedPassword);
    }

    public boolean writePassword(){
        FileWriter employeeData = null;
        try {
            employeeData = new FileWriter(emp.getEmployeeID());
            BufferedWriter bw = new BufferedWriter(employeeData);
            bw.write(String.format("Employee email: %s%nEmplooye Password: %s", emp.getEmail(), generatedPassword));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean makeEmployeeAnUserAndSendPassword() {
        if(!makeEmployeeAUser())
            return false;
        return writePassword();
    }
}
