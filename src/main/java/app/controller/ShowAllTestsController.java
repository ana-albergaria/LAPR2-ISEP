package app.controller;

import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.store.TestStore;
import app.mappers.TestMapper;
import app.mappers.dto.TestDTO;

import java.util.List;

public class ShowAllTestsController {

    private Company company;

    public ShowAllTestsController() {
        this(App.getInstance().getCompany());
    }

    public ShowAllTestsController(Company company) {
        this.company = company;
    }

    public List<TestDTO> getAllTests(){
        TestStore testStore = this.company.getTestStore();
        List<Test> tests = testStore.getTests();

        TestMapper testMapper = new TestMapper();
        return testMapper.toDTO(tests);
    }
}
