package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.OrgRole;
import app.domain.model.SpecialistDoctor;
import app.mappers.OrgRoleMapper;
import app.mappers.dto.ClinicalAnalysisLaboratoryDTO;
import app.mappers.dto.EmployeeDTO;
import app.mappers.dto.OrgRoleDTO;
import app.mappers.dto.SpecialistDoctorDTO;

import java.util.List;

public class RegisterEmployeeController {
    private Company company;
    private Employee emp;
    private SpecialistDoctor sd;

    public RegisterEmployeeController() {
        this(App.getInstance().getCompany());
    }

    public RegisterEmployeeController(Company company) {
        this.company = company;
        this.emp = null;
        this.sd = null;
    }

    public boolean createEmployee(EmployeeDTO empDTO) {
        if (empDTO.getRoleDesignation().equalsIgnoreCase("Specialist Doctor"))
            this.sd = this.company.createSpecialistDoctor((SpecialistDoctorDTO) empDTO);
        else
            this.emp = this.company.createEmployee(empDTO);

        return this.company.validateEmployee(emp);
    }



    public List<OrgRoleDTO> getRoles() {
        List<OrgRole> roles = this.company.getRoles();
        OrgRoleMapper mapper = new OrgRoleMapper();
        return mapper.toDTO(roles);

    }

}
