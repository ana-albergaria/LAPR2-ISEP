package app.controller;

import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.model.TestType;

/**
 * Controller for creating a new Test object
 *
 * @author João Wolff
 */
public class CreateTestController {

    /**
     * Company instance of the session
     */
    private final Company company;

    /**
     * Test to be created by the controller
     */
    private Test test;

    /**
     * Empty constructor for having the actual instance of the company when instantiated.
     */
    public CreateTestController() {
        this(App.getInstance().getCompany());
    }

    /**
     * Construtor recieving the company as an argument
     *
     * @param company instance of company to be used
     */
    public CreateTestController(Company company) {
        this.company = company;
        this.test = null;
    }

}
