package app.ui.console;

import app.controller.RecordSamplesController;
import app.mappers.dto.TestDTO;
import app.ui.console.utils.Utils;
import net.sourceforge.barbecue.BarcodeException;

import java.util.List;

public class RecordSamplesUI implements Runnable {
    private RecordSamplesController ctrl;

    public RecordSamplesUI() {
        ctrl = new RecordSamplesController();
    }

    @Override
    public void run() {
        boolean success;




    }
    private boolean recordSamples() {
        boolean success = false;

        try {
            List<TestDTO> listTestsNoSamplesDto = ctrl.getTestsNoSamples();
            TestDTO selectedTest = (TestDTO) Utils.showAndSelectOne(listTestsNoSamplesDto,
                    "To record the Samples collected of a Test, please selected a Test from the list:");

            System.out.println("How many Samples are to be collected?");
            int numSamples = Utils.readIntegerFromConsole("Number of Samples: ");

            for (int i = 0; i < numSamples; i++) {
                ctrl.createSample();
                boolean addSampleToTest = ctrl.addSample(selectedTest.getCode());

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



    }
}
