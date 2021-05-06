package app.ui.console;

import app.controller.RegisterNewCalController;
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
        boolean success = true;

        System.out.print("To register a new Clinical Analysis Laboratory, please insert the requested data.");
        do {
            success = registerClinicalAnalysisLaboratory();
        } while (!success);


    }

    private boolean registerClinicalAnalysisLaboratory() {
        boolean success = false;

        try {

            String laboratoryID = Utils.readLineFromConsole("Laboratory ID: ");
            String name = Utils.readLineFromConsole("Name: ");
            String address = Utils.readLineFromConsole("Address: ");
            String phoneNumber = Utils.readLineFromConsole("Phone Number: ");
            String numTIN = Utils.readLineFromConsole("TIN Number: ");
            List<String> selectedTT = new ArrayList<>();

            if(getTypesOfTestToCode(selectedTT)) {
                success = true;
            } else {
                ClinicalAnalysisLaboratoryDTO calDto = new ClinicalAnalysisLaboratoryDTO(laboratoryID,
                        name, address, phoneNumber, numTIN, selectedTT);
                ctrl.createClinicalAnalysisLaboratory(calDto);
                boolean confirm = Utils.confirm(String.format("Please, confirm the data (type `s` if its correct, `n` if it is not):" +
                                "%nLaboratory ID: %s%nName: %s%nAddress: %s%nPhone Number: %s%nTIN Number: %s%nTypes Of Test: %s%n",
                        calDto.getLaboratoryID(), calDto.getName(), calDto.getAddress(),
                        calDto.getPhoneNumber(), calDto.getNumTIN(), calDto.getTestTypeCodes()));

                if (!confirm) {
                    System.out.print("Please, insert again the data you wish.");
                } else {
                    boolean save = ctrl.saveClinicalAnalysisLaboratory();
                    if (save) {
                        success = true;
                        System.out.println("\nClinical Analysis Laboratory successfully created!");
                    } else {
                        throw new IllegalArgumentException("That Clinical Analysis Laboratory is already registered in the system.");
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }


        return success;
    }

    private boolean showTestTypeListAndSelectingThem(List<TestTypeDTO> listTestType) {
        boolean success = false;
        List<TestTypeDTO> listTestTypeDto = ctrl.getTestTypes();
        TestTypeDTO testTypeToAdd = (TestTypeDTO) Utils.showAndSelectOne(listTestTypeDto,
                "Please, select the types of test from the list this laboratory will operate:");
        if(testTypeToAdd != null) {
            addSelectedTestTypeToList(listTestType, testTypeToAdd);
        } else {
            System.out.println("Operation canceled!");
            success = true;
        }
        return success;
    }

    private boolean getTypesOfTestToCode(List<String> selectedTT) {
        boolean success = false;
        List<TestTypeDTO> listTestType = new ArrayList<>();
        if(showTestTypeListAndSelectingThem(listTestType)) {
            success = true;
        } else {
            for (TestTypeDTO tt : listTestType) {
                selectedTT.add(tt.getCode());
            }
        }
        return success;
    }

    private void addSelectedTestTypeToList(List<TestTypeDTO> listTestType, TestTypeDTO testTypeToAdd) {
        listTestType.add(testTypeToAdd);
        boolean addMore = Utils.confirm("If you want to add more types of test please type 's', otherwise type 'n'");
        if (addMore)
            showTestTypeListAndSelectingThem(listTestType);
    }
}
