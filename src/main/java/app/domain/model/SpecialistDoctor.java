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

<<<<<<< HEAD
    private void checkDoctorIndexNumberRules(String doctorIndexNumber){

    }

=======
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
>>>>>>> 912e56986f2646e9c60296d74eb06232694235bd
}
