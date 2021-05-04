package app.domain.model;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Main {
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

    }
}
