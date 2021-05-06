package app.ui.console;

import app.controller.RegisterNewCalController;
import app.domain.model.TestType;
import app.mappers.dto.ClinicalAnalysisLaboratoryDTO;
import app.mappers.dto.TestTypeDTO;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class RegisterNewCalUI implements Runnable {
    private RegisterNewCalController ctrl;

    public RegisterNewCalUI() {
        ctrl = new RegisterNewCalController();
    }

    public void run() {




    }
    private boolean registerClinicalAnalysisLaboratory() {
        boolean invalidData = true;
        boolean confirm = true;
        System.out.printf("To register a new Clinical Analysis Laboratory, please insert the requested data.");

        do {
            try {
                String laboratoryID = Utils.readLineFromConsole("Laboratory ID: ");
                String name = Utils.readLineFromConsole("Name: ");
                String address = Utils.readLineFromConsole("Address: ");
                String phoneNumber = Utils.readLineFromConsole("Phone Number: ");
                String numTIN = Utils.readLineFromConsole("TIN Number: ");
                List<String> selectedTT = getTypesOfTestToCode();

                ClinicalAnalysisLaboratoryDTO calDto = new ClinicalAnalysisLaboratoryDTO(laboratoryID,
                        name, address, phoneNumber, numTIN, selectedTT);

                ctrl.createClinicalAnalysisLaboratory(calDto);



            }
        } while(invalidData);



    }
    private List<TestTypeDTO> showTestTypeListAndSelectingThem(List<TestTypeDTO> listTestType) {
        List<TestTypeDTO> listTestTypeDto = ctrl.getTestTypes();
        TestTypeDTO testTypeToAdd = (TestTypeDTO) Utils.showAndSelectOne(listTestTypeDto,
                "Please, select the types of test from the list this laboratory will operate:");
        listTestType.add(testTypeToAdd);
        boolean addMore = Utils.confirm("If you want to add more types of test please type 's', otherwise type 'n'");
        return addMore ? showTestTypeListAndSelectingThem(listTestType) : listTestType;

    }

    private List<String> getTypesOfTestToCode() {
        List<TestTypeDTO> listTestType = new ArrayList<>();
        listTestType = showTestTypeListAndSelectingThem(listTestType);
        List<String> testTypesCodes = new ArrayList<>();
        for (TestTypeDTO tt : listTestType) {
            testTypesCodes.add(tt.getCode());
        }
        return testTypesCodes;
    }
}
