package app.mappers.dto;


public class SpecialistDoctorDTO extends EmployeeDTO {
    private String doctorIndexNumber;

    public SpecialistDoctorDTO(String roleDesignation,
                            String employeeID,
                            String name,
                            String address,
                            String phoneNumber,
                            String email,
                            String socCode,
                            String doctorIndexNumber) {
        super(roleDesignation, employeeID, name, address, phoneNumber, email, socCode);
        this.doctorIndexNumber = doctorIndexNumber;
    }

    public String getDoctorIndexNumber() {
        return doctorIndexNumber;
    }
}
