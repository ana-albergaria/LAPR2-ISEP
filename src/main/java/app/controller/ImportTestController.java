package app.controller;

import app.domain.model.Company;
import app.mappers.dto.TestFileDTO;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;

import java.io.IOException;

public class ImportTestController {

    /**
     * Company instance of the session
     */
    private final Company company;

    private CreateFullTestController testController;

    private RegisterClientController clientController;

    /**
     * Empty constructor for having the actual instance of the company when instantiated.
     */
    public ImportTestController() {
        this(App.getInstance().getCompany());
    }

    /**
     * Construtor recieving the company as an argument
     *
     * @param company instance of company to be used
     */
    public ImportTestController(Company company) {
        this.company = company;
        this.clientController = new RegisterClientController();
        this.testController = new CreateFullTestController();
    }


    public boolean importTestFromFile(TestFileDTO testFileDTO) throws IllegalAccessException, ClassNotFoundException, InstantiationException, BarcodeException, OutputException, IOException {
        boolean existsClient = clientController.registerClient(testFileDTO.getClientDTO());
        if(existsClient)
            clientController.saveClient();
        testController.createTest(testFileDTO);
        if(!testController.saveTest()) return false;
        return testController.addSample();
    }

}
