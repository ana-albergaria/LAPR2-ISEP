package app.domain.model;

import app.controller.RegisterEmployeeController;
import app.mappers.dto.EmployeeDTO;
import app.mappers.dto.SpecialistDoctorDTO;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    /*
    private RegisterEmployeeController ctrl;

    public Main() {
        ctrl = new RegisterEmployeeController();
    }

    @Override
    public void run() {
        //SpecialistDoctorDTO sdDTO = new SpecialistDoctorDTO("Specialist Doctor","12345","Manuel","Lisboa","123","email","254645653","12345");
        EmployeeDTO empDTO = new EmployeeDTO("Receptionist","12345","Manuel","Lisboa","123","email","254645653");

        boolean sd1 = this.ctrl.createEmployee(empDTO);



    }

 */


    public static void main(String[] args) {
        List<ParameterCategory> pcList = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("CODE1","Descriptio");
        ParameterCategory p2 = new ParameterCategory("CODE2","Descriptio");
        pcList.add(p1);
        pcList.add(p2);
        TestType t1 = new TestType("CODE3","Description","swab",pcList);
        TestType t2 = new TestType("CODE4","Description","swab",pcList);
        List<TestType> selectedTT = new ArrayList<>();
        selectedTT.add(t1);
        selectedTT.add(t2);

        /*ClinicalAnalysisLaboratory instance4 = new ClinicalAnalysisLaboratory("CAL12",
                "CAL","Lisboa","91841378811","1234567890", selectedTT);

        System.out.println(instance4);
         */
        /*
        boolean invalidData = true;

        do {
            try {
                String laboratoryID = Utils.readLineFromConsole("Laboratory ID: ");
                String name = Utils.readLineFromConsole("Name: ");
                String address = Utils.readLineFromConsole("Address: ");
                String phoneNumber = Utils.readLineFromConsole("Phone Number: ");
                String numTIN = Utils.readLineFromConsole("TIN Number: ");


                invalidData = false;
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while(invalidData);

         */

        OrgRole r1 = new OrgRole("Specialist Doctor");
        SpecialistDoctor expObj = new SpecialistDoctor(r1,"12345",
                "Afonso","Lisboa","123456","afonso@gmail.com",
                "socCode12","344444444");

        System.out.println(expObj);

    }


}
