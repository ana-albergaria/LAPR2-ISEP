package app.domain.model;

import org.apache.commons.lang3.StringUtils;

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
        super(role, name, address, phoneNumber, email, socCode);
        checkDoctorIndexNumberRules(doctorIndexNumber);
        this.doctorIndexNumber = doctorIndexNumber;
    }

    public String getDoctorIndexNumber() {
        return doctorIndexNumber;
    }

    private void checkDoctorIndexNumberRules(String doctorIndexNumber){
        if (StringUtils.isBlank(doctorIndexNumber))
            throw new IllegalArgumentException("Doctor Index Number cannot be blank.");
        if (doctorIndexNumber.length()!=6)
            throw new IllegalArgumentException("Doctor Index Number must have 6 digits.");
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
