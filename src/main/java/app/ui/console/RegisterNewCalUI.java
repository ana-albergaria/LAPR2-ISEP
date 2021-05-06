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
        boolean success;

        System.out.print("To register a new Clinical Analysis Laboratory, please insert the requested data.");
        do {
            success = registerClinicalAnalysisLaboratory();
        } while (!success);
        System.out.println("\nClinical Analysis Laboratory successfully created!");


    }

    private boolean registerClinicalAnalysisLaboratory() {
        boolean invalidData = true;


        try {

            ClinicalAnalysisLaboratoryDTO calDto = insertingData();

            ctrl.createClinicalAnalysisLaboratory(calDto);

            boolean confirm = Utils.confirm(String.format("Please, confirm the data (type `s` if its correct, `n` if it is not):" +
                            "%nLaboratory ID: %s%nName: %s%nAddress: %s%nPhone Number: %s%nTIN Number: %s%nTypes Of Test: %s%n",
                    calDto.getLaboratoryID(), calDto.getName(), calDto.getAddress(),
                    calDto.getPhoneNumber(), calDto.getNumTIN(), calDto.getTestTypeCodes()));

            if (!confirm) {
                System.out.printf("Please, insert again the data you wish.");
                calDto = insertingData();
            } else {
                boolean save = ctrl.saveClinicalAnalysisLaboratory();
                if (save) {
                    invalidData = false;

                } else {
                    throw new IllegalArgumentException("That Clinical Analysis Laboratory is already registered in the system.");
                }

            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }


        return invalidData;
    }

    private ClinicalAnalysisLaboratoryDTO insertingData() {
        String laboratoryID = Utils.readLineFromConsole("Laboratory ID: ");
        String name = Utils.readLineFromConsole("Name: ");
        String address = Utils.readLineFromConsole("Address: ");
        String phoneNumber = Utils.readLineFromConsole("Phone Number: ");
        String numTIN = Utils.readLineFromConsole("TIN Number: ");
        List<String> selectedTT = getTypesOfTestToCode();

        return new ClinicalAnalysisLaboratoryDTO(laboratoryID,
                name, address, phoneNumber, numTIN, selectedTT);
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
            if(tt == null)
                throw new IllegalArgumentException("You should select at least one type of test in order to register a new Clinical Analysis Laboratory!");
            else
                testTypesCodes.add(tt.getCode());
        }
        return testTypesCodes;
    }
}
