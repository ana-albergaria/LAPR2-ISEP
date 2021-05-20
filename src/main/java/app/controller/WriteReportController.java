package app.controller;

import app.domain.model.Company;
import app.domain.model.Report;
import app.domain.model.Test;
import app.domain.store.TestStore;

import java.util.List;

public class WriteReportController {

    private Company company;

    private Report report;

    public WriteReportController(){
        this(App.getInstance().getCompany());
    }

    public WriteReportController(Company company){
        this.company = company;
        this.report = null;
    }

    /*public boolean createReport(String reportText){
        this.report = company.createReport(reportText);
        return
    }*/

    public List<Test> getTestsToDiagnose(){
        TestStore testStore = this.company.getTestStore();
        List<Test> testsToDiagnose = testStore.getTestsReadyToDiagnose();
        return  testsToDiagnose;
    }

}
