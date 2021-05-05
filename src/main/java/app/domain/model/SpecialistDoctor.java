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
        this.doctorIndexNumber = doctorIndexNumber;
    }

    public String getDoctorIndexNumber() {
        return doctorIndexNumber;
    }

    @Override
    public String toString() {
        return super.toString() + "SpecialistDoctor{" +
                "doctorIndexNumber='" + doctorIndexNumber + '\'' +
                '}' + getClass().getSimpleName();
    }

    @Override
    public boolean equals (Object otherObject){
        if (!super.equals(otherObject))
            return false;

        SpecialistDoctor instance = (SpecialistDoctor) otherObject;

        return this.doctorIndexNumber.equalsIgnoreCase(instance.doctorIndexNumber);
    }
}
