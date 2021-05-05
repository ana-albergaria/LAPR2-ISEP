package app.mappers.dto;

import app.domain.model.OrgRole;

public class SpecialistDoctorDTO extends EmployeeDTO {
    private String doctorIndexNumber;

    public SpecialistDoctorDTO(OrgRole role,
                            String employeeID,
                            String name,
                            String address,
                            String phoneNumber,
                            String email,
                            String socCode,
                            String doctorIndexNumber) {
        super(role, employeeID, name, address, phoneNumber, email, socCode);
        this.doctorIndexNumber = doctorIndexNumber;
    }

    public String getDoctorIndexNumber() {
        return doctorIndexNumber;
    }
}
