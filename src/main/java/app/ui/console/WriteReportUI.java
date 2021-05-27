package app.ui.console;

import app.controller.WriteReportController;
import app.mappers.dto.TestDTO;
import app.mappers.dto.TestParametersDTO;
import app.ui.console.utils.OurUtils;
import app.ui.console.utils.Utils;

import java.util.List;

public class WriteReportUI implements Runnable {

    private WriteReportController ctrl;

    public WriteReportUI(){
        ctrl = new WriteReportController();
    }

    public void run() {
        boolean success;
        boolean confirm;
        List<String> menu = OurUtils.menuToContinueOrCancel();

        do {
            System.out.println("To make a new diagnosis and write a report, please insert the requested data.");
            do {
                int index = Utils.showAndSelectIndex(menu, ""); //shows list of tests ready to diagnose and asks to select one
                success = (index == -1) ? true : createReport();
            } while (!success);
            confirm = Utils.confirm("Do you intend to make more diagnosis and write more reports?\n[Type 's' for yes or 'n' for no.]");
        } while (confirm);
        System.out.println("Reports successfully created!");
    }

    private boolean createReport() {
        boolean confirmation;
        boolean success;
        try {
            TestDTO tst = showListAndSelectOneObject(); //vai buscar o test pretendido

            if(tst == null)
                throw new IllegalArgumentException("Operation canceled!");

            for (TestParametersDTO testParameter : ctrl.getTestParameters(tst.getCode())){
                System.out.printf(">> TEST PARAMETER \"" + testParameter.getParameter().getShortName() + "\" <<" +
                        "%n> Result: " + testParameter.getTestParameterResult() + " " + testParameter.getResultMetric() + ";" +
                        "%n> Reference Value: " + testParameter.getResultReferenceValue() + " " + testParameter.getResultMetric() + ";");
            }

            String reportText = Utils.readLineFromConsole("Write the report bellow.\n[you can use SHIFT + ENTER for paragraphs]");


            ctrl.createReport(reportText);
            confirmation = Utils.confirm(String.format(">> REPORT <<" +
                            "%n%s" +
                            "%n[Type 's' for correct or 'n' for wrong.]",
                    reportText));
            if (!confirmation) throw new Exception("Please enter the correct data.");
            success = ctrl.createReport(reportText);
            if (!success) throw new Exception("Report is null. Please try again.");
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            success = false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            success = false;
        }
        return success;
    }

    private TestDTO showListAndSelectOneObject() {
        List<TestDTO> testDTO = ctrl.getTestsToDiagnose();
        TestDTO test = (TestDTO) Utils.showAndSelectOne(testDTO, "Enter the number of the test for which you want to make a diagnosis and write a report: ");
        return test;
    }

}