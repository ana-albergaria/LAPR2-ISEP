package app.ui.console;

import app.controller.RecordResultsController;
import app.domain.model.Parameter;
import app.mappers.dto.ParameterDTO;
import app.ui.console.utils.OurUtils;
import app.ui.console.utils.Utils;

import java.util.List;

/*
FALTA:
- Não deixar o utilizador selecionar o mesmo parâmetro duas vezes
- Criar o ClinicalChemistryLaboratoryUI
- Tratar melhor, ver se faltam exceções
 */

public class RecordResultsUI implements Runnable {
    private RecordResultsController ctrl;

    public RecordResultsUI() {
        this.ctrl = new RecordResultsController();
    }

    @Override
    public void run() {
        boolean success;
        List<String> menu = OurUtils.menuToContinueOrCancel();

        System.out.println("To record the results of a test, please insert the requested data.");
        do {
            int index = Utils.showAndSelectIndex(menu, "");
            success = (index == -1) ? true : recordResultsOfATest();
        } while (!success);
    }

    private boolean recordResultsOfATest() {
        boolean success = false;

        try {
            String barcodeNumber = Utils.readLineFromConsole("Barcode Number: ");
            List<ParameterDTO> listTotalParameters = ctrl.getTotalTestParameters(barcodeNumber);
            System.out.println("The Test was found.");
            for (int i = 0; i < listTotalParameters.size(); i++) {
                ParameterDTO selectedParameter = (ParameterDTO) Utils.showAndSelectOne(listTotalParameters,
                        "Choose the parameter of the test for which you want to record the result:");
                Double resultValue = Utils.readDoubleFromConsole("Result Value: ");
                String metric = Utils.readLineFromConsole("Metric: ");
                ctrl.addTestResult(selectedParameter.getParameterCode(), resultValue, metric);
            }
            success = true;
            System.out.println("\nResults successfully recorded!");


        }
        catch(UnsupportedOperationException uoe) {
            System.out.println(uoe.getMessage());
        }
        catch(ClassNotFoundException cnfe) {
            System.out.println("The API Adapter Class was not found!");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return success;
    }
}
