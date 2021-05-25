package app.ui.console;

import app.controller.RecordSamplesController;
import app.mappers.dto.TestDTO;
import app.ui.console.utils.Utils;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;

import java.io.IOException;
import java.util.List;

public class RecordSamplesUI implements Runnable {
    private RecordSamplesController ctrl;

    public RecordSamplesUI() {
        ctrl = new RecordSamplesController();
    }

    @Override
    public void run() {
        boolean success;
        do {
            success = recordSamples();
        } while (!success);
    }

    private boolean recordSamples() {
        boolean success = false;

        try {
            List<TestDTO> listTestsNoSamplesDto = ctrl.getTestsNoSamples();
            TestDTO selectedTest = (TestDTO) Utils.showAndSelectOne(listTestsNoSamplesDto,
                    "To record the Samples collected of a Test, please selected a Test from the list:");

            if(selectedTest != null) {
                System.out.println("How many Samples are to be collected?");
                int numSamples = Utils.readIntegerFromConsole("Number of Samples: ");

                boolean confirm = Utils.confirm(String.format("%d Samples will be added to the selected Test. (type `s` if its correct, `n` if it is not)", numSamples, selectedTest.getCode()));

                if(confirm) {
                    for (int i = 0; i < numSamples; i++) {
                        ctrl.createSample(); //ClassNotFoundException, InstantiationException, IllegalAccessException, Barcode Exception
                        boolean addSampleToTest = ctrl.addSample(selectedTest.getCode());
                        if(addSampleToTest)
                            ctrl.saveImageBarcode(selectedTest.getCode()); //IOException, Output Exception (Barbecue)
                        success = true;
                    }
                    System.out.println("\nSamples successfully recorded and added to the Test!\n" +
                            "You can find the Samples Barcodes in the subfolder Test-Code" + selectedTest.getCode() + " in the folder Samples.");
                } else {
                    System.out.println("\nOperation canceled!");
                    success = true;
                }

            } else {
                success = true;
            }
        }
        catch(ClassNotFoundException cnde) {
            System.out.println(cnde.getMessage());
        }
        catch(InstantiationException ie) {
            System.out.println(ie.getMessage());
        }
        catch(BarcodeException be) {
            System.out.println(be.getMessage());
        }
        catch(IllegalAccessException iae) {
            System.out.println(iae.getMessage());
        }
        catch (OutputException oe) {
            System.out.println(oe.getMessage());
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        catch(IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }

        return success;

    }

}
