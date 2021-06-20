package app.controller;

import app.domain.model.Company;
import app.domain.model.CompanyPerformance;
import app.mappers.dto.TestFileDTO;
import app.ui.console.utils.TestFileUtils;
import net.sourceforge.barbecue.BarcodeException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Controller class for analysing the company performance
 *
 * @author Marta Ribeiro 1201592
 */
public class CompanyPerformanceAnalysisController {

    /**
     * The company associated with the Controller.
     */
    private Company company;

    public Company getCompany() {
        return company;
    }

    /**
     * Builds an empty constructor for having the actual instance of the company when instantiated.
     */
    public CompanyPerformanceAnalysisController(){
        this(App.getInstance().getCompany());
    }

    /**
     * Builds a Company Performance Analysis Controller's instance receiving the company.
     *
     * @param company company associated to the Controller.
     */
    public CompanyPerformanceAnalysisController(Company company){
        this.company = company;
    }

    /**
     * The company performance.
     */
    private CompanyPerformance companyPerformance;

    public void setCompanyPerformance(CompanyPerformance companyPerformance) {
        this.companyPerformance = companyPerformance;
    }

    /**
     -     * Creates an instance of company performance type.
     -     * @param beginningDate the first moment of study
     -     * @param endingDate the last moment of study
     -     * @param chosenAlg the chosen algorithm
     -     * @return true is the company performance is successfully created
     -     * otherwise return false.
     -     */
    public boolean createCompanyPerformance(Date beginningDate, Date endingDate, String chosenAlg) {
            this.companyPerformance = company.createCompanyPerformance(beginningDate,endingDate,chosenAlg);
            return (companyPerformance != null);
    }

    /**
     * Gets the number of clients for an interval
     * @return the number of clients for an interval
     */
    public int getClientsInfoPerInterval() {
        return companyPerformance.getClientsNum();
    }

    /**
     * Gets the number of processed tests for an interval
     * @return the number of processed tests for an interval
     */
    public int getNumTestsProcessedInterval(){
        return companyPerformance.getProcessTestsNum();
    }

    //FIRST THE LB SELECTS DAY OR INTERVAL
    //SHOW ERROR MESSAGES!!! (CAN'T CHOOSE PRESENT OR FUTURE)
    //12 WORKING HOURS PER DAY
    //WORKING DAY IS FROM 8:00 TO 20:00 (endingDay will end at 19:59:59)
    //SUNDAY IS NOT A WORKING DAY

    /**
     * Gets an ArrayList with the tests info for the days of the interval
     * @return ArrayList with the tests info for the days of the interval
     */
    public ArrayList<int[]> getTestInfoPerDay(){
        return companyPerformance.getTestInfoDay();
    }

    /**
     * Gets an ArrayList with the tests info for the weeks of the interval
     * @return ArrayList with the tests info for the weeks of the interval
     */
    public ArrayList<int[]> getTestInfoPerWeek(){ //WEEK: FROM MONDAY TO SATURDAY (NO WORK AT SUNDAY)
        return companyPerformance.getTestInfoWeek();
    }

    /**
     * Gets an ArrayList with the tests info for the months of the interval
     * @return ArrayList with the tests info for the months of the interval
     */
    public ArrayList<int[]> getTestInfoPerMonth(){ //MONTH: FROM 1 TO END OF MONTH
        return companyPerformance.getTestInfoMonth();
    }

    /**
     * Gets an ArrayList with the tests info for the years of the interval
     * @return ArrayList with the tests info for the years of the interval
     */
    public ArrayList<int[]> getTestInfoPerYear(){ //YEAR: FROM JAN 1 TO DEC 31
        return companyPerformance.getTestInfoYear();
    }

    /**
     * Finds the beginning and the ending dates of the contiguous subsequence with maximum sum of an interval, through the chosen algorithm
     * @return the beginning and the ending dates of the contiguous subsequence with maximum sum
     */
    public Date[] findWorstSubIntWithChosenAlgorithm() {
        return companyPerformance.getWorstSubInt();
    }

}