package app.domain.model;

public class SpecialistDoctor extends Employee {
    private String doctorIndexNumber;

    public SpecialistDoctor(OrgRole role,
                            String employeeID,
                            String name,
                            String address,
                            String phoneNumber,
                            String email,
                            String socCode,
                            String doctorIndexNumber) {
        super(role, employeeID, name, address, phoneNumber, email, socCode);
        checkDoctorIndexNumberRules(doctorIndexNumber);
        this.doctorIndexNumber = doctorIndexNumber;
    }

    public String getDoctorIndexNumber() {
        return doctorIndexNumber;
    }

    private void checkDoctorIndexNumberRules(String doctorIndexNumber){

    }

}
